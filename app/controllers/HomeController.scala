package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import org.joda.time.format.DateTimeFormat
import scala.io.Source

import models.{PostItem, PostReader}


@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  // Temporary in-memory Blog Posts db
  val reader = new PostReader
  val listOfPosts: Vector[PostItem] = reader.getSortedPublishedPosts()
  val dateFormatter = reader.dateFormatter

  val imagesDir = "posts/images/"

  /**
    * Index or main (home) page.
    */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index(listOfPosts))
  }

  /**
    * The route for handle requests to view individual blog posts.
    */
  def getPost(href: String) = Action {
    val post: PostItem = listOfPosts.find(_.href == href).get
    val title = post.frontmatter.title
    val datePublished = dateFormatter.print(post.frontmatter.date)
    val renderedHtml = post.html

    Ok(views.html.post(title, datePublished, renderedHtml))
  }

  /**
   * Displays images for each post.
   */
  def getImage(src: String) = Action {
    val mimeType = "image/png"
    val imageData: Array[Byte] = 
      Source.fromFile(imagesDir + src)(scala.io.Codec.ISO8859)
            .map(_.toByte).toArray
  
    Ok(imageData).as(mimeType)
  }
}
