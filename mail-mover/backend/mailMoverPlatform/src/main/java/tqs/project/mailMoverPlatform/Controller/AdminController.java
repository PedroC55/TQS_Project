package tqs.project.mailMoverPlatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tqs.project.mailMoverPlatform.services.AdminsService;

@RestController
@RequestMapping("v1/admin")
public class AdminController {
    @Autowired
    private AdminsService service;
}
