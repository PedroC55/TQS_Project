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
        return null;
    }
    @Override
    public List<Order> getByAcp(ACP acp, String trackingNumber) {
        return null;
    }
    @Override
    public Order getByTrackingNumber(String trackingNumber){
        return null;
    }
    @Override
    public Order addOrder(Order order){
        return null;
    }
    @Override
    public boolean changeState_STORE_to_COURIER(String trackingNumber, Long ts){
        return false;
    }
    @Override
    public boolean changeState_COURIER_to_ACPPOINT(String trackingNumber, Long ts){
        return false;
    }
    @Override
    public boolean changeState_ACPPOINT_to_COLLECTED(String trackingNumber, Long ts){
        return false;
    }

    
}
