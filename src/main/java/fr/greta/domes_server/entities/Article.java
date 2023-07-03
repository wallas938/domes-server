// NOT USED ANYMORE
//
// package fr.greta.domes_server.entities;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.UUID;
//
//@Entity(name = "t_article")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class Article {
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    private UUID id;
//    @Column
//    private double price;
//    @ManyToOne
//    private Specie specie;
//    @ManyToOne
//    private Category category;
//
//    public Article(double price, Specie specie, Category category) {
//        this.price = price;
//        this.specie = specie;
//        this.category = category;
//    }
//}
