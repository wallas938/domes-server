package fr.greta.domes_server;

import fr.greta.domes_server.entities.Category;
import fr.greta.domes_server.repositories.CategoryRepository;
import fr.greta.domes_server.repositories.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class DomesServerApplication implements CommandLineRunner {
    private final EmployeeRepository employeeRepository;
    private final CategoryRepository categoryRepository;

    public DomesServerApplication(EmployeeRepository employeeRepository, CategoryRepository categoryRepository) {
        this.employeeRepository = employeeRepository;
        this.categoryRepository = categoryRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(DomesServerApplication.class, args);
    }

    @Override
    public void run(String... args) {
        initCategories();

//        employeeRepository.save(new Employee(null, "Dramé","Sissako", "sissako@email.com", "3 place charles munch"));
//        employeeRepository.save(new Employee(null, "Toure","Mamadou", "mamadou@email.com", "3 place charles munch"));
//        employeeRepository.save(new Employee(null, "Goita","Asimi", "asimi@email.com", "3 place charles munch"));
//        employeeRepository.save(new Employee(null, "Berthe","Johan", "johan@email.com", "3 place charles munch"));
//        employeeRepository.save(new Employee(null, "Bolloré","Tristan", "tristan@email.com", "3 place charles munch"));


//		employeeRepository.findAll().forEach(employee -> System.out.println(employee));
//        Employee asimi = employeeRepository.findByFirstname("Asimi");
//		System.out.println(asimi);

        // Pagination
//		List<Employee> pageOfEmployees = employeeRepository.findAll(PageRequest.of(2, 3)).getContent();
//		pageOfEmployees.forEach(employee -> System.out.println(employee.getName()));

        /**
         *		Pagination with repository method that returns employees
         *		by name that contains a specific letter and paginate
         *		through the results
         *	    Ps: The method name was choose so that spring will automatically know what to search
         */
//        Page<Employee> pageOfEmployees = employeeRepository.findByNameContaining("s", PageRequest.of(0, 3));
//
//        pageOfEmployees.getContent().forEach(employee -> {
//            System.out.println(employee);
//        });
    }

    private void initCategories() {
        categoryRepository.save(new Category(null, "DOG"));
        categoryRepository.save(new Category(null, "CAT"));
        categoryRepository.save(new Category(null, "BIRD"));
        categoryRepository.save(new Category(null, "FISH"));
        categoryRepository.save(new Category(null, "REPTILE"));
    }
}
