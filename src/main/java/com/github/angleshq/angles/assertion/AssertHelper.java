package com.github.angleshq.angles.assertion;

import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;

import static com.github.angleshq.angles.util.AnglesReporterUtils.getAnglesReporter;
import static org.apache.commons.lang3.StringUtils.EMPTY;

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

    public static void handleDoesNotThrow(String step, Executable executable) {
        handleDoesNotThrow(step, executable, EMPTY);
    }

    public static void handleDoesNotThrow(String step, Executable executable, String details) {
        try {
            executable.execute();
        } catch(Throwable e) {
            getAnglesReporter().fail(step, "No Exception thrown",
                    e.getClass().getSimpleName() + " : " + e.getMessage(), details);
            return;
        }
        getAnglesReporter().pass(step, "No Exception thrown", "No Exception thrown", details);
    }

    public static void handleAssertSame(String step, Object expected, Object actual) {
        handleAssertSame(step, expected, actual, EMPTY);
    }

    public static void handleAssertSame(String step, Object expected, Object actual, String details) {
        if(expected == actual) {
            getAnglesReporter().pass(step, expected.toString(), actual.toString(), details);
        } else {
            getAnglesReporter().fail(step, expected.toString(), actual.toString(), details);
        }
    }

    public static void handleAssertNotSame(String step, Object expected, Object actual) {
        handleAssertEquals(step, expected, actual, EMPTY);
    }

    public static void handleAssertNotSame(String step, Object expected, Object actual, String details) {
        if(expected != actual) {
            getAnglesReporter().pass(step, expected.toString(), actual.toString(), details);
        } else {
            getAnglesReporter().fail(step, expected.toString(), actual.toString(), details);
        }
    }

    public static void handleAssertEquals(String step, Object expected, Object actual) {
        handleAssertEquals(step, expected, actual, EMPTY);
    }

    public static void handleAssertEquals(String step, Object expected, Object actual, String details) {
        if(expected.equals(actual)) {
            getAnglesReporter().pass(step, expected.toString(), actual.toString(), details);
        } else {
            getAnglesReporter().fail(step, expected.toString(), actual.toString(), details);
        }
    }

    public static void handleAssertNotEquals(String step, Object expected, Object actual) {
        handleAssertNotEquals(step, expected, actual, EMPTY);
    }

    public static void handleAssertNotEquals(String step, Object expected, Object actual, String details) {
        if(!expected.equals(actual)) {
            getAnglesReporter().pass(step, expected.toString(), actual.toString(), details);
        } else {
            getAnglesReporter().fail(step, expected.toString(), actual.toString(), details);
        }
    }
}
