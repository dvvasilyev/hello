package com.dolvesa.hello.json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by Denis Vasilyev (dv@dolvesa.com) on 07.05.2015 at 11:57.
 *
 * JSON-to-JSON transformation test
 */
public class JsonJsonTest {
   private static Logger log = LoggerFactory.getLogger(JsonJsonTest.class);

  private JsonJson jsonJson;

  @BeforeClass
  public void init() {
    jsonJson = new JsonJson();
  }

  @Test
  public void testSourceJson() {
    String res = jsonJson.sourceAsJson();
    log.debug("source JSON is {}", res);
    Assert.assertTrue(res.contains("name"));
    Assert.assertTrue(res.contains("13"));
  }

  @Test
  public void testTargetJson() {
    String res = jsonJson.targetAsJson();
    log.debug("target JSON is {}", res);
    Assert.assertTrue(res.contains("title"));
    Assert.assertTrue(res.contains("13"));
  }
}
