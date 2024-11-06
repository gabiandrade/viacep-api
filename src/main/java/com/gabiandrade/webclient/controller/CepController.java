package com.gabiandrade.webclient.controller;


import com.gabiandrade.webclient.response.CepResponse;
import com.gabiandrade.webclient.service.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class CepController {

    @Autowired
    private CepService cepService;

    @GetMapping("/api/consulta-cep/{cep}")
    public Mono<CepResponse> consultaCep(@PathVariable String cep) {
        return cepService.consultaCep(cep);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
        return ResponseEntity.badRequest().body(illegalArgumentException.getMessage());
    }
}
