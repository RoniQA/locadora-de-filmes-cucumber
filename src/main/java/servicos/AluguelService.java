package servicos;

import entidades.Filme;
import entidades.NotaAluguel;

import java.util.Calendar;

public class AluguelService {
    public NotaAluguel alugar(Filme filme) {
        if (filme.getEstoque() == 0)
            throw new RuntimeException("Filme sem estoque");
        NotaAluguel nota = new NotaAluguel();
        nota.setPreco(filme.getAluguel());
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        nota.setDataEntrega(cal.getTime());
        filme.setEstoque(filme.getEstoque() - 1);
        return nota;
    }
}
