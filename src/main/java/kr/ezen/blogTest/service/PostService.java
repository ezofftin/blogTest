package kr.ezen.blogTest.service;

import kr.ezen.blogTest.domain.Post;
import kr.ezen.blogTest.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public void insertPost(Post post) {
        postRepository.save(post);
    }

//    public List<Post> getPostList() {
//        return postRepository.findAll();
//    }

    public Page<Post> getPostList(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public Post getPost(int id) {
        return postRepository.findById(id).get();
    }
}
