package controllers

import models.Request
import play.api.libs.json._
import play.api.mvc._
import play.api.libs.functional.syntax._


object Application extends Controller {

	def index = Action {
		Ok(views.html.index("Your new application is ready."))
	}

	def sayHello = Action { request =>
		request.body.asJson.map { json =>
			json.validate[Request].map {
				case r: Request => Ok("Hello " + r.name + ", you're " + r.age)
			}.recoverTotal {
				e => BadRequest("Detected error:" + JsError.toFlatJson(e))
			}
		}.getOrElse {
			BadRequest("Expecting Json data")
		}
	}

	implicit val rds = (
		(__ \ 'name).read[String] and
			(__ \ 'cheerio).read[String]
		) tupled

	def sayGoodbye = Action { request =>
		request.body.asJson.map { json =>
			json.validate[(String, String)].map {
				case (name, cheerio) => Ok("Goodbye " + name + " and " + cheerio)
			}.recoverTotal {
				e => BadRequest("Detected error:" + JsError.toFlatJson(e))
			}
		}.getOrElse {
			BadRequest("Expecting Json data")
		}
	}

	def sayWhatYouLike = Action { request =>

		request.body.asJson.map { json =>
			val something = (json \ "something")
			something.validate[(String)].map {
				case (something) => Ok("Hello, i like " + something)
			}.recoverTotal {
				e => BadRequest("Detected error:" + JsError.toFlatJson(e))
			}
		}.getOrElse {
			BadRequest("Expecting Json data")
		}
	}

}