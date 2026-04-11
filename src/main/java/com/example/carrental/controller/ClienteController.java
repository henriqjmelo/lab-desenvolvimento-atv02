package com.example.carrental.controller;

import com.example.carrental.model.Cliente;
import com.example.carrental.service.ClienteService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.views.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Get("/")
    public ModelAndView<Map<String, Object>> listClientes() {
        Map<String, Object> model = new HashMap<>();
        model.put("clientes", clienteService.findAll());
        return new ModelAndView<>("clientes/list", model);
    }

    @Get("/new")
    public ModelAndView<Map<String, Object>> showAddForm() {
        Map<String, Object> model = new HashMap<>();
        model.put("cliente", new Cliente());
        return new ModelAndView<>("clientes/add-edit", model);
    }

    @Post("/")
    public HttpResponse<?> addCliente(@Body Cliente cliente) {
        clienteService.save(cliente);
        return HttpResponse.redirect("/clientes");
    }

    @Get("/edit/{id}")
    public ModelAndView<Map<String, Object>> showEditForm(@PathVariable Long id) {
        Map<String, Object> model = new HashMap<>();
        clienteService.findById(id).ifPresent(cliente -> model.put("cliente", cliente));
        return new ModelAndView<>("clientes/add-edit", model);
    }

    @Post("/edit/{id}")
    public HttpResponse<?> updateCliente(@PathVariable Long id, @Body Cliente cliente) {
        cliente.setId(id);
        clienteService.save(cliente);
        return HttpResponse.redirect("/clientes");
    }

    @Get("/delete/{id}")
    public HttpResponse<?> deleteCliente(@PathVariable Long id) {
        clienteService.deleteById(id);
        return HttpResponse.redirect("/clientes");
    }
}
