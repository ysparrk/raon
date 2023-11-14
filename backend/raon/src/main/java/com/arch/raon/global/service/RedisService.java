package com.arch.raon.global.service;

import com.arch.raon.domain.dictionary.dto.query.DictionaryMyRankQueryDTO;
import com.arch.raon.domain.grammar.dto.redis.GrammarMyRankRedisDTO;
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

    public void setCountryMyGrammarPoint(String nickname, int point){
        rankingRedis.opsForZSet().incrementScore("countryMyGrammar", nickname, point);
    }

    public void setSchoolMyGrammarPoint(String nickname, String school, int point){
        String key = "schoolMyGrammar:" + school;
        rankingRedis.opsForZSet().incrementScore(key, nickname, point);
    }

    public void setSchoolGrammarPoint(String school, int point) {
        rankingRedis.opsForZSet().incrementScore("schoolGrammar", school, point);
    }

    public Long getCountryMyGrammarRank(String nickname) {
        Long rank = rankingRedis.opsForZSet().reverseRank("countryMyGrammar", nickname);
        return rank != null ? rank : -1;
    }

    public double getCountryMyGrammarPoint(String nickname){
        Double score = rankingRedis.opsForZSet().score("countryMyGrammar", nickname);
        return score != null ? score : 0;
    }


    public Long getSchoolMyGrammarRank(String nickname, String school) {
        String key = "schoolMyGrammar:" + school;
        Long rank = rankingRedis.opsForZSet().reverseRank(key, nickname);
        return rank != null ? rank : -1;
    }


    public double getSchoolMyGrammarPoint(String nickname, String school) {
        String key = "schoolMyGrammar:" + school;
        Double score = rankingRedis.opsForZSet().score(key, nickname);
        return score != null ? score : 0;
    }

    public Long getSchoolGrammarRank(String school) {
        Long rank = rankingRedis.opsForZSet().reverseRank("schoolGrammar", school);
        return rank != null ? rank : -1;
    }

    public double getSchoolGrammarPoint(String school) {
        Double score = rankingRedis.opsForZSet().score("schoolGrammar", school);
        return score != null ? score : 0;
    }

    public void setCountryDictionaryPoint(String nickName, int point){
        rankingRedis.opsForZSet().incrementScore("countryDictionary", nickName, point);
    }

    public void setSchoolDictionaryPoint(String nickName, String school, int point){
        rankingRedis.opsForZSet().incrementScore("countryDictionary:" + school, nickName, point);
    }

    public void setMySchoolDictionaryPoint(String school, int point){
        rankingRedis.opsForZSet().incrementScore("schoolDictionary", school, point);
    }

    public double getCountryDictionaryPoint(String nickName){
        Double score = rankingRedis.opsForZSet().score("countryDictionary", nickName);
        return score != null ? score : 0;
    }

    public long getCountryDictionaryMyRank(String nickName){
        Long rank = rankingRedis.opsForZSet().reverseRank("countryDictionary", nickName);
        return rank != null ? rank : -1;
    }

    public double getSchoolMyDictionaryPoint(String nickName, String school){
        Double score = rankingRedis.opsForZSet().score("countryDictionary:" + school, nickName);
        return score != null ? score : 0;
    }

    public long getSchoolMyDictionaryMyRank(String nickName, String school){
        Long rank = rankingRedis.opsForZSet().reverseRank("countryDictionary:" + school, nickName);
        return rank != null ? rank : -1;
    }

    public double getSchoolDictionaryPoint(String school){
        Double score = rankingRedis.opsForZSet().score("schoolDictionary", school);
        return score != null ? score : 0;
    }

    public long getSchoolDictionaryMyRank(String school){
        Long rank = rankingRedis.opsForZSet().reverseRank("schoolDictionary", school);
        return rank != null ? rank : -1;
    }

    public List<DictionaryMyRankQueryDTO> getCountryDictionaryRank(){
        ZSetOperations<String, String> stringStringZSetOperations = rankingRedis.opsForZSet();
        Set<ZSetOperations.TypedTuple<String>> typedTuples;
        typedTuples = stringStringZSetOperations.reverseRangeWithScores("countryDictionary", 0, -1);
        List<DictionaryMyRankQueryDTO> collect = typedTuples.stream().map(DictionaryMyRankQueryDTO::convertToDictionaryMyRankQueryDTO).collect(Collectors.toList());
        return collect;
    }


    public List<GrammarMyRankRedisDTO> getCountryMyGrammarRankList(){
        ZSetOperations<String, String> stringStringZSetOperations = rankingRedis.opsForZSet();
        Set<ZSetOperations.TypedTuple<String>> typedTuples;
        typedTuples = stringStringZSetOperations.reverseRangeWithScores("countryMyGrammar", 0, -1);
        List<GrammarMyRankRedisDTO> collect = new ArrayList<>();
        for (ZSetOperations.TypedTuple<String> typedTuple : typedTuples) {
            collect.add(GrammarMyRankRedisDTO.convertToGrammarMyRankRedisDTO(typedTuple));
        }
        return collect;
    }

    public List<GrammarMyRankRedisDTO> getSchoolMyGrammarRankList(String school){
        ZSetOperations<String, String> stringStringZSetOperations = rankingRedis.opsForZSet();
        String key = "schoolMyGrammar:" + school;
        Set<ZSetOperations.TypedTuple<String>> typedTuples;
        typedTuples = stringStringZSetOperations.reverseRangeWithScores(key, 0, -1);

        List<GrammarMyRankRedisDTO> collect = new ArrayList<>();
        for (ZSetOperations.TypedTuple<String> typedTuple : typedTuples) {
            collect.add(GrammarMyRankRedisDTO.convertToGrammarMyRankRedisDTO(typedTuple));
        }
        return collect;
    }

    public List<GrammarMyRankRedisDTO> getSchoolGrammarRankList(){
        ZSetOperations<String, String> stringStringZSetOperations = rankingRedis.opsForZSet();
        String key = "schoolGrammar";
        Set<ZSetOperations.TypedTuple<String>> typedTuples;
        typedTuples = stringStringZSetOperations.reverseRangeWithScores(key, 0, -1);

        List<GrammarMyRankRedisDTO> collect = new ArrayList<>();
        for (ZSetOperations.TypedTuple<String> typedTuple : typedTuples) {
            collect.add(GrammarMyRankRedisDTO.convertToGrammarMyRankRedisDTO(typedTuple));
        }
        return collect;
    }

    public List<DictionaryMyRankQueryDTO> getSchoolMyDictionaryRank(String school){
        ZSetOperations<String, String> stringStringZSetOperations = rankingRedis.opsForZSet();
        Set<ZSetOperations.TypedTuple<String>> typedTuples;
        typedTuples = stringStringZSetOperations.reverseRangeWithScores("countryDictionary:" + school, 0, -1);
        List<DictionaryMyRankQueryDTO> collect = typedTuples.stream().map(DictionaryMyRankQueryDTO::convertToDictionaryMyRankQueryDTO).collect(Collectors.toList());
        return collect;
    }

    public List<DictionaryMyRankQueryDTO> getSchoolDictionaryRank(String school){
        ZSetOperations<String, String> stringStringZSetOperations = rankingRedis.opsForZSet();
        Set<ZSetOperations.TypedTuple<String>> typedTuples;
        typedTuples = stringStringZSetOperations.reverseRangeWithScores("schoolDictionary", 0, -1);
        List<DictionaryMyRankQueryDTO> collect = typedTuples.stream().map(DictionaryMyRankQueryDTO::convertToDictionaryMyRankQueryDTO).collect(Collectors.toList());
        return collect;
    }

    public void changeMemberInfo(String oldNickName,String oldSchool, String newNickName, String newSchool){
        rankingRedis.opsForZSet().incrementScore("countryDictionary", newNickName, getCountryDictionaryPoint(oldNickName));
        rankingRedis.opsForZSet().incrementScore("countryDictionary:" + newSchool, newNickName, getSchoolMyDictionaryPoint(oldNickName,oldSchool));
        rankingRedis.opsForZSet().remove("countryDictionary",oldNickName);
        rankingRedis.opsForZSet().remove("countryDictionary:"+oldSchool,oldNickName);
        rankingRedis.opsForZSet().incrementScore("countryMyGrammar", newNickName, getCountryMyGrammarPoint(oldNickName));
        rankingRedis.opsForZSet().incrementScore("schoolMyGrammar:" + newSchool, newNickName, getSchoolMyGrammarPoint(oldNickName, oldSchool));
        rankingRedis.opsForZSet().remove("countryMyGrammar", oldNickName);
        rankingRedis.opsForZSet().remove("schoolMyGrammar:" + oldSchool, oldNickName);
    }

}
