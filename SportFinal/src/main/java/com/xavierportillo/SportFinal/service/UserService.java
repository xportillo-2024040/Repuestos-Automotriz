package com.xavierportillo.SportFinal.service;
import com.xavierportillo.SportFinal.model.User;
import java.util.List;

import com.xavierportillo.SportFinal.model.User;

public interface UserService {
    List<User>getAllUsers();
    User getUserById(Integer id);
    User saverUser(User user);
    User updateUser(Integer id, User user);
    void deleteUser(Integer id);
}
