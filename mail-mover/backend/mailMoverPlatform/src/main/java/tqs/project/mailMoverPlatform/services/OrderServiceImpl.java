package tqs.project.mailMoverPlatform.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tqs.project.mailMoverPlatform.repositories.OrderRepository;
import tqs.project.mailMoverPlatform.entities.ACP;
import tqs.project.mailMoverPlatform.entities.Order;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository repository;
    
    @Override
    public List<Order> getAllOrders(){
        return repository.findAll();
    }
    @Override
    public List<Order> getByAcp(ACP acp) {
        return repository.findByAcp(acp);
    }
    @Override
    public Order getByTrackingNumber(String trackingNumber){
        return repository.findByTrackingNumber(trackingNumber);
    }
    @Override
    public Order getByAcpAndTrackingNumber(ACP acp, String trackingNumber){
        return repository.findByAcpAndTrackingNumber(acp, trackingNumber);
    }
    @Override
    public Order addOrder(Order order){
        return repository.save(order);
    }
    @Override
    public boolean changeState_STORE_to_COURIER(String trackingNumber, Long ts){
        Order order = repository.findByTrackingNumber(trackingNumber);
        try {
            order.status_fromStoreToCourier(ts);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    @Override
    public boolean changeState_COURIER_to_ACPPOINT(String trackingNumber, Long ts){
        Order order = repository.findByTrackingNumber(trackingNumber);
        try {
            order.status_fromCourierToAcpPoint(ts);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    @Override
    public boolean changeState_ACPPOINT_to_COLLECTED(String trackingNumber, Long ts){
        Order order = repository.findByTrackingNumber(trackingNumber);
        try {
            order.status_fromAcpPointToCollected(ts);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    
}
