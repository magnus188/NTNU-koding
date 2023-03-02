package oving6.delegation.office;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Printer {

    private HashMap<Employee, ArrayList<String>> printHistory = new HashMap<Employee, ArrayList<String>>();

    void printDocument(String document, Employee employee) {
        System.out.println("Printing document: " + document);
        try {
            ArrayList<String> history = printHistory.get(employee);
            history.add(document);
            printHistory.put(employee, history);

        } catch (NullPointerException e) {
            ArrayList<String> history = new ArrayList<String>();
            history.add(document);
            printHistory.put(employee, history);
        }
    }

    List<String> getPrintHistory(Employee employee) {

        try {
            return Objects.requireNonNull(Collections.unmodifiableList(printHistory.get(employee)));
        } catch (Exception e) {
            return List.of();
        }
    }
}
