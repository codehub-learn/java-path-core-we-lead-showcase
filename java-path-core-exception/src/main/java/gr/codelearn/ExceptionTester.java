package gr.codelearn;

import gr.codelearn.exception.NumberBelowZeroException;
import gr.codelearn.exception.NumberIsZeroException;

public class ExceptionTester {
    public static void main(String[] args) throws NumberIsZeroException {
//        int number = tryCatchFinallySpecialCase("asdfa");
        int number = throwMultipleCustomExceptions("-1");
        System.out.println(number);
        System.out.println("The program still runs");
    }

    private static int tryCatchFinallySpecialCase(String userInput) {
        try {
            int i = Integer.parseInt(userInput);
            return i;
        } catch (NumberFormatException e) {
            return -1;
        } finally {
            return 999;
        }
    }

    private static int throwMultipleCustomExceptions(String userInput) throws NumberIsZeroException {
        int i = 0;
        try {
            i = Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        if (i == 0) {
            throw new NumberIsZeroException("Number should not 0");
        }
        if (i < 0) {
            throw new NumberBelowZeroException("Number should not be below 0");
        }
        return i;
    }

    public static int tryWithOneCatchMultipleExceptions(String userInput) {
        //handles wrong user input with one catch clause which contains multiple exceptions (business rules and built-in)
        try {
            int i = Integer.parseInt(userInput);
            if (i == 0) {
                throw new NumberIsZeroException("Number should not 0.");
            }
            if (i < 0) {
                throw new NumberBelowZeroException("Number should not be below 0.");
            }
            return i;
        } catch (NumberFormatException | NumberBelowZeroException | NumberIsZeroException e) {
            return -1;
        }
    }
}
