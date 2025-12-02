# Quick Start Guide

## 🚀 Getting Started in 3 Steps

### 1️⃣ Prerequisites Check
```bash
# Check Java version (need 17+)
java -version

# Check Maven version
mvn -version

# Check MongoDB is running
./check-db.sh
```

### 2️⃣ Install & Build
```bash
# Install dependencies
make install

# Or using Maven directly
mvn clean install
```

### 3️⃣ Run the Application
```bash
# Run with default settings (port 8083)
make run

# Or run with custom port
PORT=8080 make run

# Or using Maven
mvn spring-boot:run
```

## ✅ Test the API

Once the server is running, test the endpoints:

### Create a word:
```bash
curl -X POST http://localhost:8083/post-word \
  -H "Content-Type: application/json" \
  -d '{"text": "Hello World"}'
```

Response:
```json
{"id":"507f1f77bcf86cd799439011"}
```

### Get word by date:
```bash
curl http://localhost:8083/get-word/2025-10-31
```

Response:
```json
{
  "id": "507f1f77bcf86cd799439011",
  "text": "Hello World",
  "ownerId": "test-user",
  "createdAt": "2025-10-31T12:00:00Z"
}
```

## 🔧 Configuration

Set environment variables before running:

```bash
export PORT=8080
export MONGO_URI=mongodb://localhost:27017
export MONGO_DB=main-services
```

Or create an `.env` file (requires spring-boot-dotenv dependency).

## 📊 Available Make Commands

```bash
make help          # Show all available commands
make run           # Run the application
make build         # Build JAR file
make test          # Run tests
make clean         # Clean build artifacts
make debug         # Run with debugging enabled
make kill          # Kill process on PORT
```

## 🐛 Troubleshooting

### MongoDB Connection Issues
```bash
# Check if MongoDB is running
./check-db.sh

# Start MongoDB (macOS with Homebrew)
brew services start mongodb-community

# Start MongoDB (Linux)
sudo systemctl start mongod
```

### Port Already in Use
```bash
# Kill process on port 8083
make kill

# Or specify different port
PORT=8080 make run
```

### Build Issues
```bash
# Clean and rebuild
mvn clean install -U

# Skip tests if needed
mvn clean install -DskipTests
```

## 🎯 Next Steps

1. ✅ Application running
2. 📚 Read [README.md](README.md) for detailed documentation
3. 🔐 Implement authentication (see TODO comments in code)
4. 🌐 Configure CORS for production
5. 🐳 Set up Docker deployment
6. 📝 Add more endpoints as needed

## 📞 Need Help?

- Check logs in the terminal where you ran the application
- Review [README.md](README.md) for detailed information
- Look for TODO comments in the code for implementation notes



