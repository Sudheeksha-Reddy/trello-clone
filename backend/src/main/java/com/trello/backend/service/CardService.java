package com.trello.backend.service;

import com.trello.backend.dto.*;
import com.trello.backend.entity.*;
import com.trello.backend.repository.*;
import com.trello.backend.mapper.CardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository repo;
    private final ListRepository listRepo;
    private final CardMapper mapper;

    public List<CardResponse> getByList(Long listId) {
        // optional: ensure list exists (better error than empty list)
        listRepo.findById(listId).orElseThrow(() -> new RuntimeException("List not found: " + listId));

        return repo.findByListId(listId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public CardResponse create(Long listId, CardRequest req) {
        ListEntity l = listRepo.findById(listId)
                .orElseThrow(() -> new RuntimeException("List not found: " + listId));

        if (req == null || req.getTitle() == null || req.getTitle().trim().isEmpty()) {
            throw new RuntimeException("Card title is required");
        }

        Card c = new Card();
        c.setTitle(req.getTitle().trim());

        // description optional
        c.setDescription(req.getDescription() == null ? "" : req.getDescription().trim());

        c.setList(l);

        return mapper.toDto(repo.save(c));
    }

    // IMPORTANT: take listId also
    public CardResponse update(Long listId, Long id, CardRequest req) {
        // ensure list exists
        ListEntity l = listRepo.findById(listId)
                .orElseThrow(() -> new RuntimeException("List not found: " + listId));

        Card c = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Card not found: " + id));

        // ensure card belongs to this list
        if (c.getList() == null || c.getList().getId() == null || !c.getList().getId().equals(l.getId())) {
            throw new RuntimeException("Card " + id + " does not belong to list " + listId);
        }

        // safe updates (do NOT overwrite with null)
        if (req != null) {
            if (req.getTitle() != null) {
                String t = req.getTitle().trim();
                if (t.isEmpty()) throw new RuntimeException("Card title cannot be empty");
                c.setTitle(t);
            }

            if (req.getDescription() != null) {
                c.setDescription(req.getDescription().trim());
            }
        }

        return mapper.toDto(repo.save(c));
    }

    public void delete(Long listId, Long id) {
        // ensure list exists
        listRepo.findById(listId).orElseThrow(() -> new RuntimeException("List not found: " + listId));

        Card c = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Card not found: " + id));

        if (c.getList() == null || c.getList().getId() == null || !c.getList().getId().equals(listId)) {
            throw new RuntimeException("Card " + id + " does not belong to list " + listId);
        }

        repo.delete(c);
    }
}
