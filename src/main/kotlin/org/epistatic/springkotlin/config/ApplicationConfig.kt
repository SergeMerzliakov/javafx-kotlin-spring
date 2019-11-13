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

package org.epistatic.springkotlin.config

import com.google.common.eventbus.EventBus
import org.epistatic.springkotlin.controller.ApplicationController
import org.epistatic.springkotlin.controller.FileDataController
import org.epistatic.springkotlin.controller.FileListController
import org.epistatic.springkotlin.controller.FilePropertiesController
import org.epistatic.springkotlin.service.FileService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * All controllers and EventBus are Spring beans using Contructor-based Dependency Injection
 */
@Configuration
open class ApplicationConfig {

   @Bean
   open fun applicationController(fileListController: FileListController,
                                  filePropertiesController: FilePropertiesController,
                                  fileDataController: FileDataController): ApplicationController {
      return ApplicationController(fileListController, fileDataController, filePropertiesController)
   }

   @Bean
   open fun eventBus(): EventBus {
      return EventBus()
   }

   @Bean
   open fun fileService(): FileService {
      return FileService()
   }

   @Bean
   open fun fileDataController(eventBus: EventBus): FileDataController {
      return FileDataController(eventBus)
   }

   @Bean
   open fun fileListController(eventBus: EventBus): FileListController {
      return FileListController(eventBus)
   }

   @Bean
   open fun filePropertiesController(eventBus: EventBus): FilePropertiesController {
      return FilePropertiesController(eventBus)
   }
}
