package kr.ezen.blogTest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // 댓글 일련 번호

    @Column(nullable = false, length = 400)
    private String content; // 댓글 내용

    @CreationTimestamp
    private Timestamp createDate; // 댓글 등록일

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "postid")
    private Post post; // 관련 포스트

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userid")
    private User user; // 관련된 유저
}
