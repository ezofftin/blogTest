package kr.ezen.blogTest.controller;

import kr.ezen.blogTest.domain.RoleType;
import kr.ezen.blogTest.domain.User;
import kr.ezen.blogTest.dto.ResponseDTO;
import kr.ezen.blogTest.exception.BlogException;
import kr.ezen.blogTest.repository.UserRepository;
import kr.ezen.blogTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    // 회원등록 Form 이동
    @GetMapping("/auth/register")
    public String registerUser() {
//        System.out.println(9/0); // 예외처리 테스트
        return "user/register";
    }

    @PostMapping("/auth/register")
    public @ResponseBody ResponseDTO<?> insertUser(@RequestBody User user) {
//        userService.insertUser(user);
//        return new ResponseDTO<>(HttpStatus.OK.value(), user.getUsername() + "님 회원 가입 성공 완료!!");

        // 아이디 중복체크
        User findUser = userService.getUser(user.getUsername());

        if (findUser.getUsername() == null) {
            userService.insertUser(user);

            return new ResponseDTO<>(HttpStatus.OK.value(), user.getUsername() + "님 회원가입 성공했습니다!!");
        }else{
            return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), user.getUsername() + "님은 이미 회원이십니다!!");
        }
    }


    // ****************************** REST API *************************
//    @Autowired
    private UserRepository userRepository;

    // 회원등록처리
//    @PostMapping("/user")
//    public @ResponseBody String insertUser(@RequestBody User user) {
//        user.setRole(RoleType.USER);
//        userRepository.save(user); // 등록(id값이 없을때), 수정(id 값이 있을 때)
//        return user.getUsername() + "회원 등록 성공";
//    }

    // 회원조회
//    @GetMapping("/user/get/{id}")
//    public @ResponseBody User getUser(@PathVariable int id) {
//
//        // Optional<User>
//        User findUser= userRepository.findById(id).get();
//        return findUser;
//    }

    // 회원조회 예외처리
//    @GetMapping("/user/get/{id}")
//    public @ResponseBody User getUser(@PathVariable int id) {
//
//        // 검색된 회원이 없을 경우에 예외를 반환
//        // Optional<User>
////        User findUser= userRepository.findById(id).orElseThrow(new Supplier<BlogException>() {
////            @Override
////            public BlogException get() {
////                return new BlogException(id + "번 회원이 없습니다!!");
////            }
////        });
//
////        User findUser= userRepository.findById(id).orElseThrow(()->
////            { return new BlogException(id + "번 회원이 없습니다!!"); }
////        );
//
//        User findUser = userRepository.findById(id).orElseThrow(() ->
//                new BlogException(id + "번 회원이 없습니다!!")
//        );
//
//        return findUser;
//    }

    // 회원정보수정
//    @PutMapping("/user")
//    public @ResponseBody String updateUser(@RequestBody User user) {
//        User findUser = userRepository.findById(user.getId()).orElseThrow(() ->
//                new BlogException(user.getId() + "번 회원이 없습니다!!")
//        );
//
//        findUser.setUsername(user.getUsername());
//        findUser.setPassword(user.getPassword());
//        findUser.setEmail(user.getEmail());
//
//        // save는
//        // User 엔티티 필드에 식별자값이 있으면 수정, 값이 없으면 insert 동작
//        userRepository.save(findUser);
//        return "수정 완료!!";
//    }

    // 회원 삭제
//    @DeleteMapping("/user/{id}")
//    public @ResponseBody String deleteUser(@PathVariable int id) {
//        userRepository.deleteById(id);
//        return "삭제완료!!";
//    }

    // 회원리스트 조회
//    @GetMapping("/user/list")
//    public @ResponseBody List<User> getUserList() {
//        return userRepository.findAll();
//    }

    // 페이징 처리 #1
//    @GetMapping("/user/page/{page}")
//    // 단순 리스트조회의 타입은 List<User>, List객체
//    // 페이지 조회의 리턴 타입은 Page<User>,
//    // Page객체안에는 paging과 관련된 여러 데이터값이 셋팅
//    public @ResponseBody Page<User> getUserListPaging(@PathVariable int page) {
//                                        //페이지번호(0부터 시작~), size:한페이지에 보여줄 갯수
//        PageRequest pageable= PageRequest.of(page, 2, Sort.Direction.DESC, "id", "username");
//
//        // pageable 객체가 인자로 전달되면 findAll()는 Page객체를 리턴
//        return userRepository.findAll(pageable);
//    }

    // 페이징 처리 #2
    @GetMapping("/user/page")
    public @ResponseBody Page<User> getUserListPaging(@PageableDefault(page=0, size=2,
    direction = Sort.Direction.DESC, sort={"id", "username"}) Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}


