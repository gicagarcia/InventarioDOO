package br.edu.ifsp.inventariodoo.application.repository.sqlite;

import br.edu.ifsp.inventariodoo.domain.entities.inventory.StatusItem;
import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.entities.item.Goods;
import br.edu.ifsp.inventariodoo.domain.entities.item.Item;
import br.edu.ifsp.inventariodoo.domain.entities.item.Place;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.usecases.item.ItemDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.edu.ifsp.inventariodoo.application.main.Main.*;

public class SqliteItemDAO implements ItemDAO {
    @Override
    public String create(Item item) {
        String sql = "INSERT INTO Item(description, status, goods, responsible, place) " +
                "VALUES(?, ?, ?, ?, ?)";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, item.getDescription());
            stmt.setString(2, item.getStatus().toString());
            stmt.setInt(3, item.getGoods().getId());
            stmt.setString(4, item.getResponsible().getRegistrationId());
            stmt.setInt(5, item.getPlace().getId());
            stmt.execute();


            return item.getTag();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Item> findOne(String key) {

            String sql = "SELECT * FROM Person WHERE tag = ?";
            Item item = null;

            try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
                stmt.setString(1,key);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()){
                    item = resultSetToEntity(rs);
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
            return Optional.ofNullable(item);

    }
    private Item resultSetToEntity(ResultSet resultSet) throws SQLException {
        int goodsId = resultSet.getInt("id");
        Goods goods = findGoodsUseCase.findOne(goodsId).get();//deixei na main public

        String personId = resultSet.getString("registrationId");
        Person person = findPersonUseCase.findOne(personId).get();

        int placeId = resultSet.getInt("id");
        Place place = findPlaceUseCase.findOne(placeId).get();

        return new Item(
                resultSet.getString("tag"),
                resultSet.getString("description"),
                StatusItem.toEnum(resultSet.getString("status")),
                goods,
                person,
                place

        );
    }

    @Override
    public List<Item> findAll() {
        String sql = "SELECT * FROM Category";
        List<Item> items = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Item item = resultSetToEntity(rs);
                items.add(item);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public boolean update(Item item) {
        String sql = "UPDATE Item SET description = ?, status = ?, goods = ?," +
                " responsible = ?, place = ? WHERE tag = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, item.getDescription());
            stmt.setString(2, item.getStatus().toString());
            stmt.setInt(3, item.getGoods().getId());
            stmt.setString(4,item.getResponsible().getRegistrationId());
            stmt.setInt(5,item.getPlace().getId());
            stmt.setString(6,item.getTag());
            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(String key) {
        String sql = "DELETE FROM Item WHERE tag = ?";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, key); //nao tem essa linha no video do lucas
            stmt.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Item item) {
        if (item == null || item.getTag() == null)
            throw new IllegalArgumentException("Item and item tag must not be NULL");
        return deleteByKey(item.getTag());
    }
}
