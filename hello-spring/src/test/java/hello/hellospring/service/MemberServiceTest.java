package hello.hellospring.service;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;

class MemberServiceTest {
	MemberService memberService;;
	MemoryMemberRepository repository;
	
	@BeforeEach
	public void beforeEach() {
		repository = new MemoryMemberRepository();
		memberService = new MemberService(repository);
	}
	
	@AfterEach
	public void afterEach() {
		repository.clearStore();
	}
	
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
		/*
		 * try { memberService.join(member2); fail(); } catch (Exception e) {
		 * assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); }
		 */
		
		
		//then
		
	}

	/*
	 * @Test void testFindAllMembers() {
	 * 
	 * }
	 * 
	 * @Test void testFindOne() {
	 * 
	 * }
	 */

}
