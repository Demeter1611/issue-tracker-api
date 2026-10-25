# Issue Tracker API

A RESTful backend utilizing Spring Boot and Spring Security designed with production-ready patterns including dynamic multi-field filtering, type-safe specifications, secure JWT authentication and structured pagination/sorting.

## Features
- Secure JWT authentication
- Dynamic filtering with JPA Specifications (multi-field & type-safe)
- Pagination & Sorting
- Dockerized App + PostgreSQL DB, ready to run with one command

## Tech Stack
- **Language:** Java 17+
- **Framework:** Spring Boot 3, Spring Security, Spring Data JPA
- **Database:** PostgreSQL
- **Containerization:** Docker, Docker Compose
- **API Testing:** Yaak
## API Endpoint Overview

### Project API
| Method | Endpoint | Description | Query Parameters |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/projects` | Create new project | Request Body (`ProjectRequest`) |
| `GET` | `/api/projects` | Get all projects | - |
| `POST` | `/api/projects/{projectId}/members/{userId}` | Add member to project | - |
| `GET` | `/api/projects/{projectId}/members` | Get all members of the project | - |


### Issues API

| Method | Endpoint | Description | Query Parameters |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/projects/{projectId}/issues` | Create new issue | Request Body (`IssueCreateRequest`) |
| `GET` | `/api/projects/{projectId}/issues` | Search & filter issues | `status`, `type`, `assigneeUsername`, `page`, `size`, `sort`, `direction` |
| `PATCH` | `/api/projects/{projectId}/issues/{issueId}/status` | Change status of the issue | Request Body (`IssueStatusUpdateRequest`) |
| `PATCH` | `/api/projects/{projectId}/issues/{issueId}/assignee` | Change assigned user of the issue | Request Body (`IssueAssigneeUpdateRequest`) |


### Comments API
| Method | Endpoint | Description | Query Parameters |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/projects/{projectId}/issues/{issueId}/comments` | Create new comment | Request Body (`CommentRequest`) |
| `GET` | `/api/projects/{projectId}/issues/{issueId}/comments` | Get comments by issue | - |
| `PATCH` | `/api/projects/{projectId}/issues/{issueId}/comments/{commentId}` | Change content of the comment | Request Body (`CommentRequest`) |
| `DELETE` | `/api/projects/{projectId}/issues/{issueId}/comments/{commentId}` | Delete comment | - |
### Authentication API

| Method | Endpoint | Description | Query Parameters |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/auth/login` | Authenticate credentials & return JWT | Request Body (`AuthRequest`)|
| `POST` | `/api/auth/register` | Register new user account | Request Body (`AuthRequest`) |
| `GET` | `/api/users` | Get all Users | - |

---

## Example Request & Response

### Dynamic Issue Search Query
`GET /api/projects/1/issues?type=BUG&status=TO_DO&page=0&size=10&sort=createdAt&direction=asc`

### Response Payload Structure
```json
{
  "content": [
    {
      "key": "PROJ-101",
      "summary": "Fix authentication token expiration bug",
      "description": "JWT tokens expire prematurely during active sessions.",
      "type": "BUG",
      "status": "TO_DO",
      "assignee": {
        "id": 1,
        "username": "dev"
      },
      "reporter": {
        "id": 2,
        "username": "lead"
      }
    }
  ],
  "page": {
    "size": 10,
    "number": 0,
    "totalElements": 1,
    "totalPages": 1
  }
}
```

# Local Setup
## Prerequisites
- Docker Desktop installed and running
- Git

## Running the application

### 1. Clone the repository:

```bash
git clone https://github.com/Demeter1611/issue-tracker-api.git
cd issue-tracker-api
```

### 2. Start the application & database with Docker Compose:
```bash
    docker compose up --build -d
```
The API will be available at `http://localhost:8080`

### 3. Stop the environment:
```bash
    docker compose down
```

## API Collection
A ready-to-use [Yaak](https://yaak.app) collection with all endpoints is available at [`docs/yaak-collection.json`](./docs/yaak-collection.json).

Steps to use:
1. Install [Yaak](https://yaak.app)
2. Import the collection file
3. Select the "Development" environment
4. Run "register" and "login" first to populate the token, then any other request.