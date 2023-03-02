package oving4;

import java.util.ArrayList;

public class Person {
    private String name;
    private char gender;
    private Person mother;
    private Person father;
    private ArrayList<Person> children = new ArrayList<Person>();

    public Person(String name, char gender) {
        if (gender == 'M' || gender == 'F') {
            this.name = name;
            this.gender = gender;
        } else {
            throw new IllegalArgumentException("Illegal values");
        }
    }

    public Person getFather() {
        return father;
    }

    public char getGender() {
        return gender;
    }

    public Person getMother() {
        return mother;
    }

    public String getName() {
        return name;
    }

    public boolean hasChild(Person child) {
        return this.children.contains(child) ? true : false;
    }

    public int getChildCount() {
        return children.size();
    }

    public Person getChild(int n) {
        try {
            return children.get(n);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid index");
        }
    }

    public void addChild(Person child) {
        children.add(child);

        if (child.getMother() != this && gender == 'F') {
            child.setMother(this);
        } else if (child.getFather() != this && gender == 'M') {
            child.setFather(this);
        }
    }

    public void removeChild(Person child) {
        children.remove(child);

        // remove parent role
        if (child.getMother() == this) {
            child.setMother(null);
        } else if (child.getFather() == this) {
            child.setFather(null);
        }
    }

    public void setMother(Person person) {
        if (person == null) {
            this.father = null;
        } else if (person.getGender() == 'F' && person != this) {
            if (this.getMother() != null) {
                this.mother.removeChild(this);
            }
            this.mother = person;
            if (!person.hasChild(this))
                person.addChild(this);

        } else {
            throw new IllegalArgumentException("Illegal values");
        }
    }

    public void setFather(Person person) {
        if (person == null) {
            this.father = null;
        } else if (person.getGender() == 'M' && person != this) {
            if (this.getFather() != null) {
                this.father.removeChild(this);
            }
            this.father = person;
            if (!person.hasChild(this))
                person.addChild(this);

        } else {
            throw new IllegalArgumentException("Illegal values");
        }

    }
}
