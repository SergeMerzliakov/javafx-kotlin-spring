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

package org.epistatic.springkotlin.controller

import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Button
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Pane
import javafx.stage.Stage
import org.springframework.stereotype.Component

/**
 * Controller for app3/app3.fxml. Manages the main UI and all the other controllers.
 * All controllers are configured as Spring Beans
 */
@Component
class ApplicationController(private val fileListController: FileListController, private val fileDataController: FileDataController, private val filePropertiesController: FilePropertiesController) {

   @FXML lateinit var exitButton: Button
   @FXML lateinit var leftPane: AnchorPane
   @FXML lateinit var topRightPane: AnchorPane
   @FXML lateinit var bottomRightPane: AnchorPane

   fun initialize() {

      // load file list UI and insert into it's pane in the application
      val fileListPane = setupController("/fileList.fxml", fileListController)
      AnchorPane.setTopAnchor(fileListPane, 0.0)
      AnchorPane.setLeftAnchor(fileListPane, 0.0)
      AnchorPane.setBottomAnchor(fileListPane, 2.0)
      AnchorPane.setRightAnchor(fileListPane, 0.0)
      leftPane.children.add(fileListPane)

      // load file properties UI and insert into it's pane in the application
      val filePropertiesPane = setupController("/fileProperties.fxml", filePropertiesController)
      AnchorPane.setTopAnchor(filePropertiesPane, 0.0)
      AnchorPane.setLeftAnchor(filePropertiesPane, 0.0)
      AnchorPane.setBottomAnchor(filePropertiesPane, 2.0)
      AnchorPane.setRightAnchor(filePropertiesPane, 5.0)
      topRightPane.children.add(filePropertiesPane)

      // load file content UI and insert into it's pane in the application
      val fileDataPane = setupController("/fileData.fxml", fileDataController)
      AnchorPane.setTopAnchor(fileDataPane, 0.0)
      AnchorPane.setLeftAnchor(fileDataPane, 0.0)
      AnchorPane.setBottomAnchor(fileDataPane, 2.0)
      AnchorPane.setRightAnchor(fileDataPane, 5.0)
      bottomRightPane.children.add(fileDataPane)
   }

   @FXML
   fun closeApplication() {
      val stage = exitButton.scene.window as Stage
      stage.close()
   }

   /**
    * Utility function to load FXML and link it to its controller
    */
   private fun setupController(fxmlPath: String, controller: EventAwareController): Pane {
      val loader = FXMLLoader(javaClass.getResource(fxmlPath))
      loader.setController(controller)
      return loader.load<Pane>()
   }
}