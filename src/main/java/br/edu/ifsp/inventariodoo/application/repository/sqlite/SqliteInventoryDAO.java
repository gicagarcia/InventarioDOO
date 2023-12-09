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
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.edu.ifsp.inventariodoo.application.main.Main.*;

public class SqliteInventoryDAO implements InventoryDAO {
    private final SqlitePlaceDAO placeDAO;
    private final SqliteItemDAO itemDAO;
    private final SqlitePersonDAO personDAO;
    public SqliteInventoryDAO(SqlitePlaceDAO placeDAO, SqliteItemDAO itemDAO, SqlitePersonDAO personDAO) {
        this.placeDAO = placeDAO;
        this.itemDAO = itemDAO;
        this.personDAO = personDAO;
    }


    @Override
    public Optional<List<Inventory>> findByInventor(Person inventor) {
        return findOneByAttribute("id",inventor.getRegistrationId());
    }
//    private Inventory resultSetToEntity(ResultSet resultSet) throws SQLException {
//        String presidentId = resultSet.getString("registrationId");
//        Person president = findPersonUseCase.findOne(presidentId).get();
//
//        String inventorsId = resultSet.getString("registrationId");
//        List<Person> people = findPersonUseCase.findAll();//fiz essas lista mas nao sei se ta certo
//
//        for (Person inventors : people) {
//            inventors.getRegistrationId();
//        }
//
//        Integer registerId = resultSet.getInt("id");
//        List<Register> itensInventoried = findRegisterUseCase.findAll();
//
//        for (Register register : itensInventoried) {
//            register.getId();
//        }
//
//        return new Inventory(//tive que deixar inventory public
//                resultSet.getInt("id"),
//                president,
//                people,//lista inventors
//                itensInventoried
//
//
//        );
//    }
    private Inventory resultSetToEntity(ResultSet resultSet) throws SQLException {
        int inventoryId = resultSet.getInt("id");

        String presidentId = resultSet.getString("president_registrationId");
        Optional<Person> presidentOptional = findPersonUseCase.findOne(presidentId);
        Person president = presidentOptional.orElse(null); // ou lide com a situação de ausência de outra forma

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
    private Person resultSetToPerson(ResultSet rs) throws SQLException {//olhar aqui porque retorna person, mas tenho tipos diferentes de person
        String registrationId = rs.getString("registrationId");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        // Outros campos, se houver

        // Crie uma instância de Person com os valores extraídos do ResultSet
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
                itensInventoried.add(resultSetToRegister(rs)); // Use o método certo para mapear o ResultSet para Register
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

        // Recupere informações do local (Place)
        Optional<Place> optionalPlace = placeDAO.findOne(placeid);
        Place place = optionalPlace.orElse(null);

        // Recupere informações do item (Item)
        Optional<Item> optionalItem = itemDAO.findOne(itemTag);
        Item item = optionalItem.orElse(null);

        // Recupere informações do inventor (Person)
        Optional<Person> optionalPerson = personDAO.findOne(personId);
        Person inventor = optionalPerson.orElse(null);

        String description = rs.getString("description");
        String status = rs.getString("status");

        // Crie uma instância de Register com os valores extraídos do ResultSet
        Register register = new Register();
        register.setId(id);
        register.setRegisterDate(registerDate);
        register.setPlace(place);
        register.setItem(item);
        register.setInventor(inventor);
        register.setDescription(description);
        register.setStatus(StatusItem.valueOf(status)); // Converte a string do status para o enum correspondente

        return register;
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
        String sql = "SELECT i.* " + //meu deus olha isso kkkkkk gpt é louco
                "FROM Inventory i " +
                "JOIN InventoryItensInventoried ii ON i.id = ii.inventoryId " +
                "JOIN Register r ON r.id = ii.registerId " +
                "WHERE r.place = ?";
        List<Inventory> inventories = new ArrayList<>();
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, place.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                inventories.add(resultSetToEntity(rs)); // Use o método certo para mapear o ResultSet para Inventory
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventories.isEmpty() ? Optional.empty() : Optional.of(inventories);
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
        String insertInventorySql = "INSERT INTO Inventory(president) VALUES(?)";

        try (PreparedStatement stmtInventory = ConnectionFactory.createPreparedStatement(insertInventorySql)) {
            stmtInventory.setString(1, inventory.getPresident().getRegistrationId());
            stmtInventory.executeUpdate();

            ResultSet generatedKeysResultSet = stmtInventory.getGeneratedKeys();
            if (generatedKeysResultSet.next()) {
                int inventoryId = generatedKeysResultSet.getInt(1);

                // Agora, insira os inventores na tabela InventoryPerson
                String insertInventoryPersonSql = "INSERT INTO InventoryPerson(inventoryId, person_registrationId) VALUES(?, ?)";
                try (PreparedStatement stmtInventoryPerson = ConnectionFactory.createPreparedStatement(insertInventoryPersonSql)) {
                    for (Person person : inventory.getInventors()) {
                        stmtInventoryPerson.setInt(1, inventoryId);
                        stmtInventoryPerson.setString(2, person.getRegistrationId());
                        stmtInventoryPerson.executeUpdate();
                    }
                }

                // Em seguida, insira os itens inventariados na tabela InventoryRegister
                String insertInventoryRegisterSql = "INSERT INTO InventoryRegister(inventoryId, registerId) VALUES(?, ?)";
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
