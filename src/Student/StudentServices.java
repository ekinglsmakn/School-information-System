package Student;

import Department.Department;
import Main.MainTest;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
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
        } catch (IOException e) {
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
        } else {
            System.out.println("** Your Actions Are Not Saved!**");
            System.out.println("Exiting...");
            MainTest.menu();
        }
    }

    @Override
    public void save(Student student) {
        try (FileWriter fw = new FileWriter("src/DatabaseFile/students.txt", true);
             PrintWriter pw = new PrintWriter(fw, true)) {
            pw.print(student + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        Student dltStudent = new Student();
        List<Student> studentList = new ArrayList<Student>();

        System.out.println("Enter A Student ID: ");
        String deletedNumber = "id=" + scanner.next();  //personal ID
        System.out.println("ID Of the Student To Be Deleted : " + deletedNumber);


        try (BufferedReader br = Files.newBufferedReader(Path.of("src/DatabaseFile/students.txt"))) {
            String line = br.readLine();
            while (line != null) {
                dltStudent = Student.fromString(line);

                //the information in the file has been added to the studentList
                studentList.add(dltStudent);
                line = br.readLine();
            }


            //file has been emptied
            try (FileWriter fw = new FileWriter("src/DatabaseFile/students.txt");
                 PrintWriter pw = new PrintWriter(fw)) {
                pw.print("");
            } catch (IOException e) {
                e.printStackTrace();
            }

            //The information in the list is checked line by line.
            for (int i = 0; i < studentList.size(); i++) {
                String newString = null;
                newString = String.valueOf(studentList.get(i)); //i. element converted to String

                if (newString.contains(deletedNumber)) {
                    studentList.remove(i);
                    System.out.println("Deletion Successful!");
                }
            }

            //list item are written to file
            for (Student s : studentList) {

                try (FileWriter fw = new FileWriter("src/DatabaseFile/students.txt", true);
                     PrintWriter pw = new PrintWriter(fw, true)) {

                    //new information has been written to the file
                    pw.print(s + "\n");

                } catch (IOException e) {
                    System.out.println("operation cannot be performed at this time");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {

        Student updateStudent = new Student();
        List<String> studentList = new ArrayList<String>();

        System.out.println("Enter A Student ID: ");
        String updatedNumber = "id=" + scanner.next();  //personal ID


        try (BufferedReader br = Files.newBufferedReader(Path.of("src/DatabaseFile/students.txt"))) {
            String line = br.readLine();
            while (line != null) {
                updateStudent = Student.fromString(line);

                //the information in the file has been added to the studentList
                studentList.add(String.valueOf(updateStudent));
                line = br.readLine();
            }

            //file has been emptied
            try (FileWriter fw = new FileWriter("src/DatabaseFile/students.txt");
                 PrintWriter pw = new PrintWriter(fw)) {
                pw.print("");
            } catch (IOException e) {
                e.printStackTrace();
            }

            //The information in the list is checked line by line.
            for (int i = 0; i < studentList.size(); i++) {
                String newString = null;
                newString = String.valueOf(studentList.get(i)); //i. element converted to String


                if (newString.contains(updatedNumber)) {

                    //the row (i. element) to be updated is deleted
                    studentList.remove(i);

                    System.out.println("Which Feature Do You Want To Change?");
                    System.out.println("Choose Operation!");
                    System.out.println("1:Name");
                    System.out.println("2:SurName");
                    System.out.println("3:Department");
                    System.out.println("4:Student Number");
                    System.out.println("5:Graduation Grade");
                    System.out.println("6:Gender");
                    System.out.println("7:PersonalID");
                    System.out.println("8-Main menu");
                    int choice = scanner.nextInt();

                    switch (choice) {
                        case 1: {
                            System.out.print("Enter A New Name: ");
                            String newName = scanner.next();

                            //string line split into parts by comma and space
                            String[] str = newString.split("[ ]+");

                            //3rd element = updated element
                            str[3] = "Person{name='" + newName + "',";
                            newString = str[0];

                            //merging all row data
                            for (int j = 1; j < str.length; j++) {

                                newString = newString + " " + str[j];
                            }
                            studentList.add(i, newString);
                            break;
                        }
                        case 2: {
                            System.out.print("Enter A New SurName: ");
                            String newSurName = scanner.next();
                            String[] str = newString.split("[ ]+");
                            str[4] = "surname='" + newSurName + "',";

                            newString = str[0];
                            for (int j = 1; j < str.length; j++) {

                                newString = newString + " " + str[j];
                            }
                            studentList.add(i, newString);
                            break;
                        }

                        case 3: {
                            System.out.print("Enter A New Department Name: ");
                            String newDepName = scanner.next();
                            String[] str = newString.split("[ ]+");
                            str[0] = "Student{department=Department{name='" + newDepName + "'},";

                            newString = str[0];
                            for (int j = 1; j < str.length; j++) {

                                newString = newString + " " + str[j];
                            }
                            studentList.add(i, newString);
                            break;
                        }
                        case 4: {
                            System.out.print("Enter A New Student Number: ");
                            String newStdNumber = scanner.next();
                            String[] str = newString.split("[ ]+");
                            str[1] = "number=" + newStdNumber + ",";

                            newString = str[0];
                            for (int j = 1; j < str.length; j++) {

                                newString = newString + " " + str[j];
                            }
                            studentList.add(i, newString);
                            break;
                        }
                        case 5: {
                            System.out.print("Enter A New Graduation Grade: ");
                            String newGrade = scanner.next();
                            String[] str = newString.split("[ ]+");
                            str[2] = "graduationGrade=" + newGrade + "}";

                            newString = str[0];
                            for (int j = 1; j < str.length; j++) {

                                newString = newString + " " + str[j];
                            }
                            studentList.add(i, newString);
                            break;
                        }
                        case 6: {
                            System.out.print("Enter A New Gender: ");
                            String newGender = scanner.next();
                            String[] str = newString.split("[ ]+");
                            str[6] = "gender='" + newGender + "'}";

                            newString = str[0];
                            for (int j = 1; j < str.length; j++) {

                                newString = newString + " " + str[j];
                            }
                            studentList.add(i, newString);
                            break;
                        }
                        case 7: {
                            System.out.print("Enter A New Personal ID: ");
                            String newStdId = scanner.next();
                            String[] str = newString.split("[ ]+");
                            str[5] = "id=" + newStdId + ",";

                            newString = str[0];
                            for (int j = 1; j < str.length; j++) {

                                newString = newString + " " + str[j];
                            }
                            studentList.add(i, newString);
                            break;
                        }
                        case 8:
                            MainTest.menu();
                            break;
                        default:
                            System.out.println("You Made The Wrong Choice!");
                    }
                }

            }

            //  list item are written to file
            for (String s : studentList) {

                try (FileWriter fw = new FileWriter("src/DatabaseFile/students.txt", true);
                     PrintWriter pw = new PrintWriter(fw, true)) {

                    //new information has been written to the file
                    pw.print(s + "\n");


                } catch (IOException e) {
                    System.out.println("operation cannot be performed at this time");
                }
            }
            System.out.println("*** Updated Data ***");
            list();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

