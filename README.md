# finops-example

A comprehensive FinOps (Financial Operations) example project demonstrating best practices for cloud cost optimization, monitoring, and financial governance.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
# finops-example

A Java + Spring Boot FinOps (Financial Operations) example demonstrating cloud cost optimization, monitoring, and financial governance patterns implemented as a service.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Examples](#examples)
- [Best Practices](#best-practices)
- [Contributing](#contributing)
- [License](#license)

## Overview

This repository contains examples and reference code for applying FinOps principles in a Java microservice: cost collection, analysis, reporting, and automation hooks. The service is built with Spring Boot and packaged with Maven for easy deployment.

## Features

- Cloud cost collection adapters (AWS/Azure/GCP)
- Budget monitoring and alerting endpoints
- Cost optimization recommendations
- Tag-based cost allocation utilities
- Exportable financial reports (CSV/PDF)
- Automated daily analysis workflows

## Prerequisites

- Java 17+ (LTS)
- Maven 3.8+
- Git
- Cloud provider CLI (AWS/Azure/GCP) for optional integrations
- Docker (optional, for containerization)

## Installation

1. Clone the repository:

```bash
git clone https://github.com/gryphus-lab/finops-example.git
cd finops-example
```

2. Build the project with Maven:

```bash
mvn -U clean package
```

3. Run the Spring Boot application locally:

```bash
mvn spring-boot:run
# or
java -jar target/finops-example-0.0.1-SNAPSHOT.jar
```

4. (Optional) Build Docker image:

```bash
docker build -t gryphus-lab/finops-example:latest .
```

## Configuration

Configuration is managed via Spring Boot `application.yml` (or `application.properties`) and environment variables. Example `application.yml` snippet:

```yaml
server:
   port: 8080

finops:
   provider: aws
   budget:
      threshold: 10000
   report:
      frequency: WEEKLY

spring:
   datasource:
      url: ${DB_URL:jdbc:h2:mem:finops}
      username: ${DB_USER:sa}
      password: ${DB_PASS:}
```

You can also set configuration via environment variables, e.g. `FINOPS_PROVIDER=aws` or typical Spring Boot mappings.

## Usage

- Start the service (`mvn spring-boot:run`) and open `http://localhost:8080/actuator/health` for basic health.
- API endpoints (examples):

   - `GET /api/v1/costs?provider=aws&period=2026-02` — fetch cost summary
   - `POST /api/v1/optimize` — run optimization suggestions (dry-run)
   - `GET /api/v1/reports/latest` — download latest generated report

Example curl:

```bash
curl -s "http://localhost:8080/api/v1/costs?provider=aws&period=2026-02" | jq
```

## Project Structure

```
finops-example/
├── README.md
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/com/gryphuslab/finops/
│   │   │   ├── controller/
│   │   │   ├── service/
│   │   │   ├── model/
│   │   │   └── adapter/  # provider-specific adapters (aws, azure, gcp)
│   │   └── resources/
│   │       ├── application.yml
│   │       └── templates/
│   └── test/java/
└── docker/
      └── Dockerfile
```

## Examples

### Example 1: Fetch AWS cost summary

```bash
curl "http://localhost:8080/api/v1/costs?provider=aws&period=2026-02"
```

### Example 2: Trigger optimization recommendations

```bash
curl -X POST "http://localhost:8080/api/v1/optimize" -H 'Content-Type: application/json' -d '{"scope":"account","dryRun":true}'
```

### Example 3: Download latest report

```bash
curl -O "http://localhost:8080/api/v1/reports/latest"
```

## Best Practices

1. **Run regular analyses**: schedule daily or weekly jobs to detect anomalies early.
2. **Centralize tagging**: enforce consistent tags for ownership/cost-center and validate ingested data.
3. **Use budgets & alerts**: wire Spring events to notification channels (email, Slack, PagerDuty).
4. **Automate right-sizing**: integrate utilization signals into recommendation pipelines.
5. **Secure integrations**: use least-privilege credentials for cloud adapters and store secrets in a vault.

## Contributing

Contributions welcome. Suggested next steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/my-change`)
3. Implement and test your changes
4. Run `mvn test` and ensure checks pass
5. Open a Pull Request describing your changes

If you'd like, I can scaffold a basic Spring Boot service and `pom.xml` for you.

## License

This project is licensed under the MIT License - see the LICENSE file for details.
