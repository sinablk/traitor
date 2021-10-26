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
  val listOfPosts: Vector[PostItem] = reader.getPublishedPosts()

  val dateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd")

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index(listOfPosts))
  }

  def getPost(href: String) = Action {
    println(href)
    val post: PostItem = listOfPosts.find(_.href == href).get
    val title = post.frontmatter.title
    val datePublished = post.frontmatter.date
    val renderedHtml = post.html

    Ok(views.html.post(title, dateFormatter.print(datePublished), renderedHtml))
  }

  def getImage(src: String) = Action {
    val mimeType = "image/png"
    val imageData: Array[Byte] = 
      Source.fromFile("posts/images/" + src)(scala.io.Codec.ISO8859)
            .map(_.toByte).toArray
  
    Ok(imageData).as(mimeType)
  }
}
