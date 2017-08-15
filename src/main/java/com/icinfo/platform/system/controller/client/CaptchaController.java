package com.icinfo.platform.system.controller.client;

import com.google.code.kaptcha.Producer;
import com.icinfo.platform.common.annotation.IgnoreSecurity;
import com.icinfo.platform.common.bean.AjaxResponse;
import com.icinfo.platform.common.constant.Constant;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 验证码控制器
 */
@Controller("clientCaptchaController")
@RequestMapping("/client/captcha")
public class CaptchaController {
    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(CaptchaController.class);

    /**
     * 验证码生成器注入
     */
    @Autowired
    private Producer captchaProducer;

    /**
     * 生成图片验证码
     *
     * @param session session
     * @return 图片验证码
     * @throws Exception
     */
    @IgnoreSecurity
    @RequestMapping(value = "/generatecode", method = RequestMethod.GET)
    public ResponseEntity<byte[]> generateCode(HttpSession session) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Pragma", "No-cache");
        headers.set("Cache-Control", "No-cache");
        headers.setDate("Expires", 0);
        headers.setContentType(MediaType.IMAGE_JPEG);
        ResponseEntity<byte[]> response = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            String capText = captchaProducer.createText();
            BufferedImage bufferedImage = captchaProducer.createImage(capText);
            ImageIO.write(bufferedImage, "jpg", out);
            response = new ResponseEntity<byte[]>(out.toByteArray(), headers, HttpStatus.OK);

            //放入session
            session.setAttribute(Constant.SESSION_ATTR_TEXT_CODE, capText);
        } catch (IOException e) {
            response = new ResponseEntity<byte[]>(new ByteArrayOutputStream().toByteArray(), headers, HttpStatus.OK);
            logger.error("生产图片验证码失败！", e);
        }

        return response;
    }

    /**
     * 校验图片验证码
     *
     * @param captcha 用户输入的验证码
     * @param session session
     * @return 校验结果
     */
    @RequestMapping(value = "/checkcode", method = RequestMethod.GET)
    @ResponseBody
    public boolean checkcode(@RequestParam(value = "captcha", required = true) String captcha, HttpSession session) {
        Object textCode = session.getAttribute(Constant.SESSION_ATTR_TEXT_CODE);
        if (textCode == null || !captcha.equals(textCode.toString())) {
            return false;
        }
        return true;
    }
}
