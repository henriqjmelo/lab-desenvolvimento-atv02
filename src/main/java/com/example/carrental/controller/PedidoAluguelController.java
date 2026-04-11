package com.example.carrental.controller;

import com.example.carrental.model.PedidoAluguel;
import com.example.carrental.service.AutomovelService;
import com.example.carrental.service.ClienteService;
import com.example.carrental.service.PedidoAluguelService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.views.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller("/pedidos")
public class PedidoAluguelController {

    private final PedidoAluguelService pedidoAluguelService;
    private final ClienteService clienteService;
    private final AutomovelService automovelService;

    public PedidoAluguelController(PedidoAluguelService pedidoAluguelService,
                                   ClienteService clienteService,
                                   AutomovelService automovelService) {
        this.pedidoAluguelService = pedidoAluguelService;
        this.clienteService = clienteService;
        this.automovelService = automovelService;
    }

    @Get("/")
    public ModelAndView<Map<String, Object>> listPedidos() {
        Map<String, Object> model = new HashMap<>();
        model.put("pedidos", pedidoAluguelService.findAll());
        return new ModelAndView<>("pedidos/list", model);
    }

    @Get("/new")
    public ModelAndView<Map<String, Object>> showAddForm() {
        Map<String, Object> model = new HashMap<>();
        model.put("pedido", new PedidoAluguel());
        model.put("clientes", clienteService.findAll());
        model.put("automoveis", automovelService.findAll());
        return new ModelAndView<>("pedidos/add-edit", model);
    }

    @Post("/")
    public HttpResponse<?> addPedido(@Body PedidoAluguel pedidoAluguel) {
        pedidoAluguel.setDataPedido(new Date());
        pedidoAluguelService.save(pedidoAluguel);
        return HttpResponse.redirect("/pedidos");
    }

    @Get("/edit/{id}")
    public ModelAndView<Map<String, Object>> showEditForm(@PathVariable Long id) {
        Map<String, Object> model = new HashMap<>();
        pedidoAluguelService.findById(id).ifPresent(pedido -> model.put("pedido", pedido));
        model.put("clientes", clienteService.findAll());
        model.put("automoveis", automovelService.findAll());
        return new ModelAndView<>("pedidos/add-edit", model);
    }

    @Post("/edit/{id}")
    public HttpResponse<?> updatePedido(@PathVariable Long id, @Body PedidoAluguel pedidoAluguel) {
        pedidoAluguel.setId(id);
        pedidoAluguelService.save(pedidoAluguel);
        return HttpResponse.redirect("/pedidos");
    }

    @Get("/delete/{id}")
    public HttpResponse<?> deletePedido(@PathVariable Long id) {
        pedidoAluguelService.deleteById(id);
        return HttpResponse.redirect("/pedidos");
    }

    @Get("/status/{id}")
    public ModelAndView<Map<String, Object>> viewStatus(@PathVariable Long id) {
        Map<String, Object> model = new HashMap<>();
        pedidoAluguelService.findById(id).ifPresent(pedido -> model.put("pedido", pedido));
        return new ModelAndView<>("pedidos/status", model);
    }
}
