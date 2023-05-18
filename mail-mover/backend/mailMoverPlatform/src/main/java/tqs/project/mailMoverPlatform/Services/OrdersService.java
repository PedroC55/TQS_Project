package tqs.project.mailMoverPlatform.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import tqs.project.mailMoverPlatform.Entities.Order;
import tqs.project.mailMoverPlatform.Repositories.OrdersRepository;

@Service
public class OrdersService {
    @Autowired
    OrdersRepository repository;

    @Autowired
    MongoTemplate mongoTemplate;
    
    public List<Order> getAllOrders(){

    }
    public List<Order> search(String acpId, String trackingNumber, String status ){
        /*Query query = new Query();
        query.addCriteria(Criteria.where(acpId).is(acpId));
        query.addCriteria(Criteria.where(acpId).is(acpId));

        return mongoTemplate.find(query,Order.class);*/
    }
    
    public String addOrder(Order order){

    }

    public boolean changeState_STORE_to_COURIER(String trackingNumber, Long ts){

    }

    public boolean changeState_COURIER_to_ACPPOINT(String trackingNumber, Long ts){

    }

    public boolean changeState_ACPPOINT_to_COLLECTED(String trackingNumber, Long ts){

    }
    
}
