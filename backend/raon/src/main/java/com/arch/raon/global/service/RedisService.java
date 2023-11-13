package com.arch.raon.global.service;

import com.arch.raon.domain.dictionary.dto.query.DictionaryMyRankQueryDTO;
import com.arch.raon.domain.grammar.dto.redis.GrammarCountryMyRankRedisDTO;
import com.arch.raon.domain.grammar.dto.redis.GrammarSchoolMyRankRedisDTO;
import com.arch.raon.domain.grammar.dto.redis.GrammarSchoolRedisDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Service
public class RedisService {

    private final RedisTemplate<String, String> rankingRedis;
    private final RedisTemplate<String, String> securityRedis;

    @Autowired
    public RedisService(@Qualifier("RankingRedis") RedisTemplate<String, String> rankingRedis,
                        @Qualifier("SecurityRedis") RedisTemplate<String, String> securityRedis) {
        this.rankingRedis = rankingRedis;
        this.securityRedis = securityRedis;
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

    // TODO: 닉네임, 학교명 변경에 따른 점수 변경 고려하기
    public void setCountryMyGrammarPoint(Long memberId, Long nickname, int point){
        String value = memberId + ":" + nickname;
        rankingRedis.opsForZSet().incrementScore("countryMyGrammar", value, point);
    }

    public void setSchoolMyGrammarPoint(Long memberId, Long nickname, String school, int point){
        String key = "schoolMyGrammar:" + school;
        String value = memberId + ":" + nickname;
        rankingRedis.opsForZSet().incrementScore(key, value, point);
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


    public void setCountryGrammarPoint(String nickName, int point){
        rankingRedis.opsForZSet().incrementScore("countryGrammar",nickName, point);
    }

    public long getCountryGrammarPoint(String nickName){
        long rank = 0;
        double score = rankingRedis.opsForZSet().score("countryGrammar",nickName);
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
        return rank;
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

    public List<GrammarCountryMyRankRedisDTO> getCountryMyGrammarRankList(){
        ZSetOperations<String, String> stringStringZSetOperations = rankingRedis.opsForZSet();
        Set<ZSetOperations.TypedTuple<String>> typedTuples;
        typedTuples = stringStringZSetOperations.reverseRangeWithScores("countryMyGrammar", 0, -1);
        List<GrammarCountryMyRankRedisDTO> collect = new ArrayList<>();
        int rank = 1;
        for (ZSetOperations.TypedTuple<String> typedTuple : typedTuples) {
            collect.add(GrammarCountryMyRankRedisDTO.convertToGrammarCountryMyRankRedisDTO(typedTuple, rank++));
        }
        return collect;
    }


    // 맞춤법 퀴즈 개인 교내 조회
    public List<GrammarSchoolMyRankRedisDTO> getSchoolMyGrammarRankList(String school){
        ZSetOperations<String, String> stringStringZSetOperations = rankingRedis.opsForZSet();
        String key = "schoolMyGrammar:" + school;
        Set<ZSetOperations.TypedTuple<String>> typedTuples;
        typedTuples = stringStringZSetOperations.reverseRangeWithScores(key, 0, -1);

        List<GrammarSchoolMyRankRedisDTO> collect = new ArrayList<>();
        int rank = 1;
        for (ZSetOperations.TypedTuple<String> typedTuple : typedTuples) {
            collect.add(GrammarSchoolMyRankRedisDTO.convertToGrammarSchoolMyRankRedisDTO(typedTuple, rank++));
        }
        return collect;
    }

    // 학교별 랭킹 조회
    public List<GrammarSchoolRedisDTO> getSchoolGrammarRankList(){
        ZSetOperations<String, String> stringStringZSetOperations = rankingRedis.opsForZSet();
        String key = "schoolGrammar";
        Set<ZSetOperations.TypedTuple<String>> typedTuples;
        typedTuples = stringStringZSetOperations.reverseRangeWithScores(key, 0, -1);

        List<GrammarSchoolRedisDTO> collect = new ArrayList<>();
        int rank = 1;
        for (ZSetOperations.TypedTuple<String> typedTuple : typedTuples) {
            collect.add(new GrammarSchoolRedisDTO(typedTuple.getValue(), rank++, typedTuple.getScore().intValue()));
        }
        return collect;
    }


}
