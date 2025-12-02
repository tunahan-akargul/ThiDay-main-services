#!/bin/bash

# MongoDB Connection Check Script
# This is the Java version equivalent to check-db.sh from Go project

echo "Checking MongoDB connection..."

MONGO_URI=${MONGO_URI:-"mongodb://localhost:27017"}
MONGO_DB=${MONGO_DB:-"main-services"}

# Try to connect to MongoDB and list databases
if command -v mongosh &> /dev/null; then
    echo "Using mongosh..."
    mongosh "$MONGO_URI" --eval "db.adminCommand('ping')"
elif command -v mongo &> /dev/null; then
    echo "Using mongo..."
    mongo "$MONGO_URI" --eval "db.adminCommand('ping')"
else
    echo "MongoDB client not found. Please install mongosh or mongo."
    echo "Attempting to check connection via curl..."
    curl -s http://localhost:27017 || echo "Cannot connect to MongoDB at localhost:27017"
    exit 1
fi

if [ $? -eq 0 ]; then
    echo "✅ MongoDB connection successful!"
    echo "URI: $MONGO_URI"
    echo "Database: $MONGO_DB"
else
    echo "❌ MongoDB connection failed!"
    exit 1
fi



