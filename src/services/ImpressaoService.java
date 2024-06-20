package services;

import model.Funcionario;
import util.DateUtil;
import util.BigDecimalUtil;

import java.math.BigDecimal;
import java.util.List;
import java.text.DecimalFormat;

public class ImpressaoService {

    public void imprimirFuncionarios(List<Funcionario> funcionarios) {
        for (Funcionario f : funcionarios) {
            System.out.println("Nome: " + f.getNome() + ", Data de Nascimento: " + DateUtil.format(f.getDataNascimento())
                    + ", Salário: " + BigDecimalUtil.format(f.getSalario())
                    + ", Função: " + f.getFuncao());
        }
    }

    public void imprimirTotalSalarios(BigDecimal totalSalarios) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        System.out.println("Total dos salários: R$ " + df.format(totalSalarios));
    }

    public void imprimirSalariosMinimos(List<Funcionario> funcionarios, BigDecimal salarioMinimo) {
        System.out.println("Salários mínimos por funcionário:");
        funcionarios.forEach(f -> System.out.println(f.getNome() + " ganha " + f.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_EVEN) + " salários mínimos."));
    }
}
