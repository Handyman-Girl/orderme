package com.kata.orderme.order;

import com.google.common.collect.Maps;
import com.kata.orderme.price.PriceManager;

import java.util.Map;

public class DefaultCheckOut implements CheckOut {

    private Map<Character, Integer> scannedItems;
    private PriceManager manager;

    public DefaultCheckOut(PriceManager manager) {
        this.manager = manager;
        this.scannedItems = Maps.newConcurrentMap();
    }

    @Override
    public void scan(final Character item) {
        if (scannedItems.containsKey(item)) {
            scannedItems.put(item, scannedItems.get(item) + 1);
        } else {
            scannedItems.put(item, 1);
        }
    }

    @Override
    public Integer getTotal() {
        Integer total = 0;

        for (Map.Entry<Character, Integer> entry : scannedItems.entrySet()) {
            total += manager.getPrice(entry.getKey(), entry.getValue());
        }

        return total;
    }

    @Override
    public String toString() {
        return "DefaultCheckOut{" +
                "scannedItems=" + scannedItems +
                ", manager=" + manager +
                '}';
    }
}
