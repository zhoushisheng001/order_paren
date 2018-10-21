package com.zhuguan.zhou.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zhuguan.com.api.OrderApi;
import com.zhuguan.com.model.OrderDto;
import com.zhuguan.zhou.response.ResponseData;
import com.zhuguan.zhou.service.OrderService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RestController
public class OrderController implements OrderApi {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    ExecutorService executorService = Executors.newFixedThreadPool(6);

    @Autowired
    private OrderService orderService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;


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

    /**
     * 这里面是服务降级，但是对应的降级服务要和上面的
     * fallbackMethod = "getOrderDtoInfoListInfo"
     * 的方法名称和入参数一样
      * @param orderDto
     * @return
     */
   public ResponseData<List<OrderDto>> getOrderDtoInfoListInfo(@RequestBody OrderDto orderDto){
        List<OrderDto> orderDtoInfoList = new ArrayList<>();
        ResponseData<List<OrderDto>> resp = new ResponseData<>();
        executorService.execute(new Runnable() {
           @Override
           public void run() {
               String sendValue = "getOrderDtoInfo";
               String sendInfo = redisTemplate.opsForValue().get(sendValue);
               logger.info("=========》获得信息为：" + sendInfo);
               if (StringUtils.isBlank(sendInfo)) {
                   logger.info("发送消息服务已经挂了...");
                   redisTemplate.opsForValue().set(sendValue,"服务已经挂了",100,TimeUnit.SECONDS);
               } else {
                   logger.info("短信已经发送过了...");
               }
           }
       });
        logger.info("OK.....");
        resp.setCode(-1);
        resp.setMsg("下单人数太多吃不消....");
        resp.setData(orderDtoInfoList);
        return resp;
    }
}
