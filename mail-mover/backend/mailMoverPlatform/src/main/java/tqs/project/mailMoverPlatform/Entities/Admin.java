package tqs.project.mailMoverPlatform.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="admins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    @NotNull(message = "email is mandatory")
    private String email;

    public void setId(Long id) {
        this.id = id;
    }
    @Column
    @NotNull(message = "password is mandatory")
    private String password;
    
    public Admin(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public Admin(){};

    public String getEmail() {
        return email;
    }
    public Long getId() {
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
