package br.edu.ifsp.inventariodoo.application.repository.sqlite;

import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.entities.item.Goods;
import br.edu.ifsp.inventariodoo.domain.usecases.category.FindCategoryUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.goods.GoodsDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.edu.ifsp.inventariodoo.application.main.Main.findCategoryUseCase;

public class SqliteGoodsDAO implements GoodsDAO {
    @Override
    public Optional<Goods> findByName(String name) {
        String sql = "SELECT * FROM Goods WHERE name = ?";
        Goods goods = null;
        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1,name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                goods = resultSetToEntity(rs);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.ofNullable(goods);
    }

    @Override
    public Integer create(Goods goods) {
        String sql = "INSERT INTO Goods(name, origin, characteristics, category) VALUES(?, ?, ?, ?)";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, goods.getName());
            stmt.setString(2, goods.getOrigin());
            stmt.setString(3, goods.getCharacteristics());
            stmt.setInt(4, goods.getCategory().getId());
            stmt.execute();


            ResultSet resultSet = stmt.getGeneratedKeys();
            int generatedKeys = resultSet.getInt(1);
            return generatedKeys;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Goods> findOne(Integer key) {
        String sql = "SELECT * FROM Goods WHERE id = ?";
        Goods goods = null;
        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1,key);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                goods = resultSetToEntity(rs);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.ofNullable(goods);
    }

    private Goods resultSetToEntity(ResultSet resultSet) throws SQLException {
        int categoryId = resultSet.getInt("category");
        Category category = findCategoryUseCase.findOne(categoryId).get();//arrumar aqui findOne

        return new Goods(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("origin"),
                resultSet.getString("characteristics"),
                category
        );
    }

    @Override
    public List<Goods> findAll() {
        String sql = "SELECT * FROM Goods";
        List<Goods> goodsList = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Goods goods = resultSetToEntity(rs);
                goodsList.add(goods);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return goodsList;
    }

    @Override
    public boolean update(Goods goods) {
        String sql = "UPDATE Category SET name = ?, origin = ?, characteristics = ?, category = ? WHERE id = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, goods.getName());
            stmt.setString(2, goods.getOrigin());
            stmt.setString(3, goods.getCharacteristics());
            stmt.setInt(4,goods.getCategory().getId());
            stmt.setInt(5,goods.getId());
            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        String sql = "DELETE FROM Goods WHERE id = ?";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, key);
            stmt.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Goods goods) {
        if (goods == null || goods.getId() == null)
            throw new IllegalArgumentException("Goods and goods id must not be NULL");
        return deleteByKey(goods.getId());
    }
}
