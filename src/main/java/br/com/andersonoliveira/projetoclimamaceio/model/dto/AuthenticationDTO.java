package br.com.andersonoliveira.projetoclimamaceio.model.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(
        @NotBlank String userName, @NotBlank String password) {
}