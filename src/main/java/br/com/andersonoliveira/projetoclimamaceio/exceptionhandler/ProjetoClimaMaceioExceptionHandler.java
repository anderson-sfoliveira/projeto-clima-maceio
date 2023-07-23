package br.com.andersonoliveira.projetoclimamaceio.exceptionhandler;

import br.com.andersonoliveira.projetoclimamaceio.client.exception.ClimaApiException;
import br.com.andersonoliveira.projetoclimamaceio.service.exception.DadosClimaticosSalvamentoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjetoClimaMaceioExceptionHandler {

    @ExceptionHandler(DadosClimaticosSalvamentoException.class)
    public ResponseEntity<ExceptionResponse> handleDadosClimaticosSalvamentoException(DadosClimaticosSalvamentoException ex) {
        ExceptionResponse response = new ExceptionResponse("Erro ao salvar dados climáticos", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ClimaApiException.class)
    public ResponseEntity<ExceptionResponse> handleClimaApiException(ClimaApiException ex) {
        ExceptionResponse response = new ExceptionResponse("Erro na chamada da API de Clima", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static class ExceptionResponse {

        private String error;
        private String message;

        public ExceptionResponse(String error, String message) {
            this.error = error;
            this.message = message;
        }

        public String getError() {
            return error;
        }

        public String getMessage() {
            return message;
        }
    }
}
