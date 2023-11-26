package com.best11.gamelog.jwt;


import com.best11.gamelog.CommonResponseDto;
import com.best11.gamelog.user.security.UserDetailsImpl;
import com.best11.gamelog.user.security.UserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Slf4j(topic = "JWT 검증 및 인가")
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = jwtUtil.resolveToken(request); // Bearer 떼기

        if(Objects.nonNull(token)) { // Null 이 아니면
            if(jwtUtil.validateToken(token)) { // 검증
                Claims info = jwtUtil.getUserInfoFromToken(token);

                /*  https://teamsparta.notion.site/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.
                    com%2Fa5154fec-66b1-4e27-838b-77218f812d83%2FUntitled.png?table=block&id=6821de8e-fcc4-402b-a1d2-c87b
                    eb445300&spaceId=83c75a39-3aba-4ba4-a792-7aefe4b07895&width=970&userId=&cache=v2 참고 */
                String userId = info.getSubject();
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                UserDetailsImpl userDetails = userDetailsService.getUserDetails(userId);
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                context.setAuthentication(authentication);
                SecurityContextHolder.setContext(context);
                // -> 이제 @AuthenticationPrincipal 로 조회할 수 있음
            } else {
                // 인증정보가 존재하지 않을때
                CommonResponseDto commonResponseDto = new CommonResponseDto("토큰이 유효하지 않습니다.", HttpStatus.BAD_REQUEST.value());
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("application/json; charset=UTF-8");
                response.getWriter().write(objectMapper.writeValueAsString(commonResponseDto));
                return;
            }
        }

        filterChain.doFilter(request, response); // 다음 필터로 이동(?)
    }
}