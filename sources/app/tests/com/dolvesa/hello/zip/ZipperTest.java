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

  private String outputFolder = "C:\\tmp\\output";

  private String extractedFileName = "xml.xsd";
  private String extractedFilePath = outputFolder + "\\xsd\\" + extractedFileName;

  private Zipper zipper = new Zipper();

  @BeforeTest
  public void prepare() {
    log.debug("for every test");
  }

  @Test
  public void extractFile() {
    String pathToArchivedFile = "xsd/xml.xsd";
    String inputZip = "C:\\tmp\\schemas.zip";
    zipper.extractFileFromArchive(inputZip, outputFolder, pathToArchivedFile);
    File f = new File(extractedFilePath);
    Assert.assertTrue(f.exists());
    int extractedFileSize = 4646;
    Assert.assertEquals(extractedFileSize, f.length());
  }

  @Test(dependsOnMethods = "extractFile")
  public void addFileToZip() {
    String outputZip = "C:\\tmp\\schemasNew.zip";
    zipper.addFileToZip(outputZip, extractedFilePath);
    File f = new File(outputZip);
    Assert.assertTrue(f.exists());
    Assert.assertTrue(f.length() != 0);
  }
}
