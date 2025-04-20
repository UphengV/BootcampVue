package org.example;

import java.util.Scanner;

public class Book {
    private int id;
    private String isbn;
    private String title;
    private boolean isCheckout;
    private String checkedOutTo;

    public Book(int id, String isbn, String title, boolean isCheckout, String checkedOutTo) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.isCheckout = isCheckout;
        this.checkedOutTo = checkedOutTo;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCheckout() {
        return isCheckout;
    }

    public void setCheckout(boolean checkout) {
        isCheckout = checkout;
    }

    public String getCheckedOutTo() {
        return checkedOutTo;
    }

    public void setCheckedOutTo(String checkedOutTo) {
        this.checkedOutTo = checkedOutTo;
    }

    public void checkoutOut(String name) {
        this.isCheckout = true;
        this.checkedOutTo = name;
    }

    public void checkIn() {
        this.isCheckout = false;
        this.checkedOutTo = "";

    }

    public static Book[] mockInventory() {
        Book[] inventory = new Book[20];

        inventory[0] = new Book(1, "978-0140449136", "The Odyssey", false, "");
        inventory[1] = new Book(2, "978-0261103573", "The Fellowship of the Ring", true, "Alice");
        inventory[2] = new Book(3, "978-0439139601", "Harry Potter and the Goblet of Fire", true, "Bob");
        inventory[3] = new Book(4, "978-0735219090", "Where the Crawdads Sing", false, "");
        inventory[4] = new Book(5, "978-0307474278", "The Girl with the Dragon Tattoo", true, "Charlie");
        inventory[5] = new Book(6, "978-0451524935", "1984", false, "");
        inventory[6] = new Book(7, "978-0061120084", "To Kill a Mockingbird", true, "Diana");
        inventory[7] = new Book(8, "978-0743273565", "The Great Gatsby", false, "");
        inventory[8] = new Book(9, "978-0385472579", "Zen and the Art of Motorcycle Maintenance", true, "Eve");
        inventory[9] = new Book(10, "978-0316017923", "Thinking, Fast and Slow", false, "");
        inventory[10] = new Book(11, "978-0143127741", "Sapiens: A Brief History of Humankind", true, "Frank");
        inventory[11] = new Book(12, "978-0062316097", "The Alchemist", false, "");
        inventory[12] = new Book(13, "978-0156027328", "Life of Pi", true, "Grace");
        inventory[13] = new Book(14, "978-1402833344", "Guns, Germs, and Steel", false, "");
        inventory[14] = new Book(15, "978-0061120084", "To Kill a Mockingbird", true, "Hank");
        inventory[15] = new Book(16, "978-0307387899", "The Road", false, "");
        inventory[16] = new Book(17, "978-0385660075", "The Kite Runner", true, "Ivy");
        inventory[17] = new Book(18, "978-0316769488", "The Catcher in the Rye", false, "");
        inventory[18] = new Book(19, "978-0345803481", "Fifty Shades of Grey", true, "Jack");
        inventory[19] = new Book(20, "978-1451673319", "Fahrenheit 451", false, "");


        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1.)Home Screen");
            System.out.println("2.)Available Books");
            System.out.println("3.)Checked out Books");
            System.out.println("4.)Check in a Book");

            int firstScreen = scanner.nextInt();

            switch (firstScreen) {
                case 1:
                    System.out.println("Welcome to the Library!");
                    System.out.println("1.)Exit");
                    int existScreen1 = scanner.nextInt();
                    mockInventory();
                    break;
                case 2:
                    listBooks(inventory, scanner);
                    break;
                case 3:
                    checkedOutBooks(inventory, scanner);
                    break;
                case 4:
                    checkInBooks(inventory, scanner);
                    break;
                default:
                    System.out.println("Please Input correct number");
                    mockInventory();

            }
        }

    }

    public static void listBooks(Book[] inventory, Scanner scanner) {
        System.out.println("1.)List of books");
        System.out.println("2.)Check out a book?");
        System.out.println("3.)Exit");

        //int number1 = 1;
        //int number2 = 2;
        //int number3 = 3;

        int secondScreen = scanner.nextInt();


        //for (int i = 0; i < inventory.length; i++) {
        //Book listBooks = inventory[i];

        switch (secondScreen) {
            case 1:
                System.out.println("Here is a list of our books!");

                for (int i = 0; i < inventory.length; i++) {
                    Book listBooks = inventory[i];

                    System.out.println(listBooks.getTitle());
                }

                System.out.println("1.)Exit");
                int exitSecondScreen = scanner.nextInt();
                break;
            case 2:
                System.out.println("These books are available!");
                for (int i = 0; i < inventory.length; i++) {
                    Book listBooks = inventory[i];

                    if (listBooks.getCheckedOutTo().equals("")) {

                        System.out.println(listBooks.getId() + "." + " " + listBooks.getTitle());
                    }
                }


                System.out.println("Input the ID.");
                int userCheckout = scanner.nextInt();

                System.out.println("Please type your name");
                scanner.nextLine();
                String userName = scanner.next();


                for (int i = 0; i < inventory.length; i++) {
                    Book listBooks = inventory[i];
                    if (inventory[i].getId() == userCheckout && inventory[i].getCheckedOutTo().equals(""));
                inventory[i].setCheckedOutTo(userName);}
                System.out.println("Thank you! " + userName);
                System.out.println("1.)Exit");
                int exitSecondScreen2 = scanner.nextInt();
                mockInventory();
                break;
            case 3:
                System.out.println(mockInventory());
                break;
            default:
                System.out.println("Wrong. You can't read huh?");
                mockInventory();

        }
    }

    public static void checkedOutBooks(Book[] inventory, Scanner scanner) {
        System.out.println("These are the books that are checked out!");
        for (int i = 0; i < inventory.length; i++) {
            Book listBooks = inventory[i];
            if (listBooks.isCheckout()) {
                System.out.println(listBooks.getCheckedOutTo());
                System.out.println(listBooks.getTitle());
                System.out.println(listBooks.getId());
                System.out.println(listBooks.getIsbn());
            }
        }
        System.out.println("1.)Exit");
        int thirdScreenExit = scanner.nextInt();
        mockInventory();
    }

    public static void checkInBooks(Book[] inventory, Scanner scanner) {
        System.out.println("Check in your book!");
        for (int i = 0; i < inventory.length; i++) {
            Book listBooks = inventory[i];

            if (!listBooks.getCheckedOutTo().equals("")) {

                System.out.println(listBooks.getId() + "." + " " + listBooks.getTitle());
            }
        }

        System.out.println("Please input book ID");
        int bookId = scanner.nextInt();
        for (int i = 0; i < inventory.length; i++) {
            Book listBooks = inventory[i];
            if (inventory[i].getId() == bookId) {
                inventory[i].setCheckedOutTo("");
            }
            break;
        }
        System.out.println("Please type your name");
        scanner.nextLine();
        String userName1 = scanner.next();
        System.out.println("Thank you! " + userName1);


        System.out.println("1.) Exit");
        int fourthScreenExit = scanner.nextInt();
        switch (fourthScreenExit) {
            case 1:
                mockInventory();
                break;
            default:
                System.out.println("Error you can't read..");

        }
    }
}













