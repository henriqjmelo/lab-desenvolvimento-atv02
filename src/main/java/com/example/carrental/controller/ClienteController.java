package com.example.carrental.controller;

import com.example.carrental.model.Cliente;
import com.example.carrental.service.ClienteService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
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

    @Post(value = "/", consumes = MediaType.APPLICATION_FORM_URLENCODED)
    public HttpResponse<?> addCliente(@Body Cliente cliente) {
        clienteService.save(cliente);
        return HttpResponse.redirect(java.net.URI.create("/clientes"));
    }

    @Get("/edit/{id}")
    public ModelAndView<Map<String, Object>> showEditForm(@PathVariable Long id) {
        Map<String, Object> model = new HashMap<>();
        clienteService.findById(id).ifPresent(cliente -> model.put("cliente", cliente));
        return new ModelAndView<>("clientes/add-edit", model);
    }

    @Post(value = "/edit/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED)
    public HttpResponse<?> updateCliente(@PathVariable Long id, @Body Map<String, String> form) {
        Cliente cliente = clienteService.findById(id).orElse(new Cliente());
        cliente.setId(id);
        cliente.setRg(form.getOrDefault("rg", cliente.getRg()));
        cliente.setCpf(form.getOrDefault("cpf", cliente.getCpf()));
        cliente.setNome(form.getOrDefault("nome", cliente.getNome()));
        cliente.setEndereco(form.getOrDefault("endereco", cliente.getEndereco()));
        cliente.setProfissao(form.getOrDefault("profissao", cliente.getProfissao()));
        clienteService.update(cliente);
        return HttpResponse.redirect(java.net.URI.create("/clientes"));
    }

    @Get("/delete/{id}")
    public HttpResponse<?> deleteCliente(@PathVariable Long id) {
        clienteService.deleteById(id);
        return HttpResponse.redirect(java.net.URI.create("/clientes"));
    }
}

