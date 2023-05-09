package tqs.project.mailMoverPlatform.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tqs.project.mailMoverPlatform.Services.OrdersService;

@RestController
@RequestMapping("v1/orders")
public class OrdersController {
    @Autowired
    private OrdersService service;
    
}
