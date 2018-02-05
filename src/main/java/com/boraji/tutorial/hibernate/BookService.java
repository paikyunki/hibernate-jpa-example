package com.boraji.tutorial.hibernate;

import com.boraji.tutorial.hibernate.Book;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
public class BookService {

  // ======================================
  // =             Attributes             =
  // ======================================

  private EntityManager em;

  // ======================================
  // =            Constructors            =
  // ======================================

  public BookService(EntityManager em) {
    this.em = em;
  }

  // ======================================
  // =           Public Methods           =
  // ======================================

  public Book createBook(Book book) {
    em.persist(book);
    return book;
  }

  public Book createBook(Long id, String title, String description, Float unitCost, String isbn, Integer nbOfPage) {
    Book book = new Book();
    book.setId(id);
    book.setTitle(title);
    book.setDescription(description);
    book.setUnitCost(unitCost);
    book.setIsbn(isbn);
    book.setNbOfPage(nbOfPage);
    em.persist(book);
    return book;
  }

  public void removeBook(Book book) {
    em.remove(em.merge(book));
  }

  public void removeBook(Long id) {
    Book book = em.find(Book.class, id);
    if (book != null)
      em.remove(book);
  }

  public Book findBook(Long id) {
    return em.find(Book.class, id);
  }

  public List<Book> queryBooks(String clause) {
    if (clause != null) {
      clause = " where " + clause;
    }
    Query query = em.createQuery("select b from Book b " + clause);
    List<Book> books = query.getResultList();
    return books;
  }

  public Book raiseUnitCost(Long id, Float raise) {
    Book book = em.find(Book.class, id);
    if (book != null)
      book.setUnitCost(book.getUnitCost() + raise);
    return book;
  }
}