package steps_definition;

import entidades.Filme;
import cucumber.api.PendingException;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import entidades.NotaAluguel;
import entidades.TipoAluguel;
import org.junit.Assert;
import servicos.AluguelService;
import utils.DateUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AlugarFilmesSteps {

    private Filme filme;
    private AluguelService aluguel = new AluguelService();
    private NotaAluguel nota;
    private String erro;
    private TipoAluguel tipoAluguel = TipoAluguel.COMUM;
    @Dado("^um filme com estoque de (\\d+) unidades$")
    public void um_filme_com_estoque_de_unidades(int arg1) throws Throwable {
        filme = new Filme();
        filme.setEstoque(arg1);
    }

    @Dado("^que o preço do aluguel seja R\\$ (\\d+)$")
    public void que_o_preço_do_aluguel_seja_R$(int arg1) throws Throwable {
        filme.setAluguel(arg1);
    }

    @Quando("^alugar$")
    public void alugar() throws Throwable {
        try {
            nota = aluguel.alugar(filme, tipoAluguel);
        } catch (RuntimeException e){
            erro = e.getMessage();
        }

    }

    @Entao("^o preço do aluguel será R\\$ (\\d+)$")
    public void o_preço_do_aluguel_será_R$(int arg1) throws Throwable {
        Assert.assertEquals(arg1, nota.getPreco());
    }

    @Entao("^o estoque do filme será (\\d+) unidade$")
    public void o_estoque_do_filme_será_unidade(int arg1) throws Throwable {
        Assert.assertEquals(arg1, filme.getEstoque());
    }

    @Entao("^não será possível por falta de estoque$")
    public void não_será_possível_por_falta_de_estoque() throws Throwable {
        Assert.assertEquals("Filme sem estoque", erro);
    }

    @Dado("^que o tipo do aluguel seja (.*)$")
    public void que_o_tipo_do_aluguel_seja_extendido(String tipo) throws Throwable {
        tipoAluguel = tipo.equals("semanal")? TipoAluguel.SEMANAL: tipo.equals("extendido")? TipoAluguel.EXTENDIDO: TipoAluguel.COMUM;
    }

    @Entao("^a data de entrega será em (\\d+) dias?$")
    public void a_data_de_entrega_será_em_dias(int arg1) throws Throwable {
        Date dataEsperada = DateUtils.obterDataDiferencaDias(arg1);
        Date dataReal = nota.getDataEntrega();

        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        Assert.assertEquals(format.format(dataEsperada), format.format(dataReal));
    }

    @Entao("^a pontuação recebida será (\\d+) pontos?$")
    public void a_pontuação_recebida_será_pontos(int arg1) throws Throwable {
        Assert.assertEquals(arg1, nota.getPontuacao());
    }
}
