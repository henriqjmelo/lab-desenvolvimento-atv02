package com.example.carrental.controller;

import com.example.carrental.model.PedidoAluguel;
import com.example.carrental.service.AutomovelService;
import com.example.carrental.service.ClienteService;
import com.example.carrental.service.PedidoAluguelService;
import com.example.carrental.service.AgenteService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
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
import java.util.Optional;
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

    @Post(value = "/", consumes = MediaType.APPLICATION_FORM_URLENCODED)
    public HttpResponse<?> addPedido(@Body Map<String, String> form) {
        PedidoAluguel pedidoAluguel = new PedidoAluguel();
        preencherPedidoComForm(pedidoAluguel, form);
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

    @Post(value = "/edit/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED)
    public HttpResponse<?> updatePedido(@PathVariable Long id, @Body Map<String, String> form) {
        Optional<PedidoAluguel> existente = pedidoAluguelService.findById(id);
        PedidoAluguel pedidoAluguel = existente.orElse(new PedidoAluguel());
        pedidoAluguel.setId(id);
        preencherPedidoComForm(pedidoAluguel, form);
        if (!existente.isPresent()) {
            pedidoAluguel.setDataPedido(new Date());
        }
        pedidoAluguelService.update(pedidoAluguel);
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

    @Post(value = "/avaliar/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED)
    public HttpResponse<?> avaliarPedido(@PathVariable Long id, @Body Map<String, String> form) {
        String parecer = form.getOrDefault("parecer", "");
        String motivo = form.getOrDefault("motivo", "");
        Long agenteId = Long.parseLong(form.getOrDefault("agenteId", "0"));

        pedidoAluguelService.findById(id).ifPresent(pedido -> {
            pedido.setParecer(parecer);
            if ("REJEITADO".equals(parecer)) {
                pedido.setMotivoRejeicao(motivo);
                pedido.setStatus("REJEITADO");
            } else if ("APROVADO".equals(parecer)) {
                pedido.setStatus("APROVADO");
            }
            if (agenteId > 0) {
                agenteService.findById(agenteId).ifPresent(pedido::setAgenteAnalista);
            }
            pedidoAluguelService.update(pedido);
        });
        return HttpResponse.redirect(java.net.URI.create("/pedidos/analise-financeira"));
    }

    private void preencherPedidoComForm(PedidoAluguel pedidoAluguel, Map<String, String> form) {
        String status = form.getOrDefault("status", "").trim();
        if (!status.isEmpty()) {
            pedidoAluguel.setStatus(status);
        }

        String valorDiaria = form.getOrDefault("valorDiaria", "").trim();
        if (!valorDiaria.isEmpty()) {
            try {
                pedidoAluguel.setValorDiaria(Double.parseDouble(valorDiaria));
            } catch (NumberFormatException ignored) {
                // Valor invalido nao interrompe o fluxo do formulario.
            }
        }

        String clienteId = form.getOrDefault("cliente.id", "").trim();
        if (!clienteId.isEmpty()) {
            try {
                clienteService.findById(Long.parseLong(clienteId)).ifPresent(pedidoAluguel::setCliente);
            } catch (NumberFormatException ignored) {
                // Ignora cliente invalido enviado pelo formulario.
            }
        }

        String automovelId = form.getOrDefault("automovel.id", "").trim();
        if (!automovelId.isEmpty()) {
            try {
                automovelService.findById(Long.parseLong(automovelId)).ifPresent(pedidoAluguel::setAutomovel);
            } catch (NumberFormatException ignored) {
                // Ignora automovel invalido enviado pelo formulario.
            }
        }
    }
}


