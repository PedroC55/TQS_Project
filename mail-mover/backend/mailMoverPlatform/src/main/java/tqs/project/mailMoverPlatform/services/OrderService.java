package tqs.project.mailMoverPlatform.services;

import java.util.List;

import tqs.project.mailMoverPlatform.entities.ACP;
import tqs.project.mailMoverPlatform.entities.Order;

public interface OrderService {
    public List<Order> getAllOrders();
    public Order getById(Long id);
    public List<Order> getByAcp(ACP acp);
    public Order getByAcpAndId(ACP acp, Long id);
    public Order addOrder(Order order);
    public boolean changeState_STORE_to_COURIER(Long id, Long ts);
    public boolean changeState_COURIER_to_ACPPOINT(Long id, Long ts);
    public boolean changeState_ACPPOINT_to_COLLECTED(Long id, Long ts);
}
