package com.kata.orderme.price;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SpecialPriceTest {
    @Test
    public void createPricingObjectsCheckInvariants() {
        final PricingRule p = new SpecialPrice('A', 47, 2);

        assertThat(p.getAmount(), is(2));
        assertThat(p.getItem(), is('A'));
        assertThat(p.getPrice(), is(47));

        assertTrue(p.equals(new SpecialPrice('A', 47, 2)));
        assertFalse(p.equals(new SpecialPrice('A', 48, 2)));
        assertFalse(p.equals(new UnitPrice('A', 48)));
    }

}
