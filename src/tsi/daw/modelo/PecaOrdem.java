package tsi.daw.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class PecaOrdem {
	@Id
	@SequenceGenerator(name="peca_ordem_id", sequenceName="peca_ordem_seq", allocationSize=1)
	@GeneratedValue(generator="peca_ordem_id", strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@ManyToOne
	private OrdemServico ordem;
	
	@ManyToOne
	private Peca peca;
	
	private int quantidade;
	
	public Peca getPeca() {
		return peca;
	}
	public void setPeca(Peca peca) {
		this.peca = peca;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public OrdemServico getOrdem() {
		return ordem;
	}
	public void setOrdem(OrdemServico ordem) {
		this.ordem = ordem;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n");
		builder.append(peca.getNome());
		builder.append("\t x ");
		builder.append(quantidade);
		return builder.toString();
	}
	
	
}
