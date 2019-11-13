/*
 * Copyright 2019 Serge Merzliakov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.epistatic.springkotlin

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.stage.Stage
import org.epistatic.springkotlin.config.ApplicationConfig
import org.epistatic.springkotlin.controller.ApplicationController
import org.springframework.context.annotation.AnnotationConfigApplicationContext

/**
 * Uses Spring Context to manage services as beans which are shared
 * across one or more application controllers
 */
class Main : Application() {
   private val context: AnnotationConfigApplicationContext = AnnotationConfigApplicationContext(ApplicationConfig::class.java)

   @Throws(Exception::class)
   override fun start(primaryStage: Stage) {
      // load all controllers via Spring Framework
      val controller = context.getBean("applicationController") as ApplicationController

      val loader = FXMLLoader(javaClass.getResource("/springkotlin.fxml"))
      loader.setController(controller)
      val root = loader.load<Pane>()

      primaryStage.title = "Spring, Events, MultipleControllers"
      primaryStage.scene = Scene(root, 750.0, 550.0)
      primaryStage.show()
   }

   companion object {
      @JvmStatic
      fun main(args: Array<String>) {
         launch(Main::class.java, *args)
      }
   }
}
