package fr.greta.domes_server;

import fr.greta.domes_server.entities.Category;
import fr.greta.domes_server.entities.Specie;
import fr.greta.domes_server.repositories.CategoryRepository;
import fr.greta.domes_server.repositories.EmployeeRepository;
import fr.greta.domes_server.repositories.SpecieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class DomesServerApplication implements CommandLineRunner {
    private final EmployeeRepository employeeRepository;
    private final CategoryRepository categoryRepository;

    private final SpecieRepository specieRepository;

    public DomesServerApplication(EmployeeRepository employeeRepository, CategoryRepository categoryRepository, SpecieRepository specieRepository) {
        this.employeeRepository = employeeRepository;
        this.categoryRepository = categoryRepository;
        this.specieRepository = specieRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(DomesServerApplication.class, args);
    }

    @Override
    public void run(String... args) {
        initCategoriesAndSpecies();

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

    private void initCategoriesAndSpecies() {
        Category dog = categoryRepository.save(new Category(null, "CHIEN"));
        Category cat = categoryRepository.save(new Category(null, "CHAT"));
        Category bird = categoryRepository.save(new Category(null, "OISEAU"));
        Category fish = categoryRepository.save(new Category(null, "POISSON"));
        Category reptile = categoryRepository.save(new Category(null, "REPTILE"));

        /*
         * Dog Species
         * */
        specieRepository.save(new Specie(null, "Yorkshire Terrier", dog));
        specieRepository.save(new Specie(null, "Teckel", dog));
        specieRepository.save(new Specie(null, "Staffordshire bull terrier", dog));
        specieRepository.save(new Specie(null, "Siberian husky", dog));
        specieRepository.save(new Specie(null, "Shiba-inu", dog));
        specieRepository.save(new Specie(null, "Berger des Shetland", dog));
        specieRepository.save(new Specie(null, "Yorkshire Terrier", dog));
        specieRepository.save(new Specie(null, "Sharpeï", dog));
        specieRepository.save(new Specie(null, "Rottweiler", dog));
        specieRepository.save(new Specie(null, "Porcelaine", dog));
        specieRepository.save(new Specie(null, "Pitbull", dog));
        specieRepository.save(new Specie(null, "Mâtin de Naples", dog));
        specieRepository.save(new Specie(null, "Lévrier afghan", dog));
        specieRepository.save(new Specie(null, "Yorkshire Terrier", dog));
        specieRepository.save(new Specie(null, "Labrador Retriever", dog));
        specieRepository.save(new Specie(null, "Jack Russell terrier", dog));
        specieRepository.save(new Specie(null, "Greyhound", dog));
        specieRepository.save(new Specie(null, "Fox-terrier", dog));
        specieRepository.save(new Specie(null, "Dobermann", dog));
        specieRepository.save(new Specie(null, "Dalmatien", dog));
        specieRepository.save(new Specie(null, "Chihuahua", dog));
        specieRepository.save(new Specie(null, "Yorkshire Terrier", dog));
        specieRepository.save(new Specie(null, "Chien-loup tchécoslovaque", dog));
        specieRepository.save(new Specie(null, "Caniche", dog));
        specieRepository.save(new Specie(null, "Bull-terrier", dog));
        specieRepository.save(new Specie(null, "Boxer", dog));
        specieRepository.save(new Specie(null, "Berger allemand", dog));

        /*
         * Cat species
         * */
        specieRepository.save(new Specie(null, "Highland Lynx (Highlander)", cat));
        specieRepository.save(new Specie(null, "Chat de gouttière", cat));
        specieRepository.save(new Specie(null, "Maine Coon", cat));
        specieRepository.save(new Specie(null, "Bleu Russe", cat));
        specieRepository.save(new Specie(null, "British shorthair", cat));
        specieRepository.save(new Specie(null, "Abyssin", cat));
        specieRepository.save(new Specie(null, "American curl", cat));
        specieRepository.save(new Specie(null, "American shorthair", cat));
        specieRepository.save(new Specie(null, "Angora turc", cat));
        specieRepository.save(new Specie(null, "Balinais", cat));
        specieRepository.save(new Specie(null, "Bengal", cat));
        specieRepository.save(new Specie(null, "Bobtail japonais", cat));
        specieRepository.save(new Specie(null, "Bombay", cat));
        specieRepository.save(new Specie(null, "Burmese", cat));
        specieRepository.save(new Specie(null, "Chartreux", cat));
        specieRepository.save(new Specie(null, "Cornish rex", cat));
        specieRepository.save(new Specie(null, "Korat", cat));
        specieRepository.save(new Specie(null, "Maine coon", cat));
        specieRepository.save(new Specie(null, "Mandarin", cat));
        specieRepository.save(new Specie(null, "Manx", cat));
        specieRepository.save(new Specie(null, "Manu egyptien", cat));
        specieRepository.save(new Specie(null, "Oriental", cat));
        specieRepository.save(new Specie(null, "Persan", cat));
        specieRepository.save(new Specie(null, "Sacré de birmanie", cat));
        specieRepository.save(new Specie(null, "Scottish fold", cat));
        specieRepository.save(new Specie(null, "Siamois", cat));
        specieRepository.save(new Specie(null, "Somali", cat));
        specieRepository.save(new Specie(null, "Sphynx", cat));
        specieRepository.save(new Specie(null, "York chocolat", cat));

        /*
        * Fish species
        * */
        specieRepository.save(new Specie(null, "Axolotl", fish));
        specieRepository.save(new Specie(null, "Barbus cerise", fish));
        specieRepository.save(new Specie(null, "Betta Splendens Combattant", fish));
        specieRepository.save(new Specie(null, "Boraras brigittae", fish));
        specieRepository.save(new Specie(null, "Boraras maculatus", fish));
        specieRepository.save(new Specie(null, "Carpe Koi", fish));
        specieRepository.save(new Specie(null, "Chromobotia macracanthus", fish));
        specieRepository.save(new Specie(null, "Danio rerio (Poisson zèbre)", fish));
        specieRepository.save(new Specie(null, "Discus", fish));
        specieRepository.save(new Specie(null, "Gourami bleu", fish));
        specieRepository.save(new Specie(null, "Gourai miel", fish));
        specieRepository.save(new Specie(null, "Gourami nain", fish));
        specieRepository.save(new Specie(null, "Gramma loreto", fish));
        specieRepository.save(new Specie(null, "Guppy", fish));
        specieRepository.save(new Specie(null, "Killies", fish));
        specieRepository.save(new Specie(null, "Labéo bicolore", fish));
        specieRepository.save(new Specie(null, "Microrasbora galaxy", fish));
        specieRepository.save(new Specie(null, "Nez rouge", fish));
        specieRepository.save(new Specie(null, "Pleco commun", fish));
        specieRepository.save(new Specie(null, "Poisson clown", fish));
        specieRepository.save(new Specie(null, "Poisson couteau", fish));
        specieRepository.save(new Specie(null, "Poisson rouge", fish));
        specieRepository.save(new Specie(null, "Poisson arlequins", fish));
        specieRepository.save(new Specie(null, "Pseudotropheus", fish));
        specieRepository.save(new Specie(null, "Rasbora Emeraude", fish));

        /*
        * Bird Species
        * */

        specieRepository.save(new Specie(null, "Agapornis Personata", bird));
        specieRepository.save(new Specie(null, "Amadine cou-coupé", bird));
        specieRepository.save(new Specie(null, "Amazone à front blanc", bird));
        specieRepository.save(new Specie(null, "Amazone à front bleu", bird));
        specieRepository.save(new Specie(null, "Amazone à front jaune", bird));
        specieRepository.save(new Specie(null, "Ara Jaune et Bleu", bird));
        specieRepository.save(new Specie(null, "caille de chine", bird));
        specieRepository.save(new Specie(null, "Caïque à tête noire", bird));
        specieRepository.save(new Specie(null, "calopsitte", bird));
        specieRepository.save(new Specie(null, "Chardonneret", bird));
        specieRepository.save(new Specie(null, "Conure à tête bleue", bird));
        specieRepository.save(new Specie(null, "Conure Nanday", bird));
        specieRepository.save(new Specie(null, "Conure Pyrrhuras Molinae", bird));
        specieRepository.save(new Specie(null, "Guppy", bird));
        specieRepository.save(new Specie(null, "Cornue Soleil", bird));
        specieRepository.save(new Specie(null, "Cornue Souris", bird));
        specieRepository.save(new Specie(null, "Diamant à bavette", bird));
        specieRepository.save(new Specie(null, "Diamant de Gould", bird));
        specieRepository.save(new Specie(null, "Diamant Mandarin", bird));
        specieRepository.save(new Specie(null, "Gris du Gabon", bird));
        specieRepository.save(new Specie(null, "Loriquet orné", bird));
        specieRepository.save(new Specie(null, "Moineau du japon", bird));
        specieRepository.save(new Specie(null, "Perriche de Bourke", bird));
        specieRepository.save(new Specie(null, "Perruche Elégante", bird));
        specieRepository.save(new Specie(null, "Perruche mélanure", bird));
        specieRepository.save(new Specie(null, "Perruche omnicolore", bird));
        specieRepository.save(new Specie(null, "Perruche ondulée", bird));
        specieRepository.save(new Specie(null, "Perruche Pennant", bird));
        specieRepository.save(new Specie(null, "Perruche Splendide", bird));
        specieRepository.save(new Specie(null, "Perruche Turquoisine", bird));
        specieRepository.save(new Specie(null, "Pionne à tête bleue", bird));
        specieRepository.save(new Specie(null, "Pione de Maximilien", bird));
        specieRepository.save(new Specie(null, "Rossignol du Japon", bird));
        specieRepository.save(new Specie(null, "Toui Catherine", bird));
        specieRepository.save(new Specie(null, "Youyou du Sénégal", bird));

        /*
        * Reptile species
        * */

        specieRepository.save(new Specie(null, "Basilic Vert", reptile));
        specieRepository.save(new Specie(null, "Boa du Duméril", reptile));
        specieRepository.save(new Specie(null, "Boaedon Fuliginosus", reptile));
        specieRepository.save(new Specie(null, "Caméléon casqué du Yémen", reptile));
        specieRepository.save(new Specie(null, "Caméléon de Jackson", reptile));
        specieRepository.save(new Specie(null, "Caméléon panthère", reptile));
        specieRepository.save(new Specie(null, "Dragon d'eau", reptile));
        specieRepository.save(new Specie(null, "Eryx", reptile));
        specieRepository.save(new Specie(null, "Gecko à crête", reptile));
        specieRepository.save(new Specie(null, "Gecko leopard", reptile));
        specieRepository.save(new Specie(null, "Heterodon nasicus", reptile));
        specieRepository.save(new Specie(null, "Iguane", reptile));
        specieRepository.save(new Specie(null, "Iguane du désert", reptile));
        specieRepository.save(new Specie(null, "Lampropeltis Getulus", reptile));
        specieRepository.save(new Specie(null, "Lampropeltis triangulum", reptile));
        specieRepository.save(new Specie(null, "Lepidodactylus lugubris", reptile));
        specieRepository.save(new Specie(null, "Lézard à collier", reptile));
        specieRepository.save(new Specie(null, "Ophisaure (Orvet)", reptile));
        specieRepository.save(new Specie(null, "Pantherophis guttatus", reptile));
        specieRepository.save(new Specie(null, "Pituophis catenifer", reptile));
        specieRepository.save(new Specie(null, "Pogona", reptile));
        specieRepository.save(new Specie(null, "Python à tête noire", reptile));
        specieRepository.save(new Specie(null, "Python de Children", reptile));
        specieRepository.save(new Specie(null, "Python malais / curtus", reptile));
        specieRepository.save(new Specie(null, "Python royal", reptile));
        specieRepository.save(new Specie(null, "Sceloporus Malachiticus", reptile));
        specieRepository.save(new Specie(null, "Scinque à langue bleue", reptile));        specieRepository.save(new Specie(null, "Pituophis catenifer", reptile));
        specieRepository.save(new Specie(null, "Scinque berbère", reptile));
        specieRepository.save(new Specie(null, "Scinque crocodile", reptile));
        specieRepository.save(new Specie(null, "Scinque de feu", reptile));
        specieRepository.save(new Specie(null, "Serpent des pins de Floride", reptile));
        specieRepository.save(new Specie(null, "Serpent des pins du nord", reptile));
        specieRepository.save(new Specie(null, "Tamnophis", reptile));
        specieRepository.save(new Specie(null, "Tortue aquatique", reptile));
        specieRepository.save(new Specie(null, "Tortue sillonnée (Sulcata)", reptile));
        specieRepository.save(new Specie(null, "Uromastyx occelata", reptile));
        specieRepository.save(new Specie(null, "Varan des savanes", reptile));

    }
}
