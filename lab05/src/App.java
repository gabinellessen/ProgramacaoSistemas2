import java.math.BigDecimal;
import java.sql.*;
import java.util.Scanner;

public class App {
    private static final String URL = "jdbc:postgresql://aws-1-sa-east-1.pooler.supabase.com:6543/postgres";
    private static final String USER = "postgres.kvviwemnqsrtzsawuule";
    private static final String PASSWORD = "BTS2015<3";

    public static void main(String[] args) {
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Conexão ok!");

            while (true) {
                System.out.println("\nEscolha uma operação:");
                System.out.println("1 - Listar todas as contas");
                System.out.println("2 - Buscar uma conta pelo número");
                System.out.println("3 - Criar uma nova conta");
                System.out.println("4 - Alterar o saldo de uma conta");
                System.out.println("5 - Apagar uma conta");
                System.out.println("0 - Sair");
                System.out.print("Opção: ");

                int opcao;
                try {
                    opcao = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Opção inválida!");
                    continue;
                }

                switch (opcao) {
                    case 1:
                        listarContas(c);
                        break;
                    case 2:
                        buscarContaPorNumero(c, scanner);
                        break;
                    case 3:
                        criarConta(c, scanner);
                        break;
                    case 4:
                        alterarSaldoConta(c, scanner);
                        break;
                    case 5:
                        apagarConta(c, scanner);
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        return;
                    default:
                        System.out.println("Opção inválida!");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro de SQL: " + e.getMessage());
        }
    }

    // 1 - Listar todas as contas
    public static void listarContas(Connection c) {
        String sql = "SELECT * FROM consulta_contas";
        try (PreparedStatement stm = c.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
            boolean encontrou = false;
            while (rs.next()) {
                encontrou = true;
                long id = rs.getLong("id_contas");
                BigDecimal valor = rs.getBigDecimal("valor");
                System.out.println("Número: " + id + " - R$ " + valor);
            }
            if (!encontrou) {
                System.out.println("Nenhuma conta encontrada.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao consultar: " + e.getMessage());
        }
    }

    // 2 - Buscar uma conta específica pelo número
    public static void buscarContaPorNumero(Connection c, Scanner scanner) {
        System.out.print("Informe o número da conta: ");
        long nro = Long.parseLong(scanner.nextLine());
        String sql = "SELECT * FROM consulta_contas WHERE id_contas = ?";
        try (PreparedStatement stm = c.prepareStatement(sql)) {
            stm.setLong(1, nro);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    BigDecimal valor = rs.getBigDecimal("valor");
                    System.out.println("Número: " + nro + " - R$ " + valor);
                } else {
                    System.out.println("Conta não encontrada.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar conta: " + e.getMessage());
        }
    }

    // 3 - Criar uma nova conta
    public static void criarConta(Connection c, Scanner scanner) {
        try {
            System.out.print("Número para a nova conta: ");
            long nro = Long.parseLong(scanner.nextLine());
            System.out.print("Saldo da nova conta: ");
            BigDecimal saldo = new BigDecimal(scanner.nextLine());

            String sql = "INSERT INTO consulta_contas (id_contas, valor) VALUES (?, ?)";
            try (PreparedStatement prepstm = c.prepareStatement(sql)) {
                prepstm.setLong(1, nro);
                prepstm.setBigDecimal(2, saldo);
                int ret = prepstm.executeUpdate();
                System.out.println("Número de registros inseridos: " + ret);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida!");
        }
    }

    // 4 - Alterar o saldo de uma conta
    public static void alterarSaldoConta(Connection c, Scanner scanner) {
        try {
            System.out.print("Informe o número da conta: ");
            long nro = Long.parseLong(scanner.nextLine());
            System.out.print("Novo saldo: ");
            BigDecimal novoSaldo = new BigDecimal(scanner.nextLine());

            String sql = "UPDATE consulta_contas SET valor = ? WHERE id_contas = ?";
            try (PreparedStatement stm = c.prepareStatement(sql)) {
                stm.setBigDecimal(1, novoSaldo);
                stm.setLong(2, nro);
                int ret = stm.executeUpdate();
                if (ret > 0) {
                    System.out.println("Saldo atualizado com sucesso.");
                } else {
                    System.out.println("Conta não encontrada.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar saldo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida!");
        }
    }

    // 5 - Apagar uma conta
    public static void apagarConta(Connection c, Scanner scanner) {
        try {
            System.out.print("Informe o número da conta a ser deletada: ");
            long nro = Long.parseLong(scanner.nextLine());

            String sql = "DELETE FROM consulta_contas WHERE id_contas = ?";
            try (PreparedStatement stm = c.prepareStatement(sql)) {
                stm.setLong(1, nro);
                int ret = stm.executeUpdate();
                if (ret > 0) {
                    System.out.println("Conta apagada com sucesso.");
                } else {
                    System.out.println("Conta não encontrada.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida!");
        }
    }
}
