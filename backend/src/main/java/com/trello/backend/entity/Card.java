package com.trello.backend.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cards")
@Data @NoArgsConstructor @AllArgsConstructor
public class Card {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @ManyToOne @JoinColumn(name = "list_id")
    private ListEntity list;
}