package com.icinfo.platform.elasticsearch.service;

import com.icinfo.platform.elasticsearch.model.Book;

/**
 * 图书接口
 */
public interface IBookService {
    /**
     * 新增/修改
     *
     * @param book
     * @param operator
     * @throws Exception
     */
    public void insertOrUpdate(Book book, String operator) throws Exception;

    public void delete(String id) throws Exception;
}
