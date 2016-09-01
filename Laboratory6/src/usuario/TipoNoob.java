package usuario;

import java.util.Set;

import excecoes.StringInvalidaException;
import jogo.Jogo;
import jogo.Jogabilidade;

public class TipoNoob implements TipoUsuario{
	public static final double DESCONTO_NOOB = 0.9;
	
	
	public String stringTipoJogador(){
		return " - Jogador Noob";
	}
	
	public int getCreditoInicial(){
		return 0;
	}
	
	public double getDesconto(){
		return DESCONTO_NOOB;
	}
	
	public int bonus(double preco){
		int bonusXp =  (int)(preco * 10);
		return bonusXp;
	}
	
	public int punirValor(Set<Jogabilidade> jogabilidade){
		int x=0;
		if (jogabilidade.contains("ONLINE")){
			x = x + 10;
		}
		if (jogabilidade.contains("COMPETITIVO")){
			x = x + 20;
		}
		if (jogabilidade.contains("COOPERATIVO")){
			x = x + 50;
		}
		return x;
	}
	
	public int recompensarValor(Set<Jogabilidade> jogabilidade){
		int x=0;
		if (jogabilidade.contains("OFFLINE")){
			x = x + 30;
		}
		if (jogabilidade.contains("MULTIPLAYER")){
			x = x + 10;
		}
		return x;
	}



}
