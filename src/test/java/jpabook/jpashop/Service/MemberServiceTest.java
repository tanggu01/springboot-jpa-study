package jpabook.jpashop.Service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Fail.fail;
//===[1]=== 진짜 눈으 DB에 반영되는지 보고싶을땐 @Rollback(false) 하고 들어가보기.
//===[2]=== 실제 트랜잭션은 롤백을 하고 강제 플러시로 쿼리도 날리게한다.

@RunWith(SpringRunner.class)
@SpringBootTest
//Spring 꺼 쓰기 . @Transactional 이 Test 에 있으면 끝나고 디비를 롤백.
@Transactional //기본적으로 롤백하기때문에 @Rollback(false)로 insert 쿼리 확인해야한다
public class MemberServiceTest {


    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    //===[2]=== 롤백이지만 db에 쿼리 날리는걸 보고싶을때:
    @Autowired EntityManager em;

    @Test
    // ===[1]===  insert 쿼리를 볼수있음 !!
    //@Rollback(false)
    public void 회원가입() throws Exception { //같은 transaction, 같은 영속성 컨텍스트 하나만 생성
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long savedId = memberService.join(member);

        //then
        em.flush(); //===[2]=== flush 로 영속성 컨텍스트에 있는걸 디비에 반영하기.쿼리날림.
        Assert.assertEquals(member, memberRepository.findOne(savedId)); //저장한멤버와 찾아온멤버가 같은지 증명
    }

    //@Test
    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        //Given
        Member member1 = new Member();
        member1.setName("kim");
        Member member2 = new Member();
        member2.setName("kim");

        //When
        memberService.join(member1);
//        try {
        memberService.join(member2); //예외가 발성해야한다!
        //ㄴ test실패해야함 -> try/catch 로 감싸기 -> @Test(expected= e.class) 로 처리 가능
//        } catch (IllegalStateException e) {
//            return;
//        }

        //Then
        fail("예외가 발생해야 한다."); //코드가 여기로 오면 안된다.

    }
}