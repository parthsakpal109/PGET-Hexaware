package com.assignment.bookstore;

import java.util.Scanner;

import model.Book;
import service.BookService;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookService service = new BookService();

        while (true) {
            System.out.println("\n1. Add Book\n2. Update Price\n3. Delete Book\n4. Generate Bill\n5. Search by ID\n6. Search by Title\n0. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    Book book = new Book();
                    System.out.print("Title: ");
                    book.setTitle(sc.nextLine());
                    System.out.print("Author: ");
                    book.setAuthor(sc.nextLine());
                    System.out.print("Price: ");
                    book.setPrice(sc.nextDouble());
                    System.out.print("Quantity: ");
                    book.setQuantity(sc.nextInt());
                    service.addBook(book);
                    break;
                case 2:
                    System.out.print("Book ID: ");
                    int id = sc.nextInt();
                    System.out.print("New Price: ");
                    double price = sc.nextDouble();
                    service.updatePrice(id, price);
                    break;
                case 3:
                    System.out.print("Book ID to delete: ");
                    int delId = sc.nextInt();
                    service.deleteBook(delId);
                    break;
                case 4:
                    System.out.print("Book ID: ");
                    int bid = sc.nextInt();
                    System.out.print("Quantity: ");
                    int qty = sc.nextInt();
                    service.generateBill(bid, qty);
                    break;
                case 5:
                    System.out.print("Enter Book ID: ");
                    service.searchById(sc.nextInt());
                    break;
                case 6:
                    System.out.print("Enter Book Title: ");
                    service.searchByTitle(sc.nextLine());
                    break;
                case 0:
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
