package tqs.project.mailMoverPlatform.Entities;

import org.springframework.data.annotation.Id;
import javax.persistence.GeneratedValue;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orders")
public class Order {
    static Integer trackingNumberTracker = 1;
    @Id
    @GeneratedValue
    private String id;
    private String trackingNumber;
    private String clientName;
    private String status;  // STORE -> COURIER -> ACP_POINT -> COLLECTED
    private Long storePickUpDate;
    private Long acpDeliveryDate;
    private Long clientPickUpDate;
    private String acpId;
    
    public Order(String clientName, String acpId) {
        this.clientName = clientName;
        this.acpId = acpId;
        
        this.trackingNumber = trackingNumberTracker.toString();
        trackingNumberTracker++; 
        this.status = "STORE";
    }

    public void status_fromStoreToCourier() throws Exception{
        if (this.getStatus().equals("STORE")) { 
            this.setStatus("COURIER");
            this.setStorePickUpDate(System.currentTimeMillis());
        }else {
            throw new Exception("Expected status STORE not found");
        }
    }
    public void status_fromCourierToAcpPoint() throws Exception{
        if (this.getStatus().equals("COURIER")) { 
            this.setStatus("ACP_POINT");
            this.setAcpDeliveryDate(System.currentTimeMillis());
        }else {
            throw new Exception("Expected status COURIER not found");
        }
    }
    public void status_fromAcpPointToCollected() throws Exception{
        if (this.getStatus().equals("ACP_POINT")) { 
            this.setStatus("COLLECTED");
            this.setClientPickUpDate(System.currentTimeMillis());
        }else {
            throw new Exception("Expected status ACP_POINT not found");
        }
    }

    public String getId() {
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
    public String getAcpId() {
        return acpId;
    }
    public void setAcpId(String acpId) {
        this.acpId = acpId;
    }

}
