package tqs.project.mailMoverPlatform.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tqs.project.mailMoverPlatform.entities.ACP;
import tqs.project.mailMoverPlatform.entities.Order;
import tqs.project.mailMoverPlatform.repositories.OrderRepository;

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
    public Order getById(Long id){
        Optional<Order> order = repository.findById(id);
        if (order.isPresent()){return order.get();}
        return null;
    }
    @Override
    public Order getByAcpAndId(ACP acp, Long id){
        return repository.findByAcpAndId(acp, id);
    }
    @Override
    public Order addOrder(Order order){
        return repository.save(order);
    }
    @Override
    public boolean changeState_STORE_to_COURIER(Long id, Long ts){
        Optional<Order> order = repository.findById(id);
        if (!order.isPresent()){return false;}
        try {
            Order newOrder = order.get();
            newOrder.status_fromStoreToCourier(ts);
            repository.save(newOrder);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    @Override
    public boolean changeState_COURIER_to_ACPPOINT(Long id, Long ts){
        Optional<Order> order = repository.findById(id);
        if (!order.isPresent()){return false;}
        try {
            Order newOrder = order.get();
            newOrder.status_fromCourierToAcpPoint(ts);
            repository.save(newOrder);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    @Override
    public boolean changeState_ACPPOINT_to_COLLECTED(Long id, Long ts){
        Optional<Order> order = repository.findById(id);
        if (!order.isPresent()){return false;}
        try {
            Order newOrder = order.get();
            newOrder.status_fromAcpPointToCollected(ts);
            repository.save(newOrder);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    public void deleteAll() {
        repository.deleteAll();
    }

    
}
