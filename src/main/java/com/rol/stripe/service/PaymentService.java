package com.rol.stripe.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import jakarta.annotation.PostConstruct;



@Service
public class PaymentService {

    @Value("${stripe.apiKey}")
    private String stripeApiKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeApiKey;
    }

    public PaymentIntent createPaymentIntent(Map<String, Object> data) throws StripeException {
        Map<String, Object> params = new HashMap<>();
        params.put("amount", data.get("amount"));
        params.put("currency", "usd");
        params.put("payment_method_types", List.of("card"));

        return PaymentIntent.create(params);
    }
}

