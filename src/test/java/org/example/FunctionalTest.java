package org.example;

import org.example.shorterner.Shortener;
import org.example.shorterner.strategy.*;
import org.junit.Ignore;
import org.junit.Test;


import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


/**
 * @author Denis Zolotarev
 */
public class FunctionalTest {

    public void testStorage(Shortener shortener) {
        String testString1 = "Test string 1";
        String testString2 = "Test string 2";
        String testString3 = "Test string 1";

        Long testId1 = shortener.getId(testString1);
        Long testId2 = shortener.getId(testString2);
        Long testId3 = shortener.getId(testString3);

        assertEquals(testString1, shortener.getString(testId1));
        assertEquals(testString2, shortener.getString(testId2));
        assertEquals(testString3, shortener.getString(testId3));

        assertNotEquals(testId1, testId2);
        assertNotEquals(testId2, testId3);
        assertEquals(testId1, testId3);
    }

    @Test
    public void testHashMapStorageStrategy() {
        StorageStrategy strategy = new HashMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy() {
        StorageStrategy strategy = new OurHashMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Ignore
    @Test
    public void testFileStorageStrategy() {
        StorageStrategy strategy = new FileStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testHashBiMapStorageStrategy() {
        StorageStrategy strategy = new HashBiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy() {
        StorageStrategy strategy = new DualHashBidiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy() {
        StorageStrategy strategy = new HashBiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }
}
