package br.com.andersonoliveira.projetoclimamaceio.exceptionhandler;

import br.com.andersonoliveira.projetoclimamaceio.client.exception.ClimaApiException;
import br.com.andersonoliveira.projetoclimamaceio.service.exception.DadosClimaticosException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ProjetoClimaMaceioExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ExceptionResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        ExceptionResponse response = new ExceptionResponse("O parâmetro '" + ex.getParameterName() + "' não foi informado.", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        String paramName = ex.getName();
        String paramValue = ex.getValue() != null ? ex.getValue().toString() : "null";
        String errorMessage = String.format("O parâmetro '%s' com valor '%s' não é válido para o tipo esperado.", paramName, paramValue);

        ExceptionResponse response = new ExceptionResponse(errorMessage, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        ExceptionResponse response = new ExceptionResponse("Requisição inválida. Verifique os parâmetros informados.", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleBadCredentialsException(BadCredentialsException ex) {
        ExceptionResponse response = new ExceptionResponse("Requisição inválida.", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DadosClimaticosException.class)
    public ResponseEntity<ExceptionResponse> handleDadosClimaticosSalvamentoException(DadosClimaticosException ex) {
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
