package kr.ezen.blogTest.service;

import kr.ezen.blogTest.domain.Post;
import kr.ezen.blogTest.domain.Reply;
import kr.ezen.blogTest.domain.User;
import kr.ezen.blogTest.repository.PostRepository;
import kr.ezen.blogTest.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReplyService {
    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private PostRepository postRepository;
    // 댓글이 등록될 포스트 정보와, 사용자의 댓글 입력정보, User 정보
    @Transactional
    public void insertReply(int postId, Reply reply, User user) {
        Post post = postRepository.findById(postId).get();
        reply.setUser(user);
        reply.setPost(post);
        replyRepository.save(reply);
    }

    public void deleteReply(int replyId) {
        replyRepository.deleteById(replyId);
    }
}
