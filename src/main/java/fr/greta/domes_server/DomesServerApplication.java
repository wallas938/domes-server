package fr.greta.domes_server;

import fr.greta.domes_server.configuration.Role;
import fr.greta.domes_server.entities.*;
import fr.greta.domes_server.repositories.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;


@Data
@AllArgsConstructor
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class DomesServerApplication implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
    private final CategoryRepository categoryRepository;
    private final SpecieRepository specieRepository;
    private final AnimalRepository animalRepository;
    private final ClientRepository clientRepository;
    private final ArticleRepository articleRepository;
    private final OrderRepository orderRepository;
    private final DomesUserRepository domesUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static void main(String[] args) {
        SpringApplication.run(DomesServerApplication.class, args);
    }

    @Override
    public void run(String... args) {
//        initClients();
//        initAnimalsCategoriesAndSpecies();
//        initArticlesAndOrders();
//        employeeRepository.save(new DomesUser(null, "Dramé","Sissako", "sissako@email.com", "3 place charles munch"));
//        employeeRepository.save(new DomesUser(null, "Toure","Mamadou", "mamadou@email.com", "3 place charles munch"));
//        employeeRepository.save(new DomesUser(null, "Goita","Asimi", "asimi@email.com", "3 place charles munch"));
//        employeeRepository.save(new DomesUser(null, "Berthe","Johan", "johan@email.com", "3 place charles munch"));
//        employeeRepository.save(new DomesUser(null, "Bolloré","Tristan", "tristan@email.com", "3 place charles munch"));
    }

    private void initClients() {
        Client client = new Client();
        client.setLastname("drame");
        client.setFirstname("Sissako");
        client.setEmail("sissako@email.com");
        client.setRole(Role.CLIENT);
        client.setPassword(bCryptPasswordEncoder.encode("Password123"));

        client.setPhoneNumber("0678942155");
        client.setAddress(new Address("france", "lile", "3 rue du petit lion", "59130"));

        Employee employee = new Employee();
        employee.setLastname("goita");
        employee.setFirstname("asimi");
        employee.setEmail("asimi@email.fr");
        employee.setRole(Role.EMPLOYEE);
        employee.setPassword(bCryptPasswordEncoder.encode("Password123"));

        domesUserRepository.findByEmail(client.getEmail()).ifPresentOrElse(domesUser -> System.out.println(domesUser.getFirstname() + "Existe déja"), () -> domesUserRepository.save(client));

        domesUserRepository.findByEmail(employee.getEmail()).ifPresentOrElse(domesUser -> System.out.println(domesUser.getFirstname() + "Existe déja"), () -> domesUserRepository.save(employee));

        //        clientRepository.save(domesUser);
//        clientRepository.save(new DomesUser("goita", "asimi", "0741893026", "asimi@email.fr", new Address("france", "paris", "6 avenue des marchands de sable", "59130"), encoder.encode("asimi")));
//        clientRepository.save(new DomesUser("jakarta", "ariful", "0648799935", "ariful@email.fr", new Address("france", "bordeaux", "32 rue du chemin des vivants", "33000"), encoder.encode("ariful")));
//        clientRepository.save(new DomesUser("petit", "jessie", "0713416885", "jessie@email.fr", new Address("france", "lyon", "71 boulevard du zenith", "69001"), encoder.encode("jessie")));
//        clientRepository.save(new DomesUser("simo", "philippe", "0698633846", "philippe@email.fr", new Address("france", "marseille", "10 place du zinedine zidane", "13007"), encoder.encode("philippe")));
    }

    private void initAnimalsCategoriesAndSpecies() {

        Category dog = categoryRepository.save(new Category(null, "CHIEN"));
        Category cat = categoryRepository.save(new Category(null, "CHAT"));
        Category bird = categoryRepository.save(new Category(null, "OISEAU"));
        Category fish = categoryRepository.save(new Category(null, "POISSON"));
        Category reptile = categoryRepository.save(new Category(null, "REPTILE"));

        //Dog Species

        Specie teckel = specieRepository.save(new Specie(null, "Teckel", dog));
        Specie staffordshireBullTerrier = specieRepository.save(new Specie(null, "Staffordshire bull terrier", dog));
        specieRepository.save(new Specie(null, "Siberian husky", dog));
        specieRepository.save(new Specie(null, "Shiba-inu", dog));
        specieRepository.save(new Specie(null, "Berger des Shetland", dog));
        Specie yorkshireTerrier = specieRepository.save(new Specie(null, "Yorkshire Terrier", dog));
        specieRepository.save(new Specie(null, "Sharpeï", dog));
        Specie rottweiler = specieRepository.save(new Specie(null, "Rottweiler", dog));
        specieRepository.save(new Specie(null, "Porcelaine", dog));
        Specie pitbull = specieRepository.save(new Specie(null, "Pitbull", dog));
        specieRepository.save(new Specie(null, "Mâtin de Naples", dog));
        specieRepository.save(new Specie(null, "Lévrier afghan", dog));
        Specie labradorRetriever = specieRepository.save(new Specie(null, "Labrador Retriever", dog));
        specieRepository.save(new Specie(null, "Jack Russell terrier", dog));
        Specie greyhound = specieRepository.save(new Specie(null, "Greyhound", dog));
        Specie foxterrier = specieRepository.save(new Specie(null, "Fox-terrier", dog));
        specieRepository.save(new Specie(null, "Dobermann", dog));
        Specie dalmatien = specieRepository.save(new Specie(null, "Dalmatien", dog));
        specieRepository.save(new Specie(null, "Chihuahua", dog));
        specieRepository.save(new Specie(null, "Chien-loup tchécoslovaque", dog));
        Specie caniche = specieRepository.save(new Specie(null, "Caniche", dog));
        Specie bullTerrier = specieRepository.save(new Specie(null, "Bull-terrier", dog));
        specieRepository.save(new Specie(null, "Boxer", dog));
        Specie bergerAllemand = specieRepository.save(new Specie(null, "Berger allemand", dog));

        //Cat species

        specieRepository.save(new Specie(null, "Highland Lynx (Highlander)", cat));
        Specie chatDeGouttiere = specieRepository.save(new Specie(null, "Chat de gouttière", cat));
        Specie maineCoon = specieRepository.save(new Specie(null, "Maine Coon", cat));
        specieRepository.save(new Specie(null, "Bleu Russe", cat));
        specieRepository.save(new Specie(null, "British shorthair", cat));
        Specie abyssin = specieRepository.save(new Specie(null, "Abyssin", cat));
        Specie americanCurl = specieRepository.save(new Specie(null, "American curl", cat));
        specieRepository.save(new Specie(null, "American shorthair", cat));
        Specie angoraTurc = specieRepository.save(new Specie(null, "Angora turc", cat));
        specieRepository.save(new Specie(null, "Balinais", cat));
        Specie bengal = specieRepository.save(new Specie(null, "Bengal", cat));
        specieRepository.save(new Specie(null, "Bobtail japonais", cat));
        Specie bombay = specieRepository.save(new Specie(null, "Bombay", cat));
        Specie burmese = specieRepository.save(new Specie(null, "Burmese", cat));
        Specie chartreux = specieRepository.save(new Specie(null, "Chartreux", cat));
        specieRepository.save(new Specie(null, "Cornish rex", cat));
        Specie korat = specieRepository.save(new Specie(null, "Korat", cat));
        specieRepository.save(new Specie(null, "Maine coon", cat));
        specieRepository.save(new Specie(null, "Mandarin", cat));
        Specie manx = specieRepository.save(new Specie(null, "Manx", cat));
        specieRepository.save(new Specie(null, "Manu egyptien", cat));
        Specie oriental = specieRepository.save(new Specie(null, "Oriental", cat));
        specieRepository.save(new Specie(null, "Persan", cat));
        specieRepository.save(new Specie(null, "Sacré de birmanie", cat));
        specieRepository.save(new Specie(null, "Scottish fold", cat));
        Specie siamois = specieRepository.save(new Specie(null, "Siamois", cat));
        Specie somali = specieRepository.save(new Specie(null, "Somali", cat));
        specieRepository.save(new Specie(null, "Sphynx", cat));
        specieRepository.save(new Specie(null, "York chocolat", cat));

        // Fish species

        specieRepository.save(new Specie(null, "Axolotl", fish));
        Specie barbusCerise = specieRepository.save(new Specie(null, "Barbus cerise", fish));
        specieRepository.save(new Specie(null, "Betta Splendens Combattant", fish));
        Specie borarasBrigittae = specieRepository.save(new Specie(null, "Boraras brigittae", fish));
        Specie borarasMaculatus = specieRepository.save(new Specie(null, "Boraras maculatus", fish));
        Specie carpeKoi = specieRepository.save(new Specie(null, "Carpe Koi", fish));
        Specie chromobotiaMacracanthus = specieRepository.save(new Specie(null, "Chromobotia macracanthus", fish));
        specieRepository.save(new Specie(null, "Danio rerio (Poisson zèbre)", fish));
        specieRepository.save(new Specie(null, "Discus", fish));
        specieRepository.save(new Specie(null, "Gourami bleu", fish));
        Specie gouraiMiel = specieRepository.save(new Specie(null, "Gourai miel", fish));
        specieRepository.save(new Specie(null, "Gourami nain", fish));
        specieRepository.save(new Specie(null, "Gramma loreto", fish));
        specieRepository.save(new Specie(null, "Guppy Fish", fish));
        Specie killies = specieRepository.save(new Specie(null, "Killies", fish));
        specieRepository.save(new Specie(null, "Labéo bicolore", fish));
        specieRepository.save(new Specie(null, "Microrasbora galaxy", fish));
        specieRepository.save(new Specie(null, "Nez rouge", fish));
        specieRepository.save(new Specie(null, "Pleco commun", fish));
        Specie poissonClown = specieRepository.save(new Specie(null, "Poisson clown", fish));
        Specie poissonCouteau = specieRepository.save(new Specie(null, "Poisson couteau", fish));
        Specie poissonRouge = specieRepository.save(new Specie(null, "Poisson rouge", fish));
        specieRepository.save(new Specie(null, "Poisson arlequins", fish));
        Specie pseudotropheus = specieRepository.save(new Specie(null, "Pseudotropheus", fish));
        specieRepository.save(new Specie(null, "Rasbora Emeraude", fish));

        // Bird Species

        Specie agapornisPersonata = specieRepository.save(new Specie(null, "Agapornis Personata", bird));
        specieRepository.save(new Specie(null, "Amadine cou-coupé", bird));
        Specie amazoneAFrontBlanc = specieRepository.save(new Specie(null, "Amazone à front blanc", bird));
        Specie amazoneAFrontBleu = specieRepository.save(new Specie(null, "Amazone à front bleu", bird));
        specieRepository.save(new Specie(null, "Amazone à front jaune", bird));
        specieRepository.save(new Specie(null, "Ara Jaune et Bleu", bird));
        specieRepository.save(new Specie(null, "caille de chine", bird));
        specieRepository.save(new Specie(null, "Caïque à tête noire", bird));
        specieRepository.save(new Specie(null, "calopsitte", bird));
        specieRepository.save(new Specie(null, "Chardonneret", bird));
        specieRepository.save(new Specie(null, "Conure à tête bleue", bird));
        specieRepository.save(new Specie(null, "Conure Nanday", bird));
        specieRepository.save(new Specie(null, "Conure Pyrrhuras Molinae", bird));
        Specie guppy = specieRepository.save(new Specie(null, "Guppy Bird", bird));
        specieRepository.save(new Specie(null, "Cornue Soleil", bird));
        Specie cornueSouris = specieRepository.save(new Specie(null, "Cornue Souris", bird));
        specieRepository.save(new Specie(null, "Diamant à bavette", bird));
        specieRepository.save(new Specie(null, "Diamant de Gould", bird));
        specieRepository.save(new Specie(null, "Diamant Mandarin", bird));
        specieRepository.save(new Specie(null, "Gris du Gabon", bird));
        specieRepository.save(new Specie(null, "Loriquet orné", bird));
        specieRepository.save(new Specie(null, "Moineau du japon", bird));
        specieRepository.save(new Specie(null, "Perriche de Bourke", bird));
        specieRepository.save(new Specie(null, "Perruche Elégante", bird));
        Specie perrucheMelanure = specieRepository.save(new Specie(null, "Perruche mélanure", bird));
        Specie perrucheOmnicolore = specieRepository.save(new Specie(null, "Perruche omnicolore", bird));
        Specie perrucheOndulee = specieRepository.save(new Specie(null, "Perruche ondulée", bird));
        specieRepository.save(new Specie(null, "Perruche Pennant", bird));
        Specie perrucheSplendide = specieRepository.save(new Specie(null, "Perruche Splendide", bird));
        specieRepository.save(new Specie(null, "Perruche Turquoisine", bird));
        Specie pionneATeteBleue = specieRepository.save(new Specie(null, "Pionne à tête bleue", bird));
        specieRepository.save(new Specie(null, "Pione de Maximilien", bird));
        Specie rossignolDuJapon = specieRepository.save(new Specie(null, "Rossignol du Japon", bird));
        specieRepository.save(new Specie(null, "Toui Catherine", bird));
        specieRepository.save(new Specie(null, "Youyou du Sénégal", bird));

        //Reptile species

        Specie basilicVert = specieRepository.save(new Specie(null, "Basilic Vert", reptile));
        Specie boaDuDumeril = specieRepository.save(new Specie(null, "Boa du Duméril", reptile));
        Specie boaedonFuliginosus = specieRepository.save(new Specie(null, "Boaedon Fuliginosus", reptile));
        Specie cameleonCasqueDuYemen = specieRepository.save(new Specie(null, "Caméléon casqué du Yémen", reptile));
        specieRepository.save(new Specie(null, "Caméléon de Jackson", reptile));
        specieRepository.save(new Specie(null, "Caméléon panthère", reptile));
        specieRepository.save(new Specie(null, "Dragon d'eau", reptile));
        Specie eryx = specieRepository.save(new Specie(null, "Eryx", reptile));
        specieRepository.save(new Specie(null, "Gecko à crête", reptile));
        Specie geckoLeopard = specieRepository.save(new Specie(null, "Gecko leopard", reptile));
        specieRepository.save(new Specie(null, "Heterodon nasicus", reptile));
        Specie iguane = specieRepository.save(new Specie(null, "Iguane", reptile));
        specieRepository.save(new Specie(null, "Iguane du désert", reptile));
        Specie lampropeltisGetulus = specieRepository.save(new Specie(null, "Lampropeltis Getulus", reptile));
        specieRepository.save(new Specie(null, "Lampropeltis triangulum", reptile));
        specieRepository.save(new Specie(null, "Lepidodactylus lugubris", reptile));
        Specie lézardÀCollier = specieRepository.save(new Specie(null, "Lézard à collier", reptile));
        specieRepository.save(new Specie(null, "Ophisaure (Orvet)", reptile));
        Specie pantherophisGuttatus = specieRepository.save(new Specie(null, "Pantherophis guttatus", reptile));
        Specie pogona = specieRepository.save(new Specie(null, "Pogona", reptile));
        Specie pythonATeteNoire = specieRepository.save(new Specie(null, "Python à tête noire", reptile));
        Specie pythonDeChildren = specieRepository.save(new Specie(null, "Python de Children", reptile));
        specieRepository.save(new Specie(null, "Python malais / curtus", reptile));
        specieRepository.save(new Specie(null, "Python royal", reptile));
        specieRepository.save(new Specie(null, "Sceloporus Malachiticus", reptile));
        Specie scinqueALangueBleue = specieRepository.save(new Specie(null, "Scinque à langue bleue", reptile));
        specieRepository.save(new Specie(null, "Pituophis catenifer", reptile));
        specieRepository.save(new Specie(null, "Scinque berbère", reptile));
        Specie scinqueCrocodile = specieRepository.save(new Specie(null, "Scinque crocodile", reptile));
        specieRepository.save(new Specie(null, "Scinque de feu", reptile));
        specieRepository.save(new Specie(null, "Serpent des pins de Floride", reptile));
        Specie serpentDesPinsDuNord = specieRepository.save(new Specie(null, "Serpent des pins du nord", reptile));
        specieRepository.save(new Specie(null, "Tortue aquatique", reptile));
        Specie sulcata = specieRepository.save(new Specie(null, "Tortue sillonnée (Sulcata)", reptile));
        specieRepository.save(new Specie(null, "Uromastyx occelata", reptile));
        Specie varanDesSavanes = specieRepository.save(new Specie(null, "Varan des savanes", reptile));

        saveAnimal(dog, bergerAllemand);

        saveAnimal(dog, bullTerrier);

        saveAnimal(dog, caniche);

        saveAnimal(dog, labradorRetriever);

        saveAnimal(dog, pitbull);

        saveAnimal(dog, rottweiler);

        saveAnimal(dog, yorkshireTerrier);

        saveAnimal(dog, greyhound);

        saveAnimal(dog, staffordshireBullTerrier);

        saveAnimal(dog, teckel);

        saveAnimal(dog, dalmatien);




        saveAnimal(cat, americanCurl);

        saveAnimal(cat, abyssin);

        saveAnimal(cat, bombay);

        saveAnimal(cat, burmese);

        saveAnimal(cat, chartreux);

        saveAnimal(cat, korat);

        saveAnimal(cat, manx);

        saveAnimal(cat, oriental);

        saveAnimal(cat, siamois);

        saveAnimal(cat, somali);

        saveAnimal(cat, chatDeGouttiere);


        saveAnimal(fish, pseudotropheus);

        saveAnimal(fish, poissonRouge);

        saveAnimal(fish, poissonCouteau);

        saveAnimal(fish, poissonClown);

        saveAnimal(fish, gouraiMiel);

        saveAnimal(fish, killies);

        saveAnimal(fish, chromobotiaMacracanthus);

        saveAnimal(fish, carpeKoi);

        saveAnimal(fish, borarasMaculatus);

        saveAnimal(fish, borarasBrigittae);

        saveAnimal(fish, barbusCerise);


        saveAnimal(bird, amazoneAFrontBlanc);

        saveAnimal(bird, amazoneAFrontBleu);

        saveAnimal(bird, perrucheMelanure);

        saveAnimal(bird, guppy);

        saveAnimal(bird, cornueSouris);

        saveAnimal(bird, perrucheOmnicolore);

        saveAnimal(bird, perrucheOndulee);

        saveAnimal(bird, perrucheSplendide);

        saveAnimal(bird, pionneATeteBleue);

        saveAnimal(bird, rossignolDuJapon);

        saveAnimal(bird, agapornisPersonata);


        saveAnimal(reptile, varanDesSavanes);

        saveAnimal(reptile, serpentDesPinsDuNord);

        saveAnimal(reptile, boaDuDumeril);

        saveAnimal(reptile, boaedonFuliginosus);

        saveAnimal(reptile, sulcata);

        saveAnimal(reptile, pythonDeChildren);

        saveAnimal(reptile, geckoLeopard);

        saveAnimal(reptile, cameleonCasqueDuYemen);

        saveAnimal(reptile, eryx);

        saveAnimal(reptile, basilicVert);

        saveAnimal(reptile, iguane);

        saveAnimal(reptile, lampropeltisGetulus);

        saveAnimal(reptile, pantherophisGuttatus);

        saveAnimal(reptile, pogona);

        saveAnimal(reptile, pythonATeteNoire);

        saveAnimal(reptile, scinqueALangueBleue);

        saveAnimal(reptile, scinqueCrocodile);

    }

    public void saveAnimal(Category category, Specie specie) {
        int randomAge = 1 + (int) (Math.random() * ((24 - 1) + 1));
        int randomPrice = 1 + (int)(Math.random() * (999 - 1) + 1);

        while (randomAge < 1 || randomAge > 24) {
            randomAge = 1 + (int) (Math.random() * ((24 - 1) + 1));
        }

        while (randomPrice < 50 || randomPrice > 999) {
            randomPrice = 1 + (int)(Math.random() * (999 - 1) + 1);
        }

        Animal animal = new Animal();

        animal.setAge(randomAge);
        animal.setCategory(category);
        animal.setSpecie(specie);
        animal.setPrice(randomPrice);
        animal.setSold(false);
        animal.setDescription(category.getName() + "/" + specie.getName() + "/" + animal.getAge() + " months");

        animalRepository.save(animal);
    }

    private void initArticlesAndOrders() {
        /*
         * Creation of a client
         * */
        Client client = clientRepository.findByFirstname("sissako");
        /*
         * Implementation of three articles
         * */

        Category chien = categoryRepository.getCategoryByName("CHIEN");
        Specie shibaInu = specieRepository.getSpeciesByName("Shiba-inu");
        Article article1 = new Article(250, shibaInu, chien);
        Article savedArticle1 = articleRepository.save(article1);

        Category chat = categoryRepository.getCategoryByName("CHAT");
        Specie abyssin = specieRepository.getSpeciesByName("Abyssin");
        Article article2 = new Article(180, abyssin, chat);
        Article savedArticle2 = articleRepository.save(article2);

        Category reptile = categoryRepository.getCategoryByName("REPTILE");
        Specie iguane = specieRepository.getSpeciesByName("Iguane");
        Article article3 = new Article(350, iguane, reptile);
        Article savedArticle3 = articleRepository.save(article3);

        /*
         * Implementing the saved order
         * */

        /*
         * Create order and persist it
         * */
        Order order = new Order();
        order.setArticles(List.of(savedArticle1, savedArticle2, savedArticle3));
        order.setNumberOfArticles(order.getArticles().size());
        order.setTotal(savedArticle1.getPrice()+savedArticle2.getPrice()+savedArticle3.getPrice());
        order.setShippingAddress(client.getAddress());
        order.setClient(client);

        orderRepository.save(order);

        clientRepository.save(client);
    }
}