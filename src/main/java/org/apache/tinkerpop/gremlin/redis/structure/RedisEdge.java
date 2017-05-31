package org.apache.tinkerpop.gremlin.redis.structure;

import org.apache.tinkerpop.gremlin.structure.*;

import java.util.Iterator;

public class RedisEdge implements Edge {
    public Iterator<Vertex> vertices(Direction direction) {
        return null;
    }

    public Vertex outVertex() {
        return null;
    }

    public Vertex inVertex() {
        return null;
    }

    public Iterator<Vertex> bothVertices() {
        return null;
    }

    public Object id() {
        return null;
    }

    public String label() {
        return null;
    }

    public Graph graph() {
        return null;
    }

    public <V> Property<V> property(String s, V v) {
        return null;
    }

    public void remove() {

    }

    public <V> Iterator<Property<V>> properties(String... strings) {
        return null;
    }
}
