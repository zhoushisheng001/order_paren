package com.zhuguan.zhou.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zhuguan.com.api.OrderApi;
import com.zhuguan.com.model.OrderDto;
import com.zhuguan.zhou.response.ResponseData;
import com.zhuguan.zhou.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    @HystrixCommand(fallbackMethod = "getOrderDtoInfoListInfo")
    public ResponseData<List<OrderDto>> getOrderDtoInfoList(@RequestBody OrderDto orderDto) {
        List<OrderDto> orderDtoInfoList = orderService.getOrderDtoInfoList();
        ResponseData<List<OrderDto>> resp = new ResponseData<>();
        resp.setData(orderDtoInfoList).ok();
        return resp;
    }

    public ResponseData<List<OrderDto>> getOrderDtoInfoListInfo(@RequestBody OrderDto orderDto){
        List<OrderDto> orderDtoInfoList = new ArrayList<>();
        ResponseData<List<OrderDto>> resp = new ResponseData<>();
        resp.setCode(-1);
        resp.setMsg("下单人数太多吃不消....");
        resp.setData(orderDtoInfoList);
        return resp;
    }
}
