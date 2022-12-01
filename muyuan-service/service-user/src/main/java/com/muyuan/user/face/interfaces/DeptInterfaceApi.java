package com.muyuan.user.face.interfaces;

import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.user.api.DeptInterface;
import com.muyuan.user.api.dto.DeptDTO;
import com.muyuan.user.api.dto.DeptQueryRequest;
import com.muyuan.user.api.dto.DeptRequest;
import com.muyuan.user.domain.model.entity.Dept;
import com.muyuan.user.domain.service.DeptService;
import com.muyuan.user.face.dto.mapper.DeptMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Method;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName DeptInterfaceApi
 * Description DeptInterface
 * @Author 2456910384
 * @Date 2022/11/28 16:08
 * @Version 1.0
 */
@AllArgsConstructor
@DubboService(group = ServiceTypeConst.USER, version = "1.0"
        , interfaceClass = DeptInterface.class,
        methods = {
        @Method(name = "addDept",retries = 0)
        }
)
public class DeptInterfaceApi implements DeptInterface {

    private DeptMapper DEPT_MAPPER;

    private DeptService deptService;

    @Override
    public Result<List<DeptDTO>> list(DeptQueryRequest request) {
        List<Dept> list = deptService.list(DEPT_MAPPER.toCommand(request));
        return ResultUtil.success(DEPT_MAPPER.toDTO(list));
    }

    @Override
    public Result addDept(DeptRequest request) {
        if (GlobalConst.NOT_UNIQUE.equals(deptService.checkUnique(new Dept.Identify(request.getName(), request.getParentId()
                )))) {
            return ResultUtil.fail(ResponseCode.ADD_EXIST);
        }
        Optional<Dept> parent = deptService.getDept(request.getParentId());
        if (ObjectUtils.isNotEmpty(request.getParentId()) &&
                !parent.isPresent()) {
            return ResultUtil.fail(ResponseCode.FAIL,"上级部门不存在");
        }
        boolean flag = deptService.addDept(DEPT_MAPPER.toCommand(request),parent.get());
        return flag ? ResultUtil.success("添加成功") : ResultUtil.fail();
    }
}
