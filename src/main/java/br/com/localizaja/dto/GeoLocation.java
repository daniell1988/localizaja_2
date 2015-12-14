package br.com.localizaja.dto;

import java.math.BigDecimal;

public class GeoLocation {

    private BigDecimal latitute;
    private BigDecimal longitute;
    private String enderecoCompleto;

    public GeoLocation() {
    }

    public GeoLocation(BigDecimal latitute, BigDecimal longitute, String enderecoCompleto) {
        this.latitute = latitute;
        this.longitute = longitute;
        this.enderecoCompleto = enderecoCompleto;
    }

    public GeoLocation build() {
        return new GeoLocation(latitute, longitute, enderecoCompleto);
    }

    public BigDecimal getLatitute() {
        return latitute;
    }

    public void setLatitute(BigDecimal latitute) {
        this.latitute = latitute;
    }

    public BigDecimal getLongitute() {
        return longitute;
    }

    public void setLongitute(BigDecimal longitute) {
        this.longitute = longitute;
    }

    public String getEnderecoCompleto() {
        return enderecoCompleto;
    }

    public void setEnderecoCompleto(String enderecoCompleto) {
        this.enderecoCompleto = enderecoCompleto;
    }
}
