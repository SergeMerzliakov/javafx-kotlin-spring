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

import com.google.common.eventbus.EventBus
import javafx.collections.FXCollections
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Label
import javafx.scene.control.ListView
import javafx.scene.input.TransferMode
import javafx.scene.layout.Pane
import org.springframework.stereotype.Component
import java.io.File

/**
 * Controller for fileList.fxml - the list if files dragged into the application
 */
@Component
class FileListController(eventBus: EventBus) : EventAwareController(eventBus) {

   // view
   @FXML lateinit var fileListView: ListView<File>

   // model
   val fileList = FXCollections.observableArrayList<File>()

   fun load(): Pane {
      val loader = FXMLLoader(javaClass.getResource("/fileList.fxml"))
      loader.setController(this)
      return loader.load<Pane>()
   }


   @FXML
   fun initialize() {
      initializePlaceHolders()
      initializeDragAndDrop()

      fileListView.items = fileList.sorted()

      // fire event on EventBus everytime selection changes - most often Mouse Click
      fileListView.selectionModel.selectedItemProperty().addListener { _, _, newSelection ->
         if (newSelection != null) {
            println("Firing FileSelectedEvent event....")
            eventBus.post(FileSelectedEvent(newSelection))
         }
      }
   }

   private fun initializeDragAndDrop() {
      // accept references
      fileListView.onDragOver = EventHandler { event ->
         event.acceptTransferModes(TransferMode.LINK)
         event.consume()
      }

      fileListView.onDragDropped = EventHandler { event ->
         if (event.gestureSource == null && event.acceptedTransferMode == TransferMode.LINK) {
            val files = event.dragboard.files
            // we will cheat and only add the first file dragged in
            val firstFile = files.first()
            files.map { fileList.add(firstFile) }
            System.out.println("Firing FileAddedEvent event....")
            eventBus.post(FileAddedEvent(firstFile))
            event.isDropCompleted = true
            event.consume()
         }
      }
   }

   /**
    * Shown when lists are empty
    */
   private fun initializePlaceHolders() {
      val filePlaceHolder = Label("Drag Files Here")
      filePlaceHolder.styleClass.add("file-placeholder")
      fileListView.placeholder = filePlaceHolder
   }


}