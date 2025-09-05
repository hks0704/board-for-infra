package com.toyproject.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toyproject.backend.domain.Article;
import com.toyproject.backend.dto.request.ArticleCreateRequestDto;
import com.toyproject.backend.dto.request.ArticleUpdateRequestDto;
import com.toyproject.backend.dto.response.ArticleResponseDto;
import com.toyproject.backend.exception.ArticleNotFoundException;
import com.toyproject.backend.repository.ArticleRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService{
	private final ArticleRepository articleRepository;

	@Override
	public ArticleResponseDto createArticle(ArticleCreateRequestDto requestDto) {
		Article article = articleRepository.save(requestDto.toEntity());
		return new ArticleResponseDto(article);
	}

	@Override
	public ArticleResponseDto updateArticle(ArticleUpdateRequestDto requestDto) {
		Article article = articleRepository.findById(requestDto.getArticleNo())
			.orElseThrow(()->new ArticleNotFoundException(requestDto.getArticleNo()));
		return new ArticleResponseDto(article.update(requestDto));
	}

	@Override
	public List<ArticleResponseDto> findAllArticles() {
		List<Article>articles = articleRepository.findAll();
		return articles.stream().map(ArticleResponseDto::new).toList();
	}

	@Override
	public ArticleResponseDto findArticleByArticleId(Long articleNo) {
		Article article = articleRepository.findById(articleNo)
			.orElseThrow(() -> new ArticleNotFoundException(articleNo));
		return new ArticleResponseDto(article);
	}

	@Override
	public List<ArticleResponseDto> filterArticlesByTitle(String title) {
		List<Article>articles = articleRepository.findByTitleContaining(title)
			.orElseThrow(() -> new ArticleNotFoundException(title));
		return articles.stream().map(ArticleResponseDto::new).toList();
	}

	@Override
	public ArticleResponseDto deleteArticle(Long id) {
		Article article = articleRepository.findById(id)
			.orElseThrow(() -> new ArticleNotFoundException(id));
		articleRepository.delete(article);
		return new ArticleResponseDto(article);
	}
}
