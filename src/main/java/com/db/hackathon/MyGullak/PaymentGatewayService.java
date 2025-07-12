package com.db.hackathon.MyGullak;

import com.db.hackathon.MyGullak.model.MessageRequest;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentGatewayService {

    @Value("${razorpay.api.key}")
    private String apiKey;

    @Value("${razorpay.api.secret}")
    private String apiSecret;

    @Autowired
    private MessageSenderService messageSenderService;

    public String makePayment(int amount, String currency, String receiptId) throws Exception {
        RazorpayClient client = new RazorpayClient(apiKey, apiSecret);
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount",amount*100);
        orderRequest.put("currency", currency);
        orderRequest.put("receipt", receiptId);

        Order order = client.orders.create(orderRequest);


//        MessageRequest messageRequest = new MessageRequest();
//        messageRequest.setPhNumber("+918910639767");
//        messageRequest.setMessage("Payment made successfully! Order Id: " + order.get("id"));
//
//        messageSenderService.sendMessage(messageRequest);

        return order.toString();
    }
}
