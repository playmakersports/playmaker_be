package com.example.playmaker.security;

import com.example.playmaker.code.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    private static final long tokenPeriod = 1000L * 60L * 60L;
    private static final long refreshTokenPeriod = 1000L * 60L * 60L * 24L * 30L * 3L;

    private final UserDetailsService userDetailsService;

    //객체초기화
    //secretKey Base64로 인코딩
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    //JWT 토근 생성
    public Token generateToken(Long id, String username, Role role) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("id", id);
        claims.put("username", username);
        claims.put("role", role);

        Date now = new Date();

        return new Token (
                //일반 토근
                Jwts.builder()
                    .setClaims(claims) //멤버정보저장
                    .setIssuedAt(now)  //토큰 발행시간
                    .setExpiration(new Date(now.getTime() + tokenPeriod)) //토큰만료시간
                    .signWith(SignatureAlgorithm.HS256, secretKey) // 암호화 알고리즘과 signature에 들어갈 secret 값 세팅
                    .compact(),
                //리프레시 토큰
                Jwts.builder()
                    .setClaims(claims)
                    .setIssuedAt(now)
                    .setExpiration(new Date(now.getTime() + refreshTokenPeriod))
                    .signWith(SignatureAlgorithm.HS256, secretKey)
                    .compact()
        );
    }

    //JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    //토큰에서 회원 이름 추출
    private String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    //토큰에서 회원 아이디 추출
    public Long getUserId(String token) {
        return (Long) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("id");
    }

    //request header 토큰값 가져옴 [Authorization : TOKEN]
    public String resolveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer")) {
            return token.substring(7);
        }

        return token;
    }

    //토큰의 유효성 + 만료일자 확인
    //토큰이 만료여부 return
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
