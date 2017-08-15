package com.icinfo.platform.image.controller.admin;

import com.icinfo.platform.common.bean.AjaxResponse;
import com.icinfo.platform.common.constant.Constant;
import com.icinfo.platform.common.util.RandomStringUtils;
import com.icinfo.platform.image.model.Image;
import com.icinfo.platform.image.service.IImageService;
import com.icinfo.platform.system.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * 图片集控制器
 */
@Controller("adminImageController")
@RequestMapping("admin/image")
@SessionAttributes(Constant.SESSION_USER_INFO)
public class ImageController {
    private static final Logger kafkaLogger = LoggerFactory.getLogger("kafkaLogger");

    @Autowired
    private IImageService iImageService;

    /**
     * 进入图片集展示页
     *
     * @return 图片集展示页
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) throws Exception {
        model.addAttribute("list", iImageService.selectList());
        //kafkaLogger.info("tolist---1213211");
        return "admin/image/list";
    }

    /**
     * 进入编辑页面
     *
     * @return 编辑页
     * @throws Exception
     */
    @RequestMapping(value = "/toaddoredit", method = RequestMethod.GET)
    public String toAddOrEdit() throws Exception {
        return "admin/image/edit";
    }

    /**
     * 新增图片信息
     *
     * @param request   请求
     * @param model     模型
     * @param image     图片集信息
     * @param imageFile 图片信息
     * @param user      用户信息
     * @return 新增结果
     * @throws Exception
     */
    @RequestMapping(value = "/addoredit", method = RequestMethod.POST)
    public String addOrEdit(HttpServletRequest request, Model model, Image image,
                            @RequestParam(value = "imageUrl", required = false) CommonsMultipartFile imageFile,
                            @ModelAttribute(Constant.SESSION_USER_INFO) User user) throws Exception {
        String path = request.getServletContext().getRealPath("/");
        String name = imageFile.getFileItem().getName();
        String fileType = name.substring(name.lastIndexOf("."));
        String url = "\\upload\\image\\" + System.currentTimeMillis() + RandomStringUtils.random(4, false, true) + fileType;

        //设置其他信息
        image.setUrl(url);
        image.setCreateTime(new Date());
        image.setCreator(user.getRealName());
        boolean insertRst = iImageService.insert(image);
        if (insertRst) {
            //新建文件
            File file = new File(path + url);
            file.createNewFile();

            //写入文件
            FileOutputStream fop = new FileOutputStream(file);
            byte[] bytes = imageFile.getBytes();
            fop.write(bytes);
            fop.flush();
            fop.close();
        }
        return "redirect:list";
    }

    /**
     * 删除
     *
     * @param id 唯一标识
     * @return 删除结果 成功：true，失败：false
     * @throws Exception
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse<Boolean> delete(@PathVariable("id") String id, HttpServletRequest request) throws Exception {
        Image qImage = iImageService.getById(id);
        if (qImage != null) {
            iImageService.delete(id);
            String path = request.getServletContext().getRealPath("/")+qImage.getUrl();
            File file = new File(path);
            if(file.exists()){
                file.delete();
            }
        }
        return new AjaxResponse<>(true);
    }
}
