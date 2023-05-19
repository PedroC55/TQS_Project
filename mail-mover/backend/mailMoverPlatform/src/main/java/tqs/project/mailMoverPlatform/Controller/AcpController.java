package tqs.project.mailMoverPlatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tqs.project.mailMoverPlatform.services.AcpService;


@RestController
@RequestMapping("v1/acp")
public class AcpController {
    @Autowired
    private AcpService service;

}
