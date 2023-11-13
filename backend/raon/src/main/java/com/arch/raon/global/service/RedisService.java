package com.arch.raon.global.service;

import com.arch.raon.domain.dictionary.dto.query.DictionaryMyRankQueryDTO;
import com.arch.raon.domain.grammar.dto.query.GrammarMyRankQueryDTO;
import com.arch.raon.domain.grammar.dto.query.GrammarRankQueryDTO;
import com.arch.raon.domain.member.entity.Member;
import com.arch.raon.domain.member.repository.MemberRepository;
import com.arch.raon.global.exception.CustomException;
import com.arch.raon.global.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Service
public class RedisService {

    private final RedisTemplate<String, String> rankingRedis;
    private final RedisTemplate<String, String> securityRedis;

    private final MemberRepository memberRepository;

    @Autowired
    public RedisService(@Qualifier("RankingRedis") RedisTemplate<String, String> rankingRedis,
                        @Qualifier("SecurityRedis") RedisTemplate<String, String> securityRedis,
                        MemberRepository memberRepository) {
        this.rankingRedis = rankingRedis;
        this.securityRedis = securityRedis;
        this.memberRepository = memberRepository;
    }

    public void setTestData(String str){
        securityRedis.opsForValue().set(str,"please");
        securityRedis.expire(str,30, TimeUnit.SECONDS);
    }

    public String getTestData(String str){
        return securityRedis.opsForValue().get(str);
    }

    /** 여기에 리프레쉬 관련 로직 넣으면 좋아용 **/
    /*
    public void setRefreshToken(String userId, String refreshToken){
        // key : refresh, value : userId
        securityRedis.opsForValue().set(userId,refreshToken);
        //30일
        securityRedis.expire(userId,30L, TimeUnit.DAYS);
    }

    public String getRefreshToken(String userId){
        return securityRedis.opsForValue().get(userId);
    }

    public boolean deleteRefreshToken(String userId){
        return securityRedis.delete(userId);
    }

     */

    // memberId와 닉네임을 저장하는 메서드
    public void setMemberNickname(Long memberId, String nickname){
        securityRedis.opsForValue().set(String.valueOf(memberId), nickname);
        // 만약 닉네임이 변경될 가능성이 있다면, 적절한 만료 시간을 설정해야 합니다.
        securityRedis.expire(String.valueOf(memberId), 7L, TimeUnit.DAYS);
    }

    // memberId에 해당하는 닉네임을 가져오는 메서드
    public String getMemberNickname(Long memberId){
        String nickname = securityRedis.opsForValue().get(String.valueOf(memberId));
        if (nickname == null) {

            // 최신 닉네임 가져오기
            Member member = memberRepository.findById(memberId).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND) {
                @Override
                public ErrorCode getErrorCode() {
                    return super.getErrorCode();
                }
            });

            String newNickname = member.getNickname();

            // 최신의 닉네임을 Redis에 다시 저장합니다.
            setMemberNickname(memberId, newNickname);

            return newNickname;
        } else {
            return nickname;
        }
    }


    public void setCountryGrammarPoint(Long memberId, int point){
        rankingRedis.opsForZSet().incrementScore("countryGrammar", String.valueOf(memberId), point);
    }

    public void setSchoolGrammarPoint(String school, int point) {
        rankingRedis.opsForZSet().incrementScore("schoolGrammar", String.valueOf(school), point);
    }

    public Long getMyCountryGrammarRank(Long memberId) {
        Long rank = rankingRedis.opsForZSet().reverseRank("countryGrammar", String.valueOf(memberId));
        return rank != null ? rank : -1;
    }

    public double getMyCountryGrammarPoint(Long memberId){
        Double score = rankingRedis.opsForZSet().score("countryGrammar", String.valueOf(memberId));
        return score != null ? score : 0;
    }


    public long getCountryGrammarPoint(String nickName){
        long rank = 0;
        double score = rankingRedis.opsForZSet().score("countryGrammar", nickName);
        System.out.println("Gscore = " + score);
        rank = rankingRedis.opsForZSet().reverseRank("countryGrammar", nickName);
        return rank;
    }

    public void setCountryDictionaryPoint(Long id, int point){
        rankingRedis.opsForZSet().incrementScore("countryDictionary", String.valueOf(id), point);
    }

    public double getCountryDictionaryPoint(Long id){
        double score = rankingRedis.opsForZSet().score("countryDictionary",String.valueOf(id));
        return score;
    }

    public long getCountryDictionaryMyRank(Long id){
        long rank = rankingRedis.opsForZSet().reverseRank("countryDictionary", String.valueOf(id));
        return rank+1;
    }

    public List<DictionaryMyRankQueryDTO> getCountryDictionaryRank(long myRank){
        ZSetOperations<String, String> stringStringZSetOperations = rankingRedis.opsForZSet();
        Set<ZSetOperations.TypedTuple<String>> typedTuples;
        if(myRank==0 || myRank==1){
            typedTuples = stringStringZSetOperations.reverseRangeWithScores("countryDictionary", 0, 2);
        }else{
            typedTuples = stringStringZSetOperations.reverseRangeWithScores("countryDictionary", myRank-2, myRank);
        }
        List<DictionaryMyRankQueryDTO> collect = typedTuples.stream().map(DictionaryMyRankQueryDTO::convertToDictionaryMyRankQueryDTO).collect(Collectors.toList());
        return collect;
    }


//    public List<GrammarRankQueryDTO> getCountryGrammarRank(){
//        ZSetOperations<String, String> stringStringZSetOperations = rankingRedis.opsForZSet();
//        Set<ZSetOperations.TypedTuple<String>> typedTuples;
//        typedTuples = stringStringZSetOperations.reverseRangeWithScores("countryGrammar");
//        List<GrammarRankQueryDTO> collect = typedTuples.stream().map(GrammarRankQueryDTO::convertToGrammarMyRankQueryDTO).collect(Collectors.toList());
//
//        return collect;
//    }

    public List<GrammarRankQueryDTO> getCountryGrammarRank(){
        ZSetOperations<String, String> stringStringZSetOperations = rankingRedis.opsForZSet();
        Set<ZSetOperations.TypedTuple<String>> typedTuples;
        typedTuples = stringStringZSetOperations.reverseRangeWithScores("countryGrammar", 0, -1);
        List<GrammarRankQueryDTO> collect = typedTuples.stream().map(GrammarRankQueryDTO::convertToGrammarMyRankQueryDTO).collect(Collectors.toList());

        return collect;
    }

}
