package com.vr.beneficios.cartao.cartao.controler;

import com.vr.beneficios.cartao.cartao.dto.request.TransacaoRequest;
import com.vr.beneficios.cartao.cartao.dto.response.TransacaoResponse;
import com.vr.beneficios.cartao.cartao.facade.CartaoFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacoes")
public class TransacoesController {

    @Autowired
    private CartaoFacade facade;

    @Operation(summary = "Realizar uma transação", description = "Realiza uma transação para débito de saldo.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Transação realizada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TransacaoResponse.class))),
            @ApiResponse(responseCode = "422", description = "Erro na transação (saldo insuficiente, senha inválida, etc.)",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TransacaoResponse.class))),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TransacaoResponse.class)))
    })
    @PostMapping
    public ResponseEntity<TransacaoResponse> realizarTransacao(@RequestBody TransacaoRequest transacao) {

        TransacaoResponse response = facade.realizarTransacao(transacao);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
