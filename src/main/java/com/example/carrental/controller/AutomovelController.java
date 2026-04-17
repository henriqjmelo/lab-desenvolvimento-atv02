package com.example.carrental.controller;

import com.example.carrental.model.Automovel;
import com.example.carrental.service.AutomovelService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.views.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller("/automoveis")
public class AutomovelController {

    private final AutomovelService automovelService;

    public AutomovelController(AutomovelService automovelService) {
        this.automovelService = automovelService;
    }

    @Get("/")
    public ModelAndView<Map<String, Object>> listAutomoveis() {
        Map<String, Object> model = new HashMap<>();
        model.put("automoveis", automovelService.findAll());
        return new ModelAndView<>("automoveis/list", model);
    }

    @Get("/new")
    public ModelAndView<Map<String, Object>> showAddForm() {
        Map<String, Object> model = new HashMap<>();
        model.put("automovel", new Automovel());
        return new ModelAndView<>("automoveis/add-edit", model);
    }

    @Post("/")
    public HttpResponse<?> addAutomovel(@Body Automovel automovel) {
        automovelService.save(automovel);
        return HttpResponse.redirect(java.net.URI.create("/automoveis"));
    }

    @Get("/edit/{id}")
    public ModelAndView<Map<String, Object>> showEditForm(@PathVariable Long id) {
        Map<String, Object> model = new HashMap<>();
        automovelService.findById(id).ifPresent(automovel -> model.put("automovel", automovel));
        return new ModelAndView<>("automoveis/add-edit", model);
    }

    @Post("/edit/{id}")
    public HttpResponse<?> updateAutomovel(@PathVariable Long id, @Body Automovel automovel) {
        automovel.setId(id);
        automovelService.save(automovel);
        return HttpResponse.redirect(java.net.URI.create("/automoveis"));
    }

    @Get("/delete/{id}")
    public HttpResponse<?> deleteAutomovel(@PathVariable Long id) {
        automovelService.deleteById(id);
        return HttpResponse.redirect(java.net.URI.create("/automoveis"));
    }
}

