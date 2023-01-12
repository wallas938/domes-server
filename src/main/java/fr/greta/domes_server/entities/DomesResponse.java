package fr.greta.domes_server.entities;

public class DomesResponse extends Exception {
    private Boolean success;

    public DomesResponse(String message, Boolean success) {
        super(message);
        this.success = success;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public Boolean getSuccess() {
        return success;
    }
}
