package com.assignment.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.userservice.entity.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer>{

}
