package med.voll.api.web.dto;

public record JwtDto(String jwt, String type) {

    public JwtDto(String jwt) {
        this(jwt, "Bearer");
    }
}
