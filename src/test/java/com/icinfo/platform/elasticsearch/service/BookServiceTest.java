package com.icinfo.platform.elasticsearch.service;

import com.icinfo.platform.common.test.SpringTxTestCase;
import com.icinfo.platform.elasticsearch.model.Book;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * es测试
 */
public class BookServiceTest extends SpringTxTestCase {
    @Autowired
    private IBookService bookService;

    @Test
    //@Ignore
    public void testInsertOrUpdate() throws Exception {
        Book book = new Book();
        book.setNumber("J20170405123K");
        book.setTitle("JAVA开发与实战");
        book.setAuthor("佚名");
        book.setCategory("计算机与计算科学");
        book.setPublisher("上海某某出版社");
        book.setPublicationDate(DateUtils.parseDate("2006-04-01", "yyyy-MM-dd"));
        String operator = "admin";
        bookService.insertOrUpdate(book, operator);
    }

    @Test
    //@Ignore
    public void testDelete() throws Exception{
        String id = "dda288e2354d11e79bbc00ff2f66a4f5";
        bookService.delete(id);
    }
}

