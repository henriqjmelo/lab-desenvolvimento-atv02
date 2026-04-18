package com.example.carrental.controller;

import com.example.carrental.model.Agente;
import com.example.carrental.model.TipoAgente;
import com.example.carrental.service.AgenteService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.views.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("/agentes")
public class AgenteController {

    private final AgenteService agenteService;

    public AgenteController(AgenteService agenteService) {
        this.agenteService = agenteService;
    }

    @Get("/")
    public ModelAndView<Map<String, Object>> listAgentes() {
        Map<String, Object> model = new HashMap<>();
        model.put("agentes", agenteService.findAll());
        return new ModelAndView<>("agentes/list", model);
    }

    @Get("/dashboard")
    public ModelAndView<Map<String, Object>> dashboard() {
        List<Agente> agentes = agenteService.findAll();
        Map<String, Object> model = new HashMap<>();
        model.put("agentes", agentes);
        model.put("bancosCount", agentes.stream().filter(a -> a.getTipo() == TipoAgente.BANCO).count());
        model.put("empresasCount", agentes.stream().filter(a -> a.getTipo() == TipoAgente.EMPRESA).count());
        return new ModelAndView<>("agentes/dashboard", model);
    }

    @Get("/new")
    public ModelAndView<Map<String, Object>> showAddForm() {
        Map<String, Object> model = new HashMap<>();
        model.put("agente", new Agente());
        model.put("tiposAgente", TipoAgente.values());
        return new ModelAndView<>("agentes/add-edit", model);
    }

    @Post(value = "/", consumes = MediaType.APPLICATION_FORM_URLENCODED)
    public HttpResponse<?> addAgente(@Body Agente agente) {
        agenteService.save(agente);
        return HttpResponse.redirect(java.net.URI.create("/agentes"));
    }

    @Get("/edit/{id}")
    public ModelAndView<Map<String, Object>> showEditForm(@PathVariable Long id) {
        Map<String, Object> model = new HashMap<>();
        agenteService.findById(id).ifPresent(agente -> {
            model.put("agente", agente);
            model.put("tiposAgente", TipoAgente.values());
        });
        return new ModelAndView<>("agentes/add-edit", model);
    }

    @Post(value = "/edit/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED)
    public HttpResponse<?> updateAgente(@PathVariable Long id, @Body Agente agente) {
        agente.setId(id);
        agenteService.update(agente);
        return HttpResponse.redirect(java.net.URI.create("/agentes"));
    }

    @Post(value = "/delete/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED)
    public HttpResponse<?> deleteAgente(@PathVariable Long id) {
        agenteService.deleteById(id);
        return HttpResponse.redirect(java.net.URI.create("/agentes"));
    }

    @Get("/bancos")
    public ModelAndView<Map<String, Object>> listBancos() {
        Map<String, Object> model = new HashMap<>();
        model.put("agentes", agenteService.findBancos());
        return new ModelAndView<>("agentes/bancos-list", model);
    }

    @Get("/empresas")
    public ModelAndView<Map<String, Object>> listEmpresas() {
        Map<String, Object> model = new HashMap<>();
        model.put("agentes", agenteService.findEmpresas());
        return new ModelAndView<>("agentes/empresas-list", model);
    }
}

