package com.example.playmaker.config;

import com.example.playmaker.security.JwtAuthenticationFilter;
import com.example.playmaker.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //HttpSecurity 객체 설정
        http
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                //토큰 기반 인증이므로 세션 사용 X
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //api/user/** 주소는 ROLE_USER 또는 ROLE_ADMIN 권한만 접근 가능
                //.antMatchers("/board/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
//                .antMatchers("/token/**").permitAll()  //토큰이 만료되어 인증을 하지 못하면 /token/expired 로 리다이렉트하여 Refresh 요청을 해야한다는 것을 알려주고 Refresh 할 수 있도록 /token/** 을 전체 허용
                .antMatchers("/api/join/**").permitAll()
                .antMatchers("/api/validation/**").permitAll()
                .antMatchers("/api/login/**").permitAll()
                .antMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-resources/**").permitAll()
//                .antMatchers("/login/**").permitAll()
//                .antMatchers("/confirm-email/**").permitAll()
                //다른 요청은 누구든지 접근 가능
                .anyRequest().authenticated();
                //.anyRequest().permitAll()
//                .and()
//                //.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
//                .oauth2Login().loginPage("/token/expired") // Authentication 객체가 springSecurityContextHolder 들어오면 모든 시큐리티 체인이 종료가 되면서 login 서비스 종료
//                .successHandler(successHandler)
//                .userInfoEndpoint().userService(oAuth2UserService);

        //http.addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class);
        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }
}
