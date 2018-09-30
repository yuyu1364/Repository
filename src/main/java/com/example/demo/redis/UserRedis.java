package com.example.demo.redis;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.example.demo.entiy.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
@Repository
public class UserRedis {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	public void add(String key,Long time,User user) {
		Gson gson = new Gson();
		redisTemplate.opsForValue().set(key, gson.toJson(user), time,TimeUnit.MINUTES);
	}
	
	public void add(String key,Long time,List<User> users) {
		Gson gson = new Gson();
		redisTemplate.opsForValue().set(key, gson.toJson(users), time,TimeUnit.MINUTES);
	}
	
	public User get(String key) {
		Gson gson = new Gson();
		User user = null;
		String gsonUser = redisTemplate.opsForValue().get(key);
		if(!StringUtils.isEmpty(gsonUser)) {
			user = gson.fromJson(gsonUser, User.class);
		}
		return user;
	}
	
	public List<User> getList(String key){
		Gson gson = new Gson();
		List<User> users = null;
		String usersGson = redisTemplate.opsForValue().get(key);
		if(!StringUtils.isEmpty(usersGson)) {
			users = gson.fromJson(usersGson, new TypeToken<List<User>>() {}.getType());
		}
		return users;
	}
	
	public void delete(String key) {
		redisTemplate.opsForValue().getOperations().delete(key);
	}
}
