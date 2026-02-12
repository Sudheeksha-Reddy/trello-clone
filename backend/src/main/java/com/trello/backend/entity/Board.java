package com.trello.backend.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "boards")
@Data @NoArgsConstructor @AllArgsConstructor
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<ListEntity> lists;
}