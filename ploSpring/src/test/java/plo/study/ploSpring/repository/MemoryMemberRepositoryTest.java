package plo.study.ploSpring.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import plo.study.ploSpring.domain.Member;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
     MemoryMemberRepository repository = new MemoryMemberRepository();

     @AfterEach //Test는 비동기적으로 작동할시 전체 테스트가 정상적으로 진행되지 않기에  하나의 코드블럭 실행 후 저장된 데이터를 지워줘야함
     public void afterEach() {
          repository.clearStore();
     }


     @Test
     public void save(){
          Member member = new Member();
          member.setName("Spring");

          repository.save(member);

          Member result = repository.findById(member.getId()).get();
//          Assertions.assertEquals(member , result); //Junit이 지원하는 함수  , expected값(기대) 과 actual값(실제)를 비교
//                                                       //error가 발생하지 않을시 정상
          assertThat(member).isEqualTo(result); //assertThat(값)이 .isEqulTo(값)과 같은지 비교
     }

     @Test
     public void findByName() {
          Member member1 = new Member();
          member1.setName("spring1");
          repository.save(member1);


          Member member2 = new Member();
          member2.setName("spring2");
          repository.save(member2);

          Member result = repository.findByName("spring2").get();
          assertThat(result).isEqualTo(member2);
     }

     @Test
     public void findAll() {
          Member member1 = new Member();
          member1.setName("spring1");
          repository.save(member1);

          Member member2 = new Member();
          member2.setName("spring2");
          repository.save(member2);

          List<Member> result = repository.findall();

          assertThat(result.size()).isEqualTo(2);


     }
}
