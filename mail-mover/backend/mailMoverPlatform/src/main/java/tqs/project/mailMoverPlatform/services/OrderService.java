package tqs.project.mailMoverPlatform.services;

import java.util.List;

import tqs.project.mailMoverPlatform.entities.ACP;
import tqs.project.mailMoverPlatform.entities.Order;

public interface OrderService {
    public List<Order> getAllOrders();
    public Order getByTrackingNumber(String trackingNumber);
    public List<Order> getByAcp(ACP acp);
    public Order getByAcpAndTrackingNumber(ACP acp, String trackingNumber);
    public Order addOrder(Order order);
    public boolean changeState_STORE_to_COURIER(String trackingNumber, Long ts);
    public boolean changeState_COURIER_to_ACPPOINT(String trackingNumber, Long ts);
    public boolean changeState_ACPPOINT_to_COLLECTED(String trackingNumber, Long ts);
}
