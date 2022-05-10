package Student;

import Department.Department;
import Main.MainTest;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class StudentServices implements IStudent {
    Scanner scanner = new Scanner(System.in);


    @Override
    public void list() {
        try (BufferedReader br = Files.newBufferedReader(Path.of("src/DatabaseFile/students.txt"))) {
            String line = br.readLine();
            while (line != null) {
                Student student = Student.fromString(line);
                System.out.println(student);
                line = br.readLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add() {
        Department department = new Department();
        Student student = new Student();
        System.out.println("Student Name : ");
        student.setName(scanner.next());
        System.out.println("Student SurName : ");
        student.setSurname(scanner.next());
        System.out.println("Student Number : ");
        student.setNumber(scanner.nextInt());
        System.out.println("Student Department : ");
        department.setName(scanner.next());
        student.setDepartment(department);
        System.out.println("Student Gender : ");
        student.setGender(scanner.next());
        System.out.println("Student ID  (TCKN) : ");
        student.setId(scanner.nextLong());
        System.out.println("Student Graduation Grade: ");
        student.setGraduationGrade(scanner.nextFloat());
        System.out.println("   ***** Press 5 To Save! *****");
        System.out.println("   ***** Press 6 To Not Save! *****");
        int choice = scanner.nextInt();
        if (choice == 5) {
            save(student);
        }
        else {
            System.out.println("** Your Actions Are Not Saved!**");
            System.out.println("Exiting...");
            MainTest.menu();
        }
    }

    @Override
    public void save(Student student) {
        try (FileWriter fw = new FileWriter("src/DatabaseFile/students.txt", true);
             PrintWriter pw = new PrintWriter(fw, true)) {
            pw.println(student);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
    }

    @Override
    public void update() {
    }
}

