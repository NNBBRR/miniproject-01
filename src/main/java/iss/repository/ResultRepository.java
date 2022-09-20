package iss.repository;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import iss.models.FoodSearchResult;


@Repository
public class ResultRepository {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	public void save(FoodSearchResult result) {
		redisTemplate.opsForValue().set(result.getUuid(), result.toJson().toString());
		redisTemplate.expire(result.getUuid(), Duration.ofMinutes(5));
	}

	public void save(List<FoodSearchResult> results) {
		Map<String, String> map = new HashMap<>();
		for (FoodSearchResult art: results)
			map.put(art.getUuid(), art.toJson().toString());
		redisTemplate.opsForValue().multiSet(map);

		for (String uuid: map.keySet())
			redisTemplate.expire(uuid, Duration.ofMinutes(5));
	}

	public Optional<FoodSearchResult> get(String uuid) {
		if (!redisTemplate.hasKey(uuid))
			return Optional.empty();
		String data = redisTemplate.opsForValue().get(uuid);
		return Optional.of(FoodSearchResult.create(data));
	}
}
