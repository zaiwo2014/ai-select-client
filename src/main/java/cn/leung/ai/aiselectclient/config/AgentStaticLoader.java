package cn.leung.ai.aiselectclient.config;


import com.alibaba.cloud.ai.agent.studio.loader.AgentLoader;
import com.alibaba.cloud.ai.graph.GraphRepresentation;
import com.alibaba.cloud.ai.graph.agent.Agent;
import jakarta.annotation.Nonnull;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Primary
class AgentStaticLoader implements AgentLoader {

	private final Map<String, Agent> agents = new ConcurrentHashMap<>();

	public AgentStaticLoader(Agent agent) {

		GraphRepresentation representation = agent.getAndCompileGraph().stateGraph.getGraph(GraphRepresentation.Type.PLANTUML);
		System.out.println(representation.content());

		this.agents.put("research_agent", agent);
	}

	@Override
	@Nonnull
	public List<String> listAgents() {
		return agents.keySet().stream().toList();
	}

	@Override
	public Agent loadAgent(String name) {
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("Agent name cannot be null or empty");
		}

		Agent agent = agents.get(name);
		if (agent == null) {
			throw new NoSuchElementException("Agent not found: " + name);
		}

		return agent;
	}
}