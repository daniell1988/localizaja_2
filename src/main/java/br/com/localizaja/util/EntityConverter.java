package br.com.localizaja.util;

import br.com.localizaja.dto.EstabelecimentoDTO;
import br.com.localizaja.entidade.CoordenadasGeograficas;
import br.com.localizaja.entidade.Endereco;
import br.com.localizaja.entidade.Estabelecimento;
import br.com.localizaja.entidade.Seguimento;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author adriana
 */
public class EntityConverter {

    private EntityConverter() {
    }

    public static EstabelecimentoDTO toDTO(Estabelecimento e) {
        EstabelecimentoDTO dto = new EstabelecimentoDTO();

        dto.setEnderecoCompleto(e.getEndereco().getEnderecoCompleto());
        dto.setLatitude(e.getEndereco().getCoordenadasGeograficas().getLatitude());
        dto.setLongitude(e.getEndereco().getCoordenadasGeograficas().getLongitude());
        dto.setNomeEmpresa(e.getNome());
        e.setTelefone(e.getTelefone());

        return dto;
    }

    public static Estabelecimento toEstabelecimento(EstabelecimentoDTO dto) {
        Estabelecimento estabelecimento = new Estabelecimento();

        estabelecimento.setNome(dto.getNomeEmpresa());
        estabelecimento.setTelefone(dto.getTelefone());

        for (String nome : dto.getCategoria().split(",")) {
            Seguimento seguimento = new Seguimento();
            seguimento.setNome(nome);
            estabelecimento.adicionarSeguimento(seguimento);
        }

        Endereco end = new Endereco();
        end.setEnderecoCompleto(dto.getEnderecoCompleto());
        end.setCoordenadasGeograficas(new CoordenadasGeograficas(dto.getLatitude(), dto.getLongitude()));
        estabelecimento.setEndereco(end);

        return estabelecimento;
    }

    public static List<EstabelecimentoDTO> toDTO(List<Estabelecimento> estabelecimentosPorLocalizacao) {
        List<EstabelecimentoDTO> result = new ArrayList<>();
        for (Estabelecimento e : estabelecimentosPorLocalizacao) {
            result.add(toDTO(e));
        }
        return result;
    }
}
