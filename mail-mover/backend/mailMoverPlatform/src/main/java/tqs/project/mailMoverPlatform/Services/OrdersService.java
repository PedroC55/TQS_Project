package tqs.project.mailMoverPlatform.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tqs.project.mailMoverPlatform.repositories.OrdersRepository;
import tqs.project.mailMoverPlatform.entities.ACP;
import tqs.project.mailMoverPlatform.entities.Order;

@Service
public class OrdersService {
    @Autowired
    OrdersRepository repository;
    
    public List<Order> getAllOrders(){
        return null;
    }
    public List<Order> search(ACP acp, String trackingNumber, String status ){
       
        return null;
    }
    
    public String addOrder(Order order){
        return null;
    }

    public boolean changeState_STORE_to_COURIER(String trackingNumber, Long ts){
        return false;
    }

    public boolean changeState_COURIER_to_ACPPOINT(String trackingNumber, Long ts){
        return false;
    }

    public boolean changeState_ACPPOINT_to_COLLECTED(String trackingNumber, Long ts){
        return false;
    }
    
}
