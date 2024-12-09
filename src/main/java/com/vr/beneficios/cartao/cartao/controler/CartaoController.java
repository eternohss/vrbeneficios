package com.vr.beneficios.cartao.cartao.controler;

import com.vr.beneficios.cartao.cartao.dto.request.CartaoRequest;
import com.vr.beneficios.cartao.cartao.dto.request.TransacaoRequest;
import com.vr.beneficios.cartao.cartao.dto.response.CartaoResponse;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

	@Autowired
	private CartaoFacade facade;


	@Operation(summary = "Criar um novo cartão", description = "Cria um cartão com um número e senha.")
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "Cartão criado com sucesso",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = CartaoResponse.class))),
			@ApiResponse(responseCode = "422", description = "Cartão já existe",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = CartaoResponse.class)))
	})
	@PostMapping
	public ResponseEntity<CartaoResponse> criarCartao(@RequestBody CartaoRequest cartao) {

		CartaoResponse response = facade.criarCartao(cartao);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}


	@Operation(summary = "Obter saldo do cartão", description = "Consulta o saldo de um cartão pelo número.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Saldo obtido com sucesso",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = CartaoResponse.class))),
			@ApiResponse(responseCode = "404", description = "Cartão não encontrado",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = CartaoResponse.class)))
	})
	@GetMapping("/{numeroCartao}")
	public ResponseEntity<Double> obterSaldo(@PathVariable String numeroCartao) {

		Double response = facade.obterSaldo(numeroCartao);

		return ResponseEntity.ok().body(response);
	}
}
