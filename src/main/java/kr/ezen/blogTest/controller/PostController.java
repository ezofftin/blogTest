package kr.ezen.blogTest.controller;

import kr.ezen.blogTest.domain.Post;
import kr.ezen.blogTest.domain.User;
import kr.ezen.blogTest.dto.PostDTO;
import kr.ezen.blogTest.dto.ResponseDTO;
import kr.ezen.blogTest.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private ModelMapper modelMapper;

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
                              @PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        m.addAttribute("postList", postService.getPostList(pageable));
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
    public @ResponseBody ResponseDTO<?> insertPost(@Valid @RequestBody PostDTO postDTO, BindingResult bindingResult, HttpSession session) {

        // 유효성 체크에 위배되는 데이터(에러)가 하나라도 있다면 true
//        if(bindingResult.hasErrors()){
//            Map<String, String> errorMap = new HashMap<>();
//            for (FieldError error : bindingResult.getFieldErrors()) {
//                // 어떤 필드(변수)에 에러가 있는지 해당메시지를 맵에 저장
//                errorMap.put(error.getField(), error.getDefaultMessage());
//            }
//
//            return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), errorMap);
//        }

        Post post = modelMapper.map(postDTO, Post.class);

        User principal = (User) session.getAttribute("principal");
        post.setUser(principal);
        postService.insertPost(post);

        return new ResponseDTO<>(HttpStatus.OK.value(), "새로운 포스트를 등록했습니다!!");
    }

    // 수정화면 이동
    @GetMapping("/post/updatePost/{id}")
    public String updatePost(@PathVariable int id, Model model) {
        model.addAttribute("post", postService.getPost(id));

        return "post/updatePost";
    }

    // 포스트 수정하기
    @PutMapping("/post")
    public @ResponseBody ResponseDTO<?> updatePost(@RequestBody Post post) {
        postService.updatePost(post);
        return new ResponseDTO<>(HttpStatus.OK.value(),
                post.getId() + "번 포스트를 수정했습니다!!");
    }

    // 삭제하기
    @DeleteMapping("/post/{id}")
    public @ResponseBody ResponseDTO<?> deletePost(@PathVariable int id) {

        postService.deletePost(id);
        return new ResponseDTO<>(HttpStatus.OK.value(),
                id + "번 포스트를 삭제했습니다.");
    }
}
