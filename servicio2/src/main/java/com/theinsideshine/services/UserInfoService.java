package com.theinsideshine.services;

import com.theinsideshine.models.UserInfo;
import com.theinsideshine.repositories.UserInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class UserInfoService {

    private final UserInfoRepository repository;

    public UserInfoService(UserInfoRepository repository) {
        this.repository = repository;
    }

    public List<UserInfo> findAll() {
        return repository.findAll();
    }

    public Optional<UserInfo> findById(Long id) {
        return repository.findById(id);
    }

    public UserInfo save(UserInfo userInfo) {
        return repository.save(userInfo);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }


}
