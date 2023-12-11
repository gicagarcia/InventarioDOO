package br.edu.ifsp.inventariodoo.domain.entities.user;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.EnumSet;
import java.util.List;

import static br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator.nullOrEmpty;

public class Person{
    private String registrationId;
    private String name;
    private String email;
    private String phone;
    private String password;
    private List<SecretPhrase> secretPhrases;
    private EnumSet<TypeWorker> roles;

    public Person() {
    }
    //Sem construtor sem registrationId porque a pessoa precisa ter vínculo com a universidade


    public Person(String registrationId, String name, String email, String phone, String password,
                  List<SecretPhrase> secretPhrases) {//tive que deixar Person public
        this.registrationId = registrationId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.secretPhrases = secretPhrases;
        this.roles = EnumSet.noneOf(TypeWorker.class);
    }

    public static Person asWarehouseman(String registrationId, String name, String email, String phone, String password) {
        String hash = hashPassword(password);
        Person person = new Person(registrationId, name, email, phone, hash, null);
        person.addRole(TypeWorker.WAREHOUSEMAN);
        person.addRole(TypeWorker.PERSON);
        return person;
    }

    public static Person asPerson(String registrationId, String name, String email, String phone){
        Person person = new Person(registrationId, name, email, phone, null, null);
        person.addRole(TypeWorker.PERSON);
        return person;
    }

    public static Person asPremier(String registrationId, String name, String email, String phone, String password){
        String hash = hashPassword(password);
        Person person = new Person(registrationId, name, email, phone, hash, null);
        person.addRole(TypeWorker.PREMIER);
        person.addRole(TypeWorker.PERSON);
        return person;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void registerSecretPhrase(String phrase, String answer){
        SecretPhrase secretPhrase = new SecretPhrase(phrase, hashPassword(answer));
        this.secretPhrases.add(secretPhrase);
    }

    public boolean checkSecretPhrase(SecretPhrase phrase, String answer){
        return verifyPassword(answer, phrase.getAnswer());
    }


    public static String hashPassword(String password) {
        try {
            if(nullOrEmpty(password))
                return password;

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
            for (byte b : encodedhash) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean verifyPassword(String password, String hashedPassword) {
        String newHash = hashPassword(password);
        return newHash.equals(hashedPassword);
    }

    public void setPassword(String password) {
        this.password = hashPassword(password);
    }

    public String getPassword(){return password;}

    public List<SecretPhrase> getSecretPhrases() {
        return secretPhrases;
    }

    public void setSecretPhrases(List<SecretPhrase> secretPhrases) {
        this.secretPhrases = secretPhrases;
    }

    public boolean hasRole(TypeWorker role){
        return roles.contains(role);
    }

    public void addRole(TypeWorker role) {
        this.roles.add(role);
    }

    @Override
    public String toString() {
        return "Person{" +
                "registrationId='" + registrationId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", secretPhrases=" + secretPhrases +
                ", roles=" + roles +
                '}';
    }
}
