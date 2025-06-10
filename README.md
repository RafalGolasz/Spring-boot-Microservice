# Spring‑boot‑Microservice 🚀

Lekki mikroserwis stworzony w **Spring Boot**.  
Konteneruje prostą logikę REST‑ową i wykorzystuje wbudowaną bazę danych H2/plikową jako przykład aplikacji mikroserwisowej.

---

## 🔍 Spis treści

1. [Opis projektu](#opis-projektu)
2. [Funkcje](#funkcje)
3. [Technologie](#technologie)
4. [Wymagania](#wymagania)
5. [Instalacja i uruchomienie](#instalacja-i-uruchomienie)
6. [Rozszerzenia](#rozszerzenia)
7. [Autor Projektu](#wkład-i-kontakt)

---

## 1. Opis projektu

Jest to prosty mikroserwis oparty na Spring Boot, zawierający REST API, model danych i prostą warstwę logiki. Dodatkowo uwzględnia pliki bazy danych H2 (`bazaDanych.mv.db`, `bazaDanych.trace.db`) jako przykład lokalnej bazy.

---

## 2. Funkcje

- Tworzenie, pobieranie, aktualizacja i usuwanie zasobów (CRUD)
- Wbudowana baza H2 (pliki lokalne)
- Struktura gotowa do rozbudowy

---

## 3. Technologie

- ☕ Java + Spring Boot
- 🗄️ H2 (lokalna baza danych)
- 🧰 Maven
- 📦 Struktura `src/`, `pom.xml`
- 🛠 REST API

---

## 4. Wymagania

- Java 11+
- Maven (lub wrapper `./mvnw`)

---

## 5. Instalacja i uruchomienie

```bash
git clone https://github.com/RafalGolasz/Spring-boot-Microservice.git
cd Spring-boot-Microservice
./mvnw spring-boot:run
```

API dostępne domyślnie pod adresem:
http://localhost:8080

## 6. Rozszerzenia
Spring Security (JWT)

Swagger UI / OpenAPI

Zewnętrzna baza PostgreSQL / MySQL

Docker
## 7. Autor projektu
Autor: Rafał Gołasz