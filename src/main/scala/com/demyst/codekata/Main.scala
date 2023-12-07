package com.demyst.codekata

import com.demyst.codekata.service.ApiService
import zio._
import zio.console._
import io.circe.parser._
import io.circe.DecodingFailure

object Main extends zio.App {

  override def run(args: List[String]): ZIO[zio.ZEnv, Nothing, ExitCode] =
  // Execute the application logic and provide the required layers
    myAppLogic.exitCode.provideCustomLayer(ApiService.live ++ Console.live)

  // Define the application logic
  val myAppLogic: ZIO[Console with Has[ApiService], Throwable, Unit] = for {
    // Access the ApiService layer and get the IP address
    ip <- ZIO.accessM[Has[ApiService]](_.get.getIpAddress)
    formattedIp <- ZIO.fromEither(formatIpAddress(ip))
    _  <- putStrLn(s"IP Address: $formattedIp")
  } yield ()

  // Function to format the JSON string and extract the IP address
  def formatIpAddress(jsonString: String): Either[DecodingFailure, String] =
    for {
      json <- parse(jsonString).left.map(DecodingFailure.fromThrowable(_, Nil))
      ipAddress <- json.hcursor.downField("ip").as[String]
    } yield ipAddress

}


