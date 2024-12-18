//Library Management

//In a library, books, and journals are kept. Journals are issued to faculty members only. A stu-dent member can have 2 books issued at a time. For faculty members it is 10. For late return
//student members are charged Rs. 1 per day. Faculties are not charge. For journals additional information like issue no., date of publish, volume no., etc., are to be stored. For any transac-
//tion, members are supposed to place transactions slip. After necessary validations, transaction is carried out. Each transaction is to be noted into a register. Implement the system described
//above after designing the necessary classes.

import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.time.temporal.ChronoUnit;
import java.time.Instant;
class books_class{//book class
    String title;
    boolean is_issued;
    int bookid;
    Date date_of_issue;
    public books_class(String title,int bookid){
        this.title=title;
        this.bookid=bookid;
        this.is_issued=false;
        this.date_of_issue=null;
        System.out.println("Book added successfully.");
        
    }
    public void set_date(Date date_of_issue){//setting the date of issue of book
         this.date_of_issue=date_of_issue;
          this.is_issued=true;
    }
    public Date get_date_of_issue(){//getting the date of issue of book
         return date_of_issue;
    }
     public void resetIssueStatus() {//resetting the status once the book is returned
        this.date_of_issue = null;
        this.is_issued = false;
    }
}
class journals_class{//class for journals
    String title;
    boolean is_issued=false;
    int issue_no,vol_no;
    Date date_of_pub,date_of_issue;
    public journals_class(String title,int issue_no,int vol_no,Date date_of_pub){
        this.title=title;
        this.issue_no=issue_no;
        this.is_issued=false;
        this.vol_no=vol_no;
        this.date_of_pub=date_of_pub;
        this.date_of_issue=null;
         System.out.println("Journal added successfully.");
        
    }
      public void set_date(Date date_of_issue){//setting the date of issue of journal
         this.date_of_issue=date_of_issue;
          this.is_issued=true;
    }
     public Date get_date_of_issue(){//getting the date of issue of journal
         return date_of_issue;
    }
     public void resetIssueStatus() {//resetting the status once the journal is returned
        this.date_of_issue = null;
        this.is_issued = false;
    }
}
class Student_member{//class for student
    String name;int id;int no_of_books_issued; 
    public static final int MAX_BOOKS = 2;//max limit of issuance for students
    public Student_member( String name,int id){
        this.name=name;
        this.id=id;
        no_of_books_issued=0;
        System.out.println("Student added successfully.");
    }
     public int get_no_of_books_issued(){
         return no_of_books_issued;
        }
     public void update_no_of_books_issued(){//if the book is issued,the no of books issued is increased
         no_of_books_issued++;
        }
    public boolean can_be_issued(){
        return no_of_books_issued < MAX_BOOKS;
    }
    public int calculateLateFee(LocalDate returnDate, LocalDate issueDate) {//only for students fees charged not for faculties
        long daysLate = ChronoUnit.DAYS.between(issueDate, returnDate) - 14;
         return (int) Math.max(daysLate, 0);//since for every day late fine charge is Rs.1 so we dont have to multipy no of days by each day late fee charge
    }
    public void return_book(){//if the book is returned,the no of books issued is decreased
        no_of_books_issued--;
    }
}
class Faculty_member{//class for faculty
    String name;int id;int no_of_items_issued,no_of_books_issued,no_of_jour_issued;
     public static final int MAX_ITEMS = 10;//max limit of issuance for faculties
    public Faculty_member( String name,int id){
        this.name=name;
        this.id=id;
        no_of_items_issued=0;
        no_of_books_issued=0;
        no_of_jour_issued=0;
        System.out.println("Faculty added successfully.");
    }
    public int get_no_of_books_issued(){
         return no_of_books_issued;
        }
        public int get_no_of_jour_issued(){
         return no_of_books_issued;
        }
    public void update_no_of_items_issued(int type_of_items_issued){//if the item is issued,the no of items issued is increased
         if(type_of_items_issued==1){
            no_of_books_issued++;
        }
        else if(type_of_items_issued==2){
            no_of_jour_issued++;
        }
        
    }
    public boolean can_be_issued(){//total no of items issued should be less than max limit 
         return no_of_books_issued+no_of_jour_issued < MAX_ITEMS;
    }
    public void return_book_and_jour(int type_of_items_issued){//if the item is returned,the no of items issued is decreased
          if(type_of_items_issued==1){
           no_of_books_issued--;
        }
        else if(type_of_items_issued==2){
            no_of_jour_issued--;
        }
        
    }
}

class Transaction {//class for transaction
    String memberName;
    int memberId;
    String itemType; // "Book" or "Journal"
    String action; // "Issue" or "Return"
    LocalDate date;

    public Transaction(String memberName, int memberId, String itemType, String action, LocalDate date) {
        this.memberName = memberName;
        this.memberId = memberId;
        this.itemType = itemType;
        this.action = action;
        this.date = date;
    }

   // @Override
    public String toString() {
        return "Transaction: [Member: " + memberName + ", ID: " + memberId + ", Item: " + itemType + ", Action: " + action + ", Date: " + date + "]";
    }
}


public class library_management//main class
{
     public static void main(String[] args)
    { 
          Scanner scanner = new Scanner(System.in);
      List<books_class> books = new ArrayList<>();
        List<journals_class> journals = new ArrayList<>();
        List<Student_member> students = new ArrayList<>();
        List<Faculty_member> faculties = new ArrayList<>();
        List<Transaction> transactions = new ArrayList<>();    
         // Adding books
        System.out.println("Enter the number of books you want to store:");
        int numBooks = scanner.nextInt();
        for (int i = 0; i < numBooks; i++) {
            System.out.println("Enter title and book ID:");
            String title = scanner.next();
            int bookId = scanner.nextInt();
            books.add(new books_class(title, bookId));
        }

        // Adding journals
        System.out.println("Enter the number of journals you want to store:");
        int numJournals = scanner.nextInt();
        for (int i = 0; i < numJournals; i++) {
            System.out.println("Enter title, issue no., volume no., and publication date (yyyy-MM-dd):");
            String title = scanner.next();
            int issueNo = scanner.nextInt();
            int volumeNo = scanner.nextInt();
            LocalDate pubDate = LocalDate.parse(scanner.next());
            Date pubDateConverted = Date.from(pubDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            journals.add(new journals_class(title, issueNo, volumeNo, pubDateConverted));
        }
        
         // Adding student members
        System.out.println("Enter the number of student members:");
        int numStudents = scanner.nextInt();
        for (int i = 0; i < numStudents; i++) {
            System.out.println("Enter name and ID:");
            String name = scanner.next();
            int id = scanner.nextInt();
            students.add(new Student_member(name, id));
        }

        // Adding faculty members
        System.out.println("Enter the number of faculty members:");
        int numFaculties = scanner.nextInt();
        for (int i = 0; i < numFaculties; i++) {
            System.out.println("Enter name and ID:");
            String name = scanner.next();
            int id = scanner.nextInt();
            faculties.add(new Faculty_member(name, id));
        }
         System.out.println("Journal cannot be issued to students as it is only for faculties");
       int choice;//main loop 
        do {
            System.out.println("Enter 1 to Issue, 2 to Return, 0 to Exit:");
            choice = scanner.nextInt();

            if (choice == 1) { // Issue
                System.out.println("Enter 1 for Student, 2 for Faculty:");
                int memberType = scanner.nextInt();

                if (memberType == 1) { // Student
                    System.out.println("Enter student ID and book title:");
                    int studentId = scanner.nextInt();
                    String bookTitle = scanner.next();
                    
                      Student_member student1 = findStudentById(students, studentId);
                      books_class book1 = findAvailableBookByTitle(books, bookTitle,1);//1 for issue

                  /*  StudentMember student = students.stream().filter(s -> s.id == studentId).findFirst().orElse(null);
                    Book book = books.stream().filter(b -> b.title.equals(bookTitle) && !b.isIssued).findFirst().orElse(null);*/

                    if (student1 != null && book1 != null && student1.can_be_issued()) {//if student is available and book is available and also it can be issued
                             student1.update_no_of_books_issued();//update the no of books issued for this student
                        book1.set_date(new Date());//update the date of issue for this book
                        transactions.add(new Transaction(student1.name, student1.id, "Book", "Issue", LocalDate.now()));//update in the transaction register
                        System.out.println("Book issued successfully.");
                    } else {
                        System.out.println("Cannot issue book.student has reached limit");
                    }
                }
                
                else if (memberType == 2) { // Faculty
                    System.out.println("Enter faculty ID and book/journal title:");
                    int facultyId = scanner.nextInt();
                    String itemTitle = scanner.next();
                    
                     Faculty_member faculty1=findFacultyById(faculties, facultyId);
                     books_class book1 = findAvailableBookByTitle(books,itemTitle,1);//1 for issue
                     journals_class journal1=findAvailableJournalByTitle(journals,itemTitle,1);//1 for issue

                   /* FacultyMember faculty = faculties.stream().filter(f -> f.id == facultyId).findFirst().orElse(null);
                    Book book = books.stream().filter(b -> b.title.equals(itemTitle) && !b.isIssued).findFirst().orElse(null);
                    Journal journal = journals.stream().filter(j -> j.title.equals(itemTitle) && !j.isIssued).findFirst().orElse(null);*/

                    if (faculty1 != null && faculty1.can_be_issued()) {//if faculty is available and  also it can be issued
                        if (book1 != null) {//if book is available
                            faculty1.update_no_of_items_issued(1);//type 1 for books.//update the no of books issued for this faculty
                            book1.set_date(new Date());//update the date of issue for this book
                            transactions.add(new Transaction(faculty1.name, faculty1.id, "Book", "Issue", LocalDate.now()));//update in the transaction register
                            System.out.println("Book issued successfully.");
                        } else if (journal1 != null ) {//if journal is available
                            faculty1.update_no_of_items_issued(2);//type 1 for journals//update the no of journal issued for this faculty
                            journal1.set_date(new Date());//update the date of issue for this journal
                            transactions.add(new Transaction(faculty1.name, faculty1.id, "Journal", "Issue", LocalDate.now()));//update in the transaction register
                            System.out.println("Journal issued successfully.");
                        } else {
                            System.out.println("Cannot issue item. Either the item is unavailable or the faculty has reached the issue limit.");
                        }
                    }
                    else{
                         System.out.println("Cannot issue item.faculty not found");
                    }
                }

            }
            else if (choice == 2) { // Return
                System.out.println("Enter 1 for Student, 2 for Faculty:");
                int memberType = scanner.nextInt();

                if (memberType == 1) { // Student
                    System.out.println("Enter student ID and book title:");
                    int studentId = scanner.nextInt();
                    String bookTitle = scanner.next();

                   Student_member student1 = findStudentById(students, studentId);
                    books_class book1 = findAvailableBookByTitle(books, bookTitle,2);//2 for return

                    if (student1 != null && book1 != null) {//if student is available and book is available
                        System.out.println("Enter return date (yyyy-MM-dd):");
                        LocalDate returnDate = LocalDate.parse(scanner.next());//return date
                        //Date dateOfIssue = book1.get_date_of_issue();
                        LocalDate issueDate = book1.get_date_of_issue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();//issue date

                        int lateFee = student1.calculateLateFee(issueDate, returnDate);
                        if (lateFee > 0) {
                            System.out.println("Late fee: Rs. " + lateFee);//late fee
                        }

                        student1.return_book();//update the no of books since a book has been returned
                        book1.resetIssueStatus();//since the book been returned ,the status should be reset
                        transactions.add(new Transaction(student1.name, student1.id, "Book", "Return", returnDate));//update in the transaction register
                        System.out.println("Book returned successfully.");
                    } else {
                        System.out.println("Cannot return book.");
                    }

                } else if (memberType == 2) { // Faculty
                    System.out.println("Enter faculty ID and book/journal title:");
                    int facultyId = scanner.nextInt();
                    String itemTitle = scanner.next();
                    
                    Faculty_member faculty1=findFacultyById(faculties, facultyId);
                     books_class book1 = findAvailableBookByTitle(books,itemTitle,2);//2 for return
                     journals_class journal1=findAvailableJournalByTitle(journals,itemTitle,2);//2 for return
                    /*FacultyMember faculty = faculties.stream().filter(f -> f.id == facultyId).findFirst().orElse(null);
                    Book book = books.stream().filter(b -> b.title.equals(itemTitle) && b.isIssued).findFirst().orElse(null);
                    Journal journal = journals.stream().filter(j -> j.title.equals(itemTitle) && j.isIssued).findFirst().orElse(null);*/

                    if (faculty1 != null) {//if no faculty avaibale
                        if (book1 != null) {//if no book availabel
                            faculty1.return_book_and_jour(1);//type 1 for book////update the no of items since a book has been returned
                            book1.resetIssueStatus();//since the book been returned ,the status should be reset
                            transactions.add(new Transaction(faculty1.name, faculty1.id, "Book", "Return", LocalDate.now()));//update in the transaction register
                            System.out.println("Book returned successfully.");
                        } else if (journal1 != null) {//if no jorunal availabel
                            faculty1.return_book_and_jour(2);//type 2 for jorunals////update the no of items since a journal has been returned
                            journal1.resetIssueStatus();//since the jorunal been returned ,the status should be reset
                            transactions.add(new Transaction(faculty1.name, faculty1.id, "Journal", "Return", LocalDate.now()));//update in the transaction register
                            System.out.println("Journal returned successfully.");
                        } else {
                            System.out.println("Cannot return item.");
                        }
                    }
                }
            }
        } while (choice != 0);
          System.out.println("Transactions:");//printing the list of transactions (whatever transaction has been done )
                    for (Transaction transaction : transactions) {
                        System.out.println(transaction);
                    }
        scanner.close();
        System.out.println("Library management session ended."); 

    }
    
      // Helper method to find a student by ID
    private static Student_member findStudentById(List<Student_member> students, int id) {
        for (Student_member student : students) {
            if (student.id == id) {
                return student; // Found the student
            }
        }
        return null; // Student not found
    }

    // Helper method to find an available book by title
    private static books_class findAvailableBookByTitle(List<books_class> books, String title,int type) {
        for (books_class book : books) {
            if(type==1)//1 for issue
            {
                if (book.title.equals(title) && !book.is_issued) {
                return book; // Found the book
            } 
            }
            else if(type==2)//2 for return
            {
               if (book.title.equals(title) && book.is_issued) {
                return book; // Found the book
            }  
            }
           
        }
        return null; // Book not found or already issued
    }
     // Helper method to find a faculty by ID
    private static Faculty_member findFacultyById(List<Faculty_member> faculties, int id) {
       for (Faculty_member faculty : faculties) {
            if (faculty.id == id) {
                return faculty; // Found the faculty
            }
        }
        return null; // faculty not found
    }
    
     // Helper method to find an available journal by title
    private static journals_class findAvailableJournalByTitle( List<journals_class> journals, String title,int type) {
        for (journals_class journal : journals) {
             if(type==1)//1 for issue
            {
                 if (journal.title.equals(title) && !journal.is_issued) {
                return journal; // Found the journal 
            }
            }
            else if(type==2){
                 if (journal.title.equals(title) && journal.is_issued) {
                return journal; // Found the journal 
            }
            }
           
        }
        return null; // journal not found or already issued
    }
}
  
