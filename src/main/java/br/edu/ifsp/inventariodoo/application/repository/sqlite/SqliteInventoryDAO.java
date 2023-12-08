package br.edu.ifsp.inventariodoo.application.repository.sqlite;

import br.edu.ifsp.inventariodoo.domain.entities.inventory.Inventory;
import br.edu.ifsp.inventariodoo.domain.entities.inventory.Register;
import br.edu.ifsp.inventariodoo.domain.entities.inventory.StatusItem;
import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.entities.item.Item;
import br.edu.ifsp.inventariodoo.domain.entities.item.Place;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.usecases.inventory.InventoryDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.edu.ifsp.inventariodoo.application.main.Main.*;

public class SqliteInventoryDAO implements InventoryDAO {
    @Override
    public Optional<List<Inventory>> findByInventor(Person inventor) {
        return findOneByAttribute("id",inventor.getRegistrationId());
    }
    private Inventory resultSetToEntity(ResultSet resultSet) throws SQLException {
        String presidentId = resultSet.getString("registrationId");
        Person president = findPersonUseCase.findOne(presidentId).get();

        String inventorsId = resultSet.getString("registrationId");
        List<Person> people = findPersonUseCase.findAll();//fiz essas lista mas nao sei se ta certo

        for (Person inventors : people) {
            inventors.getRegistrationId();
        }

        Integer registerId = resultSet.getInt("id");
        List<Register> itensInventoried = findRegisterUseCase.findAll();

        for (Register register : itensInventoried) {
            register.getId();
        }

        return new Inventory(//tive que deixar inventory public
                resultSet.getInt("id"),
                president,
                people,//lista inventors
                itensInventoried


        );
    }
    public Optional<List<Inventory>> findOneByAttribute(String attribute, String value){
        String sql = "SELECT * FROM Inventory WHERE" + attribute + " = ?";
        Inventory inventory = null;

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1,value);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                inventory = resultSetToEntity(rs);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }//adaptei pra retornar uma lista
        return Optional.ofNullable((List<Inventory>) inventory);
    }
    @Override
    public Optional<List<Inventory>> findByPlace(Place place) {
        String sql = "SELECT * FROM Inventory WHERE place = ?"; //nao tem place em inventory, mas register tem, seria register.getPlace().getId()
        List<Inventory> inventories = new ArrayList<>();
        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1,place.getId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                //inventories = resultSetToEntity(rs);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.ofNullable(inventories);
    }

    @Override
    public Optional<List<Inventory>> findByStatus(StatusItem status) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Inventory>> findByResponsible(Person responsiblePerson) {
        return Optional.empty();
    }

    @Override
    public Integer create(Inventory inventory) {
        String sql = "INSERT INTO Inventory(president, inventors, itensInventoried) " +
                "VALUES(?, ?, ?, ?)";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, inventory.getPresident().getRegistrationId());
            for (Person person : inventory.getInventors()) {//tentativa aqui
                stmt.setString(2, person.getRegistrationId());
                stmt.executeUpdate();
            }
            for (Register register : inventory.getItensInventoried()) {//tentativa aqui
                stmt.setInt(3, register.getId());
                stmt.executeUpdate();
            }
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
    public Optional<Inventory> findOne(Integer key) {
        String sql = "SELECT * FROM Inventory WHERE id = ?";
        Inventory inventory = null;

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1,key);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                inventory = resultSetToEntity(rs);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.ofNullable(inventory);
    }

    @Override
    public List<Inventory> findAll() {
        String sql = "SELECT * FROM Inventory";
        List<Inventory> inventories = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Inventory inventory = resultSetToEntity(rs);
                inventories.add(inventory);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return inventories;
    }
}
