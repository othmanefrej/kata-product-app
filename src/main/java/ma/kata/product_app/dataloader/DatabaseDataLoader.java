package ma.kata.product_app.dataloader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseDataLoader implements CommandLineRunner {

    @Autowired
    private ProductDataLoader productDataLoader;
    @Autowired
    private UserDataLoader userDataLoader;



    @Override
    public void run(String... args) {
        System.out.println("Starting database seeding...");
        userDataLoader.loadData();
        productDataLoader.loadData();
        System.out.println("Database seeding completed.");
    }
}
