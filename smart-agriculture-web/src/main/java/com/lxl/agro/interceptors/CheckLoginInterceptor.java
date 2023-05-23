package com.lxl.agro.interceptors;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.lxl.agro.common.Constant;
import com.lxl.agro.common.ResultInfo;
import com.lxl.agro.common.SysUserHolder;
import com.lxl.agro.pojo.SysUser;
import com.lxl.agro.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Project : smart-agriculture-parent
 * Package : com.lxl.agro.interceptors
 * Description : CheckLoginInterceptor
 * Author : LiuXinLei
 * createDate : 2023/5/20 17:06
 */
@Component
public class CheckLoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isEmpty(authorization)) {
            ResultInfo resultInfo = ResultInfo.error(Constant.NOT_LOGIN);
            response.getWriter().write(JSON.toJSONString(resultInfo));
            return false;
        }

        if (JwtUtil.tokenIsExpired(authorization)) {
            ResultInfo resultInfo = ResultInfo.error(Constant.NOT_LOGIN);
            response.getWriter().write(JSON.toJSONString(resultInfo));
            return false;
        }

        try {
            Claims claims = JwtUtil.parseJWT(authorization);
            if (claims != null) {
                String userid = claims.getId();
                String subject = claims.getSubject();
                JSONObject jsonObject = JSON.parseObject(subject);
                SysUser sysUser = JSON.toJavaObject(jsonObject, SysUser.class);
                if (sysUser != null) {
                    SysUserHolder.set(sysUser);
                    return true;
                }
            }
        } catch (Exception e) {
            ResultInfo resultInfo = ResultInfo.error(Constant.NOT_LOGIN);
            String json = JSON.toJSONString(resultInfo);
            response.getWriter().write(json);
            return false;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        SysUserHolder.remove();
    }
}
