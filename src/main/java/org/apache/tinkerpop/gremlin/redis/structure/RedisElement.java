package org.apache.tinkerpop.gremlin.redis.structure;

import org.apache.tinkerpop.gremlin.structure.Element;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Property;

import java.util.Iterator;

public class RedisElement implements Element {
    public Object id() {
        return null;
    }

    public String label() {
        return null;
    }

    public Graph graph() {
        return null;
    }

    public <V> Property<V> property(String key) {
        return null;
    }

    public <V> Property<V> property(String s, V v) {
        return null;
    }

    public void remove() {

    }

    public <V> Iterator<? extends Property<V>> properties(String... strings) {
        return null;
    }
}
