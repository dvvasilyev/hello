package com.dolvesa.hello.zip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

/**
 * Created by Denis Vasilyev (dv@dolvesa.com) on 19.05.2015 at 11:51.
 *
 * Simple Zipper tester
 */
public class ZipperTest {

  private static Logger log = LoggerFactory.getLogger(ZipperTest.class);

  private Zipper zipper = new Zipper();
  private final String pathToArchivedFile = "NT_MME_NODE2_vnfplgin.zip";
  private final String outputFolder = "C:\\tmp\\output";
  private final String extractedFilePath = outputFolder + "\\" + pathToArchivedFile;

  @BeforeTest
  public void prepare() {
    log.debug("for every test");
  }

  @Test
  public void extractFile() {
    String inputZip = "C:\\tmp\\MME.zip";
    zipper.extractFileFromArchive(inputZip, outputFolder, pathToArchivedFile);
    File f = new File(extractedFilePath);
    Assert.assertTrue(f.exists());
    int extractedFileSize = 189000;
    Assert.assertTrue(extractedFileSize < f.length());
  }

  @Test(dependsOnMethods = "extractFile", enabled = false)
  public void addFileToZip() {
    String outputZip = "C:\\tmp\\schemasNew.zip";
    zipper.addFileToZip(outputZip, extractedFilePath);
    File f = new File(outputZip);
    Assert.assertTrue(f.exists());
    Assert.assertTrue(f.length() != 0);
  }
}
