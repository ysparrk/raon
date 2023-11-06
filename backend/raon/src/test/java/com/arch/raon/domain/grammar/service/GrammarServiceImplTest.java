package com.arch.raon.domain.grammar.service;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.arch.raon.domain.grammar.dto.query.GrammarMyRankQueryDTO;
import com.arch.raon.domain.grammar.dto.request.GrammarResultDTO;
import com.arch.raon.domain.grammar.dto.request.GrammarResultSaveReqDTO;
import com.arch.raon.domain.grammar.dto.response.GrammarQuizResDTO;
import com.arch.raon.domain.grammar.entity.GrammarQuiz;
import com.arch.raon.domain.grammar.entity.GrammarScore;
import com.arch.raon.domain.member.entity.Member;
import com.arch.raon.domain.member.repository.MemberRepository;
import com.arch.raon.global.util.enums.Gender;
import com.arch.raon.global.util.enums.School;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.arch.raon.domain.grammar.repository.GrammarQuizRepository;
import com.arch.raon.domain.grammar.repository.GrammarScoreRepository;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class GrammarServiceImplTest {
	@Autowired
	GrammarQuizRepository grammarQuizRepository;

	@Autowired
	GrammarService grammarService;

	@Autowired
	GrammarScoreRepository grammarScoreRepository;

	@Autowired
	MemberRepository memberRepository;


	static Member MEMBER1;
	static Member MEMBER2;
	static Member MEMBER3;
	static Member MEMBER4;
	static Member MEMBER5;
	static Member MEMBER6;
	static Member MEMBER7;
	static Member MEMBER8;
	static Member MEMBER9;
	@BeforeEach
	void beforeEach() {
		MEMBER1 = Member.builder()
				.id(6L)
				.email("arch@arch.com")
				.nickname("아치아빠재우")
				.profileUrl("https://")
				.gender(Gender.MALE)
				.school(School.ARCH)
				.yearOfBirth(2017)
				.mileage(0)
				.createdAt(LocalDateTime.now())
				.modifiedAt(LocalDateTime.now())
				.isDeleted(false)
				.deletedAt(LocalDateTime.now())
				.build();

		memberRepository.save(MEMBER1);

		MEMBER2 = Member.builder()
				.id(6L)
				.email("arch@arch.com")
				.nickname("정준혁주녁")
				.profileUrl("https://")
				.gender(Gender.MALE)
				.school(School.ARCH)
				.yearOfBirth(2017)
				.mileage(0)
				.createdAt(LocalDateTime.now())
				.modifiedAt(LocalDateTime.now())
				.isDeleted(false)
				.deletedAt(LocalDateTime.now())
				.build();

		memberRepository.save(MEMBER2);

		MEMBER3 = Member.builder()
				.id(6L)
				.email("arch@arch.com")
				.nickname("서인덕션")
				.profileUrl("https://")
				.gender(Gender.MALE)
				.school(School.INDEOK)
				.yearOfBirth(2017)
				.mileage(0)
				.createdAt(LocalDateTime.now())
				.modifiedAt(LocalDateTime.now())
				.isDeleted(false)
				.deletedAt(LocalDateTime.now())
				.build();

		memberRepository.save(MEMBER3);

		MEMBER4 = Member.builder()
				.id(6L)
				.email("arch@arch.com")
				.nickname("고재원고")
				.profileUrl("https://")
				.gender(Gender.MALE)
				.school(School.INDEOK)
				.yearOfBirth(2017)
				.mileage(0)
				.createdAt(LocalDateTime.now())
				.modifiedAt(LocalDateTime.now())
				.isDeleted(false)
				.deletedAt(LocalDateTime.now())
				.build();

		memberRepository.save(MEMBER4);

		MEMBER5 = Member.builder()
				.id(6L)
				.email("arch@arch.com")
				.nickname("태현태현태현")
				.profileUrl("https://")
				.gender(Gender.MALE)
				.school(School.YOUNGSEO)
				.yearOfBirth(2017)
				.mileage(0)
				.createdAt(LocalDateTime.now())
				.modifiedAt(LocalDateTime.now())
				.isDeleted(false)
				.deletedAt(LocalDateTime.now())
				.build();

		memberRepository.save(MEMBER5);

		MEMBER6 = Member.builder()
				.id(6L)
				.email("arch@arch.com")
				.nickname("young서")
				.profileUrl("https://")
				.gender(Gender.FEMALE)
				.school(School.YOUNGSEO)
				.yearOfBirth(2017)
				.mileage(0)
				.createdAt(LocalDateTime.now())
				.modifiedAt(LocalDateTime.now())
				.isDeleted(false)
				.deletedAt(LocalDateTime.now())
				.build();

		memberRepository.save(MEMBER6);

		MEMBER7 = Member.builder()
				.id(6L)
				.email("arch@arch.com")
				.nickname("히진상")
				.profileUrl("https://")
				.gender(Gender.FEMALE)
				.school(School.YOUNGSEO)
				.yearOfBirth(2017)
				.mileage(0)
				.createdAt(LocalDateTime.now())
				.modifiedAt(LocalDateTime.now())
				.isDeleted(false)
				.deletedAt(LocalDateTime.now())
				.build();

		memberRepository.save(MEMBER7);

		MEMBER8 = Member.builder()
				.id(6L)
				.email("arch@arch.com")
				.nickname("솔케이")
				.profileUrl("https://")
				.gender(Gender.FEMALE)
				.school(School.YOUNGSEO)
				.yearOfBirth(2017)
				.mileage(0)
				.createdAt(LocalDateTime.now())
				.modifiedAt(LocalDateTime.now())
				.isDeleted(false)
				.deletedAt(LocalDateTime.now())
				.build();

		memberRepository.save(MEMBER8);

		MEMBER9 = Member.builder()
				.id(6L)
				.email("arch@arch.com")
				.nickname("ko서영")
				.profileUrl("https://")
				.gender(Gender.FEMALE)
				.school(School.YOUNGSEO)
				.yearOfBirth(2017)
				.mileage(0)
				.createdAt(LocalDateTime.now())
				.modifiedAt(LocalDateTime.now())
				.isDeleted(false)
				.deletedAt(LocalDateTime.now())
				.build();

		memberRepository.save(MEMBER9);

		// 점수 등록
		GrammarScore grammarScore1 = GrammarScore.builder().member(MEMBER1).score(100).build();
		GrammarScore grammarScore2 = GrammarScore.builder().member(MEMBER2).score(90).build();
		GrammarScore grammarScore3 = GrammarScore.builder().member(MEMBER3).score(80).build();
		GrammarScore grammarScore4 = GrammarScore.builder().member(MEMBER4).score(70).build();
		GrammarScore grammarScore5 = GrammarScore.builder().member(MEMBER5).score(60).build();
		GrammarScore grammarScore6 = GrammarScore.builder().member(MEMBER6).score(50).build();
		GrammarScore grammarScore7 = GrammarScore.builder().member(MEMBER7).score(40).build();
		GrammarScore grammarScore8 = GrammarScore.builder().member(MEMBER8).score(30).build();
		GrammarScore grammarScore9 = GrammarScore.builder().member(MEMBER9).score(20).build();

		grammarScoreRepository.save(grammarScore1);
		grammarScoreRepository.save(grammarScore2);
		grammarScoreRepository.save(grammarScore3);
		grammarScoreRepository.save(grammarScore4);
		grammarScoreRepository.save(grammarScore5);
		grammarScoreRepository.save(grammarScore6);
		grammarScoreRepository.save(grammarScore7);
		grammarScoreRepository.save(grammarScore8);
		grammarScoreRepository.save(grammarScore9);

	}



	@Test
	void getQuizzes() {
		// given

		// when
		List<GrammarQuizResDTO> expectQuizzes = grammarService.getQuizzes();
		System.out.println("expectQuizzes = " + expectQuizzes);
		// then
		assertThat(10).isEqualTo(expectQuizzes.size());
	}

	@Test
	@Transactional
	void saveScoreResult() {
		// given -> 한 문제를 맞혔다고 가정
		List<GrammarResultDTO> grammarResultList = new ArrayList<>();
		GrammarResultDTO grammarResultDTO = GrammarResultDTO.builder()
				.id(1L)
				.hit(1).build();
		grammarResultList.add(grammarResultDTO);
		GrammarResultSaveReqDTO grammarResultSaveReqDTO = GrammarResultSaveReqDTO.builder()
				.grammarResultList(grammarResultList).build();

		// when
		grammarService.saveScoreResult(grammarResultSaveReqDTO);

		// then -> 맞힌 횟수가 증가 했는지
		Member member = memberRepository.findById(777L).get();
		GrammarScore scoreResult = grammarScoreRepository.findByMember(member);
		assertThat(scoreResult.getScore()).isEqualTo(1);
	}

	@Test
	@Transactional
	void updateStatistics() {
		// given -> 1번 문제를 맞혔다고 가정
		List<GrammarResultDTO> grammarResultList = new ArrayList<>();
		GrammarResultDTO grammarResultDTO = GrammarResultDTO.builder()
				.id(1L)
				.hit(1).build();
		grammarResultList.add(grammarResultDTO);
		GrammarResultSaveReqDTO grammarResultSaveReqDTO = GrammarResultSaveReqDTO.builder()
				.grammarResultList(grammarResultList).build();

		// when
		GrammarQuiz grammarQuiz = grammarQuizRepository.findById(1L).get();
		int originalHit = grammarQuiz.getHit();
		grammarService.updateStatistics(grammarResultSaveReqDTO);
		int updatedHit = grammarQuiz.getHit();

		// then -> 맞힌 횟수가 증가 했는지
		assertThat(originalHit + 1).isEqualTo(updatedHit);
	}


	@DisplayName("내가 중간 순위 일 때")
    @Test
	@Transactional
    void getMiddlePlaceRankResult() {
		// given
		List<GrammarMyRankQueryDTO> allByCountry = grammarScoreRepository.findAllByCountry();

		// when
		List<GrammarMyRankQueryDTO> expectResult = grammarService.getMiddlePlaceRankResult(6, allByCountry);

		// then
		assertThat("히진상").isEqualTo(expectResult.get(4).getNickname());
    }

	@DisplayName("내가 상위 순위 일 때")
	@Test
	@Transactional
	void getTopPlaceRankResult() {
		// given
		List<GrammarMyRankQueryDTO> allByCountry = grammarScoreRepository.findAllByCountry();

		// when
		List<GrammarMyRankQueryDTO> expectResult = grammarService.getTopPlaceRankResult(allByCountry);

		// then
		assertThat("고재원고").isEqualTo(expectResult.get(3).getNickname());
	}

	@DisplayName("내가 최하위 순위 일 때")
	@Test
	@Transactional
	void getLastPlaceRankResult() {
		// given
		List<GrammarMyRankQueryDTO> allByCountry = grammarScoreRepository.findAllByCountry();

		// when
		List<GrammarMyRankQueryDTO> expectResult = grammarService.getLastPlaceRankResult(allByCountry);

		// then
		assertThat(allByCountry.size()).isEqualTo(expectResult.get(5).getRank());
	}
}