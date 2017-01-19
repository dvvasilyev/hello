package com.dolvesa.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Denis Vasilyev (dv@dolvesa.com) on 06.05.2015 at 18:15.
 *
 * Simple test for Hello
 */
public class HelloTest {

  private static Logger log = LoggerFactory.getLogger(HelloTest.class);

  @Test
  public void testRes() {
    Hello hello = new Hello();
    int res = 3;
    log.debug("res is {}", res);
    hello.setRes(res);
    Assert.assertEquals(res, hello.getRes());
  }

}
