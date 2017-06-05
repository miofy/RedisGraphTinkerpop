package org.apache.tinkerpop.gremlin.redis.structure;

import com.redislabs.redisgraph.RedisNode;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.tinkerpop.gremlin.process.computer.GraphComputer;
import org.apache.tinkerpop.gremlin.structure.*;
import com.redislabs.redisgraph.RedisGraphAPI;
import org.apache.tinkerpop.gremlin.structure.util.StringFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Graph.OptIn(Graph.OptIn.SUITE_STRUCTURE_STANDARD)
public class RedisGraph implements Graph {
    RedisGraphAPI redisGraphAPI;
    String id;
    protected Features features = new RedisGraphFeatures();
    public static final Logger LOGGER = LoggerFactory.getLogger(RedisGraph.class);

    private static final Configuration EMPTY_CONFIGURATION = new BaseConfiguration() {{
        this.setProperty(Graph.GRAPH, RedisGraph.class.getName());
    }};

    public static RedisGraph open() {
        return new RedisGraph("tinkerpop");
    }

    public static RedisGraph open(final Configuration configuration) {
        return new RedisGraph("tinkerpop");
    }

    public RedisGraph(String id) {
        this.id = id;
        this.redisGraphAPI = new RedisGraphAPI(id);
    }

    public RedisGraphAPI getRedisGraphAPI() {
        return this.redisGraphAPI;
    }

    public Vertex addVertex(Object... objects) {
        Vertex vertex;

        /* Vertex label is optional,
         * in case the number of arguments passed in is even
         * then label is not specified */
        if(objects.length%2 == 0) {
            RedisNode node = this.redisGraphAPI.createNode(objects);
            vertex = new RedisVertex(this, node);
        } else {
            String label = objects[0].toString();

            // Discard first element
            Object[] attr = Arrays.copyOfRange(objects, 1, objects.length);
            RedisNode node = this.redisGraphAPI.createLabeledNode(label, attr);
            vertex = new RedisVertex(this, node);
        }

        return vertex;
    }

    public Iterator<Vertex> vertices(Object... vertexIds) {
        List<Vertex> vertices = new ArrayList<Vertex>(vertexIds.length);

        for(Object vertexId: vertexIds) {
            RedisNode node = this.redisGraphAPI.getNode(vertexId.toString());
            vertices.add(new RedisVertex(this, node));
        }
        return vertices.iterator();
    }

    public Iterator<Edge> edges(Object... edgeIds) {
        List<Edge> edges = new ArrayList<Edge>(edgeIds.length);

        for(Object edgeId: edgeIds) {
            com.redislabs.redisgraph.RedisEdge edge = this.redisGraphAPI.getEdge(edgeId.toString());
            edges.add(new RedisEdge(this, edge));
        }
        return edges.iterator();
    }

    public <C extends GraphComputer> C compute(Class<C> aClass) throws IllegalArgumentException {
        throw Graph.Exceptions.graphComputerNotSupported();
    }

    public void close() throws Exception {

    }

    public GraphComputer compute() throws IllegalArgumentException {
        throw Graph.Exceptions.graphComputerNotSupported();
    }

    public Transaction tx() {
        throw Exceptions.transactionsNotSupported();
    }

    /* Redis graph doesn't have any variables associated with it */
    public Variables variables() {
        throw Graph.Exceptions.variablesNotSupported();
    }

    public Configuration configuration() {
        return EMPTY_CONFIGURATION;
    }

    public Features features() {
        return features;
    }

    public class RedisGraphFeatures implements Features {
        protected GraphFeatures graphFeatures = new RedisGraphGraphFeatures();
        protected VertexFeatures vertexFeatures = new RedisVertexFeatures();
        protected EdgeFeatures edgeFeatures = new RedisEdgeFeatures();

        @Override
        public GraphFeatures graph() {
            return graphFeatures;
        }

        @Override
        public VertexFeatures vertex() {
            return vertexFeatures;
        }

        @Override
        public EdgeFeatures edge() {
            return edgeFeatures;
        }

        @Override
        public String toString() {
            return StringFactory.featureString(this);
        }


        public class RedisGraphVariableFeatures implements Graph.Features.VariableFeatures {
            @Override
            public boolean supportsVariables() {
                return false;
            }
        }

        public class RedisGraphGraphFeatures implements GraphFeatures {
            private Features.VariableFeatures variableFeatures = new RedisGraphVariableFeatures();

            @Override
            public boolean supportsComputer() {
                return false;
            }

            @Override
            public boolean supportsTransactions() {
                return false;
            }

            @Override
            public Features.VariableFeatures variables() {
                return variableFeatures;
            }

            @Override
            public boolean supportsThreadedTransactions() {
                return false;
            }
        }

        public class RedisVertexFeatures extends RedisElementFeatures implements Features.VertexFeatures {

            private final Features.VertexPropertyFeatures vertexPropertyFeatures = new RedisVertexPropertyFeatures();

            protected RedisVertexFeatures() {
            }

            @Override
            public Features.VertexPropertyFeatures properties() {
                return vertexPropertyFeatures;
            }

            @Override
            public boolean supportsDuplicateMultiProperties() {
                return false;
            }

            @Override
            public boolean supportsMetaProperties() {
                return false;
            }

            @Override
            public boolean supportsUserSuppliedIds() {
                return false;
            }
        }

        public class RedisEdgeFeatures extends RedisElementFeatures implements Features.EdgeFeatures {

            private final Features.EdgePropertyFeatures edgePropertyFeatures = new RedisEdgePropertyFeatures();

            RedisEdgeFeatures() {
            }

            @Override
            public Features.EdgePropertyFeatures properties() {
                return edgePropertyFeatures;
            }
        }

        public class RedisElementFeatures implements Features.ElementFeatures {
            RedisElementFeatures() {
            }

            @Override
            public boolean supportsUserSuppliedIds() {
                return false;
            }

            @Override
            public boolean supportsNumericIds() {
                return false;
            }

            @Override
            public boolean supportsCustomIds() {
                return false;
            }
        }


        public class RedisVertexPropertyFeatures implements Features.VertexPropertyFeatures {
            RedisVertexPropertyFeatures() {

            }

            @Override
            public boolean supportsMapValues() {
                return false;
            }

            @Override
            public boolean supportsMixedListValues() {
                return false;
            }

            @Override
            public boolean supportsSerializableValues() {
                return false;
            }

            @Override
            public boolean supportsUniformListValues() {
                return false;
            }
        }

        public class RedisEdgePropertyFeatures implements Features.EdgePropertyFeatures {
            RedisEdgePropertyFeatures() {

            }

            @Override
            public boolean supportsMapValues() {
                return false;
            }

            @Override
            public boolean supportsMixedListValues() {
                return false;
            }

            @Override
            public boolean supportsSerializableValues() {
                return false;
            }

            @Override
            public boolean supportsUniformListValues() {
                return false;
            }
        }
    }
}
