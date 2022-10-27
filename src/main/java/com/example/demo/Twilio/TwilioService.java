package com.example.demo.Twilio;

import com.example.demo.Twilio.SMS.SmsRequest;
import com.example.demo.Twilio.SMS.SmsService;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {
    private final SmsService service;

    public TwilioService(SmsService service) {
        this.service = service;
    }
    public void sendSms(SmsRequest request){
        service.smsSender(request);
    }
}
