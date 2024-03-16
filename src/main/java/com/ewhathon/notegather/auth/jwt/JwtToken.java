package com.ewhathon.notegather.auth.jwt;

import lombok.Builder;

public class JwtToken {
    private String jwtAccessToken; //사용자가 자원에 접근할 수 있는 권한 부여
    //private String jwtRefreshToken; //사용자가 accessToken을 계속해서 발급받을 수 있도록 하는 토큰
    @Builder
    public JwtToken(String jwtAccessToken) {
        this.jwtAccessToken = jwtAccessToken;
    }
}