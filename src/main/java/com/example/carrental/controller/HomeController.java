package com.example.carrental.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/")
public class HomeController {

    @Get("/")
    public HttpResponse<?> home() {
        return HttpResponse.redirect(java.net.URI.create("/automoveis"));
    }

}

