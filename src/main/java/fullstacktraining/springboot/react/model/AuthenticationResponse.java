package fullstacktraining.springboot.react.model;

public class AuthenticationResponse {

    private String JwtToken;
    private String message;

    public AuthenticationResponse(String jwtToken, String msg) {
        JwtToken = jwtToken;
        message = msg;
    }

    public String getJwtToken() {
        return JwtToken;
    }

    public String getMessage() {
        return message;
    }
}
