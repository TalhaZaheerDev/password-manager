# 🔐 Secure Password Manager (CLI)

## Features
- AES Encryption for stored passwords
- BCrypt-based master password authentication
- PostgreSQL database integration
- CLI-based interaction

## Tech Stack
- Java (JDK 17+)
- PostgreSQL
- JDBC
- BCrypt
- AES (javax.crypto)

## Run
1. Configure DB in DBConnection.java
2. Run Main.java

## Structure
- config → DB
- dao → DB ops
- service → logic
- util → encryption
- app → CLI