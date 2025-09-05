package com.toyproject.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toyproject.backend.domain.ResponseData;
import com.toyproject.backend.dto.request.ArticleCreateRequestDto;
import com.toyproject.backend.dto.request.ArticleUpdateRequestDto;
import com.toyproject.backend.dto.response.ArticleResponseDto;
import com.toyproject.backend.service.ArticleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/article")
public class ArticleController {
	private final ArticleService articleService;

	@PostMapping("")
	public ResponseEntity<?> createArticle(@RequestBody ArticleCreateRequestDto requestDto) {
		ArticleResponseDto responseDto = articleService.createArticle(requestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(ResponseData.success("게시글 생성 완료", responseDto));
	}

	@GetMapping("")
	public ResponseEntity<?> findAllArticles() {
		List<ArticleResponseDto> responseDto = articleService.findAllArticles();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseData.success(responseDto));
	}

	@GetMapping("/search/{title}")
	public ResponseEntity<?> findArticlesByTitle(@PathVariable String title) {
		List<ArticleResponseDto> responseDto = articleService.filterArticlesByTitle(title);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseData.success(responseDto));

	}

	@GetMapping("/detail/{articleNo}")
	public ResponseEntity<?> findArticleByArticleNo(@PathVariable(value = "articleNo") Long articleNo) {
		ArticleResponseDto responseDto = articleService.findArticleByArticleId(articleNo);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseData.success(responseDto));
	}


	@PutMapping("/update")
	public ResponseEntity<?> updateArticle(@RequestBody ArticleUpdateRequestDto requestDto) {
		ArticleResponseDto responseDto = articleService.updateArticle(requestDto);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseData.success("게시글 수정 완료", responseDto));
	}

	@DeleteMapping("/{articleNo}")
	public ResponseEntity<?> deleteArticle(@PathVariable(value = "articleNo") Long articleNo) {
		ArticleResponseDto responseDto = articleService.deleteArticle(articleNo);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseData.success(responseDto));
	}
}
