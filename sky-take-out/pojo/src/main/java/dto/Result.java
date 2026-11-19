package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "服务器返回数据格式")
public class Result {
    @Schema(description = "状态：1-成功，0-失败")
    private int code;
    @Schema(description = "返回提示")
    private String msg;
    @Schema(description = "具体数据")
    private Object data;


    public static Result success(){
        return new Result(1,"success",null);
    }
    public static Result success(Object data){
        return new Result(1,"success",data);
    }
    public static Result success(String msg,Object data) {
        return new Result(1,msg,data);
    }

    public static Result error(){
        return new Result(0,"error",null);
    }
    public static Result error(Object data){
        return new Result(0,"error",data);
    }
    public static Result error(String msg,Object data) {
        return new Result(0,msg,data);
    }
}
