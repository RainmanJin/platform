//package com.icinfo.platform.elasticsearch.service.impl;
//
//import com.icinfo.platform.common.util.StringUtils;
//import com.icinfo.platform.elasticsearch.mapper.BookMapper;
//import com.icinfo.platform.elasticsearch.model.Book;
//import com.icinfo.platform.elasticsearch.service.IBookService;
//import io.searchbox.client.JestClient;
//import io.searchbox.client.JestResult;
//import io.searchbox.core.Delete;
//import io.searchbox.core.Index;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Date;
//
///**
// * 图书接口实现
// */
//@Service
//public class BookServiceImpl implements IBookService {
//    @Autowired
//    private JestClient jestClient;
//
//    @Autowired
//    private BookMapper bookMapper;
//
//    /**
//     * 新增/修改
//     *
//     * @param book
//     * @param operator
//     * @throws Exception
//     */
//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public void insertOrUpdate(Book book, String operator) throws Exception {
//        book.setOperator(operator);
//        book.setOperateDate(new Date());
//        if (StringUtils.isBlank(book.getId())) {
//            book.setCreateDate(book.getOperateDate());
//            book.setCreator(book.getOperator());
//            bookMapper.insert(book);
//        } else {
//            bookMapper.updateByPrimaryKey(book);
//        }
//
//        Index.Builder builder = new Index.Builder(book);
//        builder.index("zmw-library").type("book").id(book.getId());
//        JestResult result = jestClient.execute(builder.build());
//        if (!result.isSucceeded()) {
//            throw new RuntimeException(result.getErrorMessage() + "插入更新索引失败!");
//        }
//    }
//
//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public void delete(String id) throws Exception {
//        bookMapper.deleteByPrimaryKey(id);
//        Delete.Builder builder = new Delete.Builder(id);
//        builder.index("library").type("book");
//
//        JestResult result = jestClient.execute(builder.build());
//        if (!result.isSucceeded()) {
//            throw new RuntimeException(result.getErrorMessage() + "删除数据失败！");
//        }
//    }
//
//}
