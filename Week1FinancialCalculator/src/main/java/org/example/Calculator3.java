package org.example;

import java.util.Scanner;

public class Calculator3 {
    public static void main(String[] args) {
        //Formula: PV = PMT × [(1 - (1 + r)^(-n)) / r]
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is your monthly payout amount?");
        double pMt = scanner.nextDouble();
        System.out.println("What is your expected interest rate?");
        double r = scanner.nextDouble();
        System.out.println("How many years to pay out?");
        double t = scanner.nextDouble();

        //n is the monthly payments
        double n = t * 12;

        //r2 is monthly Rate
        double r2 = (r / 12) / 100;
        double monthlyTotal = n * 12;

        double pV = pMt * (1 - Math.pow(1 + r2 , -n)) / r2;
        System.out.printf("Present annuity value is %.2f", pV);


    }
}
