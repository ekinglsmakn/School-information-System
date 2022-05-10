package Person;

import java.io.Serializable;

public class Person implements Serializable{
    private String name;
    private String surname;
    private long id; //TC Kimlik Numarasi
    private String gender;

    public Person(String name, String surname, long id, String gender) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.gender = gender;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", id=" + id +
                ", gender='" + gender + '\'' +
                '}';
    }
}


//    @Override
//    public String toString() {
//        return name +
//                "," + surname +
//                "," + id +
//                "," + gender;
//    }