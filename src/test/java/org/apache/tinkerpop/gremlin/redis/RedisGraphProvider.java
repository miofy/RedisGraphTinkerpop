package org.apache.tinkerpop.gremlin.redis;

import com.redislabs.redisgraph.RedisGraphAPI;
import org.apache.commons.configuration.Configuration;
import org.apache.tinkerpop.gremlin.AbstractGraphProvider;
import org.apache.tinkerpop.gremlin.LoadGraphWith;
import org.apache.tinkerpop.gremlin.redis.structure.*;
import org.apache.tinkerpop.gremlin.structure.Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RedisGraphProvider extends AbstractGraphProvider{

    private static final Set<Class> IMPLEMENTATIONS = new HashSet<Class>() {{
        add(RedisEdge.class);
        add(RedisElement.class);
        add(RedisGraph.class);
        add(RedisProperty.class);
        add(RedisVertex.class);
        add(RedisVertexProperty.class);
    }};


    @Override
    public Map<String, Object> getBaseConfiguration(String graphName, Class<?> test, String testMethodName, LoadGraphWith.GraphData graphData) {
        return new HashMap<String, Object>() {{
            put(Graph.GRAPH, RedisGraph.class.getName());
        }};
    }

    @Override
    public void clear(Graph graph, Configuration configuration) throws Exception {
        if (null != graph) {
            RedisGraph g = (RedisGraph) graph;
            RedisGraphAPI api = g.getRedisGraphAPI();
            api.deleteGraph();
        }
    }

    @Override
    public Set<Class> getImplementations() {
        return IMPLEMENTATIONS;
    }
}
