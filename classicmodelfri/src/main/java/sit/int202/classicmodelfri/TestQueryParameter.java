package sit.int202.classicmodelfri;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import sit.int202.classicmodelfri.entities.Environment;
import sit.int202.classicmodelfri.entities.Office;

import java.util.List;
import java.util.Scanner;

public class TestQueryParameter {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Environment.PU_NAME);
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("OFFICE.FIND_BY_COUNTRY");
        Scanner sc =new Scanner(System.in);
        while(true) {
            System.out.print("Search office by country enter 0 to exit: ");
            String country = sc.next();
            if (country.equalsIgnoreCase("0")) {
                break;
            }
            query.setParameter("countryParam", country + "%");
            List<Office> officeList = query.getResultList();
            for (Office o : officeList) {
                System.out.printf("%-2s %-25s %-13s %s\n",
                        o.getOfficeCode(), o.getAddressLine1(), o.getCity(), o.getCountry());
            }
        }
        em.close();
    }
}
