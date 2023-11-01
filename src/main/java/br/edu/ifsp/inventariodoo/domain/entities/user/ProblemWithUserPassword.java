package br.edu.ifsp.inventariodoo.domain.entities.user;

public class ProblemWithUserPassword extends RuntimeException{
    public ProblemWithUserPassword(String message) {
        super(message);
    }
}
