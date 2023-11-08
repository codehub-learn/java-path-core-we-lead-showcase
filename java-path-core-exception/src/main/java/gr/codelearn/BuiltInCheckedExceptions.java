package gr.codelearn;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BuiltInCheckedExceptions {
    public static void main(String[] args) {
//        readFromFile();
//        readFromFileBetter();
        loadClassInformation();
        System.out.println("The program continued");
    }

    private static void readFromFile() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("java-path-core-exception\\targest\\classes\\example.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Problem reading the file:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println("Cannot close buffered reader" + e.getMessage());
                }
            }
            System.out.println("\nFinally block: This will always run\n");
        }
    }

    private static void readFromFileBetter() {
        try (BufferedReader br = new BufferedReader(new FileReader("java-path-core-exception\\target\\classes\\example.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Class<?> loadClassInformation() {
        try {
            return Class.forName("gr.codeleasrn.BuiltInCheckedExceptions");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
