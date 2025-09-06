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
                System.out.println("1 - Criar conta");
                System.out.println("2 - Listar contas");
                System.out.println("3 - Atualizar conta");
                System.out.println("4 - Deletar conta");
                System.out.println("0 - Sair");
                System.out.print("Opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer

                switch (opcao) {
                    case 1:
                        create(c, scanner);
                        break;
                    case 2:
                        read(c);
                        break;
                    case 3:
                        update(c, scanner);
                        break;
                    case 4:
                        delete(c, scanner);
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

    // CREATE
    public static void create(Connection c, Scanner scanner) {
        try {
            System.out.print("Número para a nova conta: ");
            long nro = scanner.nextLong();
            System.out.print("Saldo da nova conta: ");
            BigDecimal saldo = scanner.nextBigDecimal();

            String sql = "INSERT INTO consulta_contas (id_contas, valor) VALUES (?, ?)";
            try (PreparedStatement prepstm = c.prepareStatement(sql)) {
                prepstm.setLong(1, nro);
                prepstm.setBigDecimal(2, saldo);
                int ret = prepstm.executeUpdate();
                System.out.println("Número de registros inseridos: " + ret);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir: " + e.getMessage());
        }
    }

    // READ
    public static void read(Connection c) {
        String sql = "SELECT * FROM consulta_contas";
        try (PreparedStatement stm = c.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                long id = rs.getLong("id_contas");
                BigDecimal valor = rs.getBigDecimal("valor");
                System.out.println("Número: " + id + " - R$ " + valor);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao consultar: " + e.getMessage());
        }
    }

    // UPDATE
    public static void update(Connection c, Scanner scanner) {
        try {
            System.out.print("Informe o ID da conta a ser alterada: ");
            long idAntigo = scanner.nextLong();
            System.out.print("Novo número da conta: ");
            long idNovo = scanner.nextLong();
            System.out.print("Novo saldo: ");
            BigDecimal novoValor = scanner.nextBigDecimal();

            String sql = "UPDATE consulta_contas SET id_contas = ?, valor = ? WHERE id_contas = ?";
            try (PreparedStatement stm = c.prepareStatement(sql)) {
                stm.setLong(1, idNovo);
                stm.setBigDecimal(2, novoValor);
                stm.setLong(3, idAntigo);
                int ret = stm.executeUpdate();
                System.out.println("Registros alterados: " + ret);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar: " + e.getMessage());
        }
    }

    // DELETE
    public static void delete(Connection c, Scanner scanner) {
        try {
            System.out.print("Informe o ID da conta a ser deletada: ");
            long id = scanner.nextLong();

            String sql = "DELETE FROM consulta_contas WHERE id_contas = ?";
            try (PreparedStatement stm = c.prepareStatement(sql)) {
                stm.setLong(1, id);
                int ret = stm.executeUpdate();
                System.out.println("Registros apagados: " + ret);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar: " + e.getMessage());
        }
    }
}