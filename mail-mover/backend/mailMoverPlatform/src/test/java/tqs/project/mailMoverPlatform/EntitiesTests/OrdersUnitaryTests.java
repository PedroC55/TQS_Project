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
    void status_fromStoreToCourier_with_timestamp_sets_storePickUpDate() throws Exception {
        String client_name = "John Doe";
        String acpId = "ACP1";
        Order order = new Order(client_name, acpId);

        Long expectedStorePickUpDate = 123456789L;
        order.status_fromStoreToCourier(expectedStorePickUpDate);

        String expectedStatus = "COURIER";
        String actualStatus = order.getStatus();

        Long actualStorePickUpDate = order.getStorePickUpDate();

        assertEquals(expectedStatus, actualStatus);
        assertEquals(expectedStorePickUpDate, actualStorePickUpDate);
    }

    @Test
    void status_fromStoreToCourier_without_timestamp_sets_current_time_as_storePickUpDate() throws Exception {
        String client_name = "John Clarke";
        String acpId = "ACP1";
        Order order = new Order(client_name, acpId);

        order.status_fromStoreToCourier(null);

        String expectedStatus = "COURIER";
        String actualStatus = order.getStatus();

        Long expectedStorePickUpDate = System.currentTimeMillis();
        Long actualStorePickUpDate = order.getStorePickUpDate();

        assertEquals(expectedStatus, actualStatus);
        assertEquals(expectedStorePickUpDate, actualStorePickUpDate);
    }

    @Test
    void status_fromCourierToAcpPoint_with_timestamp_sets_acpDeliveryDate() throws Exception {
        String client_name = "John Clarke";
        String acpId = "ACP1";
        Order order = new Order(client_name, acpId);
        
        Long expectedAcpDeliveryDate = 123456789L;
        order.status_fromStoreToCourier(expectedAcpDeliveryDate);
        order.status_fromCourierToAcpPoint(expectedAcpDeliveryDate);
        
        String expectedStatus = "ACP_POINT";
        String actualStatus = order.getStatus();

        Long actualAcpDeliveryDate = order.getAcpDeliveryDate();
        
        assertEquals(expectedStatus, actualStatus);
        assertEquals(expectedAcpDeliveryDate, actualAcpDeliveryDate);
    }

    @Test
    void status_fromCourierToAcpPoint_without_timestamp_sets_current_time_as_acpDeliveryDate() throws Exception {
        String client_name = "John Clarke";
        String acpId = "ACP1";
        Order order = new Order(client_name, acpId);
        
        order.status_fromStoreToCourier(null);
        order.status_fromCourierToAcpPoint(null);
        
        String expectedStatus = "ACP_POINT";
        String actualStatus = order.getStatus();

        Long expectedAcpDeliveryDate = System.currentTimeMillis();
        Long actualAcpDeliveryDate = order.getAcpDeliveryDate();
        
        assertEquals(expectedStatus, actualStatus);
        assertEquals(expectedAcpDeliveryDate, actualAcpDeliveryDate);
    }

    @Test
    void status_fromAcpPointToCollected_with_timestamp_sets_clientPickUpDate() throws Exception {
        String client_name = "John Clarke";
        String acpId = "ACP1";
        Order order = new Order(client_name, acpId);

        Long expectedClientPickUpDate = 123456789L;
        order.status_fromStoreToCourier(expectedClientPickUpDate);
        order.status_fromCourierToAcpPoint(expectedClientPickUpDate);
        order.status_fromAcpPointToCollected(expectedClientPickUpDate);

        String expectedStatus = "COLLECTED";
        String actualStatus = order.getStatus();

        Long actualClientPickUpDate = order.getClientPickUpDate();

        assertEquals(expectedStatus, actualStatus);
        assertEquals(expectedClientPickUpDate, actualClientPickUpDate);
    }
    
    @Test
    void status_fromAcpPointToCollected_without_timestamp_sets_current_time_as_clientPickUpDate() throws Exception {
        String client_name = "John Clarke";
        String acpId = "ACP1";
        Order order = new Order(client_name, acpId);

        order.status_fromStoreToCourier(null);
        order.status_fromCourierToAcpPoint(null);
        order.status_fromAcpPointToCollected(null);

        String expectedStatus = "COLLECTED";
        String actualStatus = order.getStatus();

        Long expectedClientPickUpDate = System.currentTimeMillis();
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
