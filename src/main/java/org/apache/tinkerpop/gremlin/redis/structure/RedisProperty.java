package org.apache.tinkerpop.gremlin.redis.structure;

import org.apache.tinkerpop.gremlin.structure.Element;
import org.apache.tinkerpop.gremlin.structure.Property;

import java.util.NoSuchElementException;

public class RedisProperty<V> implements Property<V> {

    protected final Element element;
    protected final String key;
    protected final RedisGraph graph;
    protected V value;

    public RedisProperty(final Element element, final String key, final V value) {
        this.element = element;
        this.key = key;
        this.value = value;
        this.graph = (RedisGraph)element.graph();
    }

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
