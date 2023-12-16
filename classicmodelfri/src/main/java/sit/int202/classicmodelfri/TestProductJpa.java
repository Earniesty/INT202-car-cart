package sit.int202.classicmodelfri;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import sit.int202.classicmodelfri.entities.Environment;
import sit.int202.classicmodelfri.entities.Product;
import sit.int202.classicmodelfri.repositories.ProductRepository;

import java.util.List;

public class TestProductJpa {
    public static void main(String[] args) {
        ProductRepository repository = new ProductRepository();
        int page = 1;
        while(true) {
            List<Product> productList = repository.findAll(page, 20);
            if (productList.isEmpty()) {
                break;
            }
            page++;
            productList.forEach(p-> System.out.printf("%4s %s\n",
                    p.getProductCode(), p.getProductName()));
            System.out.println("----------------");
        }
    }
}
