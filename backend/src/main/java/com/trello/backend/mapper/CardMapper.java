package com.trello.backend.mapper;
import com.trello.backend.dto.*;
import com.trello.backend.entity.Card;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {
    public CardResponse toDto(Card c) {
        CardResponse r = new CardResponse();
        r.setId(c.getId()); r.setTitle(c.getTitle()); r.setDescription(c.getDescription());
        return r;
    }
}