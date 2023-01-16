package me.isayaksh.tutorial.repository;

import me.isayaksh.tutorial.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberId(Long memberId);

    Optional<Member> findByUsername(String username);

    @Modifying
    @Query(value = "insert into member_roles(member_member_id, roles) values (:memberId, 'USER')", nativeQuery = true)
    void createMemberRole(@Param("memberId") Long memberId);
}
