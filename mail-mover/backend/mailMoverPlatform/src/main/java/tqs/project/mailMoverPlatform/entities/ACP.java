package tqs.project.mailMoverPlatform.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name="acps")
public class ACP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    @NotNull(message = "name is mandatory")
    private String name;

    @Column
    @NotNull(message = "address is mandatory")
    private String address;

    @Column
    @NotNull(message = "email is mandatory")
    private String email;

    @Column
    @NotNull(message = "password is mandatory")
    private String password;
    
    public ACP(String name, String address, String email, String password) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
    }
    public ACP(){};
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getEmail() {
        return email;
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
