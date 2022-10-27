package com.example.demo.Twilio.SMS;

import com.example.demo.Twilio.TwilioConfig;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsService implements SmsSender {

    private final TwilioConfig twilioConfig;

    @Autowired
    public SmsService(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }

    @Override
    public void smsSender(SmsRequest smsRequest) {
        MessageCreator creator = Message.creator(
                new PhoneNumber(smsRequest.getTo()),
                new PhoneNumber(""),
                smsRequest.getSms()
        );

    }
}
