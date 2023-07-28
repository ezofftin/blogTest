package kr.ezen.blogTest.controller;

import kr.ezen.blogTest.domain.Post;
import kr.ezen.blogTest.domain.User;
import kr.ezen.blogTest.dto.ResponseDTO;
import kr.ezen.blogTest.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

//    @GetMapping({"", "/"})
//    public String getPostList() {
//        return "home";
//    }

//    @GetMapping({"", "/"})
//    public String getPostList(Model m) {
//        m.addAttribute("postList",postService.getPostList());
//        return "home";
//    }

    // 페이징 처리
    @GetMapping({"", "/"})
    public String getPostList(Model m,
                              // page매개변수를 없애면 동적으로 받는다.
                              @PageableDefault(size=2, sort = "id", direction = Sort.Direction.DESC)Pageable pageable) {
        m.addAttribute("postList",postService.getPostList(pageable));
        return "home";
    }

    // 상세보기
    @GetMapping("/post/{id}")
    public String getPost(@PathVariable int id, Model model) {
        model.addAttribute("post", postService.getPost(id));

        return "post/getPost";
    }


    // 포스트 등록 화면 이동
    @GetMapping("/post/registerPost")
    public String registerPost() {
        return "post/registerPost";
    }

    // 포스트 등록
    @PostMapping("/post")
    public @ResponseBody ResponseDTO<?> insertPost(@RequestBody Post post, HttpSession session) {

        User principal = (User) session.getAttribute("principal");
        post.setUser(principal);
        postService.insertPost(post);

        return new ResponseDTO<>(HttpStatus.OK.value(), "새로운 포스트를 등록했습니다!!");
    }
}
