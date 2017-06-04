package org.apache.tinkerpop.gremlin.redis.structure;

import com.redislabs.redisgraph.*;
import org.apache.tinkerpop.gremlin.structure.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RedisVertex extends RedisElement implements Vertex {
    private RedisNode node;

    public RedisVertex(RedisGraph graph, RedisNode node) {
        super(node.getId(), node.getLabel(), graph);
        this.node = node;
    }

    public Edge addEdge(String label, Vertex inVertex, Object... keyValues) {
        RedisGraphAPI api = this.graph().getRedisGraphAPI();

        com.redislabs.redisgraph.RedisEdge edge =
                api.connectNodes(this.node,
                        label,
                        ((RedisVertex)inVertex).getNode(),
                        keyValues);

        RedisEdge e = new RedisEdge(this.graph, edge, this, (RedisVertex) inVertex);
        return e;
    }

    // Get vertex property
    public <V> VertexProperty<V> property(String key) {
        if(this.node.getAttributes().containsKey(key)) {
            String val = this.node.getAttributes().get(key);
            return new RedisVertexProperty(this, key, val);
        } else {
            return VertexProperty.empty();
        }
    }

    // Set vertex property
    public <V> VertexProperty<V> property(String key, V value) {
        RedisGraphAPI api = this.graph.redisGraphAPI;
        api.setProperty(this.id, key, value);

        // Update local node
        this.node.getAttributes().put(key, value.toString());

        return new RedisVertexProperty(this, key, value);
    }

    public <V> VertexProperty<V> property(String key, V value, Object... keyValues) {
        return this.property(key, value);
    }

    public <V> VertexProperty<V> property(VertexProperty.Cardinality cardinality, String key, V value, Object... objects) {
        return this.property(key, value);
    }

    public Iterator<Edge> edges(Direction direction, String... edgeLabels) {
        RedisGraphAPI api = this.graph.redisGraphAPI;
        List<Edge> edges = new ArrayList<>();
        for(String edgeLabel: edgeLabels) {
            for(com.redislabs.redisgraph.RedisEdge edge : api.getNodeEdges(this.id, edgeLabel, direction.ordinal())) {
                edges.add(new RedisEdge(this.graph, edge));
            }
        }

        return edges.iterator();
    }

    public Iterator<Vertex> vertices(Direction direction, String... edgeLabels) {
        RedisGraphAPI api = this.graph.redisGraphAPI;
        List<Vertex> neighbours = new ArrayList<>();
        for(String edgeLabel: edgeLabels) {
            for(RedisNode neighbour : api.getNeighbours(this.id, edgeLabel, direction.ordinal())) {
                neighbours.add(new RedisVertex(this.graph, neighbour));
            }
        }

        return neighbours.iterator();
    }

    public void remove() {

    }

    public <V> Iterator<VertexProperty<V>> properties(String... propertyKeys) {
        ArrayList<VertexProperty<V>> properties = new ArrayList<>();

        for (String key : propertyKeys) {
            if (this.node.getAttributes().containsKey(key)) {
                String val = this.node.getAttributes().get(key);
                properties.add(new RedisVertexProperty<V>(this, key, (V) val));
            }
        }

        return properties.iterator();
    }

    public RedisNode getNode() {
        return node;
    }
}
