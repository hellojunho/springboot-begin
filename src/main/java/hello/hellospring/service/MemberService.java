 // ------------ 1) Component 스캔을 이용한 스프링 빈 등록 --------------


package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service    // 스프링이 올라올 때, Service 클래스를 스프링 컨테이너에 등록해줌
// 클래스 이름에 커서를 두고, ctrl + shift + t : 자동으로 테스트 케이스 생성
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    // alt + insert : 생성자 자동 완성
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */

    public Long join(Member member) {
        // 같은 이름을 사용하는 중복 회원 불가
        // ctrl + alt + shift + t : refactor 단축키
        validateDuplicateMember(member);    // 중복 회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });     // result에 같은 값이 이미 있으면 예외처리
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {

        return memberRepository.findAll();

    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}


//// ------------- 2) 자바 코드로 직접 스프링 빈 등록하기 --------------
//
//package hello.hellospring.service;
//
//import hello.hellospring.domain.Member;
//import hello.hellospring.repository.MemberRepository;
//import hello.hellospring.repository.MemoryMemberRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//
//public class MemberService {
//
//    private final MemberRepository memberRepository;
//
//
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    /**
//     * 회원 가입
//     */
//    public Long join(Member member) {
//        // 같은 이름을 사용하는 중복 회원 불가
//        // ctrl + alt + shift + t : refactor 단축키
//        validateDuplicateMember(member);    // 중복 회원 검증
//
//        memberRepository.save(member);
//        return member.getId();
//    }
//
//    private void validateDuplicateMember(Member member) {
//        memberRepository.findByName(member.getName())
//                .ifPresent(m -> {
//                    throw new IllegalStateException("이미 존재하는 회원입니다.");
//                });     // result에 같은 값이 이미 있으면 예외처리
//    }
//
//    /**
//     * 전체 회원 조회
//     */
//    public List<Member> findMembers() {
//        return memberRepository.findAll();
//    }
//
//    public Optional<Member> findOne(Long memberId) {
//        return memberRepository.findById(memberId);
//    }
//
//}
