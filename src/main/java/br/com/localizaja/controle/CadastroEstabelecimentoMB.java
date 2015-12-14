package br.com.localizaja.controle;

import br.com.localizaja.servico.Servico;
import br.com.localizaja.dto.EstabelecimentoDTO;
import br.com.localizaja.dto.GeoLocation;
import br.com.localizaja.util.EntityConverter;
import br.com.localizaja.util.FacesMessageUtil;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author eyvdmw
 */
@ManagedBean
@ViewScoped
public class CadastroEstabelecimentoMB {

    @EJB
    private Servico servico;

    private List<GeoLocation> resultadoEnderecos;
    private EstabelecimentoDTO empresa = new EstabelecimentoDTO();
    private GeoLocation enderecoSelecionado;

    public void buscarEnderecos() {
        try {
            resultadoEnderecos = servico.getCoordenadasGeograficas(empresa.getEndereco() + "," + empresa.getCidade() + "," + empresa.getEstado());
            if (resultadoEnderecos.isEmpty()) {
                FacesMessageUtil.addMensagemWarn("Endereço não localizado");
            }
        } catch (Exception ex) {
            resultadoEnderecos = null;
            FacesMessageUtil.addMensagemError("Não foi possível obter a localização: " + ex);
            ex.printStackTrace();
        }
    }

    public String cadastrarEndereco() {
        try {
            empresa.setLatitude(enderecoSelecionado.getLatitute());
            empresa.setLongitude(enderecoSelecionado.getLongitute());
            empresa.setEnderecoCompleto(enderecoSelecionado.getEnderecoCompleto());
            servico.salvarEstabelecimento(EntityConverter.toEstabelecimento(empresa));
            FacesMessageUtil.addMensagemInfo(empresa.getNomeEmpresa() + " cadastrada com sucesso!");
            return "/busca_estabelecimento.jsf";
        } catch (Exception ex) {
            FacesMessageUtil.addMensagemError("Não foi possível obter a cadastrar: " + ex);
            ex.printStackTrace();
        }
        return "/busca_estabelecimento.jsf";
    }

    public List<GeoLocation> getResultadoEnderecos() {
        return resultadoEnderecos;
    }

    public void setResultadoEnderecos(List<GeoLocation> resultadoEnderecos) {
        this.resultadoEnderecos = resultadoEnderecos;
    }

    public EstabelecimentoDTO getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EstabelecimentoDTO empresa) {
        this.empresa = empresa;
    }

    public GeoLocation getEnderecoSelecionado() {
        return enderecoSelecionado;
    }

    public void setEnderecoSelecionado(GeoLocation enderecoSelecionado) {
        this.enderecoSelecionado = enderecoSelecionado;
    }
}
