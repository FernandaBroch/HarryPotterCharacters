package br.com.dextra.HarryPotterCharacters.models.exceptions;

public class UnexistentEntityException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UnexistentEntityException() {}
    public UnexistentEntityException(String message) {
        super(message);
    }

}
