package ru.kpfu.itis.charntsev.net.service;

import ru.kpfu.itis.charntsev.net.dto.UserDto;
import ru.kpfu.itis.charntsev.net.model.User;

import java.util.List;

public interface UserService {

    List<UserDto> getAll();

    UserDto get(int id);

    void save(User user);
}
