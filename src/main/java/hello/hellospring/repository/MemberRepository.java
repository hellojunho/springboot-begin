package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    // Optional<T> : findById와 같은 함수가 null을 반환하는 대신 Optional로 감싸서 반환
    Optional<Member> findById(Long id);         // findById : id로 회원을 찾음
    Optional<Member> findByName(String name);   // findByName : name으로 회원을 찾음
    List<Member> findAll();                     // 지금까지 저장된 모든 회원 리스트 반환
}
