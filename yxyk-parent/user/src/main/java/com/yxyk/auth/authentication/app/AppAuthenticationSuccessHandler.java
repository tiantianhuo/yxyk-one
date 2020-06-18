package com.yxyk.auth.authentication.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Deng
 * @date 2019-08-23
 */
public class AppAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;
    @Resource
    private ClientDetailsService clientDetailsService;
    @Resource
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    private String clientId = "yxyk_app";
    private String clientSecret = "yxyk_app";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

        if (clientDetails == null) {
            throw new UnapprovedClientAuthenticationException("clientId对应的配置信息不存在:" + clientId);
        } else if (!StringUtils.equals(clientDetails.getClientSecret(), clientSecret)) {
            throw new UnapprovedClientAuthenticationException("clientSecret不匹配:" + clientId);
        }

        TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP, clientId, clientDetails.getScope(), "custom");

        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);

        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);

        OAuth2AccessToken token = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write((objectMapper.writeValueAsString(token)));
    }

}
