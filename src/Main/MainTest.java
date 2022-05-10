package Main;

import Student.StudentServices;
import Teacher.TeacherServices;

import java.util.Scanner;

public class MainTest {
    public static void main(String[] args) {

        System.out.println("\n----------- S C H O L L   I N F O R M A T I O N   S Y S T E M ------------");

        menu();


    }

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        StudentServices studentServices = new StudentServices();
        TeacherServices teacherServices = new Teacher.TeacherServices();


        outerloop:
        do {

            System.out.println("\nChoose Operation ;\n" +
                    "For Student Operation Press \" S \" " +
                    "\nFor Teacher Operations Press \" T \"" +
                    "\nFor Exit Press 6");
            char operation = scanner.next().charAt(0);

            outerloop2:
            switch (operation) {

                //Student
                case 's':
                case 'S': {
                    System.out.println("1-List Student");
                    System.out.println("2-Add Student");
                    System.out.println("3-Delete Student");
                    System.out.println("4-Update Student");
                    System.out.println("5-Save All");
                    System.out.println("6-Exit");
                    System.out.print("\nChoice:");
                    int choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            studentServices.list();
                            break;
                        case 2:
                            studentServices.add();
                            break;
                        case 3:
                            studentServices.delete();
                            break;
                        case 4:
                            studentServices.update();
                            break;
                        case 5:
                            System.out.println("You Haven't Added Any Students Yet! Press 2 To Add Students...");
                            break;
                        case 6:
                            System.out.println("Exiting...");
                            break outerloop2;
                        default:
                            System.out.println("You entered incorrectly, please try again...");
                            break;
                    }
                }
                break;
                case 'T':
                case 't': {
                    System.out.println("1-List Teacher");
                    System.out.println("2-Add Teacher");
                    System.out.println("3-Delete Teacher");
                    System.out.println("4-Update Teacher");
                    System.out.println("5-Save All");
                    System.out.println("6-Exit");
                    System.out.println("Choice:");
                    int choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            teacherServices.list();
                            break;
                        case 2:
                            teacherServices.add();
                            break;
                        case 3:
                            teacherServices.delete();
                            break;
                        case 4:
                            teacherServices.update();
                            break;
                        case 5:
                            System.out.println("You haven't added any students yet! Press 2 to add students...");
                            break;
                        case 6:
                            System.out.println("Exiting...");
                            break outerloop2;
                        default:
                            System.out.println("You entered incorrectly, please try again...");
                            break;
                    }
                }
                break;
                case '6':
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("You entered incorrectly, please try again...");
                    break;
            }
        } while (true);
    }
}

