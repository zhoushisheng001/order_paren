package com.zhuguan.com.model;

import com.zhuguan.zhou.model.ProductDto;

public class OrderDto  {

    public OrderDto() {
    }

    private String id;

    private String orderName;

    private ProductDto productDto;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }
}
