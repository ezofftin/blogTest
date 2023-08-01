package kr.ezen.blogTest.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userid")
    private User user;

    @CreationTimestamp
    private Timestamp createDate;

    // 양방향 관계에서 mappedBy는 Many의 반대쪽에 적어준다.
    // FK를 두개 만들 수 없기 때문에 mappedBy를 사용한다.
    // OneToMany는 mappedBy 속성을 갖는다.

//    CascadeType.ALL :
//    CascadeType.PERSIST :
//    CascadeType.REMOVE:
//    CascadeType.DETACH:
//    CascadeType.REFRESH:

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Reply> replyList = new ArrayList<>();
}
