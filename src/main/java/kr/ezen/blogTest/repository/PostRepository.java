package kr.ezen.blogTest.repository;

import kr.ezen.blogTest.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
