package library;

public class Student extends Person {
    int Student_ID;
    String Faculty_name;
    String Course_name;


    public void borrow(){
        System.out.println("The student has borrowed a book");
    }
    public void returned(){
        System.out.println("The student has returned a book");
    }
}
