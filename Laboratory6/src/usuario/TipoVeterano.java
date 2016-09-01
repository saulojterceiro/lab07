package usuario;

import java.util.Set;

import jogo.Jogabilidade;

public class TipoVeterano implements TipoUsuario{

	public static final double DESCONTO_VETERANO = 0.8;
	
	public String stringTipoJogador(){
		return " - Jogador Veterano";
	}
	
	public double getDesconto(){
		return DESCONTO_VETERANO;
	}
	
	public int getCreditoInicial(){
		return 1000;
	}
	
	public int bonus(double preco){
		int parteInteira =(int)(preco - (preco % 1));
		int bonusXp =  (int)(parteInteira * 15);
		return bonusXp;
	}

	public int punirValor(Set<Jogabilidade> jogabilidade){
		int x=0;
		if (jogabilidade.contains("COMPETITIVO")){
			x = x + 20;
		}
		if (jogabilidade.contains("OFFLINE")){
			x = x + 50;
		}
		return x;
	}
	public int recompensarValor(Set<Jogabilidade> jogabilidade){
		int x=0;
		if (jogabilidade.contains("ONLINE")){
			x = x + 10;
		}
		if (jogabilidade.contains("COOPERATIVO")){
			x = x + 20;
		}
		return x;
	}
}
