package plo.study.ploSpring.service;

import plo.study.ploSpring.domain.Member;
import plo.study.ploSpring.repository.MemberRepository;
import plo.study.ploSpring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService { //함수 내에서 new 로 객체를 만들어주지않고 외부에서 객체를 입력할 수 있게하는 방식 DI
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    //회원가입
    public Long join(Member member){
        //같은 이름이있는 중복회원은 x
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { //ifPresent : lamda식 조건문 Exception처리
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    //전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findall();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
