
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    String ID; // this value must be unique, no two different can have same id
    String name;
    int mobile;

    Student(String name, String ID, int mobile) {
        this.name = name;
        this.ID = ID;
        this.mobile = mobile;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getid() {
        return ID;
    }

    public void setid(String ID) {
        this.ID = ID;
    }

    public String toString() {
        return name + " " + ID + " " + mobile;
    }
    // add necessary code if need
}

class borrowlist extends Student {
    String bookTitle;

    borrowlist(String name, String ID, int mobile, String bookTitle) {
        super(name, ID, mobile);
        this.bookTitle = bookTitle;
    }

    public String toString() {
        return name + " " + ID + " " + mobile + " " + bookTitle;
    }

}

class Book {
    String bookTitle;
    int numOfCopy;
    String status = "available"; // how many copies of this book are in this library

    Book(String bookTitle, int numOfCopy) {
        this.bookTitle = bookTitle;
        this.numOfCopy = numOfCopy;

    }

    public String getbooktitle() {
        return bookTitle;
    }

    public void setbooktitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getnumbook() {
        return numOfCopy;
    }

    // public String setbooktitle(String title){this.title=title;}
    public String getstatus() {
        return status;
    }

    public void setstatus(String status) {
        this.status = status;
    }

    public String toString() {
        return "books {" + bookTitle + " " + numOfCopy + " " + status + "}";
    }
}

class validate {
    Scanner input = new Scanner(System.in);

    public String validtitle() {
        System.out.println("enter the booktitle=");
        String booktitle = input.nextLine();
        return booktitle;
    }

    public int validcopy() {
        System.out.println("enter nuber of copy=");
        int numcopy = input.nextInt();
        input.nextLine();
        return numcopy;
    }

    public String validid() {
        String id;
        System.out.println("enter the id=");
        id = input.nextLine();

        return id;
    }

    public String validname() {
        System.out.println("enter the name=");
        String name = input.nextLine();
        return name;
    }

    public int validmobile() {
        System.out.println("enter mobile=");
        int mobile = input.nextInt();
        input.nextLine();
        return mobile;
    }
}

public class _19CSE037 {
    public validate v1 = new validate();
    public List<Book> b1 = new ArrayList<>();
    public List<Student> s1 = new ArrayList<>();
    public List<borrowlist> b2 = new ArrayList<>();

    // add a new Book in the library
    void addNewBook() {
        // implement this method
        String booktitle = v1.validtitle();
        int numcopy = v1.validcopy();
        Book books = new Book(booktitle, numcopy);
        b1.add(books);
        System.out.println("add successfully");
    }

    // register a student if he/she is not registered before
    public void registration() {
        // implement this method
        String name = v1.validname();
        String id = v1.validid();
        for (Student student : s1) {
            if (student.getid().equals(id)) {
                System.out.println("id already registered plese unique id");
                System.out.flush();
                id = v1.validid();
            }
        }
        int mobile = v1.validmobile();
        Student Stu = new Student(name, id, mobile);
        s1.add(Stu);
        System.out.println("registered successfully");
    }

    // search a book in the library whether it is avilable or not

    // print all the books in the library
    void printAllBook() {
        // implement this method
        boolean flag = false;
        for (Book book : b1) {
            System.out.format("%s %13s", book.bookTitle, book.numOfCopy);
            System.out.println();
            flag = true;
        }
        if (flag == false) {
            System.out.println("there is no book in library0");
        }
    }

    // returns the borrower list of this book
    void printAllBorrower() {
        boolean flag = false;
        for (borrowlist allborrow : b2) {
            System.out.format("%s %13s %13s %13s", allborrow.name, allborrow.ID, allborrow.mobile, allborrow.bookTitle);
            System.out.println();
            flag = true;
        }
        if (flag == false) {
            System.out.println("there is no borrower");
        }
    }

    // search the student using studentID
    // call this method when a student requests to borrow a book
    void borrowRequest() {
        // implement this method
        String booktile = v1.validtitle();
        String stuid = v1.validid();
        boolean flag = false;
        for (Student student : s1) {
            if (student.getid().equals(stuid)) {
                for (Book book : b1) {
                    if (book.getbooktitle().equals(booktile) && book.getstatus().equals("available")) {
                        flag = true;
                        System.out.println("book borrer successfully");
                        book.setstatus("not available");
                        // book.setbooktitle(booktile);
                        // student.setid(stuid);
                        borrowlist bu = new borrowlist(student.name, student.ID, student.mobile, book.bookTitle);
                        b2.add(bu);
                    }
                }
            }
        }
        if (flag == false) {
            System.out.println("not available in library");
        }
    }

    // call this method when a student returns a book
    void returned() {
        // implement this method
        boolean flag = false;
        String booktile = v1.validtitle();
        for (Book book : b1) {
            if (book.getbooktitle().equals(booktile) && book.getstatus().equals("not available")) {
                flag = true;
                System.out.println("books successfully returned");
                book.setstatus("available");
            }
        }
        if (flag == false) {
            System.out.println("we can not returned");
        }

    }

    public static void main(String[] args) {
        int option;
        Scanner input = new Scanner(System.in);
        _19CSE037 servise = new _19CSE037();
        System.out.flush();
        while (true) {
            System.out.println(
                    " Registration: 1 \n Add New Book: 2 \n Print Books: 3 \n Print Borrower: 4 \n Borrow Request: 5\n Returned: 6");
            System.out.println("Enter Value: ");
            option = input.nextInt();
            if (option == 1) {
                // take input id, name and mobile from student
                System.out.flush();
                servise.registration();

            } else if (option == 2) {
                System.out.flush();
                servise.addNewBook();
                // servise.printAllBorrower();

            } else if (option == 3) {
                System.out.flush();
                servise.printAllBook();
            } else if (option == 4) {
                System.out.flush();
                servise.printAllBorrower();
            } else if (option == 5) {
                System.out.flush();
                servise.borrowRequest();

            } else {
                System.out.flush();
                servise.returned();

            }
        }
    }
}