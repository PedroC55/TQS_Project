package tqs.project.mailMoverPlatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tqs.project.mailMoverPlatform.services.AcpServiceImpl;


@RestController
@RequestMapping("v1/acp")
public class AcpController {
    
    @Autowired
    private AcpServiceImpl service;


    @GetMapping("")
    public Page<Order> getOrders() {

    }
}
