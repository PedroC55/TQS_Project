package tqs.project.mailMoverPlatform.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tqs.project.mailMoverPlatform.entities.Order;
import tqs.project.mailMoverPlatform.services.OrderServiceImpl;

@RestController
@RequestMapping("v1/orders")
public class OrdersController {
    @Autowired
    private OrderServiceImpl service;

    @PostMapping("/new")
    public ResponseEntity<Order> createACP(@RequestBody Order newOrder) {
        Order ret_order = service.addOrder(newOrder);
        if (ret_order == null) {
            return new ResponseEntity<Order>(ret_order, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(ret_order, HttpStatus.CREATED);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllAcps() {
        List<Order> allAcps = service.getAllOrders();
        return new ResponseEntity<>(allAcps, HttpStatus.OK);
    }
    
}
