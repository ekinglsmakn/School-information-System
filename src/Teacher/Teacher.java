package Teacher;

import Department.Department;
import Person.Person;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Teacher extends Person {
    private Department department;


    public Teacher(String name,
                   String surname,
                   long id,
                   String gender,
                   Department department) {
        super(name, surname, id, gender);
        this.department = department;
    }

    public Teacher() {
        this(null, null, 0L, null, null);
    }

    public static Teacher fromString(String str) {
        Department department = getDepartmentFromString(str);
        String gender = getGenderFromString(str);
        long id = getIdFromString(str);
        String name = getNameFromString(str);
        String surname = getSurnameFromString(str);
        return new Teacher(name, surname, id, gender, department);
    }

    private static String getSurnameFromString(String str) {
        Pattern patterns = Pattern.compile("', surname='([a-zA-Z]+)', ");
        Matcher matcher = patterns.matcher(str);
        String surname;
        if (matcher.find()) {
            surname = matcher.group(1);
        } else {
            surname = null;
        }
        return surname;
    }

    public static String getNameFromString(String str) {
        Pattern patterns = Pattern.compile("\\} Person\\{name='([a-zA-Z]+)', ");
        Matcher matcher = patterns.matcher(str);
        String name;
        if (matcher.find()) {
            name = matcher.group(1);
        } else {
            name = null;
        }
        return name;
    }

    public static long getIdFromString(String str) {
        Pattern patterns = Pattern.compile(", id=(\\d+), ");
        Matcher matcher = patterns.matcher(str);
        long id;
        if (matcher.find()) {
            String temp = matcher.group(1);
            id = Long.parseLong(temp);
        } else {
            id = 0;
        }
        return id;
    }

    public static String getGenderFromString(String str) {

        Pattern patterns = Pattern.compile(", gender='([a-zA-Z]+)'\\}");
        Matcher matcher = patterns.matcher(str);
        String gender;
        if (matcher.find()) {
            gender = matcher.group(1);
        } else {
            gender = null;
        }
        return gender;
    }

    public static Department getDepartmentFromString(String str) {
        Pattern pattern = Pattern.compile("department=Department\\{name='([a-zA-Z0-9]+)'\\}\\}");
        Matcher matcher = pattern.matcher(str);
        Department dept;
        if (matcher.find()) {
            dept = new Department(matcher.group(1));
        } else {
            dept = null;
        }
        return dept;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "department=" + department +
                "} " + super.toString();
    }
}
