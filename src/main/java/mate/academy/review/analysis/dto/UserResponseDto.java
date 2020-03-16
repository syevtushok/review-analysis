package mate.academy.review.analysis.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponseDto {
    private String profileName;
    private String userId;
    private Long activityIndex;
    private String token;

    public UserResponseDto(String profileName, String userId, Long activityIndex) {
        this.profileName = profileName;
        this.userId = userId;
        this.activityIndex = activityIndex;
    }

    public UserResponseDto(String token) {
        this.token = token;
    }
}

