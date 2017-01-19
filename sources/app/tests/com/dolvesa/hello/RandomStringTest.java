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
public class RandomStringTest {

  private static Logger log = LoggerFactory.getLogger(RandomStringTest.class);

  @Test
  public void testRes() {
    RandomStringGenerator rsg = new RandomStringGenerator();
    byte length = 7;
    String res = rsg.randomString(length);
    log.debug("res: {}", res);
    Assert.assertEquals(res.length(), length);
  }

}
