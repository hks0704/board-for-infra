package com.toyproject.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toyproject.backend.domain.Article;
import com.toyproject.backend.domain.Comment;
import com.toyproject.backend.dto.request.CommentCreateRequestDto;
import com.toyproject.backend.dto.request.CommentUpdateRequestDto;
import com.toyproject.backend.dto.response.CommentResponseDto;
import com.toyproject.backend.exception.ArticleNotFoundException;
import com.toyproject.backend.exception.CommentNotFoundException;
import com.toyproject.backend.repository.ArticleRepository;
import com.toyproject.backend.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {


	private final ArticleRepository articleRepository;
	private final CommentRepository commentRepository;

	@Override
	public CommentResponseDto createComment(CommentCreateRequestDto requestDto) {
		Article article = articleRepository.findById(requestDto.getArticleNo())
			.orElseThrow(()->new ArticleNotFoundException(requestDto.getArticleNo()));
		Comment comment = commentRepository.save(requestDto.toEntity(article));
		return new CommentResponseDto(comment);
	}

	@Override
	public CommentResponseDto updateComment(CommentUpdateRequestDto requestDto) {
		Comment comment = commentRepository.findById(requestDto.getCommentNo())
			.orElseThrow(()->new CommentNotFoundException(requestDto.getCommentNo()));
		return new CommentResponseDto(comment.update(requestDto));
	}

	@Override
	public List<CommentResponseDto> findAllCommentsByArticleNo(Long articleNo) {
		Article article = articleRepository.findById(articleNo)
			.orElseThrow(()->new ArticleNotFoundException(articleNo));
		List<Comment> comments = commentRepository.findAllByArticle(article)
			.orElseThrow(()->new CommentNotFoundException(articleNo));
		return comments.stream().map(CommentResponseDto::new).toList();
	}

	@Override
	public CommentResponseDto deleteComment(Long id) {
		Comment comment = commentRepository.findById(id)
			.orElseThrow(()->new CommentNotFoundException(id));
		commentRepository.delete(comment);
		return new CommentResponseDto(comment);
	}
}
