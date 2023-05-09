package tqs.project.mailMoverPlatform;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import tqs.project.mailMoverPlatform.Entities.Order;

public class OrdersUnitaryTests {
    
    @Test
    void when_create_order_then_status_is_store(){

        String tracking_number = "123456";
        String client_name = "John Clarke";
        String acpId = "ACP1";
        Order order = new Order(tracking_number, client_name, acpId);

        assertEquals("STORE", order.getStatus());
    }

    @Test
    void status_fromStoreToCourier_changes_status_and_storePickUpDate() throws Exception {
        String tracking_number = "123456";
        String client_name = "John Clarke";
        String acpId = "ACP1";
        Order order = new Order(tracking_number, client_name, acpId);

        order.status_fromStoreToCourier();

        String expectedStatus = "COURIER";
        Long expectedStorePickUpDate = System.currentTimeMillis();
        String actualStatus = order.getStatus();
        Long actualStorePickUpDate = order.getStorePickUpDate();

        assertEquals(expectedStatus, actualStatus);
        assertEquals(expectedStorePickUpDate, actualStorePickUpDate);
    }

    @Test
    void status_fromCourierToAcpPoint_changes_status_and_acpDeliveryDate() throws Exception {
        String tracking_number = "123456";
        String client_name = "John Clarke";
        String acpId = "ACP1";
        Order order = new Order(tracking_number, client_name, acpId);
        
        order.status_fromStoreToCourier();
        order.status_fromCourierToAcpPoint();
        
        String expectedStatus = "ACP_POINT";
        Long expectedAcpDeliveryDate = System.currentTimeMillis();
        String actualStatus = order.getStatus();
        Long actualAcpDeliveryDate = order.getAcpDeliveryDate();
        
        assertEquals(expectedStatus, actualStatus);
        assertEquals(expectedAcpDeliveryDate, actualAcpDeliveryDate);
    }
    
    @Test
    void status_fromAcpPointToCollected_changes_status_and_clientPickUpDate() throws Exception {
        String tracking_number = "123456";
        String client_name = "John Clarke";
        String acpId = "ACP1";
        Order order = new Order(tracking_number, client_name, acpId);

        order.status_fromStoreToCourier();
        order.status_fromCourierToAcpPoint();
        order.status_fromAcpPointToCollected();

        String expectedStatus = "COLLECTED";
        Long expectedClientPickUpDate = System.currentTimeMillis();
        String actualStatus = order.getStatus();
        Long actualClientPickUpDate = order.getClientPickUpDate();

        assertEquals(expectedStatus, actualStatus);
        assertEquals(expectedClientPickUpDate, actualClientPickUpDate);
    }

}
