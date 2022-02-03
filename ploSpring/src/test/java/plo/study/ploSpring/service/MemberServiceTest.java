package plo.study.ploSpring.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import plo.study.ploSpring.domain.Member;
import plo.study.ploSpring.repository.MemoryMemberRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() { // 테스트의 실행 전 memberRepository를 만들어주고 memberService에 memberRepository를 넣어준다.
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }


    @Test
    void join() {
        //given : 뭔가가 주어졌다
        Member member = new Member();
        member.setName("plo");

        //when : 이걸 실행했을 때
        Long saveId = memberService.join(member);

        //then : 결과는 이게 나와야해!
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복회원예외() {
        //given
        Member member1 = new Member();
        member1.setName("plo1");

        Member member2 = new Member();
        member2.setName("plo1");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        try {
//            memberService.join(member2);
//            fail("예외가 발생해야합니다.");
//        }catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        //then
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}