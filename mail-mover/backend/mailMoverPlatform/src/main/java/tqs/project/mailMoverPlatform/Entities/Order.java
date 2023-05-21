package tqs.project.mailMoverPlatform.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Table(name="orders")
@Entity
public class Order {
    static Integer trackingNumberTracker = 1;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull(message = "tracking number is mandatory")
    private String trackingNumber;
    
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
        
        this.trackingNumber = trackingNumberTracker.toString();
        trackingNumberTracker++; 
        this.status = "STORE";
    }

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
    public String gettrackingNumber() {
        return trackingNumber;
    }
    public void settrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
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
