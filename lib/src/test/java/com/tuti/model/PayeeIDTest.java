package com.tuti.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PayeeIDTest {

    @Test
    void getName() {
        PayeeID id = PayeeID.Zain;
        assertEquals("0010010002", id.getName());
    }
}