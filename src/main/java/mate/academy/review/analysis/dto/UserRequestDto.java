package mate.academy.review.analysis.dto;

import lombok.Data;

@Data
public class UserRequestDto {
    private String login;
    private String password;
    private String repeatedPassword;
}
