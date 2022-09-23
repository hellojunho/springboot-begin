package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


//@Repository     // repository를 명시
public class MemoryMemberRepository implements MemberRepository{
    // 메소드 오버라이딩 : alt + insert

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));  // id가 null 이어도 감싸서 반환 가능
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter((member->member.getName().equals(name)))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }


}


//// ------------ 2) 자바 코드로 직접 스프링 빈 등록 -----------
//
//
//package hello.hellospring.repository;
//
//import hello.hellospring.domain.Member;
//import org.springframework.stereotype.Repository;
//
//import java.util.*;
//
//
//public class MemoryMemberRepository implements MemberRepository{
//    // 메소드 오버라이딩 : alt + insert
//
//    private static Map<Long, Member> store = new HashMap<>();
//    private static long sequence = 0L;
//
//    @Override
//    public Member save(Member member) {
//        member.setId(++sequence);
//        store.put(member.getId(), member);
//        return member;
//    }
//
//    @Override
//    public Optional<Member> findById(Long id) {
//        return Optional.ofNullable(store.get(id));  // id가 null 이어도 감싸서 반환 가능
//    }
//
//    @Override
//    public Optional<Member> findByName(String name) {
//        return store.values().stream()
//                .filter((member->member.getName().equals(name)))
//                .findAny();
//    }
//
//    @Override
//    public List<Member> findAll() {
//        return new ArrayList<>(store.values());
//    }
//
//    public void clearStore(){
//        store.clear();
//    }
//}
