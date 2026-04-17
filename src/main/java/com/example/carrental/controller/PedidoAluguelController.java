package com.example.carrental.controller;

import com.example.carrental.model.PedidoAluguel;
import com.example.carrental.service.AutomovelService;
import com.example.carrental.service.ClienteService;
import com.example.carrental.service.PedidoAluguelService;
import com.example.carrental.service.AgenteService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.views.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Controller("/pedidos")
public class PedidoAluguelController {

    private final PedidoAluguelService pedidoAluguelService;
    private final ClienteService clienteService;
    private final AutomovelService automovelService;
    private final AgenteService agenteService;

    public PedidoAluguelController(PedidoAluguelService pedidoAluguelService,
                                   ClienteService clienteService,
                                   AutomovelService automovelService,
                                   AgenteService agenteService) {
        this.pedidoAluguelService = pedidoAluguelService;
        this.clienteService = clienteService;
        this.automovelService = automovelService;
        this.agenteService = agenteService;
    }

    @Get("/")
    public ModelAndView<Map<String, Object>> listPedidos(@QueryValue(defaultValue = "") String status) {
        Map<String, Object> model = new HashMap<>();
        if (status != null && !status.isEmpty()) {
            model.put("pedidos", pedidoAluguelService.findAll().stream()
                .filter(p -> p.getStatus().equals(status))
                .collect(Collectors.toList()));
        } else {
            model.put("pedidos", pedidoAluguelService.findAll());
        }
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
        pedidoAluguel.setStatus("PENDENTE");
        pedidoAluguelService.save(pedidoAluguel);
        return HttpResponse.redirect(java.net.URI.create("/pedidos"));
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
        return HttpResponse.redirect(java.net.URI.create("/pedidos"));
    }

    @Get("/delete/{id}")
    public HttpResponse<?> deletePedido(@PathVariable Long id) {
        pedidoAluguelService.deleteById(id);
        return HttpResponse.redirect(java.net.URI.create("/pedidos"));
    }

    @Get("/status/{id}")
    public ModelAndView<Map<String, Object>> viewStatus(@PathVariable Long id) {
        Map<String, Object> model = new HashMap<>();
        pedidoAluguelService.findById(id).ifPresent(pedido -> model.put("pedido", pedido));
        return new ModelAndView<>("pedidos/status", model);
    }

    // Funcionalidades de analise financeira por agentes
    @Get("/analise-financeira")
    public ModelAndView<Map<String, Object>> listPedidosParaAnalise() {
        Map<String, Object> model = new HashMap<>();
        model.put("pedidos", pedidoAluguelService.findByParecer("PENDENTE"));
        return new ModelAndView<>("pedidos/analise-financeira", model);
    }

    @Get("/avaliar/{id}")
    public ModelAndView<Map<String, Object>> showAvaliacaoForm(@PathVariable Long id) {
        Map<String, Object> model = new HashMap<>();
        pedidoAluguelService.findById(id).ifPresent(pedido -> model.put("pedido", pedido));
        model.put("agentes", agenteService.findAll());
        return new ModelAndView<>("pedidos/avaliar", model);
    }

    @Post("/avaliar/{id}")
    public HttpResponse<?> avaliarPedido(@PathVariable Long id,
                                         @QueryValue String parecer,
                                         @QueryValue(defaultValue = "") String motivo,
                                         @QueryValue Long agenteId) {
        pedidoAluguelService.findById(id).ifPresent(pedido -> {
            pedido.setParecer(parecer);
            if ("REJEITADO".equals(parecer)) {
                pedido.setMotivoRejeicao(motivo);
                pedido.setStatus("REJEITADO");
            } else if ("APROVADO".equals(parecer)) {
                pedido.setStatus("APROVADO");
            }
            agenteService.findById(agenteId).ifPresent(pedido::setAgenteAnalista);
            pedidoAluguelService.save(pedido);
        });
        return HttpResponse.redirect(java.net.URI.create("/pedidos/analise-financeira"));
    }
}


