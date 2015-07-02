package com.kata.orderme.price;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Lists;

public class SimplePriceManagerTest {

    @Test
    public void createManager() {
        final PriceManager manager = new SimplePriceManager();
        assertNotNull(manager);
        assertTrue(manager.getPriceRules().isEmpty());
    }

    @Test
    public void addRuleFromGivenPricable() {
        final PriceManager manager = new SimplePriceManager();
        manager.addPricingRule(new UnitPrice('A', 4711));

        assertThat(manager.getPriceRules().size(), is(1));
        final PricingRule rule = manager.getPriceRules().get('A').get(0);
        assertNotNull(rule);
        assertThat(rule.getAmount(), is(1));
        assertThat(rule.getPrice(), is(4711));
    }

    @Test
    public void addMixedPricablesToRules() {
        final PriceManager manager = new SimplePriceManager();
        manager.addPricingRule(new UnitPrice('A', 4711));
        manager.addPricingRule(new SpecialPrice('A', 11, 3));

        final List<PricingRule> rules = manager.getPriceRules().get('A');
        assertNotNull(rules);
        assertEquals(2, rules.size());

        for (final PricingRule p : rules) {
            assertThat(p.getItem(), is('A'));
            if (p instanceof SpecialPrice) {
                assertThat(p.getAmount(), is(3));
                assertThat(p.getPrice(), is(11));
            } else {
                assertThat(p.getAmount(), is(1));
                assertThat(p.getPrice(), is(4711));
            }
        }
    }

    @Test
    public void addSimpleMixedPricablesToRulesAndCalculatePrices() {
        final PriceManager manager = new SimplePriceManager();
        manager.addPricingRule(new UnitPrice('A', 4711));
        manager.addPricingRule(new UnitPrice('C', 4712));
        manager.addPricingRule(new SpecialPrice('B', 45, 2));
        manager.addPricingRule(new UnitPrice('B', 30));

        final Map<Character, List<PricingRule>> pricingRules = manager.getPriceRules();
        assertNotNull(pricingRules);
        assertEquals(3, pricingRules.size());

        for (final Character item : Lists.newArrayList('A', 'B', 'C')) {
            assertTrue(pricingRules.containsKey(item));
        }
        assertEquals(2, pricingRules.get('B').size());

        // unknown values cost nothing :-D
        assertThat(manager.getPrice('U', 1), is(0));
        assertThat(manager.getPrice('u', 123), is(0));
        // verify calculation
        assertThat(manager.getPrice('A', 1), is(4711));
        assertThat(manager.getPrice('A', 2), is(2*4711));
        assertThat(manager.getPrice('C', 1), is(4712));

        // ensure discount :-D
        assertThat(manager.getPrice('B', 1), is(30));
        assertThat(manager.getPrice('B', 2), is(45));
        assertThat(manager.getPrice('B', 3), is(45 + 30));
        assertThat(manager.getPrice('B', 4), is(2 * 45));
        assertThat(manager.getPrice('B', 5), is(2 * 45 + 30));
        assertThat(manager.getPrice('B', 6), is(3 * 45));
    }


}
