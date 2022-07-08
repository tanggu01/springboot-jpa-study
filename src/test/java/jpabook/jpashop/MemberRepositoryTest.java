package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//
//public class MemberRepositoryTest {
//
////    @Autowired MemberRepository memberRepository;
//
//
//    @Test
//    @Transactional
//    @Rollback(false)
//    public void testMember() throws Exception {
//        //given
//        Member member = new Member();
//        member.setUsername("memberA");
//
//        //when
//        Long saveId = memberRepository.save(member);
//        Member findMember = memberRepository.find(saveId);
//
//        //then
//        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
//        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
//        Assertions.assertThat(findMember).isEqualTo(member); //저장한것과 조회한거는 같다
//        System.out.println("findMember == member = " + (findMember == member));
        //같은 transaction 안에서 저장, 조회 하면 영속성 컨텍스트가 같다.
        //같은 영속성 컨텍스트 안에선 id 값이 같으면 같은 Entity로 식별된다.
        //1차캐시에서 꺼내옴: select 쿼리도 안나간다.
//    }
//}