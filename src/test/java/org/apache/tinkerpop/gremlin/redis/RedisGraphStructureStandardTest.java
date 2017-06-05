package org.apache.tinkerpop.gremlin.redis;

import org.apache.tinkerpop.gremlin.GraphProviderClass;
import org.apache.tinkerpop.gremlin.redis.structure.RedisGraph;
import org.apache.tinkerpop.gremlin.structure.StructureStandardSuite;
import org.junit.runner.RunWith;

/**
 * Executes the Standard Gremlin Structure Test Suite using TinkerGraph.
 */

@RunWith(StructureStandardSuite.class)
@GraphProviderClass(provider = RedisGraphProvider.class, graph = RedisGraph.class)
public class RedisGraphStructureStandardTest {
}

//@RunWith(GroovyProcessStandardSuite.class)
//@GraphProviderClass(provider = RedisGraphProvider.class, graph = RedisGraph.class)
//public class RedisGroovyProcessStandardTest {}