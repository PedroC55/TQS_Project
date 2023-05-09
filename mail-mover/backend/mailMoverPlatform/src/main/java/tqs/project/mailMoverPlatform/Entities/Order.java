package tqs.project.mailMoverPlatform.Entities;

import org.springframework.data.annotation.Id;
import javax.persistence.GeneratedValue;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orders")
public class Order {
    
    @Id
    @GeneratedValue
    private String id;
    private String tracking_number;
    private String client_name;
    private String status;  // STORE -> COURIER -> ACP_POINT -> COLLECTED
    private Long storePickUpDate;
    private Long acpDeliveryDate;
    private Long clientPickUpDate;
    private String acpId;
    
    public Order(String tracking_number, String client_name, String acpId) {
        this.tracking_number = tracking_number;
        this.client_name = client_name;
        this.acpId = acpId;
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
    public void setId(String id) {
        this.id = id;
    }
    public String getTracking_number() {
        return tracking_number;
    }
    public void setTracking_number(String tracking_number) {
        this.tracking_number = tracking_number;
    }
    public String getClient_name() {
        return client_name;
    }
    public void setClient_name(String client_name) {
        this.client_name = client_name;
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
