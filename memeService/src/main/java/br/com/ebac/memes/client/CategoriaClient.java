package br.com.ebac.memes.client;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Configuration
@EnableDiscoveryClient
@FeignClient(name = "categorias", url = "http://categoria-service:8082/categorias")
public interface CategoriaClient {
    @GetMapping("/{id}/is-cadastrado")
    Boolean encontrarCategoriaPorId(@PathVariable UUID id);
}
