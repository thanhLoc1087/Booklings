package com.loc.identity.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.loc.identity.constant.PredefinedRoles;
import com.loc.identity.dto.request.UserCreationRequest;
import com.loc.identity.dto.request.UserUpdateRequest;
import com.loc.identity.dto.response.UserResponse;
import com.loc.identity.entity.Role;
import com.loc.identity.entity.User;
import com.loc.identity.exception.AppException;
import com.loc.identity.exception.ErrorCode;
import com.loc.identity.mapper.ProfileMapper;
import com.loc.identity.mapper.UserMapper;
import com.loc.identity.repository.RoleRepository;
import com.loc.identity.repository.UserRepository;
import com.loc.identity.repository.httpclient.ProfileClient;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserMapper userMapper;
    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    ProfileClient profileClient;
    ProfileMapper profileMapper;

    public UserResponse createUser(UserCreationRequest request) {
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        var roles = new HashSet<Role>();
        roles.add(PredefinedRoles.USER);

        user.setRoles(roles);

        try {
            user = userRepository.save(user);

            var profileRequest = profileMapper.toProfileCreationRequest(request);
            profileRequest.setUserId(user.getId());
            profileClient.createProfile(profileRequest);
        } catch (DataIntegrityViolationException exception) {
            throw new AppException(ErrorCode.USER_EXISTS);
        }

        return userMapper.toUserResponse(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    @PostAuthorize("returnObject.username == authentication.name || hasRole('ADMIN')")
    public UserResponse getUser(String id) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(authentication.getName());
        log.info("In method getUser of UserService");
        return userMapper.toUserResponse(
                userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_EXISTS)));
    }

    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTS));

        return userMapper.toUserResponse(user);
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_EXISTS));

        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        var roles = roleRepository.findAllById(request.getRoles());

        user.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
