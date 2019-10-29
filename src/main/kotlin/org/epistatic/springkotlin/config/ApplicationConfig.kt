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
