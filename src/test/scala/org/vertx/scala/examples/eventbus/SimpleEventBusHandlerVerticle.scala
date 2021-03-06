/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.vertx.scala.examples.eventbus

import org.vertx.scala.core._
import org.vertx.scala.core.eventbus.Message
import org.vertx.scala.platform.Verticle

/**
 * @author swilliams
 */
class SimpleEventBusHandlerVerticle extends Verticle {

  override def start(future: Future[Void]):Unit = {
    start()
    vertx.eventBus.registerHandler("echo")(
      (msg: Message[String]) => {
        msg.reply(msg.body)
      },
      result => {
          if (result.succeeded()) {
            future.setResult(null)
          }
          else {
            future.setFailure(result.cause())
          }
      })
  }

}