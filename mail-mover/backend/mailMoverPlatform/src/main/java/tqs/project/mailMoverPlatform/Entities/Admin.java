package tqs.project.mailMoverPlatform.Entities;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import javax.persistence.GeneratedValue;

@Document(collection = "admins")
public class Admin {
    @Id
    @GeneratedValue
    private String id;
    private String email;
    private String password;
    
    public Admin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public String getId() {
        return id;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
