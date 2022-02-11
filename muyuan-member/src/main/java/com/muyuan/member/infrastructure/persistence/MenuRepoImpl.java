package com.muyuan.member.infrastructure.persistence;

import com.muyuan.member.domain.repo.MenuRepo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName MenuRepoImpl
 * Description MenuRepoImpl
 * @Author 2456910384
 * @Date 2022/2/11 16:39
 * @Version 1.0
 */
@Component
public class MenuRepoImpl implements MenuRepo {
    @Override
    public List<String> selectMenuPermissionByRoleNames(List<String> roleNames) {
        return null;
    }
}
