package org.example.shorterner.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Denis Zolotarev
 */
public class HashMapStorageStrategy implements StorageStrategy {
    private final HashMap<Long, String> data = new HashMap<>();

    @Override
    public boolean containsKey(Long key) {
        return data.containsKey(key);
    }

    @Override
    public boolean containsValue(String value) {
        return data.containsValue(value);
    }

    @Override
    public void put(Long key, String value) {
        data.put(key, value);
    }

    @Override
    public Long getKey(String value) {
        return data
                .entrySet()
                .stream()
                .filter(entry -> value.equals(entry.getValue()))
                .map(Map.Entry::getKey).findFirst().orElse(null);
    }

    @Override
    public String getValue(Long key) {
        return data.get(key);
    }
}
