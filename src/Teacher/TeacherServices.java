package Teacher;

import Department.Department;
import Main.MainTest;
import Student.Student;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class TeacherServices implements ITeacher{
    Scanner scanner = new Scanner(System.in);
    @Override
    public void list() {

        try (BufferedReader br = Files.newBufferedReader(Path.of("src/DatabaseFile/teachers.txt"))) {
            String line = br.readLine();
            while (line != null) {
                Teacher teacher = Teacher.fromString(line);
                System.out.println(teacher);
                line = br.readLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void add() {
        Department department = new Department();
        Teacher teacher = new Teacher();

        System.out.println("Teacher Name : ");
        teacher.setName(scanner.next());
        System.out.println("Teacher SurName : ");
        teacher.setSurname(scanner.next());
        System.out.println("Teacher Department : ");
        department.setName(scanner.next());
        teacher.setDepartment(department);
        System.out.println("Teacher Gender : ");
        teacher.setGender(scanner.next());
        System.out.println("Teacher ID  (TCKN) : ");
        teacher.setId(scanner.nextLong());

        System.out.println("   ***** Press 5 To Save! *****");
        System.out.println("   ***** Press 6 To Not Save! *****");
        int choice = scanner.nextInt();
        if (choice == 5) {
            save(teacher);
        }
        else {
            System.out.println("** Your Actions Are Not Saved!**");
            System.out.println("Exiting...");
            MainTest.menu();
        }

    }

    @Override
    public void save(Teacher teacher) {
        try (FileWriter fw = new FileWriter("src/DatabaseFile/teachers.txt", true);
             PrintWriter pw = new PrintWriter(fw, true)) {
            pw.println(teacher);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
