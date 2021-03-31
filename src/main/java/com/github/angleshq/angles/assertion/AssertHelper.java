package com.github.angleshq.angles.assertion;

import java.math.BigDecimal;

import static com.github.angleshq.angles.util.AnglesReporterUtils.getAnglesReporter;

public class AssertHelper {

    public static boolean anglesAssertGreaterThan(Double value1, Double value2) {
        BigDecimal a = new BigDecimal(value1.toString());
        BigDecimal b = new BigDecimal(value2.toString());
        boolean comparison = a.compareTo(b) == 1;
        handleAssertEquals("AssertGreaterThan (Double)", true, comparison,
                "Checking if [" + a.toPlainString() + "] is greater than [" + b.toPlainString() + "]");
        return comparison;
    }

    public static boolean anglesAssertLessThan(Double value1, Double value2) {
        BigDecimal a = new BigDecimal(value1.toString());
        BigDecimal b = new BigDecimal(value2.toString());
        boolean comparison = a.compareTo(b) == -1;
        handleAssertEquals("AssertLessThan (Double)", true, comparison,
                "Checking if [" + a.toPlainString() + "] is less than [" + b.toPlainString() + "]");
        return comparison;
    }

    public static boolean anglesAssertGreaterThan(Integer value1, Integer value2) {
        BigDecimal a = new BigDecimal(value1.toString());
        BigDecimal b = new BigDecimal(value2.toString());
        boolean comparison = a.compareTo(b) == 1;
        handleAssertEquals("AssertGreaterThan (Integer)", true, comparison,
                "Checking if [" + a.toPlainString() + "] is greater than [" + b.toPlainString() + "]");
        return comparison;
    }

    public static boolean anglesAssertLessThan(Integer value1, Integer value2) {
        BigDecimal a = new BigDecimal(value1.toString());
        BigDecimal b = new BigDecimal(value2.toString());
        boolean comparison = a.compareTo(b) == -1;
        handleAssertEquals("AssertLessThan (Integer)", true, comparison,
                "Checking if [" + a.toPlainString() + "] is less than [" + b.toPlainString() + "]");
        return comparison;
    }

    public static boolean anglesAssertGreaterThan(Float value1, Float value2) {
        BigDecimal a = new BigDecimal(value1.toString());
        BigDecimal b = new BigDecimal(value2.toString());
        boolean comparison = a.compareTo(b) == 1;
        handleAssertEquals("AssertGreaterThan (Float)", true, comparison,
                "Checking if [" + a.toPlainString() + "] is greater than [" + b.toPlainString() + "]");
        return comparison;
    }

    public static boolean anglesAssertLessThan(Float value1, Float value2) {
        BigDecimal a = new BigDecimal(value1.toString());
        BigDecimal b = new BigDecimal(value2.toString());
        boolean comparison = a.compareTo(b) == -1;
        handleAssertEquals("AssertLessThan (Float)", true, comparison,
                "Checking if [" + a.toPlainString() + "] is less than [" + b.toPlainString() + "]");
        return comparison;
    }

    public static boolean anglesAssertGreaterThan(String value1, String value2) {
        BigDecimal a = new BigDecimal(value1);
        BigDecimal b = new BigDecimal(value2);
        boolean comparison = a.compareTo(b) == 1;
        handleAssertEquals("AssertGreaterThan (String)", true, comparison,
                "Checking if [" + a.toPlainString() + "] is greater than [" + b.toPlainString() + "]");
        return comparison;
    }

    public static boolean anglesAssertLessThan(String value1, String value2) {
        BigDecimal a = new BigDecimal(value1);
        BigDecimal b = new BigDecimal(value2);
        boolean comparison = a.compareTo(b) == -1;
        handleAssertEquals("AssertLessThan (String)", true, comparison,
                "Checking if [" + a.toPlainString() + "] is less than [" + b.toPlainString() + "]");
        return comparison;
    }

    private static void handleAssertEquals(String step, Object expected, Object actual, String details) {
        if(expected.equals(actual)) {
            getAnglesReporter().pass(step, expected.toString(), actual.toString(), details);
        } else {
            getAnglesReporter().fail(step, expected.toString(), actual.toString(), details);
        }
    }
}
