package services;

import model.Funcionario;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
public class FuncionarioUtils {

    public Optional<Funcionario> encontrarMaisVelho(List<Funcionario> funcionarios) {
        return funcionarios.stream().min(Comparator.comparing(Funcionario::getDataNascimento));
    }

    public List<String> encontrarAniversariantes(List<Funcionario> funcionarios, int... meses) {
        int anoAtual = LocalDate.now().getYear();
        return funcionarios.stream()
                .filter(funcionario -> {
                    int mesNascimento = funcionario.getDataNascimento().getMonthValue();
                    for (int mes : meses) {
                        if (mes == mesNascimento) {
                            return true;
                        }
                    }
                    return false;
                })
                .map(funcionario -> {
                    int idade = anoAtual - funcionario.getDataNascimento().getYear();
                    String dataFormatada = String.format("%02d/%02d/%d",
                            funcionario.getDataNascimento().getDayOfMonth(),
                            funcionario.getDataNascimento().getMonthValue(),
                            anoAtual);
                    return String.format("%s, %d anos - %s",
                            funcionario.getNome(), idade, dataFormatada);
                })
                .collect(Collectors.toList());
    }
}
