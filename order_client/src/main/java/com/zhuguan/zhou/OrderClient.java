package com.zhuguan.zhou;

import com.zhuguan.com.api.OrderApi;
import com.zhuguan.com.model.OrderDto;
import com.zhuguan.zhou.response.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import java.util.List;

@FeignClient(name = "order-paren",fallback = OrderClient.OrderClientService.class)
public interface OrderClient extends OrderApi {

    @Component
    static class OrderClientService implements OrderClient {
        @Override
        public ResponseData<OrderDto> getOrderDtoInfoById(String id) {
            return null;
        }

        @Override
        public ResponseData<List<OrderDto>> getOrderDtoInfoList(OrderDto orderDto) {
            System.out.println("=================数据太多吃不消了。。。====================");
            return null;
        }
    }
}
