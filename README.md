

# 🔍 Mini Search Engine

Welcome to the repository of our **Java + DSA** Mini Project — a **console-based Search Engine** built from scratch, leveraging the power of **core data structures** to simulate real-world search functionalities. This project was crafted with the intent to deepen our understanding of Java, Data Structures, and File Handling — all under one roof.

---

## 🚀 Overview

This Mini Search Engine lets users:

* Manage their own document collection
* Perform **keyword** and **prefix-based** (lazy) searches
* Get real-time suggestions based on partial input
* Experience how search engines handle indexing, querying, and ranking behind the scenes

---

## ⚙️ Features

* 📝 **File Indexing** – Add `.txt` files to the engine and they’ll be scanned, indexed, and ready for search.
* 🔍 **Keyword Search** – Search for exact matches across indexed documents using optimized data retrieval.
* 🧠 **Lazy Search (Autocomplete)** – Type part of a word and get smart suggestions based on **Trie** prefix matching.
* 🔁 **Dynamic Updates** – Easily add more files and refresh the index.
* 📄 **File Management** – Automatically maps and links search results back to their respective source documents.

---

## 🧩 Data Structures Used

* 🔹 `HashMap` – For constant-time keyword-to-document lookups.
* 🔹 `Trie` – For fast and efficient **prefix-based suggestions**.
* 🔹 `HashSet` – To remove duplicates during the result aggregation phase.
* 🔹 `ArrayList` – To store and sort suggestion or result lists dynamically.

---

## 🛠️ Tech Stack

* **Language**: Java
* **IDE**: IntelliJ IDEA / VS Code
* **Execution**: Terminal / Console
* **Supported File Type**: `.txt` only

---

## 📂 Project Structure

```
MiniSearchEngine/
│
├── Main.java              # Main UI & control flow
├── SearchEngine.java      # Core indexing and search logic
├── DocumentManager.java   # Handles file I/O and data preparation
├── Trie.java              # Trie implementation for prefix matching
└── /documents             # Folder for all text files to be indexed
```

---

## 💻 How to Run

> 💡 **Requirements:** Make sure you have Java 17+ installed.

### 🔧 1. Clone the Repository

```bash
git clone https://github.com/yourusername/MiniSearchEngine.git
cd MiniSearchEngine
```

### 📁 2. Add Text Files

Place your `.txt` files inside the `/documents/` folder. Each file represents a searchable document.

### ⚙️ 3. Compile the Code

```bash
javac *.java
```

### ▶️ 4. Run the Program

```bash
java Main
```

---

## 🧪 Sample Demo Flow

> Here's what a typical run might look like:

```text
Welcome to Mini Search Engine 🚀

1. Load Documents
2. Search by Keyword
3. Lazy Search (Auto-Suggest)
4. Exit

Enter choice: 1
> Documents loaded and indexed successfully!

Enter choice: 2
> Enter keyword to search: algorithm
Found in: document1.txt, notes.txt

Enter choice: 3
> Enter prefix to suggest: alg
Suggestions:
- algorithm
- algebra
- algo-trading
```

---

## 🧑‍🤝‍🧑 Team Members

| Name                    | Roll Number | Section |
| ----------------------- | ----------- | ------- |
| 👨‍💻 Krishan Kumar Sah | 2310990467  | 20-A    |
| 👨‍💻 Abhinav Gupta     | 2310991758  | 20-A    |
| 👨‍💻 Adish Jain        | 2310991761  | 20-A    |
| 👩‍💻 Driksha Thakur    | 2310991822  | 20-B    |

---

## 📌 Future Enhancements

* 🔍 Add ranking based on word frequency
* 🌐 Add GUI using JavaFX or Swing
* 📊 Include statistics like document count, word frequency
* 📦 Export results to a file

---
