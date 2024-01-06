package org.example;

import org.example.shorterner.Shortener;
import org.example.shorterner.strategy.HashBiMapStorageStrategy;
import org.example.shorterner.strategy.HashMapStorageStrategy;
import org.junit.jupiter.api.Test;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.example.shorterner.Helper.generateRandomString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * @author Denis Zolotarev
 */
public class SpeedTest {

    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        Date startTimestamp = new Date();
        Set<Long> idSet = strings.stream().map(shortener::getId).collect(Collectors.toSet());
        ids.addAll(idSet);
        Date endTimestamp = new Date();
        return endTimestamp.getTime() - startTimestamp.getTime();
//        Date startTimestamp = new Date();
//        for (String s : strings)
//            ids.add(shortener.getId(s));
//        Date endTimestamp = new Date();
//        return endTimestamp.getTime() - startTimestamp.getTime();
    }

    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        Date startTimestamp = new Date();
        Set<String> stringSet = ids.stream().map(shortener::getString).collect(Collectors.toSet());
        strings.addAll(stringSet);
        Date endTimestamp = new Date();
        return endTimestamp.getTime() - startTimestamp.getTime();
    }

    @Test
    public void testHashMapStorage() {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            origStrings.add(generateRandomString());
        }

        Set<Long> ids1 = new HashSet<>();
        Set<String> strings1 = new HashSet<>();
        long stringToIdTime1 = getTimeToGetIds(shortener1, origStrings, ids1);
        long idToStringTime1 = getTimeToGetStrings(shortener1, ids1, strings1);

        Set<Long> ids2 = new HashSet<>();
        Set<String> strings2 = new HashSet<>();
        long stringToIdTime2 = getTimeToGetIds(shortener2, origStrings, ids2);
        long idToStringTime2 = getTimeToGetStrings(shortener2, ids2, strings2);

        assertTrue(stringToIdTime1 > stringToIdTime2);
        assertEquals(idToStringTime1, idToStringTime2, 30);
    }
}
