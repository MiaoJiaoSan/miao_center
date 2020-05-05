package com.miaojiaosan.demo.controller;

import com.miaojiaosan.common.Result;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author miaojiaosan
 * @date 2020/5/5
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

  @Resource
  private RestTemplate restTemplate;

  @HystrixCommand
  @PostMapping("/check")
  public Result<Boolean> checkUser() {
    //url 实例名 + url
    Result<?> voResult = restTemplate.getForObject("http://user:user@user/account/qry/id/4", Result.class);
    System.out.println(voResult);
    return Result.successful(true);
  }


  /**
   * HystrixCommand 隔离策略
   * 1.对全局变量cas SEMAPHORE
   * 2.线程池 THREAD 默认
   * command属性:
   * 隔离策略
   * execution.isolation.strategy
   * <p>
   * execution.isolation.thread.timeoutInMilliseconds
   *
   * @return true
   */
  @HystrixCommand(
    // 定义回退方法的名称, 此方法必须和hystrix的执行方法在相同类中
    fallbackMethod = "threadFallback",
    // HystrixCommand 命令的key值，默认值为注解方法的名称
    commandKey = "thread",
    // HystrixCommand 命令所属的组的名称：默认注解方法类的名称
    groupKey = "DemoController",
    //线程名 threadPoolKey相同用的同一个线程池 没有配置 则哦判断groupKey 相同用同一个线程池
    threadPoolKey = "hystrixPool",
    // 配置hystrix依赖的线程池的参数
    threadPoolProperties = {
      //并发默认是10
      @HystrixProperty(name = HystrixPropertiesManager.CORE_SIZE, value = "10")
    },
    // 配置hystrix命令的参数
    commandProperties = {
      //隔离策略
      @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_STRATEGY, value = "THREAD"),
      //命令执行超时时间，默认1000ms
      @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "500"),
      //是否启用超时默认true
      @HystrixProperty(name=HystrixPropertiesManager.EXECUTION_TIMEOUT_ENABLED,value = "true")
    }
  )
  @GetMapping("/thread")
  public Result<Boolean> thread() {
    //url 实例名 + url
    System.out.println("DemoController#thread output:" + Thread.currentThread().getName());
    return Result.successful(true);
  }
  /**
   * 可以用HystrixCommand 再次降级
   * @return 降级
   */
  public Result<Boolean> threadFallback(){
    return Result.unsuccessful("thread降级");
  }




  /**
   * HystrixCommand 隔离策略
   * 1.对全局变量cas SEMAPHORE
   * 2.线程池 THREAD 默认
   * command属性:
   * 隔离策略
   * execution.isolation.strategy
   * <p>
   * execution.isolation.thread.timeoutInMilliseconds
   *
   * @return true
   */
  @HystrixCommand(
    // 定义回退方法的名称, 此方法必须和hystrix的执行方法在相同类中
    fallbackMethod = "semaphoreFallback",
    // HystrixCommand 命令的key值，默认值为注解方法的名称
    commandKey = "semaphore",
    // HystrixCommand 命令所属的组的名称：默认注解方法类的名称
    groupKey = "DemoController",
    // 配置hystrix命令的参数
    commandProperties = {
      //隔离策略
      @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_STRATEGY, value = "SEMAPHORE"),
      //命令执行超时时间，默认1000ms
      @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "500"),
      //阈值 SEMAPHORE 有效 默认10
      @HystrixProperty(name=HystrixPropertiesManager.EXECUTION_ISOLATION_SEMAPHORE_MAX_CONCURRENT_REQUESTS, value = "10"),
      //是否启用超时默认true
      @HystrixProperty(name=HystrixPropertiesManager.EXECUTION_TIMEOUT_ENABLED,value = "true")
    }
  )
  @GetMapping("/semaphore")
  public Result<Boolean> semaphore() {
    //url 实例名 + url
    System.out.println("DemoController#semaphore output:" + Thread.currentThread().getName());
    return Result.successful(true);
  }

  /**
   * 可以用HystrixCommand 再次降级
   * @return 降级
   */
  @HystrixCommand(
    // 定义回退方法的名称, 此方法必须和hystrix的执行方法在相同类中
    fallbackMethod = "semaphore"
  )
  public Result<Boolean> semaphoreFallback(){
    throw new UnsupportedOperationException();
  }

}
