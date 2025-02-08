
# Library Management System

## 📖 Overview
The **Library Management System** is a robust and user-friendly application designed for efficient library operations. Developed in **Java (JDK 1.8)**, it supports functionalities like book and user management, book lending and returning, and detailed reporting to ensure streamlined library management.

---

## 🚀 Features

### 1️⃣ Data Structure
- Utilizes binary files (`Books.dat`, `Users.dat`, `Loans.dat`) for data persistence.
- Leverages Map data structures to efficiently manage and retrieve information about books, users, and transactions.

### 2️⃣ User-Friendly GUI
- A **console-based graphical user interface** (menu-driven).
- Clearly labeled menus for navigation between functionalities such as adding books, registering users, and generating reports.
- Input validation and error handling for seamless user interaction.

### 3️⃣ Book Management
- **Add Books**: Register new books with essential details (title, author, ISBN, etc.).
- **Update Book Information**: Modify details of existing books with validation.
- **Delete Books**: Soft deletion by marking books as inactive.
- **Show All Books**: Display a neatly formatted list of all active books.

### 4️⃣ User Management
- **Add Users**: Register new users, ensuring unique IDs.
- **Update User Information**: Modify user details like phone number or email.
- **Delete Users**: Soft deletion by marking users as inactive.
- **Show All Users**: Display a detailed list of active users.

### 5️⃣ Loan Management
- **Borrow Books**: Validate user eligibility and book availability before issuing loans.
- **Return Books**: Update transaction records with return dates.
- **Show Borrowed Books**: Display all active loans with due dates and borrower details.

### 6️⃣ Reporting
- Generate **real-time reports** for:
  - Borrowed books with due dates.
  - Overdue books with days overdue.
  - Comprehensive borrowing and returning activities for any date range.

### 7️⃣ Data Persistence
- Automatically saves changes to binary files and reloads them on startup for data continuity.

### 8️⃣ Exit Safely
- Ensures data consistency by saving all updates before exiting.

---

## 🖥️ Graphical User Interface (GUI)

### Main Menu Example:
```plaintext
===================================
    Library Management System
===================================
1. Manage Books
2. Manage Users
3. Borrow/Return Books
4. Reports
5. Exit
Enter your choice: _
```

### Add Book Example:
```plaintext
-----------------------------------
           Add New Book
-----------------------------------
Enter Book ID: B005
Enter Title: Advanced Algorithms
Enter Author: Mark Smith
Enter Publication Year: 2023
Enter Publisher: TechPress
Enter ISBN: 978-3-16-148410-0
Book added successfully!
-----------------------------------
```

### Report Example:
```plaintext
-----------------------------------
      Books Borrowed Report
-----------------------------------
| Book ID | Title            | Borrower  | Due Date   |
|---------|------------------|-----------|------------|
| B001    | The Alchemist    | U101      | 2025-02-15 |
| B002    | Java Basics      | U102      | 2025-02-12 |
-----------------------------------
```

---

## 📋 Example Reports

### 📅 Upcoming Due Dates (Next 3 Days):
| Book ID | Title             | User ID | Borrower Name | Due Date   |
|---------|-------------------|---------|---------------|------------|
| B003    | Python Mastery    | U103    | Alice Nguyen  | 2025-02-10 |
| B004    | Data Science 101  | U104    | John Tran     | 2025-02-11 |

### 📌 Overdue Books:
| Book ID | Title            | User ID | Borrower Name | Days Overdue |
|---------|------------------|---------|---------------|--------------|
| B005    | Advanced Java    | U105    | Mai Le        | 5            |
| B006    | Clean Code       | U106    | Bao Vu        | 3            |

### 📊 Borrowing Activity Summary:
| Date       | Borrowed Books | Returned Books | Overdue Books |
|------------|----------------|----------------|---------------|
| 2025-02-08 | 15             | 10             | 2             |
| 2025-02-09 | 12             | 14             | 5             |

---

## 🔧 Setup and Execution

### Prerequisites
- **JDK 1.8**
- A Java IDE (e.g., IntelliJ IDEA, Eclipse) or CLI tools.

### Steps to Run
1. **Clone the Repository:**
   ```bash
   git clone <repository_url>
   cd src
   ```

2. **Compile the Code:**
   ```bash
   javac run/Main.java
   ```

3. **Run the Application:**
   ```bash
   java run.Main
   ```

---

## 📅 Planned Enhancements
- Real-time notifications for overdue books.
- Integration with a database for scalability.
- Enhanced GUI using Swing or JavaFX for better user interaction.

---

## 📜 License

MIT License

Copyright (c) 2025 Nyakko Team

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
