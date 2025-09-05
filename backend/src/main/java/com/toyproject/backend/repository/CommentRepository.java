package com.toyproject.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toyproject.backend.domain.Article;
import com.toyproject.backend.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	Optional<List<Comment>> findAllByArticle(Article article);

}
