package com.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * @auther gzhen
 * @date 2023-09-12  16:35
 * @description
 */
@Data
@Document(indexName = "book")
public class Book {

    @Id
    @Field(type = FieldType.Text)
    private String id;

    @Field(analyzer="ik_max_word")
    private String title;

    @Field(analyzer="ik_max_word")
    private String author;

    @Field(type = FieldType.Double)
    private Double price;

    @Field(type = FieldType.Date,format = DateFormat.basic_date_time)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Field(type = FieldType.Date,format = DateFormat.basic_date_time)
    private Date updateTime;

}
