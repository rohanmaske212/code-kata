package com.demyst.codekata.client

import sttp.client3._
import sttp.client3.asynchttpclient.zio._
import zio._

// Object to handle HTTP requests for fetching the IP address

object HttpClient {

  // Function to fetch the IP address using STTP client
  def fetchIpAddress(): Task[String] =
    AsyncHttpClientZioBackend()
      .flatMap { implicit backend =>
        basicRequest
          .get(uri"https://api.ipify.org/?format=json")
          .response(asStringAlways)
          .send()
      }
      .map(_.body)
}

