package com.tuti.api.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PaymentTokenTest {

    @Test
    void parseQRToken() {
        PaymentToken pt = PaymentToken.ParseQRToken("eyJJRCI6MzksIkNyZWF0ZWRBdCI6IjIwMjItMDgtMTdUMTE6MzY6NTQuOTMzNzAxNjM0WiIsIlVwZGF0ZWRBdCI6IjIwMjItMDgtMTdUMTE6MzY6NTQuOTMzNzAxNjM0WiIsIkRlbGV0ZWRBdCI6bnVsbCwiVXNlcklEIjoyLCJhbW91bnQiOjEwMDAsInV1aWQiOiJiNjZmYmQyNS1mYjI2LTRjYjEtYjkxNy1iNjJmMWQzY2FiYTYiLCJub3RlIjoiVGhpcyBpcyB3b3JraW5nIiwidG9DYXJkIjoiMzI4OTMyOTgzOTgyOTgzMjk4MyIsInRyYW5zYWN0aW9uIjp7IklEIjowLCJDcmVhdGVkQXQiOiIwMDAxLTAxLTAxVDAwOjAwOjAwWiIsIlVwZGF0ZWRBdCI6IjAwMDEtMDEtMDFUMDA6MDA6MDBaIiwiRGVsZXRlZEF0IjpudWxsLCJVc2VySUQiOjAsInJlc3BvbnNlQ29kZSI6MH0sIlRyYW5zYWN0aW9uSUQiOjAsImlzX3BhaWQiOmZhbHNlfQ==");
        assertEquals(pt.getAmount(), 1000);
    }
}