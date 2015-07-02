package com.kata.orderme.price;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class UnitPriceTest {
    @Test
    public void createPricingObjectsCheckInvariants() {
        PricingRule p = new UnitPrice('A', 47);

        assertThat(p.getAmount(), is(1));
        assertThat(p.getItem(), is('A'));
        assertThat(p.getPrice(), is(47));

        assertTrue(p.equals(new UnitPrice('A', 47)));
        assertFalse(p.equals(new UnitPrice('A', 48)));
    }

}
