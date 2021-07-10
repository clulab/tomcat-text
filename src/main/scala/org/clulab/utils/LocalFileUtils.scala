package org.clulab.utils

import java.io.File

import org.slf4j.LoggerFactory

import scala.io.Source


object LocalFileUtils {
  private lazy val logger = LoggerFactory.getLogger(this.getClass())

  /** Determine the list of input files to process
   * @param inputDirname String path, may be a file or directory of files
   * @return A list of zero or more filenames
   */
  def getFileNames(inputDirname: String): List[String] = {
    getFiles(new File(inputDirname)).map(_.getAbsolutePath)
  }

  /** Determine the list of input files to process
   * @param input String path, may be a file or directory of files
   * @return A list of zero or more File objects
   */
  def getFiles(input: String): List[File] = getFiles(new File(input))

  /** Determine the list of input files to process
   * @param input File object, may be a file or directory of files
   * @return A list of zero or more File objects
   */
  def getFiles(input: File): List[File] = {
    if(input.exists) {
      if(input.isDirectory) {
        logger.info(s"Using input directory '${input.getAbsolutePath}'")
        input.listFiles.toList.sorted
      }
      else {
        logger.info(s"Using input file '${input.getAbsolutePath}'")
        List(input)
      }
    }
    else {
      logger.error(s"File not found '${input.getAbsolutePath}'")
      List()
    }
  }

  /** Make sure we have a place to put the output files.
   * @returns true if the output file directory was found or created
   */
  def ensureDir(dirName: String): Boolean = {
    ensureDir(new File(dirName))
  }

  def ensureDir(dir: File): Boolean = {
    if(dir.exists) {
      if(dir.isDirectory) true  // use existing dir
      else {  // don't clobber non-dir file of the same name
        logger.error(s"Can't create directory '${dir.getAbsolutePath}'")
        logger.error("A file with the same name is in the way.")
        false
      }
    }
    else {
      val ret = dir.mkdir // create dir if needed
      if(!ret)
        logger.error(s"Can't create directory '${dir.getAbsolutePath}'")
      ret
    }
  }


  // Return a string iterator of the lines in a file, without the '\n' chars
  def lineIterator(filename: String): Iterator[String] = 
    lineIterator(new File(filename))

  def lineIterator(file: File): Iterator[String] = 
    Source.fromFile(file).getLines


}
