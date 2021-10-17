package models

import java.io.File

case class PostItem(
  title: String,
  summary: String = "Lorem ipsum...",
  href: String
)

class PostReader {

  val allowedExts = Set("md", "markdown")

  // Simple method to get the extension of the file.
  def getExtension(file: File): String = file.getName.split('.').last

  // Lists all Markdown posts in directory.
  def listPosts(directory: String = "posts"): Seq[File] = {
    val dir = new File(directory)

    dir.listFiles().filter { each =>
      each.isFile && allowedExts(getExtension(each))
    }.toSeq
  }

  // Parses the filename and constructs a PostItem from it
  def parseFilename(file: File): PostItem = {
    val Array(fileWithoutExt) = file.getName.split('.').dropRight(1)
    val title = fileWithoutExt.split('-').drop(3).mkString(" ").capitalize
    PostItem(title, "Some kinds summary...", file.getName)
  }

  // Takes the list of files produced by listPosts and constructs the case classes.
  def getPublishedPosts(): Vector[PostItem] = {
    val posts = listPosts()   // temporary only!
    posts.map(parseFilename(_)).toVector
  }
}



