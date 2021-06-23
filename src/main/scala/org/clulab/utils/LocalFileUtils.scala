package org.clulab.utils

import java.io.File

import org.slf4j.LoggerFactory

object FileUtils {
  private lazy val logger = LoggerFactory.getLogger(this.getClass())

  /** Determine the list of input files to process
   * @param inputDirname User input arg, may be a file or directory of files
   * @returns A list of zero or more filenames to process
   */
  def getFiles(inputDirname: String): List[String] = {
    val input = new File(inputDirname)
    
    if(input.exists) {
      if(input.isDirectory) {
        logger.info("Using input directory '%s'".format(inputDirname))
        input.listFiles.toList.map(_.getPath).sorted
      }
      else {
        logger.info("Using input file '%s'".format(inputDirname))
        List(input.getPath)
      }
    }
    else {
      logger.error("File not found '%s'".format(inputDirname))
      List()
    }
  }
}
