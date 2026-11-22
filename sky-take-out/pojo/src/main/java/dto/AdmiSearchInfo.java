package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "前端管理员搜索条件")
public class AdmiSearchInfo {
    @Schema(description = "匹配姓名")
    private String searchName;
    @Schema(description = "单页数据条数")
    private Integer pageSize;
    @Schema(description = "当前页码")
    private Integer currentPage;
    @Schema(description = "起始索引")
    private Integer start;
}
