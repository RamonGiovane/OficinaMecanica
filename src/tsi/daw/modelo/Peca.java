package tsi.daw.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

@Entity
public class Peca {
	
	@Id
	@SequenceGenerator(name="peca_id", sequenceName="peca_seq", allocationSize=1)
	@GeneratedValue(generator="peca_id", strategy=GenerationType.SEQUENCE)
	private Long id;
	private String nome, fornecedor, tipo, marca;
	private int quantidade;
	
	@Transient
	private int quantidadeARetirar = 1;
	
	public Long getId() {
		return id;
	}
	public void setIdpeca(Long idpeca) {
		this.id = idpeca;
	}
	public String getNome() {
		return nome;
	}
	public int getQuantidadeARetirar() {
		return quantidadeARetirar;
	}
	
	public void retirarPecas() {
		quantidade -= quantidadeARetirar;
	}
	
	public void setQuantidadeARetirar(int quantidadeARetirar) {
		this.quantidadeARetirar = quantidadeARetirar;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}
