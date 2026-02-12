package com.trello.backend.controller;

import com.trello.backend.dto.*;
import com.trello.backend.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lists/{listId}/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService service;

    @GetMapping
    public List<CardResponse> get(@PathVariable Long listId) {
        return service.getByList(listId);
    }

    @PostMapping
    public CardResponse create(@PathVariable Long listId, @RequestBody CardRequest req) {
        return service.create(listId, req);
    }

    @PutMapping("/{id}")
    public CardResponse update(@PathVariable Long listId,
                               @PathVariable Long id,
                               @RequestBody CardRequest req) {
        return service.update(listId, id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long listId,
                       @PathVariable Long id) {
        service.delete(listId, id);
    }
}
