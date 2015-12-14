package br.com.localizaja.controle;

import br.com.localizaja.dto.BuscaEstabelecimentoDTO;
import br.com.localizaja.dto.EstabelecimentoDTO;
import br.com.localizaja.servico.Servico;
import br.com.localizaja.dto.GeoLocation;
import br.com.localizaja.loaddata.CarregarBanco;
import br.com.localizaja.util.FacesMessageUtil;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.Marker;

/**
 *
 * @author eyvdmw
 */
@ManagedBean
@ViewScoped
public class BuscaEstabelecimentoMB {

    @EJB
    private Servico servico;

    @EJB
    private CarregarBanco carregarBanco;

    private List<EstabelecimentoDTO> estabelecimentos;
    private BuscaEstabelecimentoDTO buscaEstabelecimentoDTO;

    private GeoLocation enderecoSelecionado;

    @PostConstruct
    public void inicia() {
        buscaEstabelecimentoDTO = new BuscaEstabelecimentoDTO();
        buscaEstabelecimentoDTO.setRaio(4);
    }

    public void carregarBanco() {
        try {
            carregarBanco.carrega();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void buscarMinhaLocalizacao() {
        try {
            GeoLocation endereco = servico.getEndereco(buscaEstabelecimentoDTO.getLatitude(), buscaEstabelecimentoDTO.getLongitude());
            buscaEstabelecimentoDTO.setEnderecoOrigem(endereco.getEnderecoCompleto());
        } catch (Exception ex) {
            FacesMessageUtil.addMensagemError("Não foi possível obter a localização: " + ex);
            ex.printStackTrace();
        }
    }

    public void buscarEnderecos() {
        try {
            estabelecimentos = servico.getEstabelecimentosPorLocalizacao(buscaEstabelecimentoDTO);
        } catch (Exception ex) {
            ex.printStackTrace();
            FacesMessageUtil.addMensagemError("Não foi possível buscar endereços: " + ex);
            estabelecimentos = null;
        }
    }

    public void onMarkerSelect(OverlaySelectEvent event) {
        Marker marker = (Marker) event.getOverlay();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", marker.getTitle()));
    }

    public String goToCadastro() {
        return "cadastro_estabelecimento.xhtml";
    }

    public String getMyLocation() {
        String location = "-23.550717006921673, -46.63357400000001";
        try {
            if (buscaEstabelecimentoDTO.getLatitude() != null
                    || buscaEstabelecimentoDTO.getLongitude() != null) {
                location = buscaEstabelecimentoDTO.getLatitude() + " " + buscaEstabelecimentoDTO.getLongitude();
            }
        } catch (Exception e) {
        }
        return location;
    }

    public GeoLocation getEnderecoSelecionado() {
        return enderecoSelecionado;
    }

    public void setEnderecoSelecionado(GeoLocation enderecoSelecionado) {
        this.enderecoSelecionado = enderecoSelecionado;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public List<EstabelecimentoDTO> getEstabelecimentos() {
        return estabelecimentos;
    }

    public void setEstabelecimentos(List<EstabelecimentoDTO> estabelecimentos) {
        this.estabelecimentos = estabelecimentos;
    }

    public BuscaEstabelecimentoDTO getBuscaEstabelecimentoDTO() {
        return buscaEstabelecimentoDTO;
    }

    public void setBuscaEstabelecimentoDTO(BuscaEstabelecimentoDTO buscaEstabelecimentoDTO) {
        this.buscaEstabelecimentoDTO = buscaEstabelecimentoDTO;
    }
}
