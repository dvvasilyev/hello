package com.dolvesa.hello;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Denis Vasilyev (dv@dolvesa.com) on 05.11.2015 at 13:15.
 *
 * Description is absent
 */
public final class FileRenamer {
  // private static Logger log = LoggerFactory.getLogger(FileRenamer.class);


  public static void main(String[] args) {
    Date currentTime = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss-SSS");
    String formattedTime = dateFormat.format(currentTime);
    File file = new File(args[0]);
    String absolutePath = file.getAbsolutePath();
    String extension = absolutePath.substring(absolutePath.lastIndexOf('.'));
    String path = args[1];
    String uniqueFileName = path + formattedTime + extension;
    System.out.println("args[0] = [" + args[0] + "]");
    System.out.println("args[1] = [" + args[1] + "]");
    File renamedFile = new File(uniqueFileName);
    System.out.println("renamedFile = [" + renamedFile + "]");
//    boolean renamed = file.renameTo(renamedFile);
    try {
      Files.move(file.toPath(), renamedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
      System.out.println("renamedFile path is = [" + renamedFile.getAbsolutePath() + "]");
    } catch (IOException e) {
      System.out.print(e.getMessage());
    }
//    Files.move(file, renamedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
/*
    if (!renamed) {
      System.out.print("File with geometry was not renamed");
    }
*/
  }
}
