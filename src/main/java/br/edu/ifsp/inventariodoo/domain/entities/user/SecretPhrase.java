package br.edu.ifsp.inventariodoo.domain.entities.user;

public class SecretPhrase {
    private String secretPhrase;
    private String answer;

    public SecretPhrase() {
    }

    public SecretPhrase(String secretPhrase, String answer) {
        this.secretPhrase = secretPhrase;
        this.answer = answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public String getSecretPhrase() {
        return secretPhrase;
    }

    public void setSecretPhrase(String secretPhrase) {
        this.secretPhrase = secretPhrase;
    }
}
