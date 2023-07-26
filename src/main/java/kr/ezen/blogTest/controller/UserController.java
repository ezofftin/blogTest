package kr.ezen.blogTest.controller;

import kr.ezen.blogTest.domain.RoleType;
import kr.ezen.blogTest.domain.User;
import kr.ezen.blogTest.exception.BlogException;
import kr.ezen.blogTest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.function.Supplier;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // 회원등록처리
    @PostMapping("/user")
    public @ResponseBody String insertUser(@RequestBody User user) {
        user.setRole(RoleType.USER);
        userRepository.save(user); // 등록(id값이 없을때), 수정(id 값이 있을 때)
        return user.getUsername() + "회원 등록 성공";
    }

    // 회원조회
//    @GetMapping("/user/get/{id}")
//    public @ResponseBody User getUser(@PathVariable int id) {
//
//        // Optional<User>
//        User findUser= userRepository.findById(id).get();
//        return findUser;
//    }

    // 회원조회 예외처리
    @GetMapping("/user/get/{id}")
    public @ResponseBody User getUser(@PathVariable int id) {

        // 검색된 회원이 없을 경우에 예외를 반환
        // Optional<User>
//        User findUser= userRepository.findById(id).orElseThrow(new Supplier<BlogException>() {
//            @Override
//            public BlogException get() {
//                return new BlogException(id + "번 회원이 없습니다!!");
//            }
//        });

//        User findUser= userRepository.findById(id).orElseThrow(()->
//            { return new BlogException(id + "번 회원이 없습니다!!"); }
//        );

        User findUser= userRepository.findById(id).orElseThrow(()->
                new BlogException(id + "번 회원이 없습니다!!")
        );

        return findUser;
    }
}
