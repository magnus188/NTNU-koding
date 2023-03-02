package oving6.delegation.office;

import java.util.function.BinaryOperator;

public class Clerk implements Employee {

    private int taskCount = 0;
    private Printer printer;

    Clerk(Printer printer) {
        this.printer = printer;
    }

    @Override
    public double doCalculations(BinaryOperator<Double> operation, double value1, double value2) {
        this.taskCount++;
        return operation.apply(value1, value2);
    }

    @Override
    public void printDocument(String document) {
        this.taskCount++;
        printer.printDocument(document, this);

    }

    @Override
    public int getTaskCount() {
        return this.taskCount;
    }

    @Override
    public int getResourceCount() {
        return 1;
    }

    public static void main(String[] args) {
        Printer printer = new Printer();
        Clerk clerk = new Clerk(printer);
        System.out.println(printer.getPrintHistory(clerk));
        clerk.printDocument("dokument");
        System.out.println(printer.getPrintHistory(clerk));
        printer.getPrintHistory(clerk).remove("dokument");
        System.out.println(printer.getPrintHistory(clerk));

    }
}
