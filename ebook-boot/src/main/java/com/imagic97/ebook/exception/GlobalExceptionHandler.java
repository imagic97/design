package com.imagic97.ebook.exception;

import com.imagic97.ebook.common.CommonMessageCode;
import com.imagic97.ebook.common.ResultBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;

/**
 * @author imagic
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理自定义的业务异常
     * @param e
     * @param req
     * @return
     */
    @ExceptionHandler(value = MessageException.class)
    @ResponseBody
    public ResultBody exceptionHandler(HttpServletRequest req ,MessageException e){
        logger.error("业务异常！原因是:"+e.getErrorMessage());
        return ResultBody.error(e.getErrorCode(),e.getErrorMessage());
    }


    /**
     * 处理空指针的异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public ResultBody exceptionHandler(HttpServletRequest req, NullPointerException e) {
        logger.error("空指针异常！原因是:", e);
        return ResultBody.error(CommonMessageCode.BODY_NOT_MATCH);
    }


    @ExceptionHandler(value = FileNotFoundException.class)
    @ResponseBody
    public ResultBody exceptionHandler(HttpServletRequest req, FileNotFoundException e) {
        logger.error("未知异常！原因是:", e);
        return ResultBody.error(CommonMessageCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * 处理其他异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultBody exceptionHandler(HttpServletRequest req, Exception e) {
        logger.error("未知异常！原因是:", e);
        return ResultBody.error(CommonMessageCode.INTERNAL_SERVER_ERROR);
    }
}
