package com.dawn.apprxjava;

public class StudentBean {
    int id;
    String name;

    public StudentBean() {
    }

    public StudentBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "StudentBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
