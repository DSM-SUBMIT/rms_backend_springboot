package com.submit.toyproject.rms_backend_springboot.domain.member;

import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, MemberId> {
}
