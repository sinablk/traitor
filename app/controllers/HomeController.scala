package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.twirl.api.Html
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer
import org.commonmark.node._
import java.io.File
import scala.io.Source
import scala.util.Using

import models.{PostItem, PostReader}


@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  // The boilerplate Commonmark stuff for parsing and rendering Markdown posts
  private val parser = Parser.builder().build()
  private val renderer = HtmlRenderer.builder().build()

  val reader = new PostReader
  val listOfPosts: Vector[PostItem] = reader.getPublishedPosts()

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index(listOfPosts))
  }

  def getPost(href: String) = Action {
    val postString = Using(Source.fromFile("posts/" + href)) {
      source => source.mkString
    }

    val title = listOfPosts.find(_.href == href).get.title

    val document = parser.parse(postString.get)
    val renderedHtml = renderer.render(document)
    Ok(views.html.post(title, Html(renderedHtml)))
  }

  def getImage(src: String) = Action {
    val mimeType = "image/png"
    val imageData: Array[Byte] = 
      Source.fromFile("posts/images/" + src)(scala.io.Codec.ISO8859)
            .map(_.toByte).toArray

    Ok(imageData).as(mimeType)
  }
}
