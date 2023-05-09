package tqs.project.mailMoverPlatform.Entities;
import org.springframework.data.annotation.Id;
import javax.persistence.GeneratedValue;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "acps")
public class ACP {
    @Id
    @GeneratedValue
    private String id;
    private String name;
    private String address;
    
    public ACP(String name, String address) {
        this.name = name;
        this.address = address;
    }
    public String getId() {
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
}
