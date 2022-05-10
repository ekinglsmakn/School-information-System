package Student;

import Department.Department;
import Person.Person;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student extends Person {
    private Department department;
    private int number; // student number
    private float graduationGrade;

    public Student() {
        this(null, null, 0L, null, null, 0, 0.0f);
    }

    public Student(String name,
                   String surname,
                   long id,
                   String gender,
                   Department department,
                   int number,
                   float graduationGrade) {
        super(name, surname, id, gender);
        this.department = department;
        this.number = number;
        this.graduationGrade = graduationGrade;
    }


    public static Student fromString(String str) {
        Department dept = getDepartmentFromString(str);
        String gender = getGenderFromString(str);
        float grade = getGraduationGradeFromString(str);
        long id = getIdFromString(str);
        String name = getNameFromString(str);
        int number = getNumberFromString(str);
        String surname = getSurnameFromString(str);
        return new Student(name, surname, id, gender, dept, number, grade);
    }

    public static Department getDepartmentFromString(String str) {
        Pattern pattern = Pattern.compile("department=Department\\{name='([a-zA-Z0-9]+)'\\},");
        Matcher matcher = pattern.matcher(str);
        Department dept;
        if (matcher.find()) {
            dept = new Department(matcher.group(1));
        } else {
            dept = null;
        }
        return dept;
    }

    public static String getGenderFromString(String str) {
        Pattern pattern = Pattern.compile(", gender='([a-zA-Z]+)'\\}");
        Matcher matcher = pattern.matcher(str);
        String gender;
        if (matcher.find()) {
            gender = matcher.group(1);
        } else {
            gender = null;
        }
        return gender;
    }

    public static float getGraduationGradeFromString(String str) {
        Pattern pattern = Pattern.compile(", graduationGrade=(\\d+\\.\\d+)\\} ");
        Matcher matcher = pattern.matcher(str);
        float grade;
        if (matcher.find()) {
            String temp = matcher.group(1);
            grade = Float.parseFloat(temp);
        } else {
            grade = 0.0f;
        }
        return grade;
    }

    public static long getIdFromString(String str) {
        Pattern pattern = Pattern.compile(", id=(\\d+), ");
        Matcher matcher = pattern.matcher(str);
        long id;
        if (matcher.find()) {
            String temp = matcher.group(1);
            id = Long.parseLong(temp);
        } else {
            id = 0;
        }
        return id;
    }

    public static String getNameFromString(String str) {
        Pattern pattern = Pattern.compile("\\} Person\\{name='([a-zA-Z]+)', ");
        Matcher matcher = pattern.matcher(str);
        String name;
        if (matcher.find()) {
            name = matcher.group(1);
        } else {
            name = null;
        }
        return name;
    }

    public static int getNumberFromString(String str) {
        Pattern pattern = Pattern.compile("'\\}, number=(\\d+), ");
        Matcher matcher = pattern.matcher(str);
        int number;
        if (matcher.find()) {
            String temp = matcher.group(1);
            number = Integer.parseInt(temp);
        } else {
            number = 0;
        }
        return number;
    }

    public static String getSurnameFromString(String str) {
        Pattern pattern = Pattern.compile("', surname='([a-zA-Z]+)', ");
        Matcher matcher = pattern.matcher(str);
        String surname;
        if (matcher.find()) {
            surname = matcher.group(1);
        } else {
            surname = null;
        }
        return surname;
    }

    public float getGraduationGrade() {
        return graduationGrade;
    }

    public void setGraduationGrade(float graduationGrade) {
        this.graduationGrade = graduationGrade;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Student{" + "department=" + department + ", number=" + number + ", graduationGrade=" + graduationGrade
                + "} " + super.toString();
    }
}

