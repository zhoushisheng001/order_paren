package com.zhuguan.zhou.service.impl;

import com.zhuguan.com.model.OrderDto;
import com.zhuguan.zhou.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public OrderDto getgetOrderDtoInfoById(String id) {
        return null;
    }

    @Override
    public List<OrderDto> getOrderDtoInfoList() {
        return null;
    }
}
