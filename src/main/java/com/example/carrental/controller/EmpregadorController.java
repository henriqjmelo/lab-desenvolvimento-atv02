package com.example.carrental.controller;

import com.example.carrental.model.Empregador;
import com.example.carrental.service.EmpregadorService;
import com.example.carrental.service.ClienteService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.views.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller("/empregadores")
public class EmpregadorController {

    private final EmpregadorService empregadorService;
    private final ClienteService clienteService;

    public EmpregadorController(EmpregadorService empregadorService, ClienteService clienteService) {
        this.empregadorService = empregadorService;
        this.clienteService = clienteService;
    }

    @Get("/cliente/{clienteId}")
    public ModelAndView<Map<String, Object>> listEmpregadores(@PathVariable Long clienteId) {
        Map<String, Object> model = new HashMap<>();
        model.put("empregadores", empregadorService.findByClienteId(clienteId));
        model.put("clienteId", clienteId);
        clienteService.findById(clienteId).ifPresent(cliente -> model.put("cliente", cliente));
        return new ModelAndView<>("empregadores/list", model);
    }

    @Get("/new/{clienteId}")
    public ModelAndView<Map<String, Object>> showAddForm(@PathVariable Long clienteId) {
        Map<String, Object> model = new HashMap<>();
        model.put("empregador", new Empregador());
        model.put("clienteId", clienteId);
        clienteService.findById(clienteId).ifPresent(cliente -> model.put("cliente", cliente));
        return new ModelAndView<>("empregadores/add-edit", model);
    }

    @Post(value = "/", consumes = MediaType.APPLICATION_FORM_URLENCODED)
    public HttpResponse<?> addEmpregador(@Body Map<String, String> form) {
        Empregador empregador = new Empregador();
        preencherEmpregadorComForm(empregador, form);
        if (empregador.getCliente() == null) {
            return HttpResponse.badRequest();
        }
        empregadorService.save(empregador);
        Long clienteId = empregador.getCliente().getId();
        return HttpResponse.redirect(java.net.URI.create("/empregadores/cliente/" + clienteId));
    }

    @Get("/edit/{id}")
    public ModelAndView<Map<String, Object>> showEditForm(@PathVariable Long id) {
        Map<String, Object> model = new HashMap<>();
        empregadorService.findById(id).ifPresent(empregador -> {
            model.put("empregador", empregador);
            model.put("clienteId", empregador.getCliente().getId());
            model.put("cliente", empregador.getCliente());
        });
        return new ModelAndView<>("empregadores/add-edit", model);
    }

    @Post(value = "/edit/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED)
    public HttpResponse<?> updateEmpregador(@PathVariable Long id, @Body Map<String, String> form) {
        Optional<Empregador> existente = empregadorService.findById(id);
        Empregador empregador = existente.orElse(new Empregador());
        empregador.setId(id);
        preencherEmpregadorComForm(empregador, form);
        if (empregador.getCliente() == null) {
            return HttpResponse.badRequest();
        }
        empregadorService.update(empregador);
        Long clienteId = empregador.getCliente().getId();
        return HttpResponse.redirect(java.net.URI.create("/empregadores/cliente/" + clienteId));
    }

    @Post(value = "/delete/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED)
    public HttpResponse<?> deleteEmpregador(@PathVariable Long id) {
        return empregadorService.findById(id)
            .map(empregador -> {
                Long clienteId = empregador.getCliente().getId();
                empregadorService.deleteById(id);
                return HttpResponse.redirect(java.net.URI.create("/empregadores/cliente/" + clienteId));
            })
            .orElse(HttpResponse.badRequest());
    }

    private void preencherEmpregadorComForm(Empregador empregador, Map<String, String> form) {
        empregador.setNomeEmpresa(form.getOrDefault("nomeEmpresa", ""));
        empregador.setCargo(form.getOrDefault("cargo", ""));

        String rendimento = form.getOrDefault("rendimento", "").trim();
        if (!rendimento.isEmpty()) {
            try {
                empregador.setRendimento(Double.parseDouble(rendimento));
            } catch (NumberFormatException ignored) {
                empregador.setRendimento(null);
            }
        }

        String clienteId = form.getOrDefault("cliente.id", "").trim();
        if (!clienteId.isEmpty()) {
            try {
                clienteService.findById(Long.parseLong(clienteId)).ifPresent(empregador::setCliente);
            } catch (NumberFormatException ignored) {
                // Ignora ID de cliente invalido enviado no formulario.
            }
        }
    }
}

