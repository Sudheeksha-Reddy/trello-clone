package com.trello.backend.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "lists")
@Data @NoArgsConstructor @AllArgsConstructor
public class ListEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne @JoinColumn(name = "board_id")
    private Board board;
    @OneToMany(mappedBy = "list", cascade = CascadeType.ALL)
    private List<Card> cards;
}