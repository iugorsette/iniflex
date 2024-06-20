package services;

import model.Funcionario;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AgrupamentoService {
    public Map<String, List<Funcionario>> agruparPorFuncao(List<Funcionario> funcionarios) {
        return funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));
    }

    public void imprimirAgrupadosPorFuncao(Map<String, List<Funcionario>> funcionariosPorFuncao) {
        funcionariosPorFuncao.forEach((funcao, listaFuncionarios) -> {
            System.out.print(funcao + ": ");
            String nomes = listaFuncionarios.stream()
                    .map(Funcionario::getNome)
                    .reduce((nome1, nome2) -> nome1 + ", " + nome2)
                    .orElse("");
            System.out.println(nomes);
        });
    }
}
