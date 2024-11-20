package br.com.ebac.memes.client;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Configuration
@EnableDiscoveryClient
@FeignClient(name = "usuarios", url = "http://usuario-service:8081/usuarios")
public interface UsuarioClient {
    @GetMapping("/{id}/is-cadastrado")
    Boolean encontrarUsuarioPorId(@PathVariable UUID id);
}
