package com.kata.orderme.price;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UnitPriceTest {
    @Test
    public void createPricingObjectsCheckInvariants() {
        final PricingRule p = new UnitPrice('A', 47);

        assertThat(p.getAmount(), is(1));
        assertThat(p.getItem(), is('A'));
        assertThat(p.getPrice(), is(47));

        assertTrue(p.equals(new UnitPrice('A', 47)));
        assertFalse(p.equals(new UnitPrice('A', 48)));
    }

}
