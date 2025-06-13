package dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.Book;
import util.HibernateUtil;
public class BookDao {

	public void addBook(Book book) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(book);
        tx.commit();
        session.close();
    }

    public void updatePrice(int bookId, double newPrice) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("update Book set price = :price where bookId = :id");
        query.setParameter("price", newPrice);
        query.setParameter("id", bookId);
        query.executeUpdate();
        tx.commit();
        session.close();
    }

    public void deleteBook(int bookId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("delete from Book where bookId = :id");
        query.setParameter("id", bookId);
        query.executeUpdate();
        tx.commit();
        session.close();
    }

    public void generateBill(int bookId, int qty) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Book book = session.get(Book.class, bookId);
        if (book != null) {
            if (book.getQuantity() >= qty) {
                System.out.println("Title: " + book.getTitle());
                System.out.println("Unit Price: " + book.getPrice());
                System.out.println("Total Amount: " + (book.getPrice() * qty));
            } else {
                System.out.println("Insufficient stock.");
            }
        } else {
            System.out.println("Book not found.");
        }
        session.close();
    }

    public void searchById(int bookId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Book> query = session.createQuery("from Book where bookId = :id", Book.class);
        query.setParameter("id", bookId);
        Book book = query.uniqueResult();
        System.out.println(book != null ? book : "Book not found.");
        session.close();
    }

    public void searchByTitle(String title) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Book> query = session.createQuery("from Book where title like :title", Book.class);
        query.setParameter("title", "%" + title + "%");
        List<Book> books = query.list();
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            books.forEach(System.out::println);
        }
        session.close();
    }
}