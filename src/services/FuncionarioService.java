package services;

import model.Funcionario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioService {

    public List<Funcionario> inicializarFuncionarios(Object[][] dadosFuncionarios) {
        List<Funcionario> funcionarios = new ArrayList<>();

        for (Object[] dados : dadosFuncionarios) {
            String nome = (String) dados[0];
            LocalDate dataNascimento = (LocalDate) dados[1];
            BigDecimal salario = (BigDecimal) dados[2];
            String funcao = (String) dados[3];

            funcionarios.add(new Funcionario(nome, dataNascimento, salario, funcao));
        }

        return funcionarios;
    }
    public void aplicarAumento(List<Funcionario> funcionarios, BigDecimal percentual) {
        for (Funcionario funcionario : funcionarios) {
            BigDecimal aumento = funcionario.getSalario().multiply(percentual);
            funcionario.setSalario(funcionario.getSalario().add(aumento));
        }
    }

    public void removerFuncionario(List<Funcionario> funcionarios, String nome) {
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals(nome));
    }
}
