package com.example.carrental.controller;

import com.example.carrental.model.Usuario;
import com.example.carrental.model.TipoUsuario;
import com.example.carrental.service.UsuarioService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.views.ModelAndView;
import io.micronaut.http.cookie.Cookie;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller("/auth")
public class AutenticacaoController {

    private final UsuarioService usuarioService;

    public AutenticacaoController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Get("/login")
    public ModelAndView<Map<String, Object>> showLoginForm(@QueryValue(defaultValue = "") String erro,
                                                            @QueryValue(defaultValue = "") String sucesso) {
        Map<String, Object> model = new HashMap<>();
        if (!erro.isEmpty()) {
            model.put("erro", erro);
        }
        if (!sucesso.isEmpty()) {
            model.put("sucesso", sucesso);
        }
        return new ModelAndView<>("auth/login", model);
    }

    @Post(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED)
    public HttpResponse<?> login(@Body Usuario loginForm) {
        String email = loginForm.getEmail();
        String senha = loginForm.getSenha();
        Optional<Usuario> usuario = usuarioService.findByEmailAndSenha(email, senha);
        
        if (usuario.isPresent()) {
            Usuario u = usuario.get();
            if (u.getAtivo()) {
                // Redirecionar baseado no tipo de usuario
                String redirect;
                if (u.getTipo() == TipoUsuario.CLIENTE) {
                    redirect = "/clientes";
                } else {
                    redirect = "/agentes/dashboard";
                }
                return HttpResponse.redirect(java.net.URI.create(redirect))
                    .cookie(Cookie.of("usuario_id", u.getId().toString()))
                    .cookie(Cookie.of("usuario_tipo", u.getTipo().toString()));
            }
        }
        return HttpResponse.redirect(java.net.URI.create("/auth/login?erro=Credenciais invalidas"));
    }

    @Get("/cadastro")
    public ModelAndView<Map<String, Object>> showCadastroForm() {
        Map<String, Object> model = new HashMap<>();
        model.put("tiposUsuario", TipoUsuario.values());
        return new ModelAndView<>("auth/cadastro", model);
    }

    @Post(value = "/cadastro", consumes = MediaType.APPLICATION_FORM_URLENCODED)
    public HttpResponse<?> cadastro(@Body Usuario usuario) {
        // Verificar se email ja existe
        if (usuarioService.findByEmail(usuario.getEmail()).isPresent()) {
            return HttpResponse.badRequest("Email ja cadastrado");
        }

        usuarioService.save(usuario);
        return HttpResponse.redirect(java.net.URI.create("/auth/login?sucesso=Cadastro realizado com sucesso"));
    }

    @Get("/logout")
    public HttpResponse<?> logout() {
        return HttpResponse.redirect(java.net.URI.create("/"));
    }
}


