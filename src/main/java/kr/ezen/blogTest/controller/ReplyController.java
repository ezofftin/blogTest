package kr.ezen.blogTest.controller;

import kr.ezen.blogTest.domain.Reply;
import kr.ezen.blogTest.domain.User;
import kr.ezen.blogTest.dto.ResponseDTO;
import kr.ezen.blogTest.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    @PostMapping("/reply/{postId}")
    public @ResponseBody ResponseDTO<?> insertReply(
            @PathVariable int postId, @RequestBody Reply reply,
            HttpSession session)
    {
        User principal= (User) session.getAttribute("principal");

        replyService.insertReply(postId, reply, principal);
        return new ResponseDTO<>(HttpStatus.OK.value(),
                postId + "번 포스트에 대한 댓글이 등록되었습니다!!");
    }
}
