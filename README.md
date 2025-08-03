# MCP Client with Spring AI and WebFlux

This is a Model Context Protocol (MCP) client built with Spring AI and WebFlux that connects to an MCP server using SSE transport.

## Prerequisites

1. Make sure the MCP server is running on `http://localhost:8080`
2. The server should expose weather tools at `/api/weather/*` endpoints

## Running the Application

1. Start the MCP server first:
   ```bash
   cd ../mcpwithspring-server
   mvn spring-boot:run
   ```

2. Start the MCP client:
   ```bash
   mvn spring-boot:run
   ```

The client will start on port 8081 (different from the server to avoid conflicts).

## Testing the Connection

### Test Connection
```bash
curl http://localhost:8081/api/client/test
```

### Get Weather for a City
```bash
curl http://localhost:8081/api/client/weather/london
```

### Get Weather Summary for a City
```bash
curl http://localhost:8081/api/client/weather/london/summary
```

## Running Tests

### Unit Tests
```bash
mvn test
```

### Integration Tests
```bash
# Make sure the MCP server is running first
cd ../mcpwithspring-server && mvn spring-boot:run

# In another terminal, run the integration tests
./integration-test.sh
```

## Configuration

The client is configured in `src/main/resources/application.properties`:

- `spring.ai.mcp.client.base-url`: Base URL of the MCP server (default: http://localhost:8080)
- `spring.ai.mcp.client.transport`: Transport type (sse)
- `spring.ai.mcp.client.endpoint`: MCP endpoint (default: /api/mcp)

## Architecture

- `McpClientService`: Service layer that handles communication with the MCP server
- `McpClientController`: REST controller exposing endpoints for testing
- Uses WebClient for HTTP communication with the MCP server
- Reactive programming with Project Reactor (Mono/Flux)

## Next Steps

Once the basic connection is working, you can:
1. Integrate with actual LLM services
2. Add more sophisticated MCP tool calls
3. Implement proper error handling and retry mechanisms
4. Add authentication and security features 