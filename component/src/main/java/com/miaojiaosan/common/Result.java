package com.miaojiaosan.common;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * 统一返回结果
 *
 * @author miaojiaosan
 * @date 2020/4/25
 */
public class Result<T> {
  private final boolean result;

  private String msg;

  private T value;

  public Result() {
    this.result = false;
  }

  private Result(boolean result, T value) {
    this.result = result;
    this.value = value;
  }

  private Result(boolean result, String msg, T value) {
    this.result = result;
    this.msg = msg;
    this.value = value;
  }

  /**
   * 成功的
   *
   * @param value 结果数据
   * @param <T>   结果泛型
   * @return {@link Result}
   */
  public static <T> Result<T> successful(T value) {
    return new Result<>(true, value);
  }

  /**
   * 失败的
   *
   * @param msg 失败原因
   * @return {@link Result}
   */
  public static <T> Result<T> unsuccessful(String msg) {
    return new Result<>(false, msg, null);
  }

  public boolean isResult() {
    return this.result;
  }

  public void isSuccessful(Consumer<? super T> consumer) {
    if (value != null) {
      consumer.accept(value);
    }
  }

  public T get() {
    return this.value;
  }

  public Result<T> filter(Predicate<? super T> predicate) {
    Objects.requireNonNull(predicate);
    if (!isResult()) {
      return this;
    } else {
      return predicate.test(value) ? this : Result.empty();
    }
  }

  public static <T> Result<T> empty() {
    return new Result<>(false, null);
  }


  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public T getValue() {
    return value;
  }

  public void setValue(T value) {
    this.value = value;
  }
}
