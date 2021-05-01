package com.dev.task;

import com.dev.service.CustomerService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 御风
 * @version V1.0
 * @Package com.dev.task
 * @Description 定时任务的执行
 * @date 2021/4/30 20:50
 * @ClassName JobTask
 */
@Component
public class JobTask {

    @Resource
    private CustomerService customerService;

    @Scheduled(cron = "0/2 * * * * ?")
    public void job(){
        System.out.println("定时任务开始执行 --> "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        // 调用需要被定时执行的方法
        customerService.customerLoss();
    }
}
