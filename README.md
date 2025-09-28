# Chat App - Microservices Architecture

## 🚀 Overview
Đây là hệ thống chat realtime được xây dựng theo kiến trúc **Microservices** với các thành phần:

- **Frontend**
  - Web client: Angular
  - Mobile client: Android (Java)
- **Backend Services** (Spring Boot, MongoDB)
  - Auth Service (Authentication & Authorization)
  - User Service (Quản lý user, danh bạ, profile)
  - Group Service (Quản lý nhóm chat)
  - Message Service (1-1 & group messaging)
  - File Service (Upload/Download file, MinIO)
  - Notification Service (Push notification, FCM/WebPush)
  - Statistic Service (Thống kê, logs)
- **Realtime Gateway**: Node.js + Socket.io (WebSocket connections, presence, messaging)
- **API Gateway**: Spring Cloud Gateway (routing & load balancing)
- **Infrastructure**
  - Kafka (message broker)
  - Redis (caching, pub/sub)
  - MongoDB (NoSQL DB)
  - MinIO (object storage)
  - Prometheus + Grafana (monitoring)

---

## 🛠️ Development Setup

### Requirements
- Docker & Docker Compose
- JDK 17
- Maven
- Node.js (>=18)
- Angular CLI

### Run locally
```bash
docker-compose up -d
