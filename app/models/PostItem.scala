package models

import java.io.File

import play.twirl.api.Html
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer
import org.joda.time.DateTime
import org.joda.time.format.{DateTimeFormat, DateTimeFormatter}
import scala.util.Using
import scala.io.Source



case class FrontMatter(
  title: String,
  summary: String,
  date: DateTime,
  author: String,
  // tags: Seq[String],
)


case class PostItem(href: String, frontmatter: FrontMatter, html: Html)


class PostReader {

 // The boilerplate Commonmark stuff for parsing and rendering Markdown posts
  private val parser = Parser.builder().build()
  private val renderer = HtmlRenderer.builder().build()

  val dateFormatter: DateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd")

  private val allowedExts = Set("md", "markdown")
  private val sep = "---"

  // Simple method to get the extension of the file.
  def getExtension(file: File): String = file.getName.split('.').last

  // Lists all Markdown posts in directory.
  def listPosts(directory: String = "posts"): Vector[String] = {
    val dir = new File(directory)

    val filepaths = dir.listFiles().filter { each =>
      each.isFile && allowedExts(getExtension(each))
    }

    filepaths.map(parseFilename(_)).toVector
  }

  // Parses the filename and constructs a PostItem from it
  def parseFilename(file: File): String = {
    val Array(fileWithoutExt) = file.getName.split('.').dropRight(1)
    file.getName
  }

  def getPublishedPosts(): Vector[PostItem] = {
    val posts = listPosts()   // temporary only!
    
    val toPublish = for {
      filepath <- posts
      postString = Using(Source.fromFile("posts/" + filepath)) { source => source.mkString }
      (frontmatter, html) = parseArticle(postString.get) 
    } yield { 
      PostItem(filepath.toString, frontmatter, html)
    }

    toPublish
  }


  def parseArticle(content: String): (FrontMatter, Html) = {
    val indexOfSecond = content.indexOf(sep, 3) // first 3 are `---` as well

    // The "+ 3" because we split at the list dash out of "---"
    val (frontmatter, text): (String, String) = content.splitAt(indexOfSecond + 3)
    val parsedFrontmatter = parseFrontMatter(frontmatter)
    val renderedHtml = markdownToHtml(text)
    (parsedFrontmatter, renderedHtml)
  }

  def parseFrontMatter(frontmatter: String): FrontMatter = {
    val meatOfFrontmatter = 
      frontmatter.trim.stripPrefix(sep).stripSuffix(sep).trim.split('\n')

    val frontmatterMap = meatOfFrontmatter.map{ values =>
      val pair = values.split(':').map(_.trim)
        if (pair.length ==2) {  // need some checking here
        (pair(0), pair(1))
        } else { ("uknown", "unknown") }
    }.toMap

    FrontMatter(
      frontmatterMap("title"),
      frontmatterMap.getOrElse("summary", "Lorem ipsum..."),
      dateFormatter.parseDateTime(frontmatterMap("date")),
      frontmatterMap("author"),
      // frontmatterMap("tags").split(',').map(_.trim)
    )

  }

  def markdownToHtml(text: String): Html = {
    val document = parser.parse(text)
    val renderedHtml = renderer.render(document)
    Html(renderedHtml)
  }

}

class PostValidator {
  def something = None
}



