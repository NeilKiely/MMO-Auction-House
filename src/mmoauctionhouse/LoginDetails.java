package mmoauctionhouse;

public class LoginDetails {
    
    private String username, password;
    
    /**
     *
     * @author Vilius
     */
    public LoginDetails(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
}
