# 🔐 Secure Password Manager (CLI)

A secure Java-based CLI application to store and manage credentials with **AES encryption + BCrypt authentication + PostgreSQL backend**.

---

## 🚀 Features

- Master password authentication (BCrypt)
- AES encryption (user-derived key via PBKDF2)
- PostgreSQL database integration
- Add credentials
- View stored credentials (decrypted)
- Search by website
- Delete credentials
- Clean layered architecture (DAO → Service → App)

---

## 🧰 Tech Stack

- Java (JDK 17+)
- PostgreSQL
- JDBC
- BCrypt (`jbcrypt`)
- AES (`javax.crypto`)
- Maven

---

## 📂 Project Structure

```

com.talha.passwordmanager
│
├── config        → DB connection
├── model         → POJOs (User, Credential)
├── dao           → DB operations
├── service       → Business logic
├── util          → Encryption (CryptoUtil)
└── app           → CLI (Main)

````

---

## ⚙️ Setup

### 1. Clone repo
```bash
git clone https://github.com/TalhaZaheerDev/password-manager.git
cd password-manager
````

---

### 2. Configure PostgreSQL

```sql
CREATE DATABASE password_manager;

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    master_password_hash TEXT NOT NULL
);

CREATE TABLE credentials (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    website VARCHAR(255),
    username VARCHAR(255),
    password TEXT
);
```

---

### 3. Update DB config

`DBConnection.java`

```java
private static final String URL = "jdbc:postgresql://localhost:5432/password_manager";
private static final String USER = "postgres";
private static final String PASS = "your_password";
```

---

### 4. Run

* Open in IntelliJ
* Run `Main.java`

---

## 🧪 Usage

```
1. Signup
2. Login

After login:
1. Add
2. View
3. Search
4. Delete
5. Exit
```

---

## 🔐 Security

* Passwords stored using **BCrypt hashing**
* Credentials encrypted using:

    * AES
    * PBKDF2 key derivation from master password
* No plaintext password storage

---

## ⚠️ Limitations (Next Improvements)

* Static salt (should be per-user)
* No IV usage (AES ECB → not secure)
* No session/token management
* CLI only (no UI)

---

## 🔮 Future Improvements

* Per-user salt storage
* AES/GCM with IV
* REST API (Spring Boot)
* GUI (JavaFX / Web)
* Docker support

---

## 🧑‍💻 Author

Talha Zaheer Malik — Java Backend Learner 

---

## ⭐ Contribute

Feel free to fork and improve.

#
