package service;

import dao.BookDao;
import model.Book;

public class BookService {
    BookDao dao = new BookDao();

    public void addBook(Book book) {
        dao.addBook(book);
    }

    public void updatePrice(int bookId, double price) {
        dao.updatePrice(bookId, price);
    }

    public void deleteBook(int bookId) {
        dao.deleteBook(bookId);
    }

    public void generateBill(int bookId, int quantity) {
        dao.generateBill(bookId, quantity);
    }

    public void searchById(int bookId) {
        dao.searchById(bookId);
    }

    public void searchByTitle(String title) {
        dao.searchByTitle(title);
    }
}