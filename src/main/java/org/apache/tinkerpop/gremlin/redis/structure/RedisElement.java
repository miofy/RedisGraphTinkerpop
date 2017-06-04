package org.apache.tinkerpop.gremlin.redis.structure;

import org.apache.tinkerpop.gremlin.structure.Element;

public abstract class RedisElement implements Element {
    String id;
    String label;
    RedisGraph graph;

    public RedisElement(String id, String label, RedisGraph graph) {
        this.id = id;
        this.label = label;
        this.graph = graph;
    }

    public Object id() {
        return this.id;
    }

    public String label() {
        return this.label;
    }

    public RedisGraph graph() {
        return this.graph;
    }

    public void remove() {

    }
}
