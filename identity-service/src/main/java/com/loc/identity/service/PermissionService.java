package com.loc.identity.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.loc.identity.dto.request.PermissionRequest;
import com.loc.identity.dto.response.PermissionResponse;
import com.loc.identity.entity.Permission;
import com.loc.identity.mapper.PermissionMapper;
import com.loc.identity.repository.PermissionRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    PermissionRepository permissionRepositoty;
    PermissionMapper permissionMapper;

    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);

        permission = permissionRepositoty.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> getAll() {
        var permissions = permissionRepositoty.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    public void delete(String permissionName) {
        permissionRepositoty.deleteById(permissionName);
    }
}
