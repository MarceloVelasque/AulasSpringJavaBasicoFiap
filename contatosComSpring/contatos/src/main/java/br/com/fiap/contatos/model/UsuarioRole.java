package br.com.fiap.contatos.model;

public enum UsuarioRole {
    ADMIN("admin"),
    USER("user");

    private String role;

    private UsuarioRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
