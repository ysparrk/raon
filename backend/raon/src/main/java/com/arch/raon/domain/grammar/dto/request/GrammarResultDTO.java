package com.arch.raon.domain.grammar.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class GrammarResultDTO implements Serializable {
	private Long id; // 믄제 id
	private int hit; // 맞으면 1, 틀리면 0

	public GrammarResultDTO() {
		super();
	}

	@Builder
	public GrammarResultDTO(Long id, int hit) {
		this.id = id;
		this.hit = hit;
	}

	@Override
	public String toString() {
		return "GrammarResultDTO{" +
				"id=" + id +
				", hit=" + hit +
				'}';
	}
}
