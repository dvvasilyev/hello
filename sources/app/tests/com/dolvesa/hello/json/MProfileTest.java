package com.dolvesa.hello.json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Denis Vasilyev (dv@dolvesa.com) on 07.05.2015 at 17:35.
 *
 * Test for MProfile
 */
public class MProfileTest {

  private static Logger log = LoggerFactory.getLogger(MProfileTest.class);

  @Test
  public void checkProfilesJson() {
    MProfile mProfile = new MProfile();
    String res = mProfile.getProfilesAsJson();
    log.debug("res is {}", res);
    Assert.assertTrue(res.contains("i0"));
    Assert.assertTrue(res.contains("monitoring-profiles"));
  }
}
