package com.db.hackathon.MyGullak;

import com.db.hackathon.MyGullak.model.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ApplicationController {

    @Autowired
    private PaymentGatewayService paymentGatewayService;

    @Autowired
    private MessageSenderService messageSenderService;

    @GetMapping("/welcome")
    public String helloUsers(){
        return "Welcome to MyGullak";
    }

    @GetMapping("/")
    public String init(){
        return "index";
    }

    @PostMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
    public void createOrder(@RequestParam int amount, @RequestParam String currency) throws Exception {
         paymentGatewayService.makePayment(amount, currency, "1234");
    }

    @PostMapping(value = "/message", produces = MediaType.APPLICATION_JSON_VALUE)
    public void sendMessage(@RequestBody MessageRequest messageRequest) {
        messageSenderService.sendMessage(messageRequest);
    }
}
