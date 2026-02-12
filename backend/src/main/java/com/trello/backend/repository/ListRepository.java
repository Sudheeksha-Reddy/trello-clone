package com.trello.backend.repository;
import com.trello.backend.entity.ListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ListRepository extends JpaRepository<ListEntity, Long> {
    List<ListEntity> findByBoardId(Long boardId);
}