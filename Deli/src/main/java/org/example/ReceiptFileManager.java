package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReceiptFileManager {
    public static void saveReceipt(Order order) {
        String folderName = "receipts";
        String filename = folderName + "/" + new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date()) + ".txt";

        File dir = new File(folderName);
        if (!dir.exists()) {
            dir.mkdirs();
            // creates receipts folder if it doesn't exist
        }

        try (FileWriter fw = new FileWriter(filename)) {
            fw.write(order.getSummary());
            System.out.println("Receipt saved to: " + filename);
        } catch (IOException e) {
            System.out.println("Error saving receipt: " + e.getMessage());
        }
    }
}
