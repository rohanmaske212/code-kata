package com.demyst.codekata.service

import com.demyst.codekata.client.HttpClient
import zio._

// Trait representing an API service with a method to get the IP address
trait ApiService {
  def getIpAddress: Task[String]
}

// Companion object for ApiService
object ApiService {
  val live: ULayer[Has[ApiService]] = ZLayer.succeed {
    new ApiService {
      def getIpAddress: Task[String] =
        HttpClient.fetchIpAddress()
    }
  }

  // Accessor method to get the IP address using the ApiService layer
  def getIpAddress: ZIO[Has[ApiService], Throwable, String] =
    ZIO.accessM(_.get.getIpAddress)
}

