package org.example;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public static List<Transactions> readfile() {

        try {
            FileReader fileReader = new FileReader("src/main/resources/finaltransactions.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();

            String input;

            List<Transactions> transactionsList = new ArrayList<>();
            while ((input = bufferedReader.readLine()) != null) {

                String[] row = input.split("\\|");

                LocalDate date = LocalDate.parse(row[0].trim());
                LocalTime time = LocalTime.parse(row[1].trim());
                String description = row[2].trim();
                String vendor = row[3].trim();
                BigDecimal amount = new BigDecimal(row[4].trim());

                Transactions transactions = new Transactions(date, time, description, vendor, amount);
                transactionsList.add(transactions);

            }

            bufferedReader.close();

            return transactionsList;
        } catch (IOException ex) {
            System.out.println("CSV FILE FAILED");
            return new ArrayList<>();
        }

    }

    public static void appendTransaction(Transactions transactions) {
        String filePath = "src/main/resources/finaltransactions.txt";
        File file = new File(filePath);
        try {
            File folder = file.getParentFile();
            if (!folder.exists()) {
                folder.mkdirs();
            }
            boolean fileExists = file.exists();
            boolean isEmpty = !fileExists || file.length() == 0;

            FileWriter writer = new FileWriter(file, true);

            if (isEmpty) {
                writer.write("date|time|description|vendor|amount\n");
            }
            writer.write(transactions.toString() + "\n");



                writer.close();
            } catch (IOException ex) {
            System.out.println("Something ain't right");
        }
    }

}
