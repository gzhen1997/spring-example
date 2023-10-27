package com.demo.pdf;

/**
 * @auther gzhen
 * @date 2023-10-27  9:54
 * @description
 */

public class PdfAsposeModel {

    //  X坐标
    private Double xCoordinate;
    //Y坐标
    private Double yCoordinate;
    //页码
    private Integer pageNum;
    //内容
    private String content;

    public Double getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(Double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public Double getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(Double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PdfAsposeModel(Double xCoordinate, Double yCoordinate, Integer pageNum, String content) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.pageNum = pageNum;
        this.content = content;
    }
}
