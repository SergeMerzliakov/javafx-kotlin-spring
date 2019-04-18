package org.epistatic.springkotlin.config

import org.epistatic.springkotlin.service.FileService
import org.springframework.context.annotation.Bean

/**
 * All beans here are services or capabilities used by JavaFX controllers
 */
class ApplicationConfig {

   @Bean
   fun fileService(): FileService {
      return FileService()
   }
}
