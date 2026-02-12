package com.trello.backend.mapper;
import com.trello.backend.dto.*;
import com.trello.backend.entity.ListEntity;
import org.springframework.stereotype.Component;

@Component
public class ListMapper {
    public ListResponse toDto(ListEntity l) {
        ListResponse r = new ListResponse();
        r.setId(l.getId()); r.setTitle(l.getTitle());
        return r;
    }
}