package dto;

import entity.Admi;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "管理员列表查询结果")
public class AdmiSearchResult {
    @Schema(description = "查询到的管理员列表")
    private List<Admi> admiList;
    @Schema(description = "管理员总数")
    private Integer total;
}
