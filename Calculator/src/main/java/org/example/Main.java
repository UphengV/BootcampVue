package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number 1");
        int num1 = scanner.nextInt();

        System.out.println("Enter number 2");
        int num2 = scanner.nextInt();


        System.out.println("Input (+,-,*,/)? "); //used char instead of string.
        //char: scanner.next().charAt(0) extracts index 0 char. In simple terms char gives value. The
        char operator = scanner.next().charAt(0);


        int result = 0;//added 0 to be able to look for result
        switch (operator) {
            case '+':
                result = num1 + num2; //had to put print inside each case
                System.out.println("Math is hard.. this might take awhile..");
                System.out.println("sorry uh... " +result);
                break;
            case '-':
                result = num1 - num2;
                System.out.println("Math is hard.. this might take awhile..");
                System.out.println("sorry uh... " +result);
                break;
            case '*':
                result = num1 * num2;
                System.out.println("Math is hard.. this might take awhile..");
                System.out.println("sorry uh... " +result);
                break;

            case '/':
                result = num1 / num2;
                System.out.println("Math is hard.. this might take awhile..");
                System.out.println("sorry uh... " +result);
                break;
            default://is the default, last computer option
                System.out.println("read the directions");

        }

    }
}