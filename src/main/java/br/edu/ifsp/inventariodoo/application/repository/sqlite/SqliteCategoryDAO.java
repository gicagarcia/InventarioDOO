package br.edu.ifsp.inventariodoo.application.repository.sqlite;

import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.usecases.category.CategoryDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteCategoryDAO implements CategoryDAO {
    @Override
    public Integer create(Category category) {
        String sql = "INSERT INTO Category(name, area, application) VALUES(?, ?, ?)";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, category.getName());
            stmt.setString(2, category.getArea());
            stmt.setString(3, category.getApplication());
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
    public Optional<Category> findOne(Integer key) {
        String sql = "SELECT * FROM Category WHERE id = ?";
        Category category = null;
        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1,key);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                category = resultSetToEntity(rs);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.ofNullable(category);
    }

    private Category resultSetToEntity(ResultSet rs) throws SQLException {
        return new Category(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("area"),
                rs.getString("application")
        );
    }

    @Override
    public Optional<Category> findByName(String name) {
        String sql = "SELECT * FROM Category WHERE name = ?";
        Category category = null;
        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1,name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                category = resultSetToEntity(rs);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.ofNullable(category);
    }




    @Override
    public List<Category> findAll() {
        String sql = "SELECT * FROM Category";
        List<Category> categories = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Category category = resultSetToEntity(rs);
                categories.add(category);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public boolean update(Category category) {
        String sql = "UPDATE Category SET name = ?, area = ?, application = ? WHERE id = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, category.getName());
            stmt.setString(2, category.getArea());
            stmt.setString(3, category.getApplication());
            stmt.setInt(4,category.getId());
            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        String sql = "DELETE FROM Category WHERE id = ?";
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
    public boolean delete(Category category) {
        if (category == null || category.getId() == null)
            throw new IllegalArgumentException("Category and category id must not be NULL");
        return deleteByKey(category.getId());
    }
}
