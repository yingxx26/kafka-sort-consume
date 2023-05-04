package com.baiyan.controller;

import com.baiyan.model.OrderDTO;
import com.baiyan.util.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发送topic测试数据值topic
 *
 * @author baiyan
 * @date 2022/01/19
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    KafkaTemplate kafkaTemplate;

    @GetMapping("/send")
    public void send(){
        for (long i = 0; i < 2; i++) {
            OrderDTO order = new OrderDTO();
            order.setOrderName("订单"+i);
            order.setId(i);
            kafkaTemplate.send("baiyan-test", GsonUtil.gsonToString(order));
        }
    }

}
