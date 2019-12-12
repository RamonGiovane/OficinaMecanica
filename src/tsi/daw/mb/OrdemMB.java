package tsi.daw.mb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import tsi.daw.dao.DAO;
import tsi.daw.modelo.OrdemServico;
import tsi.daw.modelo.Peca;
import tsi.daw.modelo.PecaOrdem;
import tsi.daw.modelo.Veiculo;

@SessionScoped
@ManagedBean
public class OrdemMB{
	private OrdemServico ordem = new OrdemServico();
	private List<Peca> pecas = new ArrayList<>();
	private List<Veiculo> veiculos = new ArrayList<>();
	private List<PecaOrdem> pecasDaOrdem = new ArrayList<>();
	private List<OrdemServico> ordens = new ArrayList<>(), ordensAprovadas =  new ArrayList<>();
	private int quantidadePeca = 1;
	private Long idPeca, idVeiculo;
	private String id;
	private Long idOrdem;
	private boolean ordemFinalizada = false;

	private PecaOrdem pecaOrdem = new PecaOrdem();

	public OrdemServico getOrdem() {
		return ordem;
	}

	public String grava(){
		DAO<OrdemServico> dao =  new DAO<>(OrdemServico.class);

		Veiculo veiculo = new DAO<>(Veiculo.class).buscaPorId(idVeiculo);
		ordem.setVeiculo(veiculo);
		ordem.setDataOrcamento(Calendar.getInstance().getTime());
		ordem.setStatus("Criada");

		dao.adiciona(ordem);
		this.ordem =  new OrdemServico();
		return "listar-ordens?faces-redirect=true";
	}


	public String formatarData(Date data){
		return new SimpleDateFormat("dd/MM/yyy HH:mm").format(data);
	}

	public void remove(OrdemServico ordem){
		DAO<OrdemServico> dao = new DAO<>(OrdemServico.class);
		dao.remove(ordem);
	}

	public void selecionarOrdem(){

		this.ordem = new DAO<>(OrdemServico.class).buscaPorId(idOrdem);

	}

	public void selecionarOrdem(OrdemServico ordem){
		this.ordem = ordem;
		System.out.println(this.ordem.getValor());
	}


	
	public String getDetalhesPecas() {
		String str = ordem.getDetalhesPecas();
		ordem = new OrdemServico();
		return str;
		
	}
	
	
	public void selecionarPeca(){
		PecaOrdem p = new PecaOrdem();
		Peca peca = new DAO<>(Peca.class).buscaPorId(idPeca);
		if(quantidadePeca < 1) quantidadePeca = 1;

		if(!pecaExisteNaOrdem(peca)){
			p.setPeca(peca);
			p.setQuantidade(quantidadePeca);
			p.setOrdem(ordem);
			ordem.getPecasDaOrdem().add(p);
			pecas.remove(peca);
		}
		quantidadePeca = 1;

	}
	
	public boolean pecaExisteNaOrdem(Peca peca) {
		/*for(PecaOrdem o : ordem.getPecasDaOrdem())
			if(o.getPeca().getNome() == peca.getNome())
				return true;*/
		return false;
	}


	public void desconsiderarPeca(PecaOrdem p){
		ordem.getPecasDaOrdem().remove(p);
		pecas.add(p.getPeca());
	}

	public void aprovar(OrdemServico o){
		DAO<OrdemServico> dao = new DAO<>(OrdemServico.class);
		o.setStatus("Aprovada");
		dao.altera(o);
	}

	public void concluir(OrdemServico o){
		DAO<OrdemServico> dao = new DAO<>(OrdemServico.class);
		o.setStatus("Concluída");
		o.setDataServico(Calendar.getInstance().getTime());
		dao.altera(o);
	}

	public String finalizarPagamento(){
		ordem.setStatus("Paga");
		ordemFinalizada = true;
		DAO<OrdemServico> dao = new DAO<>(OrdemServico.class);
		dao.altera(ordem);
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();
		return "recibo?faces-redirect=true";
	}


	public void resetar(){
		this.ordem = new OrdemServico();
	}
	public void setOrdem(OrdemServico ordem) {
		this.ordem = ordem;
	}

	public List<Peca> getPecas() {
		DAO<Peca> dao = new DAO<>(Peca.class);
		pecas = dao.listaTodos();
		return pecas;	
	}

	public void setPecas(List<Peca> pecas) {
		this.pecas = pecas;
	}

	public int getQuantidadePeca() {
		return quantidadePeca;
	}

	public void setQuantidadePeca(int quantidadePeca) {
		this.quantidadePeca = quantidadePeca;
	}

	public Long getIdPeca() {
		return idPeca;
	}

	public void setIdPeca(Long idPeca) {
		this.idPeca = idPeca;
	}

	public PecaOrdem getPecaOrdem() {
		return pecaOrdem;
	}

	public void setPecaOrdem(PecaOrdem pecaOrdem) {
		this.pecaOrdem = pecaOrdem;
	}

	public List<PecaOrdem> getPecasDaOrdem() {
		return pecasDaOrdem;
	}

	public void setPecasDaOrdem(List<PecaOrdem> pecasDaOrdem) {
		this.pecasDaOrdem = pecasDaOrdem;
	}

	public Long getIdVeiculo() {
		return idVeiculo;
	}

	public void setIdVeiculo(Long idVeiculo) {
		this.idVeiculo = idVeiculo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Veiculo> getVeiculos() {
		DAO<Veiculo> dao = new DAO<>(Veiculo.class);
		veiculos = dao.listaTodos();
		return veiculos;
	}

	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}

	public List<OrdemServico> getOrdens() {
		DAO<OrdemServico> dao = new DAO<>(OrdemServico.class);
		ordens = dao.listaTodos();
		return ordens;
	}

	public void setOrdens(List<OrdemServico> ordens) {
		this.ordens = ordens;
	}

	public Long getIdOrdem() {
		return idOrdem;
	}

	public void setIdOrdem(Long idOrdem) {
		this.idOrdem = idOrdem;
	}

	public List<OrdemServico> getOrdensAprovadas() {
		ordensAprovadas.clear();
		DAO<OrdemServico> dao = new DAO<>(OrdemServico.class);
		List<OrdemServico>l = dao.listaTodos();
		for(int i=0; i<l.size(); i++)
			if(l.get(i).getStatus().equals("Concluída"))
				this.ordensAprovadas.add(l.get(i));


		return ordensAprovadas;
	}


	public void setOrdensAprovadas(List<OrdemServico> ordensAprovadas) {
		this.ordensAprovadas = ordensAprovadas;
	}

	public boolean isOrdemFinalizada() {
		return ordemFinalizada;
	}

	public void setOrdemFinalizada(boolean ordemFinalizada) {
		this.ordemFinalizada = ordemFinalizada;
	}


}
