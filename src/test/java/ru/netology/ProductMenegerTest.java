package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.ProductMeneger;
import ru.netology.ProductRepo;

public class ProductMenegerTest {


    public void testProduct (Product expected, Product actual)
    {
        Assertions.assertEquals (expected.getId(), actual.getId());
        Assertions.assertEquals (expected.getName(), actual.getName());
        Assertions.assertEquals (expected.getPrice(), actual.getPrice());
    }

    public void testBook (Book expected, Book actual)
    {
        testProduct(expected, actual);

        Assertions.assertEquals (expected.getAuthor(), actual.getAuthor());
    }

    public void testSmartphone (Smartphone expected, Smartphone actual)
    {
        testProduct(expected, actual);

        Assertions.assertEquals (expected.getVendor(), actual.getVendor());
    }

    @Test

    public void repoTest () {
        ProductRepo repo = new ProductRepo();
        ProductMeneger manager = new ProductMeneger(repo);
        Book book1 = new Book(122, "Book 1", 500,"Author 1");
        Book book2 = new Book(132, "Book 2", 600,"Author 2");
        Smartphone smartphone1 = new Smartphone(344, "Smartphone1", 7_000,"Vendor1");
        manager.add(book1);
        manager.add(book2);
        manager.add(smartphone1);

        Product [] actual = repo.findAll();
        testBook(book1, (Book) actual[0]);
        testBook(book2, (Book) actual[1]);
        testSmartphone(smartphone1, (Smartphone) actual [2]);

        repo.deleteProduct(132);
        Product [] actualAfterDelete = repo.findAll();
        Assertions.assertEquals(actualAfterDelete.length, 2);
        testBook(book1, (Book) actualAfterDelete[0]);
        testSmartphone(smartphone1, (Smartphone) actualAfterDelete [1]);
    }

    @Test

    public void searchTestFewProductsFound () {
        ProductRepo repo = new ProductRepo();
        ProductMeneger manager = new ProductMeneger(repo);
        Book book1 = new Book(122, "Book 1", 500,"Author 1");
        Book book2 = new Book(132, "Book 2", 600,"Author 2");
        Book book3 = new Book(142, "Test", 700,"Author 3");
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);

        Product [] actual = manager.searchBy("Book");
        Assertions.assertEquals(actual.length, 2);
        testBook(book1, (Book) actual[0]);
        testBook(book2, (Book) actual[1]);


    }

    @Test

    public void deleteByIdTest () {
        ProductRepo repo = new ProductRepo();
        ProductMeneger manager = new ProductMeneger(repo);
        Book book1 = new Book(122, "Book 1", 500, "Author 1");
        Book book2 = new Book(132, "Book 2", 600, "Author 2");
        Smartphone smartphone1 = new Smartphone(344, "Smartphone1", 7_000, "Vendor1");
        manager.add(book1);
        manager.add(book2);
        manager.add(smartphone1);

        repo.deleteProduct(132);

        Assertions.assertEquals(repo.findById(132), null);
    }

    @Test

    public void deleteByIdExceptionTest () {
        ProductRepo repo = new ProductRepo();
        ProductMeneger manager = new ProductMeneger(repo);
        Book book1 = new Book(122, "Book 1", 500, "Author 1");
        Book book2 = new Book(132, "Book 2", 600, "Author 2");
        Smartphone smartphone1 = new Smartphone(344, "Smartphone1", 7_000, "Vendor1");
        manager.add(book1);
        manager.add(book2);
        manager.add(smartphone1);

        Assertions.assertThrows(NotFoundException.class, () -> {repo.deleteProduct(47);});

    }


}
