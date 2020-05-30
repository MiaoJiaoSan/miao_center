package com.miaojiaosan.processor;

/**
 * @author miaojiaosan
 * @param <T>
 */
public interface Processor<T,R> {

  R prepare(T t);

  void process(R r);

  void completable(R r);
}
