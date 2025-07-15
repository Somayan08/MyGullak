package com.db.hackathon.MyGullak;

import com.db.hackathon.MyGullak.model.MessageRequest;
import com.twilio.rest.api.v2010.account.Message;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;

@Service
public class MessageSenderService {

    @Autowired
    private TwilioConfiguration twilioConfiguration;

    private String message =
                                    "Hi,\n" +
                                    "\n" +
                                    "Greetings from MyGullak team!\n" +
                                    "Your payment is successful, keep the transaction ID safe for any future reference: {{1}}.\n" +
                                    "\n" +
                                    "Have a nice day!";

    public void sendMessage(String paymentId) {
        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setPhNumber("+918910639767");
        messageRequest.setMessage(StringUtils.replace(message, "txnID", paymentId));

        String fromNumber = "whatsapp:" + twilioConfiguration.getTrialNumber();
        String toNumber = "whatsapp:" + messageRequest.getPhNumber();
//        Message.creator(
//                new PhoneNumber(toNumber),
//                new PhoneNumber(fromNumber),
//                messageRequest.getMessage())
//                .create();
//        Message message = Message.creator(
//                new com.twilio.type.PhoneNumber("whatsapp:+918910639767"),
//                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
//                "Your message")
//                .create();

        Message
                .creator(new com.twilio.type.PhoneNumber(toNumber),
                        new com.twilio.type.PhoneNumber(fromNumber),
                        message)
                .setContentSid("HXbe124b72e9ecaab694239fb419385759")
                .setContentVariables(new JSONObject(new HashMap<String, Object>() {
                    {
                        put("1", paymentId);
                    }
                }).toString())
                .setMessagingServiceSid("MG8e9b8f3e8aa8c4c17e9abae949eb53ad")
                .create();

    }
}
