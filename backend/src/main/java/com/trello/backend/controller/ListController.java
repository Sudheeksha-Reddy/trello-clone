package com.trello.backend.controller;
import com.trello.backend.dto.*;
import com.trello.backend.service.ListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/boards/{boardId}/lists") @RequiredArgsConstructor
public class ListController {
    private final ListService service;
    @GetMapping public List<ListResponse> get(@PathVariable Long boardId) { return service.getByBoard(boardId); }
    @PostMapping public ListResponse create(@PathVariable Long boardId, @RequestBody ListRequest req) { return service.create(boardId, req); }
    @PutMapping("/{id}") public ListResponse update(@PathVariable Long id, @RequestBody ListRequest req) { return service.update(id, req); }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { service.delete(id); }
}