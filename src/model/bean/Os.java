/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author Master
 */
public class Os {

    private int idos;
    private String data;
    private String nome_cliente;
    private String nome_equip;
    private String defeito;
    private String tecnico;
    private String dt_inicio;
    private String hr_inic;
    private String servico;
    private Boolean uso_peca;
    private Boolean equip_retirado;
    private String obs;
    private Boolean aberta_fech;
    private String dt_fim;
    private String hr_fim;
    private Tiposerv tiposerv;
    private Cliente cliente;
    private Equipamento equipamento;

    public Os() {
    }

    public Os(int idos, String data, String nome_cliente, String nome_equip, String defeito, String tecnico, String dt_inicio, String hr_inic, String servico, Boolean uso_peca, Boolean equip_retirado, String obs, Boolean aberta_fech, String dt_fim, String hr_fim, Tiposerv tiposerv, Cliente cliente, Equipamento equipamento) {
        this.idos = idos;
        this.data = data;
        this.nome_cliente = nome_cliente;
        this.nome_equip = nome_equip;
        this.defeito = defeito;
        this.tecnico = tecnico;
        this.dt_inicio = dt_inicio;
        this.hr_inic = hr_inic;
        this.servico = servico;
        this.uso_peca = uso_peca;
        this.equip_retirado = equip_retirado;
        this.obs = obs;
        this.aberta_fech = aberta_fech;
        this.dt_fim = dt_fim;
        this.hr_fim = hr_fim;
        this.tiposerv = tiposerv;
        this.cliente = cliente;
        this.equipamento = equipamento;
    }

    public int getIdos() {
        return idos;
    }

    public void setIdos(int idos) {
        this.idos = idos;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getNome_equip() {
        return nome_equip;
    }

    public void setNome_equip(String nome_equip) {
        this.nome_equip = nome_equip;
    }

    public String getDefeito() {
        return defeito;
    }

    public void setDefeito(String defeito) {
        this.defeito = defeito;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public String getDt_inicio() {
        return dt_inicio;
    }

    public void setDt_inicio(String dt_inicio) {
        this.dt_inicio = dt_inicio;
    }

    public String getHr_inic() {
        return hr_inic;
    }

    public void setHr_inic(String hr_inic) {
        this.hr_inic = hr_inic;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public Boolean getUso_peca() {
        return uso_peca;
    }

    public void setUso_peca(Boolean uso_peca) {
        this.uso_peca = uso_peca;
    }

    public Boolean getEquip_retirado() {
        return equip_retirado;
    }

    public void setEquip_retirado(Boolean equip_retirado) {
        this.equip_retirado = equip_retirado;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Boolean getAberta_fech() {
        return aberta_fech;
    }

    public void setAberta_fech(Boolean aberta_fech) {
        this.aberta_fech = aberta_fech;
    }

    public String getDt_fim() {
        return dt_fim;
    }

    public void setDt_fim(String dt_fim) {
        this.dt_fim = dt_fim;
    }

    public String getHr_fim() {
        return hr_fim;
    }

    public void setHr_fim(String hr_fim) {
        this.hr_fim = hr_fim;
    }

    public Tiposerv getTiposerv() {
        return tiposerv;
    }

    public void setTiposerv(Tiposerv tiposerv) {
        this.tiposerv = tiposerv;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    @Override
    public String toString() {
        return "" + getIdos(); //To change body of generated methods, choose Tools | Templates.
    }

}

