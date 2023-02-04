package fr.greta.domes_server;

import fr.greta.domes_server.entities.*;
import fr.greta.domes_server.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class DomesServerApplication implements CommandLineRunner {
    private final EmployeeRepository employeeRepository;
    private final CategoryRepository categoryRepository;
    private final SpecieRepository specieRepository;
    private final AnimalRepository animalRepository;
    private final ClientRepository clientRepository;
    private final ArticleRepository articleRepository;
    private final OrderRepository orderRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public DomesServerApplication(EmployeeRepository employeeRepository, CategoryRepository categoryRepository, SpecieRepository specieRepository, AnimalRepository animalRepository, ClientRepository clientRepository, ArticleRepository articleRepository, OrderRepository orderRepository) {
        this.employeeRepository = employeeRepository;
        this.categoryRepository = categoryRepository;
        this.specieRepository = specieRepository;
        this.animalRepository = animalRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
        this.orderRepository = orderRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(DomesServerApplication.class, args);
    }

    @Override
    public void run(String... args) {
        initClients();
        initAnimalsCategoriesAndSpecies();
        initArticlesAndOrders();
//        employeeRepository.save(new Employee(null, "Dramé","Sissako", "sissako@email.com", "3 place charles munch"));
//        employeeRepository.save(new Employee(null, "Toure","Mamadou", "mamadou@email.com", "3 place charles munch"));
//        employeeRepository.save(new Employee(null, "Goita","Asimi", "asimi@email.com", "3 place charles munch"));
//        employeeRepository.save(new Employee(null, "Berthe","Johan", "johan@email.com", "3 place charles munch"));
//        employeeRepository.save(new Employee(null, "Bolloré","Tristan", "tristan@email.com", "3 place charles munch"));
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
        Specie save = specieRepository.save(new Specie(null, "Fox-terrier", dog));
        specieRepository.save(new Specie(null, "Dobermann", dog));
        Specie dalmatien = specieRepository.save(new Specie(null, "Dalmatien", dog));
        specieRepository.save(new Specie(null, "Chihuahua", dog));
        specieRepository.save(new Specie(null, "Chien-loup tchécoslovaque", dog));
        Specie caniche = specieRepository.save(new Specie(null, "Caniche", dog));
        Specie bullTerrier = specieRepository.save(new Specie(null, "Bull-terrier", dog));
        specieRepository.save(new Specie(null, "Boxer", dog));
        Specie bergerAllemand = specieRepository.save(new Specie(null, "Berger allemand", dog));

        for (int i = 1; i <= 4; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), dog, dalmatien, i * 50, i + 1));
        }

        for (int i = 1; i <= 2; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), dog, teckel, i * 50, i + 1));
        }

        for (int i = 1; i <= 5; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), dog, staffordshireBullTerrier, i * 50, i + 1));
        }

        for (int i = 1; i <= 1; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), dog, greyhound, i * 50, i + 1));
        }

        for (int i = 1; i <= 3; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), dog, yorkshireTerrier, i * 50, i + 1));
        }

        for (int i = 1; i <= 4; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), dog, rottweiler, i * 50, i + 1));
        }

        for (int i = 1; i <= 6; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), dog, pitbull, i * 50, i + 1));
        }

        for (int i = 1; i <= 8; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), dog, labradorRetriever, i * 50, i + 1));
        }

        for (int i = 1; i <= 6; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), dog, caniche, i * 50, i + 1));
        }

        for (int i = 1; i <= 9; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), dog, bullTerrier, i * 50, i + 1));
        }

        for (int i = 1; i <= 5; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), dog, bergerAllemand, i * 50, i + 1));
        }

        //Cat species

        specieRepository.save(new Specie(null, "Highland Lynx (Highlander)", cat));
        Specie chatDeGouttière = specieRepository.save(new Specie(null, "Chat de gouttière", cat));
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

        for (int i = 1; i <= 4; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), cat, chatDeGouttière, i * 50, i + 1));
        }

        for (int i = 1; i <= 2; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), cat, somali, i * 50, i + 1));
        }

        for (int i = 1; i <= 5; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), cat, siamois, i * 50, i + 1));
        }

        for (int i = 1; i <= 1; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), cat, oriental, i * 50, i + 1));
        }

        for (int i = 1; i <= 3; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), cat, manx, i * 50, i + 1));
        }

        for (int i = 1; i <= 4; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), cat, korat, i * 50, i + 1));
        }

        for (int i = 1; i <= 6; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), cat, chartreux, i * 50, i + 1));
        }

        for (int i = 1; i <= 8; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), cat, burmese, i * 50, i + 1));
        }

        for (int i = 1; i <= 4; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), cat, bombay, i * 50, i + 1));
        }

        for (int i = 1; i <= 9; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), cat, abyssin, i * 50, i + 1));
        }

        for (int i = 1; i <= 5; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), cat, americanCurl, i * 50, i + 1));
        }

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
        for (int i = 1; i <= 4; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), fish, barbusCerise, i * 50, i + 1));
        }

        for (int i = 1; i <= 2; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), fish, borarasBrigittae, i * 50, i + 1));
        }

        for (int i = 1; i <= 5; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), fish, borarasMaculatus, i * 50, i + 1));
        }

        for (int i = 1; i <= 1; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), fish, carpeKoi, i * 50, i + 1));
        }

        for (int i = 1; i <= 3; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), fish, chromobotiaMacracanthus, i * 50, i + 1));
        }

        for (int i = 1; i <= 4; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), fish, killies, i * 50, i + 1));
        }

        for (int i = 1; i <= 6; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), fish, gouraiMiel, i * 50, i + 1));
        }

        for (int i = 1; i <= 8; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), fish, poissonClown, i * 50, i + 1));
        }

        for (int i = 1; i <= 3; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), fish, poissonCouteau, i * 50, i + 1));
        }

        for (int i = 1; i <= 9; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), fish, poissonRouge, i * 50, i + 1));
        }

        for (int i = 1; i <= 5; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), fish, pseudotropheus, i * 50, i + 1));
        }

        // Bird Species

        Specie agapornisPersonata = specieRepository.save(new Specie(null, "Agapornis Personata", bird));
        specieRepository.save(new Specie(null, "Amadine cou-coupé", bird));
        Specie amazoneÀFrontBlanc = specieRepository.save(new Specie(null, "Amazone à front blanc", bird));
        Specie amazoneÀFrontBleu = specieRepository.save(new Specie(null, "Amazone à front bleu", bird));
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
        Specie perrucheMélanure = specieRepository.save(new Specie(null, "Perruche mélanure", bird));
        Specie perrucheOmnicolore = specieRepository.save(new Specie(null, "Perruche omnicolore", bird));
        Specie perrucheOndulée = specieRepository.save(new Specie(null, "Perruche ondulée", bird));
        specieRepository.save(new Specie(null, "Perruche Pennant", bird));
        Specie perrucheSplendide = specieRepository.save(new Specie(null, "Perruche Splendide", bird));
        specieRepository.save(new Specie(null, "Perruche Turquoisine", bird));
        Specie pionneÀTêteBleue = specieRepository.save(new Specie(null, "Pionne à tête bleue", bird));
        specieRepository.save(new Specie(null, "Pione de Maximilien", bird));
        Specie rossignolDuJapon = specieRepository.save(new Specie(null, "Rossignol du Japon", bird));
        specieRepository.save(new Specie(null, "Toui Catherine", bird));
        specieRepository.save(new Specie(null, "Youyou du Sénégal", bird));

        for (int i = 1; i <= 4; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), bird, agapornisPersonata, i * 50, i + 1));
        }

        for (int i = 1; i <= 2; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), bird, rossignolDuJapon, i * 50, i + 1));
        }

        for (int i = 1; i <= 5; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), bird, pionneÀTêteBleue, i * 50, i + 1));
        }

        for (int i = 1; i <= 1; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), bird, perrucheSplendide, i * 50, i + 1));
        }

        for (int i = 1; i <= 3; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), bird, perrucheOndulée, i * 50, i + 1));
        }

        for (int i = 1; i <= 4; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), bird, perrucheOmnicolore, i * 50, i + 1));
        }

        for (int i = 1; i <= 6; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), bird, cornueSouris, i * 50, i + 1));
        }

        for (int i = 1; i <= 8; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), bird, guppy, i * 50, i + 1));
        }

        for (int i = 1; i <= 2; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), bird, perrucheMélanure, i * 50, i + 1));
        }

        for (int i = 1; i <= 9; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), bird, amazoneÀFrontBleu, i * 50, i + 1));
        }

        for (int i = 1; i <= 5; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), bird, amazoneÀFrontBlanc, i * 50, i + 1));
        }

        //Reptile species

        Specie basilicVert = specieRepository.save(new Specie(null, "Basilic Vert", reptile));
        Specie boaDuDuméril = specieRepository.save(new Specie(null, "Boa du Duméril", reptile));
        Specie boaedonFuliginosus = specieRepository.save(new Specie(null, "Boaedon Fuliginosus", reptile));
        Specie caméléonCasquéDuYémen = specieRepository.save(new Specie(null, "Caméléon casqué du Yémen", reptile));
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
        Specie pythonÀTêteNoire = specieRepository.save(new Specie(null, "Python à tête noire", reptile));
        Specie pythonDeChildren = specieRepository.save(new Specie(null, "Python de Children", reptile));
        specieRepository.save(new Specie(null, "Python malais / curtus", reptile));
        specieRepository.save(new Specie(null, "Python royal", reptile));
        specieRepository.save(new Specie(null, "Sceloporus Malachiticus", reptile));
        Specie scinqueÀLangueBleue = specieRepository.save(new Specie(null, "Scinque à langue bleue", reptile));
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

        for (int i = 1; i <= 4; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), reptile, varanDesSavanes, i * 50, i + 1));
        }

        for (int i = 1; i <= 2; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), reptile, sulcata, i * 50, i + 1));
        }

        for (int i = 1; i <= 5; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), reptile, serpentDesPinsDuNord, i * 50, i + 1));
        }

        for (int i = 1; i <= 1; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), reptile, scinqueCrocodile, i * 50, i + 1));
        }

        for (int i = 1; i <= 3; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), reptile, scinqueÀLangueBleue, i * 50, i + 1));
        }

        for (int i = 1; i <= 4; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), reptile, pythonÀTêteNoire, i * 50, i + 1));
        }

        for (int i = 1; i <= 6; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), reptile, pogona, i * 50, i + 1));
        }

        for (int i = 1; i <= 8; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), reptile, pantherophisGuttatus, i * 50, i + 1));
        }

        for (int i = 1; i <= 3; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), reptile, lampropeltisGetulus, i * 50, i + 1));
        }

        for (int i = 1; i <= 9; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), reptile, iguane, i * 50, i + 1));
        }

        for (int i = 1; i <= 5; i++) {
            animalRepository.save(new Animal("Animal " + (i + 1), reptile, basilicVert, i * 50, i + 1));
        }
    }

    private void initClients() {
        clientRepository.save(new Client("dramé", "sissako", "0678942155", "sissako@email.fr", new Address("france", "lile", "3 rue du petit lion", "59130"), encoder.encode("sissako")));
        clientRepository.save(new Client("goita", "asimi", "0741893026", "asimi@email.fr", new Address("france", "paris", "6 avenue des marchands de sable", "59130"), encoder.encode("asimi")));
        clientRepository.save(new Client("jakarta", "ariful", "0648799935", "ariful@email.fr", new Address("france", "bordeaux", "32 rue du chemin des vivants", "33000"), encoder.encode("ariful")));
        clientRepository.save(new Client("petit", "jessie", "0713416885", "jessie@email.fr", new Address("france", "lyon", "71 boulevard du zenith", "69001"), encoder.encode("jessie")));
        clientRepository.save(new Client("simo", "philippe", "0698633846", "philippe@email.fr", new Address("france", "marseille", "10 place du zinedine zidane", "13007"), encoder.encode("philippe")));
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