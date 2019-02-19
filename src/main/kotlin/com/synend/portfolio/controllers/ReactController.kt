package com.synend.portfolio.controllers

import com.synend.portfolio.utils.logger
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ReactController {

    val logger = logger<ReactController>()


    @GetMapping("/")
    @CrossOrigin
    fun loadFrontEnd() : String{
        logger.info("Loading React application at root route")
        return "index.html"
    }

}