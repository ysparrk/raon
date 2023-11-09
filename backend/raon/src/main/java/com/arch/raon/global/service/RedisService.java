package com.arch.raon.global.service;

import com.arch.raon.domain.dictionary.dto.query.DictionaryMyRankQueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

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

    public void setCountryDictionaryPoint(String nickName, int point){
        rankingRedis.opsForZSet().incrementScore("countryDictionary",nickName, point);
    }

    public double getCountryDictionaryPoint(String nickName){
        double score = rankingRedis.opsForZSet().score("countryDictionary",nickName);
        return score;
    }

    public long getCountryDictionaryMyRank(String nickName){
        long rank = rankingRedis.opsForZSet().reverseRank("countryDictionary", nickName);
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



}
