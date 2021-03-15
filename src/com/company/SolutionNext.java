package com.company;

import java.math.BigInteger;
import java.util.Stack;

public class SolutionNext {
    private final static String POP_COMMAND = "POP";
    private final static String DUPLICATE_COMMAND = "DUP";
    private final static String ADD_TWO_LAST_COMMAND = "+";
    private final static String SUBTRACT_TWO_LAST_COMMAND = "-";
    private final static BigInteger MAX_ALLOWED_INT = BigInteger.valueOf(Integer.MAX_VALUE);
    private final static String DELIMITER = " ";
    private final static int MIN_ALLOWED_NUMBER = 0;
    private final static int MAX_ALLOWED_NUMBER = 1048575;
    private final static int ERROR_CODE = -1;
    private final Stack<Integer> wordMachineStack = new Stack<>();
    public int solution(String S) {
        if (S == null || S.isEmpty()) {
            //log error
            return ERROR_CODE;
        }
        final var commands = S.split(DELIMITER);
        final var lastCommandIndex = commands.length - 1;
        try {
            for(int i=0; i<lastCommandIndex; ++i) {
                runCommand(commands[i]);
            }
            return runCommand(commands[lastCommandIndex]);
        } catch (Exception ex) {
            //Here should be logged exception
            return ERROR_CODE;
        }
    }

    private int runCommand(String command) {
        if (wordMachineStack.size() == 20000) {
            throw new RuntimeException("Stack overflow");
        }
        if (isPositiveNumber(command)) {
            final var givenNumber = new BigInteger(command);
            if (givenNumber.compareTo(MAX_ALLOWED_INT) >= 0) {
                throw new IllegalArgumentException("Only allowed numbers are between 0 and 1048575");
            }
            return wordMachineStack.push(givenNumber.intValue());
        }
        switch (command) {
            case POP_COMMAND:
                return wordMachineStack.pop();
            case DUPLICATE_COMMAND:
                return wordMachineStack.push(wordMachineStack.peek());
            case ADD_TWO_LAST_COMMAND:
                return performTwoLastAdd();
            case SUBTRACT_TWO_LAST_COMMAND:
                return performTwoLastSubtraction();
            default:
                throw new IllegalArgumentException("Only allowed values are [POP, DUP,+, -] and any number between 0 and 1048575");
        }
    }

    private int performTwoLastAdd() {
        int firstElement = wordMachineStack.pop();
        int secondElement = wordMachineStack.pop();
        int sum = firstElement + secondElement;
        if (sum > MAX_ALLOWED_NUMBER) {
            wordMachineStack.push(firstElement);
            wordMachineStack.push(secondElement);
            throw new ArithmeticException("Max allowed value is 1048575");
        }
        return wordMachineStack.push(sum);
    }

    private int performTwoLastSubtraction() {
        int firstElement = wordMachineStack.pop();
        int secondElement = wordMachineStack.pop();
        int difference = firstElement - secondElement;
        if (difference < MIN_ALLOWED_NUMBER) {
            wordMachineStack.push(firstElement);
            wordMachineStack.push(secondElement);
            throw new ArithmeticException("Min allowed value is 0");
        }
        return wordMachineStack.push(difference);
    }

    private boolean isPositiveNumber(String S) {
        return S.chars().allMatch( Character::isDigit );
    }
}
