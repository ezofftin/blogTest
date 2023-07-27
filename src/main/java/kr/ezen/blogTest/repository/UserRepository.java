package kr.ezen.blogTest.repository;

import kr.ezen.blogTest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
//    SQL => JPQL => 쿼리메서드
    // find + 엔티티명 + By + 변수명 ==> findUserByUsername
    // 엔티티명은 생략 가능 : findByUsername

    // SELECT * FROM users WHERE username = ?1;
    Optional<User> findByUsername(String username);
}
