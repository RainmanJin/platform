package com.icinfo.platform.common.advice;

import com.icinfo.platform.common.bean.AjaxResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * controller异常处理器
 */
@ControllerAdvice({"com.icinfo.platform"})
public class ControllerExceptionAdvice {
    public static final Logger logger = LoggerFactory.getLogger(ControllerExceptionAdvice.class);

    /**
     * 方法调用参数缺失
     *
     * @param request 请求
     * @param e       异常
     * @return 结果
     * @throws Exception
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public AjaxResponse<String> handle(HttpServletRequest request, MissingServletRequestParameterException e) throws Exception {
        String message = e.getParameterName() + "参数项必传！";
        return new AjaxResponse<>("401", message);
    }


}
