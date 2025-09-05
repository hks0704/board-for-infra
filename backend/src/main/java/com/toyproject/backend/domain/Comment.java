package com.toyproject.backend.domain;

import static lombok.AccessLevel.*;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.toyproject.backend.dto.request.CommentUpdateRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "comment")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commentNo;
	private String content;
	@CreationTimestamp
	private LocalDateTime regDate;
	@UpdateTimestamp
	private LocalDateTime modDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "article_no")
	private Article article;

	public Comment update(CommentUpdateRequestDto requestDto) {
		if (requestDto.getContent() != null) this.content = requestDto.getContent();
		return this;
	}
}
