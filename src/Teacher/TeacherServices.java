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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeacherServices implements ITeacher {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {

        Teacher updateTeacher = new Teacher();
        List<String> teacherList = new ArrayList<String>();

        System.out.println("Enter A Teacher ID: ");
        String updatedNumber = "id=" + scanner.next();  //personal ID


        try (BufferedReader br = Files.newBufferedReader(Path.of("src/DatabaseFile/teachers.txt"))) {
            String line = br.readLine();
            while (line != null) {
                updateTeacher = Teacher.fromString(line);

                //the information in the file has been added to the teacherList
                teacherList.add(String.valueOf(updateTeacher));
                line = br.readLine();
            }

            //file has been emptied
            try (FileWriter fw = new FileWriter("src/DatabaseFile/teachers.txt");
                 PrintWriter pw = new PrintWriter(fw)) {
                pw.print("");
            } catch (IOException e) {
                e.printStackTrace();
            }

            //The information in the list is checked line by line.
            for (int i = 0; i < teacherList.size(); i++) {
                String newString = null;
                newString = String.valueOf(teacherList.get(i)); //i. element converted to String


                if (newString.contains(updatedNumber)) {

                    //the row (i. element) to be updated is deleted
                    teacherList.remove(i);

                    System.out.println("Which Feature Do You Want To Change?");
                    System.out.println("Choose Operation!");
                    System.out.println("1:Name");
                    System.out.println("2:SurName");
                    System.out.println("3:Department");
                    System.out.println("4:Gender");
                    System.out.println("5:PersonalID");
                    System.out.println("6:Main Menu");
                    int choice = scanner.nextInt();

                    switch (choice) {
                        case 1: {
                            System.out.print("Enter A New Name: ");
                            String newName = scanner.next();

                            //string line split into parts by comma and space
                            String[] str = newString.split("[ ]+");

                            //1st element = element to be updated
                            str[1] = "Person{name='" + newName + "',";
                            newString = str[0];

                            //merging all row data
                            for (int j = 1; j < str.length; j++) {

                                newString = newString + " " + str[j];
                            }
                            teacherList.add(i, newString);
                            break;
                        }
                        case 2: {
                            System.out.print("Enter A New SurName: ");
                            String newSurName = scanner.next();
                            String[] str = newString.split("[ ]+");

                            //2nd element = element to be updated
                            str[2] = "surname='" + newSurName + "',";

                            newString = str[0];
                            for (int j = 1; j < str.length; j++) {

                                newString = newString + " " + str[j];
                            }
                            teacherList.add(i, newString);
                            break;
                        }

                        case 3: {
                            System.out.print("Enter A New Department Name: ");
                            String newDepName = scanner.next();
                            String[] str = newString.split("[ ]+");
                            str[0] = "Teacher{department=Department{name='" + newDepName + "'}}";

                            newString = str[0];
                            for (int j = 1; j < str.length; j++) {

                                newString = newString + " " + str[j];
                            }
                            teacherList.add(i, newString);
                            break;
                        }
                        case 4: {
                            System.out.print("Enter A New Gender: ");
                            String newGender = scanner.next();
                            String[] str = newString.split("[ ]+");
                            str[4] = "gender='" + newGender + "'}";

                            newString = str[0];
                            for (int j = 1; j < str.length; j++) {

                                newString = newString + " " + str[j];
                            }
                            teacherList.add(i, newString);
                            break;
                        }
                        case 5: {
                            System.out.print("Enter A New Personal ID: ");
                            String newStdId = scanner.next();
                            String[] str = newString.split("[ ]+");
                            str[3] = "id=" + newStdId + ",";

                            newString = str[0];
                            for (int j = 1; j < str.length; j++) {

                                newString = newString + " " + str[j];
                            }
                            teacherList.add(i, newString);
                            break;
                        }
                        case 6:
                            MainTest.menu();
                            break;
                        default:
                            System.out.println("You Made The Wrong Choice!");
                    }
                }

            }

            //  list item are written to file
            for (String t : teacherList) {

                try (FileWriter fw = new FileWriter("src/DatabaseFile/teachers.txt", true);
                     PrintWriter pw = new PrintWriter(fw, true)) {

                    //new information has been written to the file
                    pw.print(t + "\n");


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

    @Override
    public void delete() {

        Teacher dltTeacher = new Teacher();
        List<Teacher> teacherList = new ArrayList<Teacher>();

        System.out.println("Enter A Teacher ID: ");
        String deletedNumber = "id=" + scanner.next();  //student number


        try (BufferedReader br = Files.newBufferedReader(Path.of("src/DatabaseFile/teachers.txt"))) {
            String line = br.readLine();
            while (line != null) {
                dltTeacher = Teacher.fromString(line);

                //the information in the file has been added to the studentList
                teacherList.add(dltTeacher);
                line = br.readLine();
            }


            //file has been emptied
            try (FileWriter fw = new FileWriter("src/DatabaseFile/teachers.txt");
                 PrintWriter pw = new PrintWriter(fw)) {
                pw.print("");
            } catch (IOException e) {
                e.printStackTrace();
            }

            //The information in the list is checked line by line.
            for (int i = 0; i < teacherList.size(); i++) {
                String newString = null;
                newString = String.valueOf(teacherList.get(i)); //i. element converted to String

                if (newString.contains(deletedNumber)) {
                    teacherList.remove(i);
                    System.out.println("Deletion Successful!");
                }
            }


            //list item are written to file
            for (Teacher t : teacherList) {

                try (FileWriter fw = new FileWriter("src/DatabaseFile/teachers.txt", true);
                     PrintWriter pw = new PrintWriter(fw, true)) {

                    //new information has been written to the file
                    pw.print(t + "\n");

                } catch (IOException e) {
                    System.out.println("operation cannot be performed at this time");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
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
        } else {
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
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
