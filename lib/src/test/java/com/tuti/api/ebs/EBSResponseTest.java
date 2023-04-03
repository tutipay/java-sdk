package com.tuti.api.ebs;

import static org.junit.jupiter.api.Assertions.*;

import com.tuti.model.PayeeID;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

class EBSResponseTest {

    @Test
    void getDueAmount() {
        // Initiating the EBSResponse object
        EBSResponse ebsResponse = new EBSResponse();
        HashMap<String, String> map = new HashMap<>();
        map.put("totalAmount", "100");
        //ebsResponse.setBillInfo(map);
        assertEquals("100", ebsResponse.getDueAmount(PayeeID.ZainPostpaid));
    }
}