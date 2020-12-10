package com.graphql.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;

import org.springframework.stereotype.Component;

import graphql.kickstart.execution.context.GraphQLContext;
import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.servlet.context.GraphQLServletContextBuilder;
import lombok.RequiredArgsConstructor;
import lombok.var;

@Component
@RequiredArgsConstructor
public class CustomGraphQLContextBuilder implements GraphQLServletContextBuilder {

	public GraphQLContext build(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		var context = DefaultGraphQLServletContext.createServletContext()
				.with(httpServletRequest)
				.with(httpServletResponse)
				.build();

		return new CustomGraphQLContext(context);
	}

	@Override
	public GraphQLContext build(Session session, HandshakeRequest handshakeRequest) {
		throw new IllegalStateException("Unsupported");
	}

	@Override
	public GraphQLContext build() {
		throw new IllegalStateException("Unsupported");
	}
}
