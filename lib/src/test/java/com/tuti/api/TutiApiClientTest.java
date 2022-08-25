package com.tuti.api;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.UUID;

class TutiApiClientTest {

    @Test
    void getPaymentToken() {
        TutiApiClient tutiApiClient = new TutiApiClient();
        UUID uuid = UUID.randomUUID();
        tutiApiClient.getPaymentToken(uuid.toString(),null, null );

    }
}