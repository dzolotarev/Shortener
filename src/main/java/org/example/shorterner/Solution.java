package org.example.shorterner;

import org.example.shorterner.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Denis Zolotarev
 */
public class Solution {
    public static void main(String[] args) {
        long elementsNumber = 10000;
        testStrategy(new HashMapStorageStrategy(), elementsNumber);
        testStrategy(new OurHashMapStorageStrategy(), elementsNumber);
        testStrategy(new FileStorageStrategy(), elementsNumber); //to slow to disk
        testStrategy(new OurHashBiMapStorageStrategy(), elementsNumber);
        testStrategy(new HashBiMapStorageStrategy(), elementsNumber);
        testStrategy(new DualHashBidiMapStorageStrategy(), elementsNumber);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        return strings.stream().map(shortener::getId).collect(Collectors.toSet());
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        return keys.stream().map(shortener::getString).collect(Collectors.toSet());
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName() + " :");
        Set<String> stringSet = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            stringSet.add(Helper.generateRandomString());
        }

        Shortener shortener = new Shortener(strategy);
        Date startTimestamp = new Date();
        Set<Long> keys = getIds(shortener, stringSet);
        Date endTimestamp = new Date();
        long time = endTimestamp.getTime() - startTimestamp.getTime();
        Helper.printMessage("Время получения идентификаторов для " + elementsNumber + " строк: " + time);

        startTimestamp = new Date();
        Set<String> strings = getStrings(shortener, keys);
        endTimestamp = new Date();
        time = endTimestamp.getTime() - startTimestamp.getTime();
        Helper.printMessage("Время получения строк для " + elementsNumber + " идентификаторов: " + time);

        if (stringSet.equals(strings)) {
            Helper.printMessage("Тест пройден.");
        } else {
            Helper.printMessage("Тест не пройден.");
        }
        Helper.printMessage("----------------------");
    }
}
