package com.gabiandrade.webclient.service;

import com.gabiandrade.webclient.response.CepResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CepService {

    @Autowired
    private WebClient webClient;

    private static final String CEP_REGEX = "^[0-9]{5}-?[0-9]{3}$";

    public Mono<CepResponse> consultaCep(String cep) {

        if (!validarCep(cep)) {
            return Mono.error(new IllegalArgumentException("Cep Inválido"));
        }

        return webClient
                .get()
                .uri("/{cep}/json", cep)
                .retrieve()
                .bodyToMono(CepResponse.class);
    }

    private boolean validarCep(String cep) {
        return cep != null && cep.matches(CEP_REGEX);
    }
}
