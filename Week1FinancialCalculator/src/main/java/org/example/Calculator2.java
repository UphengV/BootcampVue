package org.example;

import java.util.Scanner;

public class Calculator2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is your initial deposit?");
        double p = scanner.nextDouble();
        System.out.println("What is your annual interest rate?");
        double r = scanner.nextDouble();
        System.out.println("Number of years");
        double t = scanner.nextDouble();
        //FV = P(1 + r/n)^(n*t)
        //n is number of days in a year 365

        double n = 365;
        double d = (r / 100);

        //double r2 = r / n;
        // double d is r converted into decimal

        double fV = p * Math.pow(1 + d / n, n * t);

        //Total Interest = fV - P

        double totalInterest = fV - p;

        System.out.printf("Your ending value is $%.2f and your earned interest is $%.2f" , fV , totalInterest);

    }
}
