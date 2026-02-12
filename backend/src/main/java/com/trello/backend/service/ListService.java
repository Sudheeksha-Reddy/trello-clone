package com.trello.backend.service;
import com.trello.backend.dto.*;
import com.trello.backend.entity.*;
import com.trello.backend.repository.*;
import com.trello.backend.mapper.ListMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class ListService {
    private final ListRepository repo;
    private final BoardRepository boardRepo;
    private final ListMapper mapper;

    public List<ListResponse> getByBoard(Long boardId) { return repo.findByBoardId(boardId).stream().map(mapper::toDto).collect(Collectors.toList()); }
    public ListResponse create(Long boardId, ListRequest req) {
        Board b = boardRepo.findById(boardId).orElseThrow();
        ListEntity l = new ListEntity(); l.setTitle(req.getTitle()); l.setBoard(b);
        return mapper.toDto(repo.save(l));
    }
    public ListResponse update(Long id, ListRequest req) {
        ListEntity l = repo.findById(id).orElseThrow();
        l.setTitle(req.getTitle());
        return mapper.toDto(repo.save(l));
    }
    public void delete(Long id) { repo.deleteById(id); }
}