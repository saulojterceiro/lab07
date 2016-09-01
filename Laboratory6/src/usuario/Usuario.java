package usuario;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;
import jogo.Jogo;

public class Usuario {

	public static final String FIM_DE_LINHA = System.lineSeparator();

	private String nome;
	private String login;
	private Set<Jogo> meusJogos;
	private double credito;
	private int xp2;
	private TipoUsuario t;

	public Usuario(String nome, String login,TipoUsuario t) throws StringInvalidaException {

		if (nome == null || nome.trim().isEmpty()) {
			throw new StringInvalidaException("Nome nao pode ser nulo ou vazio.");
		}
		if (login == null || login.trim().isEmpty()) {
			throw new StringInvalidaException("Login nao pode ser nulo ou vazio.");
		}

		this.nome = nome;
		this.login = login;
		meusJogos = new HashSet<Jogo>();
		this.t = t;
		this.credito = t.getCreditoInicial();
		
	}
	
	private void updateXp2(){
		if (this.xp2 >= 1000){
			this.t =  new TipoVeterano();
		}
	}
	
	public void compraJogo(Jogo jogo) throws Exception {
		double custo = jogo.getPreco() * t.getDesconto();
		if (custo > this.getCredito()) {
			throw new ValorInvalidoException("Crédito insuficiente para realizar a compra.");
		} else {
			int bonusXp = t.bonus(jogo.getPreco());
			this.aumentaXp2(bonusXp);
			setCredito(getCredito() - custo);
			this.cadastraJogo(jogo);
		}
		this.updateXp2();
	}

	public void setXp2(int novoValor) {
		this.xp2 = novoValor;
		this.updateXp2();
	}
	
	public void aumentaXp2(int bonus){
		this.xp2 = this.xp2 + bonus;
		this.updateXp2();
	}
	
	public void diminuiXp2(int bonus){
		this.xp2 = this.xp2 - bonus;
		this.updateXp2();
	}

	public int getXp2() {
		return this.xp2;
	}

	public void cadastraJogo(Jogo jogo) {
		this.meusJogos.add(jogo);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setCredito(double novoValor) {
		this.credito = novoValor;
	}

	public double getCredito() {
		return this.credito;
	}

	public void punir(String nomeJogo, int scoreObtido, boolean zerou)throws StringInvalidaException{
		Jogo jogo = this.buscaJogo(nomeJogo);
		if (jogo == null) {
			throw new StringInvalidaException("Erro com o jogo");
		}
		this.diminuiXp2(this.t.punirValor(jogo.getJogabilidade()));
		this.updateXp2();
		
	}
	
	public void recompensar(String nomeJogo, int scoreObtido, boolean zerou)throws Exception{
		Jogo jogo = this.buscaJogo(nomeJogo);
		if (jogo == null) {
			throw new StringInvalidaException("Erro com o jogo");
		}
		this.aumentaXp2(this.t.punirValor(jogo.getJogabilidade()));
		aumentaXp2(jogo.registraJogada(scoreObtido, zerou));
		this.updateXp2();
		
	}
	
	public Jogo buscaJogo(String nomeJogo) {
		Jogo buscado = null;
		Iterator itr = meusJogos.iterator();
		while (itr.hasNext()) {
			Jogo achado = (Jogo) itr.next();
			if (achado.getNome().equals(nomeJogo)) {
				buscado = achado;
			}
		}
		return buscado;
	}
	
	public Set<Jogo> getMeusJogos() {
		return meusJogos;
	}

	public void setMeusJogos(Set<Jogo> meusJogos) {
		this.meusJogos = meusJogos;
	}

	public double calculaPrecoTotal() {
		double total = 0;
		Iterator itr = meusJogos.iterator();
		while (itr.hasNext()) {
			Jogo achado = (Jogo) itr.next();
			total += achado.getPreco();
		}
		return total;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Usuario) {
			Usuario temp = (Usuario) obj;
			return this.getNome().equals(temp.getNome()) && this.getLogin().equals(temp.getLogin());
		} else {
			return false;
		}
	}
	
	public String toString() {
		String myString = this.getLogin() + FIM_DE_LINHA;
		myString += this.getNome() + t.stringTipoJogador()+ FIM_DE_LINHA;
		myString += "Lista de Jogos:" + FIM_DE_LINHA;

		Iterator itr = getMeusJogos().iterator();
		while (itr.hasNext()) {
			Jogo j = (Jogo) itr.next();
			myString += j.toString();
		}
		myString += FIM_DE_LINHA;
		myString += "Total de pre�o dos jogos: R$ " + this.calculaPrecoTotal() + FIM_DE_LINHA;
		myString += "--------------------------------------------";
		return myString;
	}
	
	
}
