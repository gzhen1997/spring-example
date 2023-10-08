package com.demo.service;

import com.demo.dao.ESBookRepository;
import com.demo.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther gzhen
 * @date 2023-09-13  11:01
 * @description
 */

@Slf4j
@Service
public class BookService {

    private final ESBookRepository esBookRepository;

    public BookService(ESBookRepository esBookRepository) {
        this.esBookRepository = esBookRepository;
    }

    public void addBook(Book book) {
        try {
            esBookRepository.save(book);
        }catch (Exception e){
            log.error(String.format("保存ES错误！%s", e.getMessage()));
        }
    }

    public List<Book> searchBook(String keyword){
        return esBookRepository.findByTitleOrAuthor(keyword, keyword);
    }

    public SearchHits<Book> searchBook1(String keyword){
        return esBookRepository.find(keyword);
    }
}