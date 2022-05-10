package Department;

import Person.Person;

import java.io.Serializable;

public class Department implements Serializable {
    private String name;  //department name

    public Department() {
    }

    public Department(String name){

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @Override
//    public String toString() {
//        return "," + name ;
//    }


    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                '}';
    }
}
