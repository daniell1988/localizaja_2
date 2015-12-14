package br.com.localizaja.dto;

public class GeoLocation {

    private Double latitute;
    private Double longitute;
    private String enderecoCompleto;

    public GeoLocation() {
    }

    public GeoLocation(Double latitute, Double longitute, String enderecoCompleto) {
        this.latitute = latitute;
        this.longitute = longitute;
        this.enderecoCompleto = enderecoCompleto;
    }

    public GeoLocation build() {
        return new GeoLocation(latitute, longitute, enderecoCompleto);
    }

    public Double getLatitute() {
        return latitute;
    }

    public void setLatitute(Double latitute) {
        this.latitute = latitute;
    }

    public Double getLongitute() {
        return longitute;
    }

    public void setLongitute(Double longitute) {
        this.longitute = longitute;
    }

    public String getEnderecoCompleto() {
        return enderecoCompleto;
    }

    public void setEnderecoCompleto(String enderecoCompleto) {
        this.enderecoCompleto = enderecoCompleto;
    }
}
