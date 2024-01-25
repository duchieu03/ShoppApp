package com.example.shopapp.core.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository <T extends BaseModel> extends JpaRepository<T, Long> {
    Optional<T> findByIdAndDeletedFalse(Long id);

    List<T> findByIdInAndDeletedFalse(Collection<Long> ids);
}
