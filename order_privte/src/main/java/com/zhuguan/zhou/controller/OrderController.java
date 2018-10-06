package com.zhuguan.zhou.controller;


import com.zhuguan.com.api.OrderApi;
import com.zhuguan.com.model.OrderDto;
import com.zhuguan.zhou.response.ResponseData;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController implements OrderApi {


    @Override
    public ResponseData<OrderDto> getOrderDtoInfoById(@RequestParam("id") String id) {
        return null;
    }

    @Override
    public ResponseData<List<OrderDto>> getOrderDtoInfoList(@RequestBody OrderDto orderDto) {
        return null;
    }
}
