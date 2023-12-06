package br.edu.ifsp.inventariodoo.application.repository.sqlite;

import br.edu.ifsp.inventariodoo.domain.entities.item.Goods;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.usecases.person.PersonDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqlitePersonDAO implements PersonDAO {
    @Override
    public Optional<Person> findByEmail(String email) {
        return findOneByAttribute("email",email);
    }

    @Override
    public String create(Person person) {
        String sql = "INSERT INTO Person(name, email, phone, passwordHash, secretPhrasesHash) " +
                "VALUES(?, ?, ?, ?, ?)";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, person.getName());
            stmt.setString(2, person.getEmail());
            stmt.setString(3, person.getPhone());
            stmt.setString(4, person.getPassword());
            stmt.setString(5, person.getSecretPhrases().toString());//coloquei toString nao sei se ta certo
            stmt.execute();


            return person.getRegistrationId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Person> findOne(String key) {
        return findOneByAttribute("id",key);
    }

    public Optional<Person> findOneByAttribute(String attribute, String value){
        String sql = "SELECT * FROM Person WHERE" + attribute + " = ?";
        Person person = null;

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1,value);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                person = resultSetToEntity(rs);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.ofNullable(person);
    }

    private Person resultSetToEntity(ResultSet rs) throws SQLException {
        String registrationId = rs.getString("registrationId");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        String passwordHash = rs.getString("passwordHash");
        Integer secretPhrasesHash = rs.getInt("secretPhrasesHash");

        //arrumar aqui acho que precisa de if igual no video
        return null;
    }

    @Override
    public List<Person> findAll() {
        String sql = "SELECT * FROM Person";
        List<Person> personList = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Person person = resultSetToEntity(rs);
                personList.add(person);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return personList;
    }

    @Override
    public boolean update(Person person) {
        String sql = "UPDATE Person SET name = ?, email = ?, phone = ?, passwordHash = ?, secretPhrasesHash = ?" +
                " WHERE registrationId = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, person.getName());
            stmt.setString(2, person.getEmail());
            stmt.setString(3, person.getPhone());
            stmt.setString(4,person.getPassword());
            stmt.setString(5,person.getSecretPhrases().toString());//usei toString mas nao sei se ta certo
            stmt.setString(6,person.getRegistrationId());
            stmt.execute();
            //nao sei se usa os if igual no video do lucas
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(String key) {
        String sql = "DELETE FROM Person WHERE id = ?";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            //stmt.setString(1, key); nao tem essa linha no video do lucas
            stmt.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Person person) {
        if (person == null || person.getRegistrationId() == null)
            throw new IllegalArgumentException("Person and person id must not be NULL");
        return deleteByKey(person.getRegistrationId());
    }
}
