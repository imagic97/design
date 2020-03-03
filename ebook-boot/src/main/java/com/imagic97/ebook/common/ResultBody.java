package com.imagic97.ebook.common;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author imagic
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultBody {
    private String code;
    private String message;
    private Object result;

    public ResultBody(BaseMessageInterface baseMessageInterface) {
        this.code = baseMessageInterface.getResultCode();
        this.message = baseMessageInterface.getResultMessage();
    }

    public static ResultBody success(Object data) {
        ResultBody resultBody = new ResultBody();
        resultBody.setMessage(CommonMessageCode.SUCCESS.getResultMessage());
        resultBody.setCode(CommonMessageCode.SUCCESS.getResultCode());
        resultBody.setResult(data);
        return resultBody;
    }

    public static ResultBody error(BaseMessageInterface baseMessageInterface) {
        ResultBody resultBody = new ResultBody();
        resultBody.setCode(baseMessageInterface.getResultCode());
        resultBody.setMessage(baseMessageInterface.getResultMessage());
        resultBody.setResult(null);
        return resultBody;
    }

    public static ResultBody error(String code, String message) {
        ResultBody resultBody = new ResultBody();
        resultBody.setCode(code);
        resultBody.setMessage(message);
        resultBody.setResult(null);
        return resultBody;
    }

    public static ResultBody error(String message) {
        ResultBody resultBody = new ResultBody();
        resultBody.setCode("-1");
        resultBody.setMessage(message);
        resultBody.setResult(null);
        return resultBody;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
