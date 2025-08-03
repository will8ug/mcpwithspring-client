#!/bin/bash

# Integration test script for MCP Client
# This script tests the MCP client against a running MCP server

echo "Starting MCP Client Integration Tests..."
echo "========================================"

# Check if the MCP server is running
echo "Checking if MCP server is running on http://localhost:8080..."
if curl -s http://localhost:8080/api/weather/london > /dev/null 2>&1; then
    echo "✅ MCP server is running"
else
    echo "❌ MCP server is not running. Please start it first:"
    echo "   cd ../mcpwithspring-server && mvn spring-boot:run"
    exit 1
fi

# Start the MCP client
echo "Starting MCP client..."
mvn spring-boot:run &
CLIENT_PID=$!

# Wait for client to start
echo "Waiting for MCP client to start..."
sleep 10

# Test endpoints
echo ""
echo "Testing MCP client endpoints..."
echo "================================"

# Test connection
echo "1. Testing connection..."
curl -s http://localhost:8081/api/client/test
echo ""
echo ""

# Test weather endpoint
echo "2. Testing weather endpoint..."
curl -s http://localhost:8081/api/client/weather/london
echo ""
echo ""

# Test weather summary endpoint
echo "3. Testing weather summary endpoint..."
curl -s http://localhost:8081/api/client/weather/paris/summary
echo ""
echo ""

# Cleanup
echo "Stopping MCP client..."
kill $CLIENT_PID
wait $CLIENT_PID 2>/dev/null

echo ""
echo "Integration tests completed!" 