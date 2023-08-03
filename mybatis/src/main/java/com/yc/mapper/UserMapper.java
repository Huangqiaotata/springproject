package com.yc.mapper;

public interface UserMapper {
    User findById(Integer id);
    int add(User user);
}
