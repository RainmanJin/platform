//package com.icinfo.platform.elasticsearch.controller.admin;
//
//import com.icinfo.platform.common.bean.AjaxResponse;
//import com.icinfo.platform.common.constant.Constant;
//import com.icinfo.platform.elasticsearch.model.Book;
//import com.icinfo.platform.elasticsearch.service.IBookService;
//import com.icinfo.platform.system.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
///**
// * 图书控制器
// */
//@Controller("adminBookController")
//@RequestMapping("/admin/book")
//@SessionAttributes(Constant.SESSION_USER_INFO)
//public class BookController {
//    @Autowired
//    private IBookService bookService;
//
//    @RequestMapping(value = "/addoredit", method = RequestMethod.GET, produces = "application/json")
//    @ResponseBody
//    public AjaxResponse<Boolean> addOrEdit(Book book, @ModelAttribute(Constant.SESSION_USER_INFO) User user) throws Exception {
//        bookService.insertOrUpdate(book, user.getRealName());
//        return new AjaxResponse<>(true);
//    }
//
//}
