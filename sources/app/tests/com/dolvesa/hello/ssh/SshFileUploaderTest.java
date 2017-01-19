package com.dolvesa.hello.ssh;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Denis Vasilyev (dv@dolvesa.com) on 19.05.2015 at 18:37.
 *
 * SshFileUploader Tester
 */
public class SshFileUploaderTest {
   private static Logger log = LoggerFactory.getLogger(SshFileUploaderTest.class);

  @Test
  public void copyFile() {
    boolean res = false;
    SshFileUploader sshCopier = new SshFileUploader();
    try {
      sshCopier.setSshServerPort(22);
      sshCopier.copyFile("10.238.10.50", "user", "123456", "C:\\tmp\\schemas.zip", "/home/user/schemas.zip");
      res = true;
    } catch (JSchException | SftpException e) {
      log.error(e.toString(), e);
    }
    Assert.assertTrue(res);
  }
}
