package plo.study.ploSpring.repository;

import plo.study.ploSpring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long , Member> store = new HashMap<>();
    private static long sequence = 0L ; //key값을 생성해준다.

    @Override
    public Member save(Member member) {
        member.setId(++sequence); //id를 셋팅함 + sequence에 ++해준다.
        store.put(member.getId(), member); // store Map에 key값을 id , value값을 member로 지정
        return member; //member를 반환
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.of(store.get(id)); //Optional.of():찾아오려는 id값이 null일 가능성이 있기에 Optionl로 감싸줌
                                            //Optionla.of()로 감싸주게 되면 클라이언트측에서

    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() //store Map의 값을 가지고 stream으로 다룸
                .filter(member -> member.getName().equals(name))// member.getname()이 함수의 parameter로 넘어온 name과 같은지
                .findAny(); //.findAny : 같은 값을 찾으면 반환한다. (Optional로 반환됨)
    }

    @Override
    public List<Member> findall() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
