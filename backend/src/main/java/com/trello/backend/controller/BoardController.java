package com.trello.backend.controller;

import com.trello.backend.dto.BoardRequest;
import com.trello.backend.dto.BoardResponse;
import com.trello.backend.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService service;

    public BoardController(BoardService service) {
        this.service = service;
    }

    @GetMapping
    public List<BoardResponse> getAll() {
        return service.getAll();
    }

    @PostMapping
    public BoardResponse create(@RequestBody BoardRequest req) {
        return service.create(req);
    }

    @PutMapping("/{id}")
    public BoardResponse update(@PathVariable Long id, @RequestBody BoardRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
