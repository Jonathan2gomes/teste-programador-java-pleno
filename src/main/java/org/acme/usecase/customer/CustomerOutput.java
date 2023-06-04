package org.acme.usecase.customer;

public record CustomerOutput(Long codigo, String nome, String telefone, String cpf, String email) {
}
