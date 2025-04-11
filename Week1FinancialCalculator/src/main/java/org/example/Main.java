package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("What is your Principal?");
        double amountP = scanner.nextDouble();
        System.out.println("What is your interest rate?");
        double interestR = scanner.nextDouble();
        System.out.println("what is your loan length?");
        double loanL = scanner.nextDouble();
        //Monthly Payment,Principal,Rate,Length

        //monthlyP = amountP *(interestR^(1+interestR)^numberMp)-1);
        //total interest = (monthlyP *numberMp)-P
        // interestR / 100 TURNS IT INTO A DECIMAl
        double monthlyRate = (interestR / 100 / 12);
        double months = loanL * 12;

        double monthlyP = amountP * (monthlyRate *Math.pow(1+ monthlyRate, months)) / (Math.pow(1+monthlyRate, months)-1);
        //used Math.pow twice ^ because of the / and equation


        //total interest = (M * n) - P

        double anwser = (monthlyP * months) - amountP;
        System.out.printf("Your monthly payment is $%.2f and your interest you will pay $%.2f", monthlyP, anwser);













    }
}