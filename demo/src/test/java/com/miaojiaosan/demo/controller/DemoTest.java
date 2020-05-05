package com.miaojiaosan.demo.controller;

import com.miaojiaosan.common.Result;
import com.miaojiaosan.demo.DemoApplication;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author miaojiaosan
 * @date 2020/5/5
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@WebAppConfiguration
public class DemoTest {

  @Rule
  public final ContiPerfRule contiPerfRule = new ContiPerfRule();

  @Resource
  private DemoController demoController;

  @Test
  //10个线程 调用11次
  @PerfTest(invocations = 11, threads = 11)
  public void hystrixThread(){
    System.out.println("DemoTest#hystrixThread"+Thread.currentThread().getName());
    demoController.thread();
  }

  @Test
  //10个线程 调用11次
  @PerfTest(invocations = 11, threads = 11)
  public void hystrixSemaphore(){
    System.out.println("DemoTest#hystrixSemaphore"+Thread.currentThread().getName());
    Result<Boolean> semaphore = demoController.semaphore();
    System.out.println(semaphore.getMsg());
  }

}
