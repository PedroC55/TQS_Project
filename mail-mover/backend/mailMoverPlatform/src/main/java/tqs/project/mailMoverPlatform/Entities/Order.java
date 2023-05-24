package tqs.project.mailMoverPlatform.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="orders")
public class Order {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    @NotNull(message = "client name is mandatory")
    private String clientName;

    private String status;  // STORE -> COURIER -> ACP_POINT -> COLLECTED
    private Long storePickUpDate;
    private Long acpDeliveryDate;
    private Long clientPickUpDate;

    @ManyToOne
    @JoinColumn(name = "acp_id")
    private ACP acp;
    
    public Order(String clientName, ACP acp) {
        this.clientName = clientName;
        this.acp = acp;

        this.status = "STORE";
    }
    public Order(){};
    public void status_fromStoreToCourier(Long ts) throws Exception{
        if (this.getStatus().equals("STORE")) { 
            this.setStatus("COURIER");
            if (ts != null){
                this.setStorePickUpDate(ts);
            }else{
                this.setStorePickUpDate(System.currentTimeMillis());
            }
            
        }else {
            throw new Exception("Expected status STORE not found");
        }
    }
    public void status_fromCourierToAcpPoint(Long ts) throws Exception{
        if (this.getStatus().equals("COURIER")) { 
            this.setStatus("ACP_POINT");
            if (ts != null){
                this.setAcpDeliveryDate(ts);
            }else{
                this.setAcpDeliveryDate(System.currentTimeMillis());
            }
        }else {
            throw new Exception("Expected status COURIER not found");
        }
    }
    public void status_fromAcpPointToCollected(Long ts) throws Exception{
        if (this.getStatus().equals("ACP_POINT")) { 
            this.setStatus("COLLECTED");
            if (ts != null){
                this.setClientPickUpDate(ts);
            }else{
                this.setClientPickUpDate(System.currentTimeMillis());
            }
        }else {
            throw new Exception("Expected status ACP_POINT not found");
        }
    }

    public Long getId() {
        return id;
    }
    public String getclientName() {
        return clientName;
    }
    public void setclientName(String clientName) {
        this.clientName = clientName;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Long getStorePickUpDate() {
        return storePickUpDate;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setStorePickUpDate(Long storePickUpDate) {
        this.storePickUpDate = storePickUpDate;
    }
    public Long getAcpDeliveryDate() {
        return acpDeliveryDate;
    }
    public void setAcpDeliveryDate(Long acpDeliveryDate) {
        this.acpDeliveryDate = acpDeliveryDate;
    }
    public Long getClientPickUpDate() {
        return clientPickUpDate;
    }
    public void setClientPickUpDate(Long clientPickUpDate) {
        this.clientPickUpDate = clientPickUpDate;
    }
    public ACP getAcp() {
        return acp;
    }
    public void setAcpId(ACP acp) {
        this.acp = acp;
    }

}
