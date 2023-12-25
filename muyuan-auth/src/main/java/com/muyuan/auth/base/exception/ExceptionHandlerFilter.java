package com.muyuan.auth.base.exception;

import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.exception.MuyuanException;
import com.muyuan.common.core.util.JSONUtil;
import com.muyuan.common.core.util.ResultUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (MuyuanException e) {

            // custom error response class used across my project
            Result errorResponse = ResultUtil.fail(e.getCode(), e.getMessage());

            response.setStatus(HttpStatus.OK.value());
            response.setCharacterEncoding(GlobalConst.UTF8);
            response.getWriter().write(Objects.requireNonNull(JSONUtil.toJsonString(errorResponse), GlobalConst.UTF8));
        }
    }

}
