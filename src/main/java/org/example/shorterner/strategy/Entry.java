package org.example.shorterner.strategy;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Denis Zolotarev
 */
public class Entry implements Serializable {
    Long key;
    String value;
    Entry next;
    int hash;

    public Entry(int hash, Long key, String value, Entry next) {
        this.value = value;
        this.next = next;
        this.key = key;
        this.hash = hash;
    }

    public final Long getKey() {
        return key;
    }

    public final String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entry entry = (Entry) o;

        if (!Objects.equals(key, entry.key)) return false;
        return Objects.equals(value, entry.value);

    }

    public final int hashCode() {
        return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());

    }

    public final String toString() {
        return getKey() + "=" + getValue();
    }
}
