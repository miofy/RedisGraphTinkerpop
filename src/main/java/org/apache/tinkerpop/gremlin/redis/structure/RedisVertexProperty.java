package org.apache.tinkerpop.gremlin.redis.structure;

import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Property;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.VertexProperty;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RedisVertexProperty<V> implements VertexProperty<V> {
    public String key() {
        return null;
    }

    public V value() throws NoSuchElementException {
        return null;
    }

    public boolean isPresent() {
        return false;
    }

    public Vertex element() {
        return null;
    }

    public Graph graph() {
        return null;
    }

    public String label() {
        return null;
    }

    public void remove() {

    }

    public Object id() {
        return null;
    }

    public <V> Property<V> property(String s, V v) {
        return null;
    }

    public <U> Iterator<Property<U>> properties(String... strings) {
        return null;
    }
}
