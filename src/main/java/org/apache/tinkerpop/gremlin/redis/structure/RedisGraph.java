package org.apache.tinkerpop.gremlin.redis.structure;

import com.redislabs.redisgraph.RedisNode;
import org.apache.commons.configuration.Configuration;
import org.apache.tinkerpop.gremlin.process.computer.GraphComputer;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Transaction;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import com.redislabs.redisgraph.RedisGraphAPI;
import java.util.Arrays;
import java.util.Iterator;

public class RedisGraph implements Graph {
    RedisGraphAPI client;

    public RedisGraph() {
        // TODO: are we getting graph name from graph open?
        this.client = new RedisGraphAPI("tinkerpop");
    }

    public Vertex addVertex(Object... objects) {
        Vertex vertex;

        if(objects.length%2 == 0) {
            RedisNode node = this.client.createNode(objects);
            vertex = new RedisVertex(node);
        } else {
            String label = objects[0].toString();
            // Discard first element
            Object[] attr = Arrays.copyOfRange(objects, 1, objects.length);

            RedisNode node = this.client.createLabeledNode(label, attr);
            vertex = new RedisVertex(node);
        }

        return vertex;
    }

    public <C extends GraphComputer> C compute(Class<C> aClass) throws IllegalArgumentException {
        throw Graph.Exceptions.graphComputerNotSupported();
    }

    public GraphComputer compute() throws IllegalArgumentException {
        throw Graph.Exceptions.graphComputerNotSupported();
    }

    public Iterator<Vertex> vertices(Object... objects) {
        return null;
    }

    public Iterator<Edge> edges(Object... objects) {
        return null;
    }

    public Transaction tx() {
        return null;
    }

    public void close() throws Exception {

    }

    public Variables variables() {
        return null;
    }

    public Configuration configuration() {
        return null;
    }

    public Features features() {
        return null;
    }
}
