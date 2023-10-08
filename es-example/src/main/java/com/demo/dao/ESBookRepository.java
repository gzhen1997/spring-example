package com.demo.dao;

import com.demo.entity.Book;
import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @auther gzhen
 * @date 2023-09-13  10:46
 * @description
 */

public interface ESBookRepository extends ElasticsearchRepository<Book, String> {

    List<Book> findByTitleOrAuthor(String title, String author);

    @Highlight(fields = {
            @HighlightField(name = "title"),
            @HighlightField(name = "author")
    })
    @Query("{\"match\":{\"title\":\"?0\"}}")
    SearchHits<Book> find(String keyword);
}
