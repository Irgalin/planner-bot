package com.github.irgalin.plannerbot.repo;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.github.irgalin.plannerbot.model.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List findAllUsers() {
        return mongoTemplate.findAll(User.class);
    }

    @Override
    public User save(@NotNull User user) {
        mongoTemplate.save(user);
        return user;
    }

    @Override
    public User update(@NotNull User user) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(user.getId()));
        Update update = new Update();
        update.set("userName", user.getUserName());
        update.set("firstName", user.getFirstName());
        update.set("lastName", user.getLastName());
        return mongoTemplate.findAndModify(query, update, User.class);
    }

    @Override
    public List findUserByName(@NotNull String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        return mongoTemplate.find(query, User.class);
    }

    @Override
    public void deleteById(@NotNull String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(userId));
        mongoTemplate.remove(query, User.class);
    }

}
