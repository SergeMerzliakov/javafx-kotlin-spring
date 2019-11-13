package org.epistatic.springkotlin.event


import java.io.File

class FileAddedEvent(file: File) : SingleFileEvent(file)
