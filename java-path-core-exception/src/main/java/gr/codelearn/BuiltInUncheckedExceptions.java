package gr.codelearn;

public class BuiltInUncheckedExceptions {
    public static void main(String[] args) {
        divisionByZero();
//        arrayOutOfBounds();
        System.out.println("The program continued");
    }

    private static int divisionByZero() {
        try {
            return 5 / 0;
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    private static int arrayOutOfBounds() {
        int array[] = new int[20];
        return array[23];
    }
}
