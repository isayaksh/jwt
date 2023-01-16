package me.isayaksh.tutorial.service;

import lombok.RequiredArgsConstructor;
import me.isayaksh.tutorial.domain.Member;
import me.isayaksh.tutorial.jwt.JwtTokenProvider;
import me.isayaksh.tutorial.repository.MemberRepository;
import me.isayaksh.tutorial.jwt.TokenInfo;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenInfo login(String username, String password) {
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);



        return tokenInfo;
    }

    @Transactional
    public void join(String username, String password) {
        Member member = new Member(username, password);
        memberRepository.save(member);
        memberRepository.createMemberRole(member.getMemberId());

    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }
}
