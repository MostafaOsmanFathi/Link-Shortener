# 🔗 Link Shortener

A lightweight URL shortening service built with **Java**, **Spring Boot**, and **JDBC** — no Hibernate or JPA.
Supports MySQL for persistent storage and provides a simple REST API to shorten and retrieve original URLs.

---

## 📆 Version

**[v0.0.1](github.com/MostafaOsmanFathi/Link-Shortener/releases/latest)** – Initial public release

---

## 🚀 Features

* ✅ Create short links from original URLs
* ✅ Redirect to original URLs using a unique code
* ✅ RESTful API with JSON input/output
* ✅ Uses MySQL with raw JDBC (no ORM overhead)
* ✅ Error handling for invalid or unknown links

---

## 🚠 Tech Stack

* Java 17+
* Spring Boot
* JDBC
* H2 Database
* Maven for build and dependency management

---

## 📁 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── your/package/
│   │       ├── ShortLink.java              # Model
│   │       ├── ShortLinkService.java       # Service Layer
│   │       ├── ShortLinkController.java    # REST Controller
│   │       └── ShortLinkRepository.java    # JDBC Repository
│   └── resources/
│       └── application.properties          # DB & server config
```

---

## 📘 How to Build

Use Maven to build the project:

```bash
mvn clean package
```

This will generate the JAR file in the `target/` directory.

---

## 🚀 How to Run

After building, run the app with:

```bash
java -jar Link-Shortener-0.0.1.jar
```

Once running, the API will be available at:

```
http://localhost:8080
```

---

## 🧪 API Usage

### ➕ Create a Short Link

TBD...
