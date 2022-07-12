package hello.hellospring.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

@SpringBootTest
@Transactional
class MemberServiceDBTest {
	@Autowired MemberService memberService;;
	@Autowired MemberRepository repository;
	
	@Test
	void testJoin() {
		//given
		 Member member = new Member();
		 member.setName("member1");
		//when
		Long id = memberService.join(member);
		//then
		Member one =  memberService.findOne(id).get();
		assertThat(member.getName()).isEqualTo(one.getName());
		
		
	}
	
	@Test
	void testJoin2() {
		//given
		Member member1 = new Member();
		member1.setName("member1");
		
		Member member2 = new Member();
		member2.setName("member1");
		//when
		memberService.join(member1);
		IllegalStateException e = assertThrows(IllegalStateException.class, ()-> memberService.join(member2));
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
	
		
	}


}
