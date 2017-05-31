package org.apache.tinkerpop.gremlin.redis.structure;

import org.apache.tinkerpop.gremlin.structure.Element;
import org.apache.tinkerpop.gremlin.structure.Property;

import java.util.NoSuchElementException;

public class RedisProperty<V> implements Property<V> {
    public String key() {
        return null;
    }

    public V value() throws NoSuchElementException {
        return null;
    }

    public boolean isPresent() {
        return false;
    }

    public Element element() {
        return null;
    }

    public void remove() {

    }
}
