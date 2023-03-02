package oving5;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.BinaryOperator;

public class BinaryComputingIterator implements Iterator<Double> {

    private Iterator iterator1;
    private Iterator iterator2;
    private BinaryOperator operator;
    private Double defaultValue1;
    private Double defaultValue2;

    public BinaryComputingIterator(Iterator<Double> iterator1, Iterator<Double> iterator2,
            BinaryOperator<Double> operator) {
        this.iterator1 = iterator1;
        this.iterator2 = iterator2;
        this.operator = operator;

    }

    public BinaryComputingIterator(Iterator<Double> iterator1, Iterator<Double> iterator2, Double default1,
            Double default2, BinaryOperator<Double> operator) {
        this.iterator1 = iterator1;
        this.iterator2 = iterator2;
        this.operator = operator;
        this.defaultValue1 = default1;
        this.defaultValue2 = default2;
    }

    @Override
    public boolean hasNext() {
        if (iterator1.hasNext() || iterator2.hasNext()) {

            if (!iterator1.hasNext() && defaultValue1 == null) {
                return false;
            } else if (!iterator2.hasNext() && defaultValue2 == null) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Double next() {

        if (iterator1.hasNext() && iterator2.hasNext()) {
            return calculate(iterator1.next(), iterator2.next());

        } else if (iterator1.hasNext() && !iterator2.hasNext()) {
            return calculate(iterator1.next(), defaultValue2);

        } else if (!iterator1.hasNext() && iterator2.hasNext()) {
            return calculate(defaultValue1, iterator2.next());
        } else {
            return null;
        }

    }


    private Double calculate(Object t, Object u) {

        return (Double) operator.apply(t, u);
    }

    public static void main(String[] args) {

        Iterator<Double> iterator1 = Arrays.asList(2.0, 3.0).iterator();
        Iterator<Double> iterator2 = Arrays.asList(5.0).iterator();

        BinaryOperator<Double> addition = (a, b) -> a + b;

        // Opprett en ny BinaryComputingIterator som tar inn iterator1 og iterator2 og
        // utfører addisjon på verdiene.
        BinaryComputingIterator binaryIterator = new BinaryComputingIterator(iterator1, iterator2, null, 10.0,
                addition);

        System.out.println(binaryIterator.next()); // 7.0)
        System.out.println(binaryIterator.hasNext()); // true)
        System.out.println(binaryIterator.next()); // 13.0)
        System.out.println(binaryIterator.hasNext());

    }

}
