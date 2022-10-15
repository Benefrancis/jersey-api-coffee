package br.com.fiap.tads.ddd.coffee.model;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;


public class Coffee {

	private Long id;

	@NotBlank(message = "O nome não pode estar em branco.")
	private String nome;

	@NotNull(message = "O preço deve ser informado.")
	@PositiveOrZero(message = "O preço deve ser maior ou igual a zero.")
	private Double preco;

	@NotNull(message = "A data de fabricacao deve ser informada.")
	@PastOrPresent(message = "A data de fabricação deve ser menor ou igual a hoje.")
	private LocalDate dataDeFabricacao;

	@NotNull(message = "A data de validade deve ser informada.")
	@FutureOrPresent(message = "A data de validade não pode ser anterior a data de hoje.")
	private LocalDate dataDeValidade;

	public Coffee() {
		super();
	}

	public Coffee(Long id, @NotBlank(message = "O nome não pode estar em branco.") String nome,
			@NotNull(message = "O preço deve ser informado.") @PositiveOrZero(message = "O preço deve ser maior ou igual a zero.") Double preco,
			@PastOrPresent(message = "A data de fabricação deve ser menor ou igual a hoje.") LocalDate dataDeFabricacao,
			@FutureOrPresent(message = "A data de validade não pode ser anterior a data de hoje.") LocalDate dataDeValidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.dataDeFabricacao = dataDeFabricacao;
		this.dataDeValidade = dataDeValidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public LocalDate getDataDeFabricacao() {
		return dataDeFabricacao;
	}

	public void setDataDeFabricacao(LocalDate dataDeFabricacao) {
		this.dataDeFabricacao = dataDeFabricacao;
	}

	public LocalDate getDataDeValidade() {
		return dataDeValidade;
	}

	public void setDataDeValidade(LocalDate dataDeValidade) {
		this.dataDeValidade = dataDeValidade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataDeFabricacao, dataDeValidade, id, nome, preco);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coffee other = (Coffee) obj;
		return Objects.equals(dataDeFabricacao, other.dataDeFabricacao)
				&& Objects.equals(dataDeValidade, other.dataDeValidade) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome) && Objects.equals(preco, other.preco);
	}

	@Override
	public String toString() {
		return "coffee [id=" + id + ", nome=" + nome + ", preco=" + preco + ", dataDeFabricacao=" + dataDeFabricacao
				+ ", dataDeValidade=" + dataDeValidade + "]";
	}

}
