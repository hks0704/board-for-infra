package com.toyproject.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toyproject.backend.domain.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

	Optional<List<Article>> findByTitleContaining(String title);
}
