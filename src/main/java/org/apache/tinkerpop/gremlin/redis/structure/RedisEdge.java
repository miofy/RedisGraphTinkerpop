package org.apache.tinkerpop.gremlin.redis.structure;

import com.redislabs.redisgraph.RedisGraphAPI;
import org.apache.tinkerpop.gremlin.structure.*;

import java.util.ArrayList;
import java.util.Iterator;

public class RedisEdge extends RedisElement implements Edge  {

    com.redislabs.redisgraph.RedisEdge edge;
    Vertex src;
    Vertex dest;

    public RedisEdge(RedisGraph graph, com.redislabs.redisgraph.RedisEdge edge) {
        super(edge.getId(), edge.getRelation(), graph);
        this.edge = edge;
        this.src = new RedisVertex(graph, edge.getSrc());
        this.dest = new RedisVertex(graph, edge.getDest());
    }

    public RedisEdge(RedisGraph graph, com.redislabs.redisgraph.RedisEdge edge, RedisVertex src, RedisVertex dest) {
        super(edge.getId(), edge.getRelation(), graph);
        this.edge = edge;
        this.src = src;
        this.dest = dest;
    }

    public Iterator<Vertex> vertices(Direction direction) {
        ArrayList<Vertex> vertices = new ArrayList<>();
        switch (direction) {
            case IN:
                vertices.add(this.dest);
            case OUT:
                vertices.add(this.src);
            case BOTH:
                vertices.add(this.src);
                vertices.add(this.dest);
        }
        return vertices.iterator();
    }

    public Vertex outVertex() {
        return this.src;
    }

    public Vertex inVertex() {
        return this.dest;
    }

    public Iterator<Vertex> bothVertices() {
        ArrayList<Vertex> vertices = new ArrayList<>();
        vertices.add(this.src);
        vertices.add(this.dest);
        return vertices.iterator();
    }

    public void remove() {

    }

    public <V> Property<V> property(String key) {
        if(this.edge.getAttributes().containsKey(key)) {
            String val = this.edge.getAttributes().get(key);
            return new RedisProperty(this, key, (V) val);
        } else {
            return Property.empty();
        }
    }

    public <V> Iterator<Property<V>> properties(final String... propertyKeys) {
        ArrayList<Property<V>> properties = new ArrayList<>();

        for (String key : propertyKeys) {
            if (this.edge.getAttributes().containsKey(key)) {
                String val = this.edge.getAttributes().get(key);
                properties.add(new RedisProperty(this, key, (V) val));
            }
        }

        return properties.iterator();
    }

    public <V> Property<V> property(String key, V value) {
        RedisGraphAPI api = this.graph.redisGraphAPI;
        api.setProperty(this.id, key, value);

        // Update local edge
        this.edge.getAttributes().put(key, value.toString());

        return new RedisProperty(this, key, value);
    }
}
