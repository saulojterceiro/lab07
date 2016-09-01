package usuario;

import java.util.Set;

import jogo.Jogabilidade;

public interface TipoUsuario {

	public double getDesconto();
	
	public int bonus(double preco);
	
	public int getCreditoInicial();
	
	public String stringTipoJogador();
	
	public int punirValor(Set<Jogabilidade> jogabilidade);
	public int recompensarValor(Set<Jogabilidade> jogabilidade);
	
}
