package com.theinsideshine.repositories;

import com.theinsideshine.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByUserId(Long userId); // Método personalizado para encontrar por `userId`
}

