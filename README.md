# library_management_system

In a library, books, and journals are kept. Journals are issued to faculty members only. A stu-dent member can have 2 books issued at a time. For faculty members it is 10. For late return student members are charged Rs. 1 per day. Faculties are not charge. For journals additional information like issue no., date of publish, volume no., etc., are to be stored. For any transaction, members are supposed to place transactions slip. After necessary validations, transaction is carried out. Each transaction is to be noted into a register. Implement the system described above after designing the necessary classes.

The key features of the **Library Management System** implemented in the provided code are as follows:

---

### **1. Entities Modeled with Classes**
- **Books**:
  - Includes attributes like title, book ID, issue status, and issue date.
  - Methods to set and reset issue status and manage issue dates.
- **Journals**:
  - Includes attributes like title, issue number, volume number, publication date, issue status, and issue date.
  - Specifically restricted for faculty members only.
- **Student Members**:
  - Can issue a maximum of 2 books at a time.
  - Late return fee of ₹1 per day beyond the 14-day period is calculated.
  - Methods for updating issued book count and validating issue limits.
- **Faculty Members**:
  - Can issue up to 10 items (books and journals combined).
  - No late return fee charged.
  - Methods for managing issued items and validating issue limits.

---

### **2. Validation Mechanisms**
- Limits on the number of books or journals a student or faculty member can issue.
- Journals can only be issued to faculty members, not students.
- Validation of available books or journals before issuing.
- Ensures that only valid members (students or faculty) can issue or return items.
- A student or faculty can only return items that are marked as issued to them. 
- If a user tries to return an item that is not currently issued or assigned to them, the return request is denied.

---

### **3. Transaction Management**
- Each transaction (issue or return) is recorded with details:
  - Member name, member ID, item type (book or journal), action (issue/return), and date.
- All transactions are stored in a list for later review and audit.
- The system checks whether the requested action is possible (e.g., issuing an already issued book is not allowed).
- If any validation fails, the transaction is rejected, and the user is notified.

---

### **4. Late Fee Calculation**
- **For Students Only**:
  - A late fee of ₹1 per day is charged if a book is returned after 14 days of the issue date.
  - The fee is dynamically calculated based on the issue and return dates.
  -  - Late fees are calculated based on the number of overdue days.

---

### **5. User Interaction**
- Interactive input for adding:
  - Books, journals, students, and faculty members.
- Facilitates issuing and returning of items through a menu-driven interface.
- Prompts user input for IDs, titles, and dates during transactions.

---

### **6. Helper Methods for Lookup**
- Efficiently find:
  - A student or faculty member by their ID.
  - Available books or journals by title, differentiated by issue or return context.

---

### **7. Transaction Logging**
- Maintains a history of all issue and return transactions.
- Enables printing the transaction list at the end of the session for review.


