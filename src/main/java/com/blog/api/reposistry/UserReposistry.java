package com.blog.api.reposistry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.api.entity.User;

@Repository
public interface UserReposistry extends JpaRepository<User, Integer> {

}
