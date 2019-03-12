package com.sanjin.configuration.shiro.filter;

import com.sanjin.configuration.shiro.token.JwtToken;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class JwtFilter extends BasicHttpAuthenticationFilter {

    /**
     * Determines whether the current subject
     * should be allowed to make the current request.
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return true 当前 subject 对应当前请求
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request,
                                      ServletResponse response,
                                      Object mappedValue) {
        if (isLoginAttempt(request,response)) {
            try {
                executeLogin(request,response);
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    /**
     * 根据header中有无 “Authorization” 判断是否进行登陆操作
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        return getJwtToken(request) != null;
    }

    /**
     * 执行 shiro 登陆操作
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        String token = getJwtToken(request);

        JwtToken jwtToken = new JwtToken();
        jwtToken.setToken(token);
        getSubject(request, response).login(jwtToken);

        return true;
    }

    private String getJwtToken(ServletRequest request) {
        HttpServletRequest req = WebUtils.toHttp(request);
        return req.getHeader("Authorization");
    }
}
