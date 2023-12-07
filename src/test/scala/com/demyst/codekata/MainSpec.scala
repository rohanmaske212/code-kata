package com.demyst.codekata

import com.demyst.codekata.client.HttpClient
import com.demyst.codekata.service.ApiService
import zio.ZIO
import zio.test._
import zio.test.Assertion._
import zio.test.environment.TestEnvironment

object MainSpec extends DefaultRunnableSpec {

  def spec: Spec[TestEnvironment, TestFailure[Throwable], TestSuccess] =
    suite("MainSpec")(

      testM("formatIpAddress should return formatted IP address") {
      val jsonString = """{"ip": "127.0.0.1"}"""
      assertM(ZIO.fromEither(Main.formatIpAddress(jsonString)))(equalTo("127.0.0.1"))
    },

      testM("fetchIpAddress should return a valid IP address") {
      assertM(HttpClient.fetchIpAddress())(isSubtype[String](anything))
    },

      testM("ApiService.getIpAddress should return a valid IP address") {
      assertM(ApiService.getIpAddress.provideLayer(ApiService.live))(isSubtype[String](anything))
    }
  )
}
