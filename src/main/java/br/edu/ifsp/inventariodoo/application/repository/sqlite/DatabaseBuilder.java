package br.edu.ifsp.inventariodoo.application.repository.sqlite;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseBuilder {

    public void buildDatabaseIfMissing(){
        if (isDatabaseMissing()){
            System.out.println("Database is missing. Building database: \n");
            buildTables();
        }
    }

    private boolean isDatabaseMissing() {
        return !Files.exists(Paths.get("database.db"));
    }

    private void buildTables(){
        try (Statement statement = ConnectionFactory.createStatement()){
            System.out.println("Criando place table:");
            statement.addBatch(createPlaceTable());
            System.out.println("Criando category table:");
            statement.addBatch(createCategoryTable());
            System.out.println("Criando goods table:");
            statement.addBatch(createGoodsTable());
            System.out.println("Criando person table:");
            statement.addBatch(createPersonTable());
            statement.addBatch(createPersonSecretPhraseTable());
            System.out.println("Criando item table:");
            statement.addBatch(createItemTable());
            System.out.println("Criando register table:");
            statement.addBatch(createRegisterTable());
            System.out.println("Criando inventory table:");
            statement.addBatch(createInventoryTable());
            statement.addBatch(createInventoryInventorsTable());
            statement.addBatch(createInventoryItensInventoriedTable());
            statement.executeBatch();

            System.out.println("Database successfully created.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }
    private String createPlaceTable(){
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE Place(");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("number INTEGER NOT NULL, \n");
        builder.append("block TEXT NOT NULL \n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createCategoryTable(){
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE Category(");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("name TEXT NOT NULL UNIQUE, \n");
        builder.append("area TEXT NOT NULL, \n");
        builder.append("application TEXT NOT NULL \n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();

    }

    private String createGoodsTable(){
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE Goods(");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("name TEXT NOT NULL UNIQUE, \n");
        builder.append("origin TEXT NOT NULL, \n");
        builder.append("characteristics TEXT NOT NULL, \n");
        builder.append("category INTEGER NOT NULL, \n");//fk
        builder.append("FOREIGN KEY(category) REFERENCES Category(id) \n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createPersonTable(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Person(");
        builder.append("registrationId TEXT NOT NULL PRIMARY KEY , \n");
        builder.append("name TEXT NOT NULL, \n");
        builder.append("email TEXT NOT NULL UNIQUE, \n");
        builder.append("phone TEXT NOT NULL, \n");
        builder.append("passwordHash TEXT, \n");
        builder.append("roles TEXT NOT NULL \n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createPersonSecretPhraseTable() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE PersonSecretPhrase(");
        builder.append("personRegistrationId TEXT NOT NULL, \n");
        builder.append("secretPhrase TEXT NOT NULL, \n");
        builder.append("answer TEXT NOT NULL, \n");
        builder.append("FOREIGN KEY(personRegistrationId) REFERENCES Person(registrationId) ON DELETE CASCADE \n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createItemTable(){
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE Item(");
        builder.append("tag TEXT NOT NULL PRIMARY KEY, \n");
        builder.append("description TEXT NOT NULL, \n");
        builder.append("status TEXT NOT NULL, \n");
        builder.append("goods INTEGER NOT NULL, \n");
        builder.append("responsible TEXT NOT NULL, \n");
        builder.append("place INTEGER NOT NULL, \n");
        builder.append("FOREIGN KEY(goods) REFERENCES Goods(id), \n");
        builder.append("FOREIGN KEY(responsible) REFERENCES Person(registrationId), \n");
        builder.append("FOREIGN KEY(place) REFERENCES Place(id) \n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createRegisterTable(){
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE Register(");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("registerDate TEXT NOT NULL, \n");
        builder.append("place TEXT NOT NULL, \n");
        builder.append("item TEXT NOT NULL, \n");
        builder.append("inventor TEXT NOT NULL, \n");
        builder.append("description TEXT NOT NULL, \n");
        builder.append("status TEXT NOT NULL, \n");
        builder.append("FOREIGN KEY(place) REFERENCES Place(id), \n");
        builder.append("FOREIGN KEY(item) REFERENCES Item(tag), \n");
        builder.append("FOREIGN KEY(inventor) REFERENCES Person(registrationId) \n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }



    private String createInventoryTable() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE Inventory(");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("president TEXT NOT NULL, \n");
        builder.append("FOREIGN KEY(president) REFERENCES Person(registrationId) \n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }
    private String createInventoryInventorsTable() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE InventoryInventors(");
        builder.append("inventoryId INTEGER, \n");
        builder.append("inventorId TEXT, \n");
        builder.append("FOREIGN KEY(inventoryId) REFERENCES Inventory(id), \n");
        builder.append("FOREIGN KEY(inventorId) REFERENCES Person(registrationId), \n");
        builder.append("PRIMARY KEY (inventoryId, inventorId) \n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createInventoryItensInventoriedTable() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE InventoryItensInventoried(");
        builder.append("inventoryId INTEGER, \n");
        builder.append("registerId TEXT, \n");
        builder.append("FOREIGN KEY(inventoryId) REFERENCES Inventory(id), \n");
        builder.append("FOREIGN KEY(registerId) REFERENCES Register(id), \n");
        builder.append("PRIMARY KEY (inventoryId, registerId) \n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

}
