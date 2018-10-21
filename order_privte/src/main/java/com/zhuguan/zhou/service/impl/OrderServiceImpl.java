package com.zhuguan.zhou.service.impl;

import com.alibaba.fastjson.JSON;
import com.zhuguan.com.model.OrderDto;
import com.zhuguan.zhou.ProductClient;
import com.zhuguan.zhou.model.ProductDto;
import com.zhuguan.zhou.response.ResponseData;
import com.zhuguan.zhou.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductClient productClient;//默认如果设置了熔断了的话时间为1S 目前环境已经配置成3S可以看配置文件

    @Override
    public OrderDto getgetOrderDtoInfoById(String id)  {
        String url = "http://product-paren";
        url = url + "/product/getProductInfoById?" + "id=" + id;
        ResponseData resp = restTemplate.getForObject(url, ResponseData.class);
        System.out.println(JSON.toJSONString(resp.getData()));
        OrderDto orderDto = new OrderDto();
        System.out.println("String:" + resp.getData().toString());
        System.out.println("JSON:" + JSON.toJSONString(resp.getData()));
        orderDto.setProductDto(JSON.parseObject(JSON.toJSONString(resp.getData()), ProductDto.class));
        orderDto.setOrderName("第一次订单");
        return orderDto;
    }

    @Override
    public List<OrderDto> getOrderDtoInfoList() {
        ResponseData<List<ProductDto>> listResponseData = productClient.getgetProductInfoList();
        System.out.println("JSON:" +JSON.toJSONString(listResponseData));
        List<OrderDto> orderDtoList = new ArrayList<>();
        listResponseData.getData().forEach(t->{
            int i = 0;
            OrderDto orderDto = new OrderDto();
            orderDto.setProductDto(t);
            orderDto.setId(UUID.randomUUID().toString());
            orderDto.setOrderName("生成订单" + i++);
            orderDtoList.add(orderDto);
        });
        return orderDtoList;
    }
}
