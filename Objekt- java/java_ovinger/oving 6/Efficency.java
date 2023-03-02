
package oving6.delegation.office;

import java.util.List;

public class Efficency {

    static double calculateEfficency(Manager manager) {
        return manager.getTaskCount()/manager.getResourceCount();
    }

    public static void main(String[] args) {

        Printer printer = new Printer();
        Clerk clerk1 = new Clerk(printer);
        Clerk clerk2 = new Clerk(printer);
        Clerk clerk3 = new Clerk(printer);
        Clerk clerk4 = new Clerk(printer);

        Manager manager1 = new Manager(List.of(clerk1, clerk2, clerk3, clerk4));
        System.out.println("Efficiency of manager1: " + calculateEfficency(manager1));

        Manager manager2 = new Manager(List.of(clerk1, clerk2, clerk3));
        Manager manager3 = new Manager(List.of(clerk1, clerk2, clerk3));
        Manager manager4 = new Manager(List.of(clerk1, clerk2, clerk3));

        Manager boss = new Manager(List.of(manager1, manager2, manager3, manager4));
        System.out.println("Efficiency of boss: " + calculateEfficency(boss));

    }

}
