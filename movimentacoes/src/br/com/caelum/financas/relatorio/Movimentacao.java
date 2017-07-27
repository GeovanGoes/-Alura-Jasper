package br.com.caelum.financas.relatorio;

import java.math.BigDecimal;
import java.sql.Date;

public class Movimentacao {

	private Integer id;
	private Date data;
	private String descricao;
	private String tipoMovimentacao;
	private BigDecimal valor;
	private long categoria_id;
	private long conta_id;

	public Movimentacao(Integer id, Date data, String descricao,
			String tipoMovimentacao, BigDecimal valor, long categoria_id,
			long conta_id) {
				this.id = id;
				this.data = data;
				this.descricao = descricao;
				this.tipoMovimentacao = tipoMovimentacao;
				this.valor = valor;
				this.categoria_id = categoria_id;
				this.conta_id = conta_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(String tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public long getCategoria_id() {
		return categoria_id;
	}

	public void setCategoria_id(long categoria_id) {
		this.categoria_id = categoria_id;
	}

	public long getConta_id() {
		return conta_id;
	}

	public void setConta_id(long conta_id) {
		this.conta_id = conta_id;
	}
}
