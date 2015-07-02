package com.kata.orderme.price;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Price calculation implementation where
 * each given item can only have one simple and one special price.
 */
public class SimplePriceManager implements PriceManager {
    private static final Integer DEFAULT_PRICE = 0;

    // simplified model: only one type of price exists per item
    private final Map<Character, PricingRule> complexRules = Maps.newHashMap();
    private final Map<Character, PricingRule> simpleRules = Maps.newHashMap();

    @SuppressWarnings("unchecked")
	@Override
    public Map<Character, List<PricingRule>> getPriceRules() {
        // merge both maps
        final Map<Character, List<PricingRule>> mergedRules = Maps.newHashMap();

        for (final Map<Character, PricingRule> map : Lists.newArrayList(complexRules, simpleRules)) {
            map.forEach((item, simpleRule) -> {
                        if (mergedRules.containsKey(item)) {
                            List<PricingRule> rulesPerKey = mergedRules.get(item);
                            // in case we do not have any rules for the current item we need to initialize it properly
                            if (rulesPerKey == null) {
                                rulesPerKey = Lists.newArrayList(simpleRule);
                            } else {
                                rulesPerKey.add(simpleRule);
                            }

                            mergedRules.put(item, rulesPerKey);
                        } else {
                            mergedRules.put(item, Lists.newArrayList(simpleRule));
                        }
                    }
            );
        }
        return mergedRules;
    }

    @Override
    public void addPricingRule(PricingRule price) {
        // TODO Since none of the rules are checked semantically or for consistency
        // you may add useless rules such as:
        // 1 costs 2, 3 cost 10 that may yield to unexpected results when buying 4 pieces.
        final Character item = price.getItem();

        // add a pricing rule, overwrite any existing one
        if (price.getAmount() == 1) {
            simpleRules.put(item, price);
        } else {
            complexRules.put(item, price);
        }
    }

    @Override
    public Integer getPrice(final Character item, final Integer amount) {
        // Handle trivial cases first
        // CASE: unknown item
        if (!complexRules.containsKey(item) && !simpleRules.containsKey(item)) {
            return DEFAULT_PRICE;
        }

        // CASE: no special price available
        if (!complexRules.containsKey(item) && simpleRules.containsKey(item)) {
            return amount * simpleRules.get(item).getPrice();
        }

        // CASE: Calculate mix from offer price and regular price taken amount into account
        final Integer unitPrice = simpleRules.get(item).getPrice();
        final PricingRule specialPrice = complexRules.get(item);

        return (amount % specialPrice.getAmount()) * unitPrice
                + (amount / specialPrice.getAmount()) * specialPrice.getPrice();
    }

    @Override
    public String toString() {
        return "SimplePriceManager{" +
                "complexRules=" + complexRules +
                ", simpleRules=" + simpleRules +
                '}';
    }
}


