package org.apache.tinkerpop.gremlin.redis.structure;

import com.redislabs.redisgraph.RedisNode;
import org.apache.tinkerpop.gremlin.structure.*;

import java.util.Iterator;

public class RedisVertex implements Vertex {
    RedisNode node;

    public RedisVertex(RedisNode node) {
        this.node = node;
    }

    public Edge addEdge(String s, Vertex vertex, Object... objects) {
        return null;
    }

    public <V> VertexProperty<V> property(String key) {
        return null;
    }

    public <V> VertexProperty<V> property(String key, V value) {
        return null;
    }

    public <V> VertexProperty<V> property(String key, V value, Object... keyValues) {
        return null;
    }

    public <V> VertexProperty<V> property(VertexProperty.Cardinality cardinality, String s, V v, Object... objects) {
        return null;
    }

    public Iterator<Edge> edges(Direction direction, String... strings) {
        return null;
    }

    public Iterator<Vertex> vertices(Direction direction, String... strings) {
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

    public void remove() {

    }

    public <V> Iterator<VertexProperty<V>> properties(String... strings) {
        return null;
    }
}
