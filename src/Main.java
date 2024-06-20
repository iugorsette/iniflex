import model.Funcionario;
import services.FuncionarioService;
import services.ImpressaoService;
import services.AgrupamentoService;
import services.FuncionarioUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        FuncionarioService funcionarioService = new FuncionarioService();
        ImpressaoService impressaoService = new ImpressaoService();
        AgrupamentoService agrupamentoService = new AgrupamentoService();
        FuncionarioUtils funcionarioUtils = new FuncionarioUtils();

        Object[][] dadosFuncionarios = {
                {"Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"},
                {"João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"},
                {"Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"},
                {"Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"},
                {"Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"},
                {"Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"},
                {"Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"},
                {"Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"},
                {"Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"},
                {"Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"}
        };

        System.out.println("3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.\n ");
        List<Funcionario> funcionarios = funcionarioService.inicializarFuncionarios(dadosFuncionarios);

        System.out.println("3.2 – Remover o funcionário “João” da lista.\n");
        funcionarioService.removerFuncionario(funcionarios, "João");

        System.out.println("3.3 – Imprimir todos os funcionários com todas suas informações");
        //• informação de data deve ser exibido no formato dd/mm/aaaa;
        //• informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.
        impressaoService.imprimirFuncionarios(funcionarios);

        System.out.println("3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.\n");
        funcionarioService.aplicarAumento(funcionarios, new BigDecimal("0.10"));


        System.out.println("3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários.\n");
        Map<String, List<Funcionario>> funcionariosPorFuncao = agrupamentoService.agruparPorFuncao(funcionarios);

        System.out.println("3.6 – Imprimir os funcionários, agrupados por função.");
        agrupamentoService.imprimirAgrupadosPorFuncao(funcionariosPorFuncao);

        System.out.println("3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.");
        List<String> aniversariantesFormatados = funcionarioUtils.encontrarAniversariantes(funcionarios, 10, 12);
        aniversariantesFormatados.forEach(System.out::println);

        System.out.println(" 3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.");
        Optional<Funcionario> maisVelho = funcionarioUtils.encontrarMaisVelho(funcionarios);
        if (maisVelho.isPresent()) {
            Funcionario funcionarioMaisVelho = maisVelho.get();
            System.out.println("Funcionário com maior idade: " + funcionarioMaisVelho.getNome() + ", Idade: "
                    + (LocalDate.now().getYear() - funcionarioMaisVelho.getDataNascimento().getYear()));
        }

        System.out.println(" 3.10 – Imprimir a lista de funcionários por ordem alfabética.");
        funcionarios.stream()
                .sorted((f1, f2) -> f1.getNome().compareTo(f2.getNome()))
                .map(Funcionario::getNome)
                .forEach(System.out::println);

        System.out.println(" 3.11 – Imprimir o total dos salários dos funcionários.");
        BigDecimal totalSalarios = funcionarios.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO, BigDecimal::add);
        impressaoService.imprimirTotalSalarios(totalSalarios);

        System.out.println(" 3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.");
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        impressaoService.imprimirSalariosMinimos(funcionarios, salarioMinimo);
    }
}
