package sit.int202.classicmodelfri;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import sit.int202.classicmodelfri.entities.Customer;
import sit.int202.classicmodelfri.repositories.CustomerRepository;

import java.util.Scanner;

public class TestArgon2 {
    private static void initPassword(Argon2 argon2) {
        CustomerRepository repository = new CustomerRepository();
        repository.getEntityManager().getTransaction().begin();
        char[] password ;
        for(Customer c : repository.findAll()) {
            password = c.getCustomerNumber().toString().toCharArray();
            c.setPassword(argon2.hash(2, 16, 1, password));
        }
        repository.getEntityManager().getTransaction().commit();
    }

    public static void main(String[] args) {
        String password = "1234XXxx$";
        char[] passwordArray = password.toCharArray();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2d,16,16);
        //hash(int iterations, int memory, int parallelism, char[] password)
        initPassword(argon2);
//        String hashedPassword = argon2.hash(2, 16, 1, passwordArray);
//        System.out.println(hashedPassword);
//        Scanner sc = new Scanner(System.in);
//        while(true) {
//            System.out.print("Enter your password: ");
//            String yourPassword = sc.next();
//            char[] yourPasswordArray = yourPassword.toCharArray();
//            boolean isMatched = argon2.verify(hashedPassword, yourPasswordArray);
//            if (isMatched) {
//                break;
//            }
//            System.out.println("Incorrect password !!!");
//        }
    }

}
