package br.com.localizaja.dao;

import br.com.localizaja.dto.EstabelecimentoDTO;
import br.com.localizaja.dto.ResultadoBuscaDTO;
import br.com.localizaja.entidade.Estabelecimento;
import br.com.localizaja.util.CalculateRadius;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author adriana
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class EstabelecimentoDAO {

    @PersistenceContext(name = "localizaja")
    private EntityManager entityManager;

    public void salvar(Estabelecimento estabelecimento) throws Exception {
        entityManager.persist(estabelecimento);
    }

    public List<ResultadoBuscaDTO> getEstabelecimentosPorLocalizacao(String enderecoOrigem, Integer distancia, String... seguimentos) throws Exception {
        StringBuilder b = new StringBuilder();
        b.append("SELECT DISTINCT EST.ID, EST.NOME, ENDERECO.ENDERECOCOMPLETO, EST.TELEFONE,");
        b.append(" COORD.LATITUDE, COORD.LONGITUDE FROM ESTABELECIMENTO EST");
        b.append(" INNER JOIN ENDERECO ENDERECO              ON (EST.ENDERECO_ID = ENDERECO.ID)");
        b.append(" INNER JOIN COORDENADASGEOGRAFICAS COORD   ON (ENDERECO.COORDENADASGEOGRAFICAS_ID = COORD.ID)");
        b.append(" INNER JOIN ESTABELECIMENTO_SEGUIMENTO ES  ON (ES.ESTABELECIMENTO_ID = EST.ID)");
        b.append(" INNER JOIN SEGUIMENTO                 SEG ON (ES.SEGUIMENTOS_ID = SEG.ID)");

        if (seguimentos.length > 0) {
            b.append(" WHERE ");
            b.append(" (");
            for (String seguimento : seguimentos) {
                b.append("(LOWER(SEG.NOME) LIKE '%").append(seguimento.toLowerCase()).append("%' ) OR");
            }
            b.append(" (1 = 1))");
        }

        Query query = entityManager.createNativeQuery(b.toString());
        List<Object[]> resultList = query.getResultList();
        System.out.println("TAMANHO " + resultList.size());

        List<ResultadoBuscaDTO> result = new ArrayList<>();
        for (Object[] linha : resultList) {
            ResultadoBuscaDTO dto = new ResultadoBuscaDTO();
            dto.setNomeEmpresa((String) linha[1]);
            dto.setEnderecoCompleto((String) linha[2]);
            dto.setTelefone((String) linha[3]);

            Double kilometragem = CalculateRadius.calcularDistanciaDouble(enderecoOrigem, dto.getEnderecoCompleto());
            if (kilometragem <= distancia) {
                dto.setDistancia(String.valueOf(kilometragem).replace(".", ",").concat(" Km"));
                result.add(dto);
            }
        }

        return result;
    }

}
