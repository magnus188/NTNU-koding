package oving6.delegation.office;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.BinaryOperator;

public class Manager implements Employee {

    // FIXME: change to 0. This is just for test purpose
    private int taskCount = 100;
    private ArrayList<Employee> employees;
    private int resourceCount = 0;

    Manager(Collection<Employee> employees) {
        if (employees.size() == 0) {
            throw new IllegalArgumentException("Employees cannot be null");
        } else {
            this.employees = new ArrayList<Employee>(employees);
        }

    }

    Employee delegateWork() {
        // Delegates equally between all employees on turn
        return employees.get(taskCount % (employees.size()));
    }

    @Override
    public double doCalculations(BinaryOperator<Double> operation, double value1, double value2) {
        this.taskCount++;
        return delegateWork().doCalculations(operation, value1, value2);
    }

    @Override
    public void printDocument(String document) {
        this.taskCount++;
        delegateWork().printDocument(document);

    }

    @Override
    public int getTaskCount() {
        return taskCount;
    }

    @Override
    public int getResourceCount() {
        if (resourceCount == 0) {

            employees.forEach(employer -> resourceCount += employer.getResourceCount());
        }

        return resourceCount + 1;

    }

}
