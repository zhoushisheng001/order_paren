package com.zhuguan.zhou.controller;


import com.zhuguan.com.api.OrderApi;
import com.zhuguan.com.model.OrderDto;
import com.zhuguan.zhou.response.ResponseData;
import com.zhuguan.zhou.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController implements OrderApi {

    @Autowired
    private OrderService orderService;


    @Override
    public ResponseData<OrderDto> getOrderDtoInfoById(@RequestParam("id") String id) {
        ResponseData<OrderDto> resp = new ResponseData<>();
        OrderDto orderDto = orderService.getgetOrderDtoInfoById(id);
        resp.setData(orderDto);
        return resp;
    }

    @Override
    public ResponseData<List<OrderDto>> getOrderDtoInfoList(@RequestBody OrderDto orderDto) {
        List<OrderDto> orderDtoInfoList = orderService.getOrderDtoInfoList();
        ResponseData<List<OrderDto>> resp = new ResponseData<>();
        resp.setData(orderDtoInfoList).ok();
        return resp;
    }
}
