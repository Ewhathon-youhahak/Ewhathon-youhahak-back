package com.ewhathon.notegather.auth.jwt;


import com.ewhathon.notegather.auth.AuthDetails;
import com.ewhathon.notegather.domain.entity.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

/* 사용자 인증(확인)
 * 사용자의 로그인 시도를 가로채서 JWT 토큰을 생성하고, 성공적으로 로그인이 완료되면 생성된 토큰을 응답 헤더에 추가 */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    /* 사용자의 로그인 시도를 가로채는 attemptAuthentication
     * 인증 객체(Authentication)을 만들기 시도 */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            ObjectMapper om = new ObjectMapper(); //JSON 데이터를 Java 객체로 매핑
            Student student = om.readValue(request.getInputStream(), Student.class); //클라이언트에서 전달된 JSON 데이터는 Member 클래스로 변환
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    student.getEmail(),
                    student.getPassword()
            );

            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            AuthDetails authDetails = (AuthDetails) authentication.getPrincipal();
            return  authentication;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /* attemptAuthentication 메소드가 호출 된 후, response에 JWT 토큰을 담아서 전송 */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        /* AuthDetails를 통해서 사용자의 인증을 추출하기 */
        AuthDetails authDetails = (AuthDetails) authResult.getPrincipal();

        /* 토큰에 넣어주어야 하는 값인 email 추출 */
        String email = authDetails.getStudent().getEmail();

        /* jwtTokenProvider를 통해 JWT 토큰을 발급 */
        String jwtToken = jwtTokenProvider.generateJwtToken(email);

        /* 가장 흔한 방식인 Bearer Token을 사용 */
        response.addHeader("Authorization", "Bearer " + jwtToken);

    }
}