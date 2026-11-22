package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "dto,传输登录时所需要的数据")
public class LoginInfo {
    // 登录信息：用户名和密码
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "密码")
    private String password;
    @Schema(description = "登录后下发的令牌")
    private String token;
    @Schema(description = "昵称")
    private String name;
}
