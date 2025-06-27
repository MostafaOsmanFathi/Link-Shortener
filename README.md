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

## 🌐 How to Use the Link Shortener Website

Once the application is running at `http://localhost:8080`, you can use the frontend website to shorten and visit links easily.

### 📝 Steps to Shorten a Link:

1. **Open the Website**

   * Navigate to: `http://localhost:8080`

2. **Enter the Original URL**

   * In the input field, paste the URL you want to shorten (e.g., `https://example.com`).

3. **Click the "Shorten" Button**

   * The site will call the backend API and generate a short link.

4. **Copy the Shortened URL**

   * A short version of your URL will be displayed. Copy it and use it anywhere.

---

### 🔗 Using the Shortened Link:

* Paste the short link into your browser (e.g., `http://localhost:8080/abc123`).
* You'll be automatically redirected to the original URL.

---

### ❗ Notes:

* Make sure the backend is running before using the website.
* The site and API must be on the same port unless CORS is configured.
* If you package the app as an `.exe` or JAR, double-clicking it should open the service.

---

Enjoy a smooth and minimal URL shortening experience!

