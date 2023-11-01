package br.edu.ifsp.inventariodoo.domain.entities.user;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class Employee extends User{
    private String email;
    private String password;
    private List<SecretPhrase> secretPhrases;
    private TypeWorker role;

    public Employee() {
        List<SecretPhrase> secretPhraseList = new ArrayList<>();
    }

    public Employee(String email, String password, TypeWorker role) {
        this.email = email;
        this.password = hashPassword(password);
        List<SecretPhrase> secretPhraseList = new ArrayList<>();
        this.secretPhrases = secretPhraseList;
        this.role = role;
    }

    public void registerSecretPhrase(String phrase, String answer){
        SecretPhrase secretPhrase = new SecretPhrase(phrase, hashPassword(answer));
        this.secretPhrases.add(secretPhrase);
    }

    public boolean checkSecretPhrase(SecretPhrase phrase, String answer){
        boolean flag = verifyPassword(answer, phrase.getAnswer());
        return flag;
    }

    public void changePassword(SecretPhrase phrase, String answer, String newPassword){
        if(checkSecretPhrase(phrase, answer))
            this.password = newPassword;
    }

    public static String hashPassword(String password) {
        try {
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
        boolean flag = newHash.equals(hashedPassword);
        return flag;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = hashPassword(password);
    }

    public List<SecretPhrase> getSecretPhrases() {
        return secretPhrases;
    }

    public TypeWorker getRole() {
        return role;
    }

    public void setRole(TypeWorker role) {
        this.role = role;
    }
}
