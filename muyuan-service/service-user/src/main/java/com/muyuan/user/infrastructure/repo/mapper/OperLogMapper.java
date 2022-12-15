package com.muyuan.user.infrastructure.repo.mapper;

import com.muyuan.common.mybatis.jdbc.CommonBaseMapper;
import com.muyuan.common.mybatis.jdbc.crud.CrudSqlProvider;
import com.muyuan.user.infrastructure.repo.dataobject.OperLogDO;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;

/**
 * 操作日志记录Mapper接口
 *
 * @author ${author}
 * @date 2022-12-15T15:27:12.638+08:00
 */
public interface OperLogMapper extends CommonBaseMapper<OperLogDO> {


    String TITLE = "title";
    String BUSINESS_TYPE = "businessType";
    String METHOD = "method";
    String REQUEST_METHOD = "requestMethod";
    String OPERATOR_TYPE = "operatorType";
    String OPER_NAME = "operName";
    String DEPT_NAME = "deptName";
    String OPER_URL = "operUrl";
    String OPER_IP = "operIp";
    String OPER_LOCATION = "operLocation";
    String OPER_PARAM = "operParam";
    String JSON_RESULT = "jsonResult";
    String ERROR_MSG = "errorMsg";
    String OPER_TIME = "operTime";

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @InsertProvider(value = CrudSqlProvider.class, method = "insert")
    Integer insertAuto(OperLogDO dataObject);

}
