package models

/**
 * Copyright © 2015. All Rights reserved
 * Author: Dennis Fricke
 * Date: 28.01.2015
 */
case class Request(name: String, age: Long)

object Request {
	import play.api.libs.json._

	implicit val requestReads = Json.reads[Request]
	implicit val requestWrites = Json.writes[Request]

}
