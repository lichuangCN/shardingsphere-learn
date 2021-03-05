package site.muzhi.common.result;

import lombok.Data;

/**
 * @author lichuang
 * @date 2021/03/05
 * @description 返回结果封装类
 */
@Data
public class Result {

    private Integer code;

    private String msg;

    private Object data;

    public static Result ok(Object data) {
        Result result = new Result();
        result.setCode(200);
        result.setMsg("操作成功");
        result.setData(data);
        return result;
    }

    public static Result ok(boolean res) {
        if (res) {
            return ok();
        } else {
            return failure();
        }
    }

    public static Result ok() {
        Result result = new Result();
        result.setCode(200);
        result.setMsg("操作成功");
        return result;
    }

    public static Result failure() {
        Result result = new Result();
        result.setCode(500);
        result.setMsg("操作失败");
        return result;
    }

    public static Result failure(String msg) {
        Result result = new Result();
        result.setCode(500);
        result.setMsg(msg);
        return result;
    }

}
