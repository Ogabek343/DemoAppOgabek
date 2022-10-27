package com.example.demo.Twilio.SMS;

public class SmsRequest {
    private String to;
    private String sms;

    public SmsRequest(String to, String sms) {
        this.to = to;
        this.sms = sms;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    @Override
    public String toString() {
        return "SmsRequest{" +
                "to='" + to + '\'' +
                ", sms='" + sms + '\'' +
                '}';
    }
}
