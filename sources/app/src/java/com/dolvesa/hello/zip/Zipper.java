package com.dolvesa.hello.zip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by Denis Vasilyev (dv@dolvesa.com) on 19.05.2015 at 11:33.
 *
 * Simple ZIP creator/extractor
 */
public class Zipper {

  private static Logger log = LoggerFactory.getLogger(Zipper.class);

  /**
   * extract specified file from zip
   *
   * @param inputZip     archive
   * @param outputFolder folder with extracted file
   * @param pathToFile   file name which must be extracted
   */
  public void extractFileFromArchive(final String inputZip, final String outputFolder, final String pathToFile) {
    try (ZipInputStream zis = new ZipInputStream(new FileInputStream(inputZip))) {

      ZipEntry ze = zis.getNextEntry();
      while (ze != null) {
        if (!ze.getName().contains(pathToFile)) {
          ze = zis.getNextEntry();
          continue;
        }
        String archivedFileName = ze.getName();
        File newFile = new File(outputFolder + File.separator + archivedFileName);
        log.debug("file unzip : {}", newFile.getAbsoluteFile());
        // todo: next line must execute (create parent folder(s) for file)
        if (new File(newFile.getParent()).mkdirs())  {
          log.debug("needed parent dirs have been created");
        }
        FileOutputStream fos = new FileOutputStream(newFile);
        int len;
        byte[] buffer = new byte[1024];
        while ((len = zis.read(buffer)) > 0) {
          fos.write(buffer, 0, len);
        }
        fos.close();
        ze = zis.getNextEntry();
      }
      zis.closeEntry();
      zis.close();
      log.debug("Done");
    } catch (IOException e) {
      log.error(e.getMessage(), e);
    }
  }


  /**
   * Add file to existed archive
   *
   * @param outputZip  existed archive
   * @param pathToFile existed file
   */
  public void addFileToZip(final String outputZip, final String pathToFile) {
    try (FileInputStream in = new FileInputStream(pathToFile)) {
      FileOutputStream fos = new FileOutputStream(outputZip);
      ZipOutputStream zos = new ZipOutputStream(fos);
      ZipEntry ze = new ZipEntry(pathToFile);
      zos.putNextEntry(ze);
      int len;
      byte[] buffer = new byte[1024];
      while ((len = in.read(buffer)) > 0) {
        zos.write(buffer, 0, len);
      }
      zos.closeEntry();
      zos.close();
      log.debug("Done");
    } catch (IOException e) {
      log.error(e.getMessage(), e);
    }
  }

}
