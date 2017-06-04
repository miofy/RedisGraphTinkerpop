package org.apache.tinkerpop.gremlin.redis.structure;

import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Property;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.VertexProperty;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RedisVertexProperty<V> implements VertexProperty<V> {
    protected RedisVertex vertex;
    protected String key;
    protected V value;

    public RedisVertexProperty(final RedisVertex vertex, final String key, final V value) {
        this.vertex = vertex;
        this.key = key;
        this.value = value;
    }

    public String key() {
        return this.key;
    }

    public V value() throws NoSuchElementException {
        return this.value;
    }

    public boolean isPresent() {
        return null != this.value;
    }

    public Vertex element() {
        return this.vertex;
    }

    public Graph graph() {
        return this.vertex.graph();
    }

    public String label() {
        return this.vertex.label();
    }

    public void remove() {
        throw new UnsupportedOperationException("yet to support property removal");
    }

    public Object id() {
        return this.vertex.id();
    }

    public <V> Property<V> property(String s, V v) {
        throw new UnsupportedOperationException("yet to support property set");
    }

    public <U> Iterator<Property<U>> properties(String... strings) {

    }
}
