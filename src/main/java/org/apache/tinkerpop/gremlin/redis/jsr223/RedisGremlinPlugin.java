package org.apache.tinkerpop.gremlin.redis.jsr223;

import org.apache.tinkerpop.gremlin.jsr223.AbstractGremlinPlugin;
import org.apache.tinkerpop.gremlin.jsr223.DefaultImportCustomizer;
import org.apache.tinkerpop.gremlin.jsr223.ImportCustomizer;
import org.apache.tinkerpop.gremlin.redis.structure.*;

public class RedisGremlinPlugin extends AbstractGremlinPlugin {

    private static final String NAME = "tinkerpop.redis";

    private static final ImportCustomizer imports = DefaultImportCustomizer.build()
        .addClassImports(RedisEdge.class,
            RedisElement.class,
            RedisGraph.class,
            RedisProperty.class,
            RedisVertex.class,
            RedisVertexProperty.class)
            .create();

    private static final RedisGremlinPlugin instance = new RedisGremlinPlugin();

    public RedisGremlinPlugin() {
        super(NAME, imports);
    }

    public static RedisGremlinPlugin instance() {
        return instance;
    }
}
