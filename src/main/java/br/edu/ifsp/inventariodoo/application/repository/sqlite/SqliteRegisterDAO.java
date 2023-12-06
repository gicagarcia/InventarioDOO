package br.edu.ifsp.inventariodoo.application.repository.sqlite;

import br.edu.ifsp.inventariodoo.domain.entities.inventory.Register;
import br.edu.ifsp.inventariodoo.domain.entities.inventory.StatusItem;
import br.edu.ifsp.inventariodoo.domain.entities.item.Goods;
import br.edu.ifsp.inventariodoo.domain.entities.item.Item;
import br.edu.ifsp.inventariodoo.domain.entities.item.Place;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.usecases.register.RegisterDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.edu.ifsp.inventariodoo.application.main.Main.*;

public class SqliteRegisterDAO implements RegisterDAO {
    @Override
    public Integer create(Register register) {
        String sql = "INSERT INTO Register(registerDate, place, item, inventor, description, status) " +
                "VALUES(?, ?, ?, ?, ?, ?)";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, register.getRegisterDate().toString());
            stmt.setInt(2, register.getPlace().getId());
            stmt.setString(3, register.getItem().getTag());
            stmt.setString(4, register.getInventor().getRegistrationId());
            stmt.setString(5, register.getDescription());
            stmt.setString(6, register.getStatus().toString());
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
    public Optional<Register> findOne(Integer key) {
        String sql = "SELECT * FROM Register WHERE id = ?";
        Register register = null;

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1,key);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                register = resultSetToEntity(rs);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.ofNullable(register);
    }

    private Register resultSetToEntity(ResultSet resultSet) throws SQLException {
        String personId = resultSet.getString("registrationId");
        Person person = findPersonUseCase.findOne(personId).get();

        String itemTag = resultSet.getString("tag");
        Item item = findItemUseCase.findOne(itemTag).get();

        int placeId = resultSet.getInt("id");
        Place place = findPlaceUseCase.findOne(placeId).get();


        return new Register(
                resultSet.getInt("id"),
                LocalDate.parse(resultSet.getString("registerDate")),
                place,
                item,
                person,
                resultSet.getString("description"),
                StatusItem.toEnum(resultSet.getString("status"))

        );
    }
    @Override
    public List<Register> findAll() {
        String sql = "SELECT * FROM Register";
        List<Register> registerList = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Register register = resultSetToEntity(rs);
                registerList.add(register);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return registerList;
    }
}
