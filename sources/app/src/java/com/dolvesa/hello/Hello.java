package com.dolvesa.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Denis Vasilyev (dv@dolvesa.com) on 06.05.2015 at 18:06.
 * Stub class for checking initial settings of test- and logging-dependencies
 */
public class Hello {

  private static Logger log = LoggerFactory.getLogger(Hello.class);

  public int getRes() {
    return 13;
  }

  public void setRes(int res) {
    int res1 = res;
  }

  public static void main(String[] args) {
    log.debug("It works");
  }
}
