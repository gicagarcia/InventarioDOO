package br.edu.ifsp.inventariodoo.application.repository.sqlite;

import br.edu.ifsp.inventariodoo.domain.entities.inventory.Register;
import br.edu.ifsp.inventariodoo.domain.entities.item.Goods;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.entities.user.SecretPhrase;
import br.edu.ifsp.inventariodoo.domain.entities.user.TypeWorker;
import br.edu.ifsp.inventariodoo.domain.usecases.person.PersonDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import static br.edu.ifsp.inventariodoo.application.main.Main.findPersonUseCase;

public class SqlitePersonDAO implements PersonDAO {
    private static PersonDAO personDAO;

    @Override
    public Optional<Person> findByEmail(String email) {
        String sql = "SELECT * FROM Person WHERE email = ?";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(resultSetToEntity(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public String create(Person person) {
        String insertPersonSql = "INSERT INTO Person(registrationId, name, email, phone, passwordHash, roles) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmtPerson = ConnectionFactory.createPreparedStatement(insertPersonSql)) {
            stmtPerson.setString(1, person.getRegistrationId());
            stmtPerson.setString(2, person.getName());
            stmtPerson.setString(3, person.getEmail());
            stmtPerson.setString(4, person.getPhone());
            stmtPerson.setString(5, person.getPassword());
            stmtPerson.setString(6, person.rolesToString());
            stmtPerson.executeUpdate();


            if (person.hasRole(TypeWorker.WAREHOUSEMAN) || person.hasRole(TypeWorker.PREMIER)) {
                String insertSecretPhraseSql = "INSERT INTO PersonSecretPhrase(personRegistrationId, secretPhrase, answer) VALUES (?, ?, ?)";
                try (PreparedStatement stmtSecretPhrase = ConnectionFactory.createPreparedStatement(insertSecretPhraseSql)) {
                    for (SecretPhrase secretPhrase : person.getSecretPhrases()) {
                        stmtSecretPhrase.setString(1, person.getRegistrationId());
                        stmtSecretPhrase.setString(2, secretPhrase.getSecretPhrase());
                        stmtSecretPhrase.setString(3, secretPhrase.getAnswer());
                        stmtSecretPhrase.executeUpdate();
                    }
                }
            }

            return person.getRegistrationId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Person> findOne(String registrationId) {
        String sql = "SELECT * FROM Person WHERE registrationId = ?";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, registrationId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(resultSetToEntity(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
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
        String roles = rs.getString("roles");

        System.out.println(registrationId);


        Person person = new Person(registrationId, name, email, phone, passwordHash);
        List<SecretPhrase> secretPhrases = getSecretPhrasesForPerson(person);
        person.setSecretPhrases(secretPhrases);

        EnumSet<TypeWorker> type = stringToRoles(roles);
        person.setRoles(type);

        return person;
    }

    public static EnumSet<TypeWorker> stringToRoles(String stringEnum) {
        String[] partes = stringEnum.split(",");

        return Arrays.stream(partes)
                .map(TypeWorker::valueOf)
                .collect(Collectors.toCollection(() -> EnumSet.noneOf(TypeWorker.class)));
    }

    private List<SecretPhrase> getSecretPhrasesForPerson(Person person) throws SQLException {
        List<SecretPhrase> secretPhrases = new ArrayList<>();

        String registrationId = person.getRegistrationId();
        if (person.hasRole(TypeWorker.WAREHOUSEMAN) || person.hasRole(TypeWorker.PREMIER)) {
            String selectSecretPhrasesSql = "SELECT secretPhrase, answer FROM PersonSecretPhrase WHERE personRegistrationId = ?";
            try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(selectSecretPhrasesSql)) {
                stmt.setString(1, registrationId);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String secretPhrase = rs.getString("secretPhrase");
                    String answer = rs.getString("answer");
                    SecretPhrase secret = new SecretPhrase(secretPhrase, answer);
                    secretPhrases.add(secret);
                }
            }
        }

        return secretPhrases;
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
        String updateSql = "UPDATE Person SET name = ?, email = ?, phone = ?, passwordHash = ?, roles = ? WHERE registrationId = ?";
        String updateSecretPhrasesSql = "UPDATE PersonSecretPhrase SET secretPhrase = ?, answer = ? WHERE personRegistrationId = ?";

        try (PreparedStatement stmtPerson = ConnectionFactory.createPreparedStatement(updateSql);
             PreparedStatement stmtSecretPhrase = ConnectionFactory.createPreparedStatement(updateSecretPhrasesSql)) {

            stmtPerson.setString(1, person.getName());
            stmtPerson.setString(2, person.getEmail());
            stmtPerson.setString(3, person.getPhone());
            stmtPerson.setString(4, person.getPassword());
            stmtPerson.setString(5, person.rolesToString());
            stmtPerson.setString(6, person.getRegistrationId());

            stmtPerson.execute();


            if (person.hasRole(TypeWorker.PREMIER) || person.hasRole(TypeWorker.WAREHOUSEMAN)) {
                for (SecretPhrase secretPhrase : person.getSecretPhrases()) {
                    stmtSecretPhrase.setString(1, secretPhrase.getSecretPhrase());
                    stmtSecretPhrase.setString(2, secretPhrase.getAnswer());
                    stmtSecretPhrase.setString(3, person.getRegistrationId());

                    stmtSecretPhrase.executeUpdate();
                }
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(String key) {
        String sql = "DELETE FROM Person WHERE registrationId = ?";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, key);
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
