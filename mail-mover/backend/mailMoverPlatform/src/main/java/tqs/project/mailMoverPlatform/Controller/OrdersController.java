package tqs.project.mailMoverPlatform.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tqs.project.mailMoverPlatform.services.OrderServiceImpl;

@RestController
@RequestMapping("v1/orders")
public class OrdersController {
    @Autowired
    private OrderServiceImpl service;
    
}
