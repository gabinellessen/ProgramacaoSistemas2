public class App {
    public static void main(String[] args) throws Exception {
        private static final String URL = "jdbc:postgresql://aws-1-sa-east-1.pooler.supabase.com:6543/postgres";
        private static final String USER = "postgres.kvviwemnqsrtzsawuule";
        private static final String PASSWORD = "BTS2015<3";
        ContaDao dao;
        Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
        dao = new ContaDao(ConnectionFactory.getConnection(url));
        List<Conta> contas;
        contas = dao.lerTodas();
        System.out.println(contas);
    }
}
