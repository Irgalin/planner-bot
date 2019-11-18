package com.github.irgalin.plannerbot.repo;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.github.irgalin.plannerbot.model.User;

public interface UserRepository {

    User save(@NotNull User user);

    List findAllUsers();

    User update(@NotNull User user);

    List findUserByName(@NotNull String name);

    void deleteById(@NotNull String userId);

}
