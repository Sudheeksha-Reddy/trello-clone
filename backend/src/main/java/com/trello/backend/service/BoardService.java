package com.trello.backend.service;
import com.trello.backend.dto.*;
import com.trello.backend.entity.Board;
import com.trello.backend.exception.ResourceNotFoundException;
import com.trello.backend.mapper.BoardMapper;
import com.trello.backend.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class BoardService {
    private final BoardRepository repo;
    private final BoardMapper mapper;

    public List<BoardResponse> getAll() { return repo.findAll().stream().map(mapper::toDto).collect(Collectors.toList()); }
    public BoardResponse create(BoardRequest req) {
        Board b = new Board(); b.setName(req.getName());
        return mapper.toDto(repo.save(b));
    }
    public BoardResponse update(Long id, BoardRequest req) {
        Board b = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found"));
        b.setName(req.getName());
        return mapper.toDto(repo.save(b));
    }
    public void delete(Long id) { repo.deleteById(id); }
}