package com.zhuguan.com.api;

import com.zhuguan.com.model.OrderDto;
import com.zhuguan.zhou.response.ResponseData;
import javafx.scene.chart.ValueAxis;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface OrderApi {

      @RequestMapping(value = "/orde/getOrderDtoInfoById",method = RequestMethod.GET)
      ResponseData<OrderDto> getOrderDtoInfoById(@RequestParam("id") String id);

      @RequestMapping(value = "/orde/getOrderDtoInfoList",method = RequestMethod.POST)
      ResponseData<List<OrderDto>> getOrderDtoInfoList (@RequestBody OrderDto orderDto);
}
