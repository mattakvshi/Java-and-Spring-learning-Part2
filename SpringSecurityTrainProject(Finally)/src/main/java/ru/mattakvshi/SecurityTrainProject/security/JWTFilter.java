package ru.mattakvshi.SecurityTrainProject.security;


import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import ru.mattakvshi.SecurityTrainProject.entity.auth.Account;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

import static io.jsonwebtoken.lang.Strings.hasText;


@Component
@Log
public class JWTFilter extends GenericFilterBean {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    //Каждый раз как будет приходить запрос мы по токену будем вытаскивать пользователя и вставлять в SecurityContextHolder,
    // это нужно чтобы далее получать пользователя который пришёл по этому токену

    //На низком уровне всё работает на сервлетах

    //ServletRequest - хранит всю информацию о прешедшем запросе

    //ServletResponse - хранит всю инфу о возвращаемом ответе

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("do filter...");
        String token = getTokenFromRequests((HttpServletRequest) servletRequest);
        if (token != null && jwtProvider.validateToken(token)) {
            String userLogin = jwtProvider.getLoginFromToken(token);
            Account account = (Account) customUserDetailsService.loadUserByUsername(userLogin);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(account, null, account.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getTokenFromRequests(HttpServletRequest servletRequest) {
        String bearer = servletRequest.getHeader(AUTHORIZATION_HEADER); // Ищем в http запросе заголовок с ключём Authorization и вытаскиваем Bearer token
        if (hasText(bearer) && bearer.startsWith("Bearer ")){
            return bearer.substring(7);
        }
        return null;
    }
}
