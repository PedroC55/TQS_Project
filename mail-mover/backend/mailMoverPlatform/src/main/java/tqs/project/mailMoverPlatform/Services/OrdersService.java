package tqs.project.mailMoverPlatform.Services;

import java.util.List;

import org.springframework.stereotype.Service;
import tqs.project.mailMoverPlatform.Entities.Order;

@Service
public interface OrdersService {

    public Order getOrderById(String id){
        
    }
    public List<Order> getAllOrders(){

    }
    public List<Order> getAllOrdersFromAcp(String acpId){

    }
    public Order getOrderByTrackingNumber(String trackingNumber){

    }
    public Order addOrder(Order order){

    }
    public boolean changeState_NaLoja_to_ComEstafeta(String trackingNumber, Long ts){

    }
    public boolean changeState_ComEstafeta_to_NoPontoACP(String trackingNumber, Long ts){

    }
    public boolean changeState_NoPontoACP_to_Entregue(String trackingNumber, Long ts){

    }
}
