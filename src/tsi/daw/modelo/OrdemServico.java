package tsi.daw.modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class OrdemServico implements Comparator<OrdemServico>{
	@Id
	@SequenceGenerator(name="ordem_id", sequenceName="ordem_seq", allocationSize=1)
	@GeneratedValue(generator="ordem_id", strategy=GenerationType.SEQUENCE)
	private Long id;
	private Date dataOrcamento, dataServico;
	private String  servico, status;
	private double valor;
	
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="ordem", orphanRemoval=true)
	private List<PecaOrdem> pecasDaOrdem = new ArrayList<>();
	
	@OneToOne
	private Veiculo veiculo;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDataOrcamento() {
		return dataOrcamento;
	}
	public void setDataOrcamento(Date dataOrcamento) {
		this.dataOrcamento = dataOrcamento;
	}
	public Date getDataServico() {
		return dataServico;
	}
	public void setDataServico(Date dataServico) {
		this.dataServico = dataServico;
	}
	public String getServico() {
		return servico;
	}
	public void setServico(String servico) {
		this.servico = servico + "\n\n";
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	public List<PecaOrdem> getPecasDaOrdem() {
		return pecasDaOrdem;
	}
	public void setPecasDaOrdem(List<PecaOrdem> pecasDaOrdem) {
		this.pecasDaOrdem = pecasDaOrdem;
	}
	
	public String getDetalhesPecas() {
		StringBuilder strBuilder = new StringBuilder();
		for(PecaOrdem p : pecasDaOrdem)
			strBuilder.append(p.toString());
		
		return strBuilder.toString();
	}
	@Override
	public int compare(OrdemServico o1, OrdemServico o2) {
		return o1.dataOrcamento.compareTo(o2.dataOrcamento);
	}
	
}
