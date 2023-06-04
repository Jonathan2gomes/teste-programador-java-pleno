package org.acme.usecase.customer;


import jakarta.enterprise.context.ApplicationScoped;
import org.acme.domain.gateway.CustomerGateway;
import org.acme.infrastructure.mapper.CustomerMapper;

@ApplicationScoped
public class CreateCustomerUseCase {

    private final CustomerGateway customerGateway;

    public CreateCustomerUseCase(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    public void execute(CustomerInput input) {


        if (!validaCpf(input.cpf())) {
            throw new RuntimeException("CPF inválido");
        }

        customerValidator(input.cpf());

        customerGateway.create(CustomerMapper.fromInputToCustomer(input));
    }

    private void customerValidator(String cpf) {
        if (customerGateway.findByCpf(cpf).isPresent()) {
            throw new RuntimeException("O cliente com CPF: " +cpf+ " já existe na base de dados");
        }
    }
    private boolean validaCpf(String cpf) {
        // Remove caracteres inválidos (como pontos e hifens) do CPF
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifica se o CPF possui 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verifica se todos os dígitos são iguais
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Calcula o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(cpf.charAt(i));
            soma += digito * (10 - i);
        }
        int resto = 11 - soma % 11;
        int dv1 = (resto >= 10) ? 0 : resto;

        // Calcula o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            int digito = Character.getNumericValue(cpf.charAt(i));
            soma += digito * (11 - i);
        }
        resto = 11 - soma % 11;
        int dv2 = (resto >= 10) ? 0 : resto;
        // Verifica se os dígitos verificadores estão corretos
        return cpf.substring(9).equals(Integer.toString(dv1) + Integer.toString(dv2));
    }

}
