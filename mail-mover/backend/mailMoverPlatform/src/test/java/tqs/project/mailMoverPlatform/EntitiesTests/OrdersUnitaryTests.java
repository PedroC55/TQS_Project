package tqs.project.mailMoverPlatform.EntitiesTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import tqs.project.mailMoverPlatform.Entities.Order;

public class OrdersUnitaryTests {
    
    @Test
    void when_create_order_then_status_is_store(){
        String client_name = "John Clarke";
        String acpId = "ACP1";
        Order order = new Order(client_name, acpId);

        assertEquals("STORE", order.getStatus());
    }

    @Test
    void status_fromStoreToCourier_changes_status_and_storePickUpDate() throws Exception {
        String client_name = "John Clarke";
        String acpId = "ACP1";
        Order order = new Order(client_name, acpId);

        order.status_fromStoreToCourier(null);

        String expectedStatus = "COURIER";
        Long expectedStorePickUpDate = System.currentTimeMillis();
        String actualStatus = order.getStatus();
        Long actualStorePickUpDate = order.getStorePickUpDate();

        assertEquals(expectedStatus, actualStatus);
        assertEquals(expectedStorePickUpDate, actualStorePickUpDate);
    }

    @Test
    void status_fromCourierToAcpPoint_changes_status_and_acpDeliveryDate() throws Exception {
        String client_name = "John Clarke";
        String acpId = "ACP1";
        Order order = new Order(client_name, acpId);
        
        order.status_fromStoreToCourier(null);
        order.status_fromCourierToAcpPoint(null);
        
        String expectedStatus = "ACP_POINT";
        Long expectedAcpDeliveryDate = System.currentTimeMillis();
        String actualStatus = order.getStatus();
        Long actualAcpDeliveryDate = order.getAcpDeliveryDate();
        
        assertEquals(expectedStatus, actualStatus);
        assertEquals(expectedAcpDeliveryDate, actualAcpDeliveryDate);
    }
    
    @Test
    void status_fromAcpPointToCollected_changes_status_and_clientPickUpDate() throws Exception {
        String client_name = "John Clarke";
        String acpId = "ACP1";
        Order order = new Order(client_name, acpId);

        order.status_fromStoreToCourier(null);
        order.status_fromCourierToAcpPoint(null);
        order.status_fromAcpPointToCollected(null);

        String expectedStatus = "COLLECTED";
        Long expectedClientPickUpDate = System.currentTimeMillis();
        String actualStatus = order.getStatus();
        Long actualClientPickUpDate = order.getClientPickUpDate();

        assertEquals(expectedStatus, actualStatus);
        assertEquals(expectedClientPickUpDate, actualClientPickUpDate);
    }

    @Test
    void status_fromStoreToCourier_throws_exception() {
        String client_name = "John Clarke";
        String acpId = "ACP1";
        Order order = new Order(client_name, acpId);

        order.setStatus("COURIER");

        Exception exception = assertThrows(Exception.class, () -> {
            order.status_fromStoreToCourier(null);
        });

        String expectedMessage = "Expected status STORE not found";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void status_fromCourierToAcpPoint_throws_exception() {
        String client_name = "John Clarke";
        String acpId = "ACP1";
        Order order = new Order(client_name, acpId);

        Exception exception = assertThrows(Exception.class, () -> {
            order.status_fromCourierToAcpPoint(null);
        });

        String expectedMessage = "Expected status COURIER not found";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void status_fromAcpPointToCollected_throws_exception() {
        String client_name = "John Clarke";
        String acpId = "ACP1";
        Order order = new Order(client_name, acpId);

        Exception exception = assertThrows(Exception.class, () -> {
            order.status_fromAcpPointToCollected(null);
        });

        String expectedMessage = "Expected status ACP_POINT not found";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

}