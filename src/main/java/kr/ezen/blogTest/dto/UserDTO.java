package kr.ezen.blogTest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotNull(message = "username 파라미터가 전달되지 않았습니다.")//null 체크
    @NotBlank(message = "username은 필수 입력 항목입니다.") // 빈문자열 확인
    @Size(min=4, max=15, message = "username은 4 ~ 15자까지 가능합니다~")
    private String username;

    private String password;

    @NotNull(message = "email 파라미터가 전달되지 않았습니다.")//null 체크
    @NotBlank(message = "email은 필수 입력 항목입니다.") // 빈문자열 확인
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;
}
