package com.zhuguan.zhou.service;

import com.zhuguan.com.model.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto getgetOrderDtoInfoById(String id);

    List<OrderDto> getOrderDtoInfoList();
}
