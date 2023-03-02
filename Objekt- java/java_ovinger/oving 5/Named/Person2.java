package oving5.Named;

public class Person2 implements Named {
    private String givenName;
    private String familyName;
    
    public Person2(String fullName) {
        this.givenName = fullName.split("\\s+")[0];
        this.familyName = fullName.split("\\s+")[1];
    }

    public void setGivenName(String name) {
        this.givenName = name;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setFamilyName(String name) {
        this.familyName = name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFullName(String fullName) {
        this.givenName = fullName.split("\\s+")[0];
        this.familyName = fullName.split("\\s+")[1];
    }

    public String getFullName() {
        return givenName + " " + familyName;
    }
}
