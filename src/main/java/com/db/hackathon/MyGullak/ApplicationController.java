package com.db.hackathon.MyGullak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public String createOrder(@RequestParam int amount, @RequestParam String currency) throws Exception {
         return paymentGatewayService.makePayment(amount, currency, "1234");
    }

    @PostMapping(value = "/message", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void sendMessage(@RequestParam String paymentId) {
        messageSenderService.sendMessage(paymentId);
    }
}
