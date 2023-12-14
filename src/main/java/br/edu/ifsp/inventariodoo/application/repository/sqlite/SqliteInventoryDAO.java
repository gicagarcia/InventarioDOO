package br.edu.ifsp.inventariodoo.application.repository.sqlite;

import br.edu.ifsp.inventariodoo.domain.entities.inventory.Inventory;
import br.edu.ifsp.inventariodoo.domain.entities.inventory.Register;
import br.edu.ifsp.inventariodoo.domain.entities.inventory.StatusItem;
import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.entities.item.Item;
import br.edu.ifsp.inventariodoo.domain.entities.item.Place;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.entities.user.SecretPhrase;
import br.edu.ifsp.inventariodoo.domain.entities.user.TypeWorker;
import br.edu.ifsp.inventariodoo.domain.usecases.inventory.InventoryDAO;
import br.edu.ifsp.inventariodoo.domain.usecases.item.ItemDAO;
import br.edu.ifsp.inventariodoo.domain.usecases.person.PersonDAO;
import br.edu.ifsp.inventariodoo.domain.usecases.place.PlaceDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.edu.ifsp.inventariodoo.application.main.Main.*;

public class SqliteInventoryDAO implements InventoryDAO {
    private final PlaceDAO placeDAO;
    private final ItemDAO itemDAO;
    private final PersonDAO personDAO;
    public SqliteInventoryDAO(PlaceDAO placeDAO, ItemDAO itemDAO, PersonDAO personDAO) {
        this.placeDAO = placeDAO;
        this.itemDAO = itemDAO;
        this.personDAO = personDAO;
    }


    @Override
    public Optional<List<Inventory>> findByInventor(Person inventor) {
        String sql = "SELECT i.* " +
                "FROM Inventory i " +
                "JOIN InventoryInventors ii ON i.id = ii.inventoryId " +
                "JOIN Person p ON p.registrationId = ii.inventorId " +
                "WHERE p.registrationId = ?";

        List<Inventory> inventories = new ArrayList<>();

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, inventor.getRegistrationId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Inventory inventory = resultSetToEntity(rs);
                inventories.add(inventory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inventories.isEmpty() ? Optional.empty() : Optional.of(inventories);
    }

    private Inventory resultSetToEntity(ResultSet resultSet) throws SQLException {
        int inventoryId = resultSet.getInt("id");

        String presidentId = resultSet.getString("president");
        Optional<Person> presidentOptional = findPersonUseCase.findOne(presidentId);
        Person president = presidentOptional.orElse(null);

        List<Person> inventors = findInventorsForInventory(inventoryId);
        List<Register> itensInventoried = findItensInventoriedForInventory(inventoryId);

        return new Inventory(
                inventoryId,
                president,
                inventors,
                itensInventoried
        );
    }

    private List<Person> findInventorsForInventory(int inventoryId) {
        List<Person> inventors = new ArrayList<>();
        String sql = "SELECT p.* FROM Person p " +
                "JOIN InventoryInventors ii ON p.registrationId = ii.inventorId " +
                "WHERE ii.inventoryId = ?";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, inventoryId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Person inventor = resultSetToPerson(rs);
                inventors.add(inventor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inventors;
    }
    private Person resultSetToPerson(ResultSet rs) throws SQLException {
        String registrationId = rs.getString("registrationId");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String phone = rs.getString("phone");

        Person person = new Person();
        person.setRegistrationId(registrationId);
        person.setName(name);
        person.setEmail(email);
        person.setPhone(phone);

        return person;


    }

    private List<Register> findItensInventoriedForInventory(int inventoryId) {
        List<Register> itensInventoried = new ArrayList<>();
        String sql = "SELECT r.* " +
                "FROM Register r " +
                "JOIN InventoryItensInventoried ii ON r.id = ii.registerId " +
                "WHERE ii.inventoryId = ?";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, inventoryId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                itensInventoried.add(resultSetToRegister(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itensInventoried;
    }
    private Register resultSetToRegister(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        Integer placeid = rs.getInt("place");
        String itemTag = rs.getString("item");
        String personId = rs.getString("registrationId");
        LocalDate registerDate = LocalDate.parse(rs.getString("registerDate"));


        Optional<Place> optionalPlace = placeDAO.findOne(placeid);
        Place place = optionalPlace.orElse(null);


        Optional<Item> optionalItem = itemDAO.findOne(itemTag);
        Item item = optionalItem.orElse(null);


        Optional<Person> optionalPerson = personDAO.findOne(personId);
        Person inventor = optionalPerson.orElse(null);

        String description = rs.getString("description");
        String status = rs.getString("status");


        Register register = new Register();
        register.setId(id);
        register.setRegisterDate(registerDate);
        register.setPlace(place);
        register.setItem(item);
        register.setInventor(inventor);
        register.setDescription(description);
        register.setStatus(StatusItem.valueOf(status));

        return register;
    }


    @Override
    public Optional<List<Inventory>> findByPlace(Place place) {
        String sql = "SELECT i.* " +
                "FROM Inventory i " +
                "JOIN InventoryItensInventoried ii ON i.id = ii.inventoryId " +
                "JOIN Register r ON r.id = ii.registerId " +
                "WHERE r.place = ?";
        List<Inventory> inventories = new ArrayList<>();
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, place.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                inventories.add(resultSetToEntity(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.of(inventories);
    }


    @Override
    public Optional<List<Inventory>> findByStatus(StatusItem status) {
        String sql = "SELECT i.* " +
                "FROM Inventory i " +
                "JOIN InventoryItensInventoried ii ON i.id = ii.inventoryId " +
                "JOIN Register r ON r.id = ii.registerId " +
                "WHERE r.status = ?";
        List<Inventory> inventories = new ArrayList<>();
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, status.toString()); // Certifique-se de que status seja uma String ou ajuste conforme necessário
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Inventory inventory = resultSetToEntity(rs);
                inventories.add(inventory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.of(inventories);
    }

    @Override
    public Optional<List<Inventory>> findByResponsible(Person responsiblePerson) {
        String sql = "SELECT * FROM Inventory WHERE president = ?";

        List<Inventory> inventories = new ArrayList<>();

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, responsiblePerson.getRegistrationId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Inventory inventory = resultSetToEntity(rs);
                inventories.add(inventory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inventories.isEmpty() ? Optional.empty() : Optional.of(inventories);
    }

    @Override
    public Integer create(Inventory inventory) {
        String insertInventorySql = "INSERT INTO Inventory(president) VALUES(?)";

        try (PreparedStatement stmtInventory = ConnectionFactory.createPreparedStatement(insertInventorySql)) {
            stmtInventory.setString(1, inventory.getPresident().getRegistrationId());
            stmtInventory.executeUpdate();

            ResultSet generatedKeysResultSet = stmtInventory.getGeneratedKeys();
            if (generatedKeysResultSet.next()) {
                int inventoryId = generatedKeysResultSet.getInt(1);


                String insertInventoryPersonSql = "INSERT INTO InventoryInventors(inventoryId, inventorId) VALUES(?, ?)";
                try (PreparedStatement stmtInventoryInventors = ConnectionFactory.createPreparedStatement(insertInventoryPersonSql)) {
                    for (Person person : inventory.getInventors()) {
                        stmtInventoryInventors.setInt(1, inventoryId);
                        stmtInventoryInventors.setString(2, person.getRegistrationId());
                        stmtInventoryInventors.executeUpdate();
                    }
                }


                String insertInventoryRegisterSql = "INSERT INTO InventoryItensInventoried(inventoryId, registerId) VALUES(?, ?)";
                try (PreparedStatement stmtInventoryRegister = ConnectionFactory.createPreparedStatement(insertInventoryRegisterSql)) {
                    for (Register register : inventory.getItensInventoried()) {
                        stmtInventoryRegister.setInt(1, inventoryId);
                        stmtInventoryRegister.setInt(2, register.getId());
                        stmtInventoryRegister.executeUpdate();
                    }
                }

                return inventoryId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Optional<Inventory> findOne(Integer key) {
        String sql = "SELECT * FROM Inventory WHERE id = ?";
        Inventory inventory = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, key);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                inventory = resultSetToEntity(rs);

                List<Person> inventors = findInventorsForInventory(inventory.getId());
                inventory.setInventors(inventors);

                List<Register> itensInventoried = findItensInventoriedForInventory(inventory.getId());
                inventory.setItensInventoried(itensInventoried);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(inventory);
    }

    @Override
    public List<Inventory> findAll() {
        String sql = "SELECT i.*, ip.inventorId, ir.registerId " +
                "FROM Inventory i " +
                "LEFT JOIN InventoryInventors ip ON i.id = ip.inventoryId " +
                "LEFT JOIN InventoryItensInventoried ir ON i.id = ir.inventoryId";

        List<Inventory> inventories = new ArrayList<>();

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Inventory inventory = resultSetToEntity(rs);
                inventory.addPersonRegistrationId(rs.getString("inventorId"));
                inventory.addRegisterId(rs.getInt("registerId"));

                inventories.add(inventory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inventories;
    }

    public boolean update(Inventory inventory) {
        String updateInventorySql = "UPDATE Inventory SET president = ? WHERE id = ?";
        String updateInventoryInventorsSql = "UPDATE InventoryInventors SET inventorId = ? WHERE inventoryId = ?";
        String updateInventoryItemsSql = "UPDATE InventoryItensInventoried SET registerId = ? WHERE inventoryId = ?";

        try (PreparedStatement stmtInventory = ConnectionFactory.createPreparedStatement(updateInventorySql);
             PreparedStatement stmtInventoryInventors = ConnectionFactory.createPreparedStatement(updateInventoryInventorsSql);
             PreparedStatement stmtInventoryItems = ConnectionFactory.createPreparedStatement(updateInventoryItemsSql)) {


            stmtInventory.setString(1, inventory.getPresident().getRegistrationId());
            stmtInventory.setInt(2, inventory.getId());
            stmtInventory.executeUpdate();


            for (Person inventor : inventory.getInventors()) {
                stmtInventoryInventors.setString(1, inventor.getRegistrationId());
                stmtInventoryInventors.setInt(2, inventory.getId());
                stmtInventoryInventors.executeUpdate();
            }

            for (Register register : inventory.getItensInventoried()) {
                System.out.println(register);
                stmtInventoryItems.setInt(1, register.getId());
                stmtInventoryItems.setInt(2, inventory.getId());
                stmtInventoryItems.executeUpdate();
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
