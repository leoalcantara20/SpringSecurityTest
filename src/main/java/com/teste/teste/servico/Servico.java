package com.teste.teste.servico;

import com.teste.teste.model.Mensagem;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "servico")
public class Servico {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mensagem consulta(){
        Mensagem msg = new Mensagem(1, "Mensagem retornada do serviço");
        return msg;
    }
    
}
