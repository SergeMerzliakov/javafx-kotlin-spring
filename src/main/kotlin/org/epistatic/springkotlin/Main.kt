package org.epistatic.springkotlin

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.stage.Stage
import org.epistatic.springkotlin.config.ApplicationConfig
import org.epistatic.springkotlin.controller.ApplicationController
import org.epistatic.springkotlin.service.FileService
import org.springframework.context.annotation.AnnotationConfigApplicationContext


/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 **/


class Main : Application() {

   var context:AnnotationConfigApplicationContext
   
   init {
      context = AnnotationConfigApplicationContext(ApplicationConfig::class.java)
   }
   
	@Throws(Exception::class)
	override fun start(primaryStage: Stage) {

      // load services as beans and inject into relevant controllers
      val fileServiceBean = context.getBean("fileService") as FileService

      val controller = ApplicationController(fileServiceBean)
      val loader = FXMLLoader(javaClass.getResource("/springkotlin.fxml"))
      loader.setController(controller)
      val root = loader.load<Pane>()

      primaryStage.title = "Spring, Events, MultipleControllers"
      primaryStage.scene = Scene(root, 650.0, 550.0)
      primaryStage.show()
	}

	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			Application.launch(Main::class.java, *args)
		}
	}
}
