package br.com.localizaja.dao;

import br.com.localizaja.dto.EstabelecimentoDTO;
import br.com.localizaja.entidade.Estabelecimento;
import java.math.BigDecimal;
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

    public List<EstabelecimentoDTO> getEstabelecimentosPorLocalizacao(Double latitude, Double longitude, Integer distancia, String... seguimentos) {
        StringBuilder b = new StringBuilder();
        b.append("SELECT EST.NOME, ENDERECO.ENDERECOCOMPLETO, EST.TELEFONE,");
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

        /*b.append(" (6378.137 * ACOS(COS(RADIANS(COORD.LATITUDE)) * COS(RADIANS(").append(latitude).append(")) *");
        b.append(" COS(RADIANS(").append(longitude).append(") - RADIANS(COORD.LONGITUDE)) + SIN(RADIANS(COORD.LONGITUDE)) *");
        b.append(" SIN(RADIANS(").append(latitude).append("))) <= ").append(distancia).append(")");*/
        Query query = entityManager.createNativeQuery(b.toString());
        List<Object[]> resultList = query.getResultList();
        System.out.println("TAMANHO " + resultList.size());

        List<EstabelecimentoDTO> result = new ArrayList<>();
        for (Object[] linha : resultList) {
            EstabelecimentoDTO dto = new EstabelecimentoDTO();
            dto.setNomeEmpresa((String) linha[0]);
            dto.setEnderecoCompleto((String) linha[1]);
            dto.setTelefone((String) linha[2]);
            dto.setLatitude((Double) linha[3]);
            dto.setLongitude((Double) linha[4]);

            if (calculateRadius(dto.getLatitude(), dto.getLongitude(), latitude, longitude, distancia)) {
                result.add(dto);
            }
        }

        return result;
    }

    private boolean calculateRadius(double latitudeEstabelecimento, double longitudeEstabelecimento, double latitudeUsuario, double longitudeUsuario, double distancia) {
        Double calculoRaio = (6378.137 * Math.acos(Math.cos(Math.toRadians(latitudeEstabelecimento)) * Math.cos(Math.toRadians(latitudeUsuario))
                * Math.cos(Math.toRadians(longitudeUsuario) - Math.toRadians(longitudeEstabelecimento)) + Math.sin(Math.toRadians(longitudeEstabelecimento))
                * Math.sin(Math.toRadians(latitudeUsuario))));

        System.out.println("Calculo do Raio: " + calculoRaio);
        boolean dentroDoRaio = calculoRaio <= distancia;
        System.out.println("Esta dentro do raio? " + dentroDoRaio);

        return dentroDoRaio;
    }
}
