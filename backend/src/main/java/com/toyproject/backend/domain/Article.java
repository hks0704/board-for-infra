package com.toyproject.backend.domain;

import static jakarta.persistence.CascadeType.*;
import static lombok.AccessLevel.*;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.toyproject.backend.dto.request.ArticleUpdateRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Table(name = "article")
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long articleNo;
	private String title;
	private String content;
	@CreationTimestamp
	private LocalDateTime regDate;
	@UpdateTimestamp
	private LocalDateTime modDate;

	@OneToMany(mappedBy = "commentNo", cascade = ALL)
	private List<Comment> comments;

	public Article update(ArticleUpdateRequestDto requestDto) {
		if (requestDto.getTitle() != null) this.title = requestDto.getTitle();
		if (requestDto.getContent() != null) this.content = requestDto.getContent();
		return this;
	}
}
