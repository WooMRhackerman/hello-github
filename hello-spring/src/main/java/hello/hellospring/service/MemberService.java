package hello.hellospring.service;

import java.util.List;
import java.util.Optional;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

public class MemberService {
	private final MemberRepository memberRepository;
	

	public MemberService(MemberRepository memberRepository) {
		super();
		this.memberRepository = memberRepository;
	}
	/* 
	 * 회원가입 
	 * 
	 * */
	public Long join(Member member) {
		validateMember(member);
		memberRepository.save(member);
		
		return member.getId();
	}
	/* 
	 * 회원목록 조회 
	 * 
	 * */
	public List<Member> findAllMembers(){
		return memberRepository.findAll();
	}
	/* 
	 * 회원아이디 기반 조회 
	 * 
	 * */
	public Optional<Member> findOne(Long memberId){
		return memberRepository.findById(memberId);
	}
	
	//중복회원 검증
	private void validateMember(Member member) {
		memberRepository.findByName(member.getName())
			.ifPresent(m ->{
				throw new IllegalStateException("이미 존재하는 회원입니다.");
			});
	}
}