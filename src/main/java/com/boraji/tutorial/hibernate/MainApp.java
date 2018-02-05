package com.boraji.tutorial.hibernate;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author imssbora
 */
public class MainApp {
  public static void main(String[] args) {
    EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
    entityManager.getTransaction().begin();

    BookService service = new BookService(entityManager);

    /*
    // Check database version
    String sql = "select version()";

    String result = (String) entityManager.createNativeQuery(sql).getSingleResult();
    System.out.println(result);


    // Creates and persists a Book
    Book book = service.createBook(4045L, "JPA for Everyone", "Best JPA Tutorial Book", 12.5f, "5234-5678-5678", 100);

    // Finds the book
    book = service.findBook(4045L);

    System.out.println("Book Found     : " + book);

    // Raises the price of the book
    book = service.raiseUnitCost(4045L, 12.5F);
    System.out.println("Book Updated   : " + book);
     */

    //Query books
    System.out.println("Book Dynamic Query Result:");
    List<Book> books = service.queryBooks(" unitCost > 13");
    for (Book b : books) {
      System.out.println(b);
    }


    entityManager.getTransaction().commit();
    entityManager.close();

    JPAUtil.shutdown();
  }
}
