package oving5.Named;

import java.util.Comparator;

public class NamedComparator implements Comparator<Named> {

    public int compare(Named named1, Named named2) {

        final String lastName1 = named1.getFamilyName();
        final String lastName2 = named2.getFamilyName();

        final String firstName1 = named1.getGivenName();
        final String firstName2 = named2.getGivenName();


        if (lastName1.compareTo(lastName2) != 0) {
            // Unequal last names
            if (lastName1.compareTo(lastName2) < 0) {
                return -1;
            } else {
                return 1;
            }
        } else {
            // Equal last names
            if (firstName1.compareTo(firstName2) == 0) {
                return 0;
            }
            else if (firstName1.compareTo(firstName2) < 0) {
                return -1;
            } else {
                return 1;
            }
        }
    }
    
}
