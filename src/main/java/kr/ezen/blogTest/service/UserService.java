package kr.ezen.blogTest.service;

import kr.ezen.blogTest.domain.RoleType;
import kr.ezen.blogTest.domain.User;
import kr.ezen.blogTest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUser(String username) {
        // 익명클래스를 사용하면 가독성이 떨어져서 람다식으로 치환해서 사용한다.
//        User findUser = userRepository.findByUsername(username).orElseGet(new Supplier<User>() {
//            @Override
//            public User get() {
//                return new User();
//            }
//        });

        // 검색결과가 없으면 빈 객체를 리턴한다.
        User findUser = userRepository.findByUsername(username).orElseGet(()->{
            return new User();
        });

        return findUser;

    }

    public void insertUser(User user) {
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }

}
