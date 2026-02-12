package com.trello.backend.mapper;
import com.trello.backend.dto.*;
import com.trello.backend.entity.Board;
import org.springframework.stereotype.Component;

@Component
public class BoardMapper {
    public BoardResponse toDto(Board b) {
        BoardResponse r = new BoardResponse();
        r.setId(b.getId()); r.setName(b.getName());
        return r;
    }
}