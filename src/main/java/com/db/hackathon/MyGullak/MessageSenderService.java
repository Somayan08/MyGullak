package com.db.hackathon.MyGullak;

import com.db.hackathon.MyGullak.model.MessageRequest;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageSenderService {

    @Autowired
    private TwilioConfiguration twilioConfiguration;

    public void sendMessage(MessageRequest messageRequest) {
        String fromNumber = "whatsapp:" + twilioConfiguration.getTrialNumber();
        String toNumber = "whatsapp:" + messageRequest.getPhNumber();
        Message.creator(
                new PhoneNumber(toNumber),
                new PhoneNumber(fromNumber),
                messageRequest.getMessage())
                .create();
//        Message message = Message.creator(
//                new com.twilio.type.PhoneNumber("whatsapp:+918910639767"),
//                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
//                "Your message")
//                .create();

    }
}
