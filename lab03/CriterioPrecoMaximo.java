public class CriterioPrecoMaximo implements CriterioBusca {
    @Override
    public boolean testar(Produto p, String valor) {
        try {
            double precoMaximo = Double.parseDouble(valor);
            return p.getPreco() <= precoMaximo;
        } catch (NumberFormatException e) {
            // Se o valor não for um número válido, retorna false
            return false;
        }
    }
}