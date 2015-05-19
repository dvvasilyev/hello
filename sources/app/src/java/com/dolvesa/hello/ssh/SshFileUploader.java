package com.dolvesa.hello.ssh;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Properties;

/**
 * Created by Denis Vasilyev (dv@dolvesa.com) on 19.05.2015 at 17:47.
 *
 * Simple SSH file copier
 */
public class SshFileUploader {

  private static Logger LOG = LoggerFactory.getLogger(SshFileUploader.class);

  private int sshServerPort = 22;

  /**
   * copy file from localhost to remote host
   *
   * @param remoteHost     remote host name or IP address
   * @param remoteUser     remote username
   * @param remoteUserPass remote user's password
   * @param localPathFile  local file path
   * @param remotePathFile remote file path
   */
  public void copyFile(final String remoteHost, final String remoteUser, final String remoteUserPass,
                       final String localPathFile, final String remotePathFile) throws JSchException, SftpException {
    Channel channel;
    ChannelSftp sftpChannel = null;
    Session sftp = null;
    try {
      JSch jsch = new JSch();
      sftp = jsch.getSession(remoteUser, remoteHost, getSshServerPort());
      Properties p = new Properties();
      p.setProperty("StrictHostKeyChecking", "no");
      sftp.setConfig(p);
      sftp.setPassword(remoteUserPass);
      LOG.debug("connecting to host {}:{} with credentials {}/{} ...", remoteHost, getSshServerPort(), remoteUser, remoteUserPass);
      sftp.connect();
      LOG.debug("connected!");
      channel = sftp.openChannel("sftp");
      channel.connect();
      sftpChannel = (ChannelSftp) channel;

      sftpChannel.put(new File(localPathFile).getAbsolutePath(), remotePathFile);
    } finally {
      if (sftpChannel != null) {
        sftpChannel.exit();
      }
      if (sftp != null) {
        sftp.disconnect();
      }
    }
  }

  public int getSshServerPort() {
    return sshServerPort;
  }

  public void setSshServerPort(int sshServerPort) {
    this.sshServerPort = sshServerPort;
  }
}
