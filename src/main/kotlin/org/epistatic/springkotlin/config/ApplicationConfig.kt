package org.epistatic.springkotlin.config

import org.epistatic.springkotlin.service.FileService
import org.springframework.context.annotation.Bean

class ApplicationConfig {

   @Bean
   fun fileService(): FileService {
      return FileService()
   }
}
