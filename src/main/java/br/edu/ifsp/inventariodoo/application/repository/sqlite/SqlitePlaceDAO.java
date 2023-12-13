package br.edu.ifsp.inventariodoo.application.repository.sqlite;

import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.entities.item.Place;
import br.edu.ifsp.inventariodoo.domain.usecases.place.PlaceDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqlitePlaceDAO implements PlaceDAO {
    @Override
    public Integer create(Place place) {
        String sql = "INSERT INTO Place(number, block) VALUES(?, ?)";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, place.getNumber());
            stmt.setString(2, place.getBlock());
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
    public Optional<Place> findOne(Integer key) {
        String sql = "SELECT * FROM Place WHERE id = ?";
        Place place = null;
        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1,key);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                place = resultSetToEntity(rs);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.ofNullable(place);
    }

    private Place resultSetToEntity(ResultSet rs) throws SQLException {
        return new Place(
                rs.getInt("id"),
                rs.getInt("number"),
                rs.getString("block")
        );
    }

    @Override
    public List<Place> findAll() {
        String sql = "SELECT * FROM Place";
        List<Place> places = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Place place = resultSetToEntity(rs);
                places.add(place);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return places;
    }

    @Override
    public boolean update(Place place) {
        String sql = "UPDATE Place SET number = ?, block = ? WHERE id = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, place.getNumber());
            stmt.setString(2, place.getBlock());
            stmt.setInt(3,place.getId());
            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        String sql = "DELETE FROM Place WHERE id = ?";
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
    public boolean delete(Place place) {
        if (place == null || place.getId() == null)
            throw new IllegalArgumentException("Place and place id must not be NULL");
        return deleteByKey(place.getId());
    }
}
