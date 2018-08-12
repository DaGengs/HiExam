package cn.iruier.common.vo;

public class ResultVo {
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ResultVo setSuccess(String message) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setMessage(message);
        return resultVo;
    }

    public static ResultVo setError(String message) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(1);
        resultVo.setMessage(message);
        return resultVo;
    }
}
