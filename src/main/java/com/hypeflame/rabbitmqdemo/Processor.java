package com.hypeflame.rabbitmqdemo;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class Processor {

  private AtomicInteger processed = new AtomicInteger();
  private AtomicBoolean available = new AtomicBoolean(Boolean.TRUE);

  public void process(String body) {
    int i = processed.incrementAndGet();
    System.out.println("Processed <" + i + "> Body: " + body);
  }

  public Integer getProcessed() {
    return processed.get();
  }

  public void clear() {
    processed.set(0);
  }

  public Boolean setEnabled(Boolean enabled) {
    available.set(enabled);
    return enabled;
  }

  public Boolean isAvailable() {
    return available.get();
  }

}