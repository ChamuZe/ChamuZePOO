package br.pucpr.chamuzepoo.model;
import br.pucpr.chamuzejava.file.Categoria;
import br.pucpr.chamuzejava.file.StatusServico;
import br.pucpr.chamuzejava.file.LocalServico;
import java.io.Serializable;

public class Servico implements Serializable{
    private static final long serialVersionUID = 1L;
    private int solicitanteId;
    private Proposta proposta;
    private String descricao;
    private String titulo;
    private Categoria categoria;
    private LocalServico localServico;
    private StatusServico statusServico;
    private double preco;

    public Servico(int solicitanteId, String descricao, String titulo, Categoria categoria, LocalServico localServico, double preco ) {
        this.descricao = descricao;
        this.titulo = titulo;
        this.solicitanteId = solicitanteId;
        this.proposta = null;
        this.categoria = categoria;
        this.localServico = localServico;
        this.statusServico = StatusServico.DISPONIVEL;
        this.preco = preco;
    }

    public int getSolicitanteId() {
        return solicitanteId;
    }

    public void setSolicitanteId(int solicitanteId) {
        this.solicitanteId = solicitanteId;
    }

    public Proposta getProposta() {
        return proposta;
    }

    public void setProposta(Proposta proposta) {
        this.proposta = proposta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public LocalServico getLocalServico() {
        return localServico;
    }

    public void setLocalServico(LocalServico localServico) {
        this.localServico = localServico;
    }

    public StatusServico getStatusServico() {
        return statusServico;
    }

    public void setStatusServico(StatusServico statusServico) {
        this.statusServico = statusServico;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    @Override
    public String toString() {
        return "Descrição: " + descricao + "\nTitulo: " + titulo + "\nCategoria: " + categoria + "\nlocalServico" + "\nstatusServico: " + statusServico + "\nPreço: " + preco ;
    }
}

