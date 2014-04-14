
public abstract class Compartiment {
	
	private int stock;
	private int valeur;
	private Compartiment compartimentSuivant;
	//private Compartiment compartimentSuivant = new CompartimentNull();
	
	
	public Compartiment()
	{
		
	}
	
	public Compartiment(int stock, int valeur, Compartiment compartiment)
	{
		this.stock = stock;
		this.valeur = valeur;
		this.compartimentSuivant = compartiment;
	}

	
	public final String rendreSomme(int somme, StringBuilder resultat)
	{
		
		
		int reste = somme;
		int quantite = 0;
		
		while(reste >= valeur)
		{
			if(stock > 0)
			{
				reste -= valeur;
				quantite++;
				stock--;
			}
		}
		

			
		resultat.append(quantite);
		resultat.append(" billets de ");
		resultat.append(valeur);
		resultat.append(".\n");
		

		if (reste == 0)
		{
			return resultat.toString();
		}
		else if (compartimentSuivant != null)
		{
			return compartimentSuivant.rendreSomme(reste, resultat);
		}
		else 
		{
			return "Pas assez de billets";
		}
	}
	
	
	
	
	
	
	
	/*
	
	private class CompartimentNull extends Compartiment
	{
		
		public String rendreSomme(int somme, StringBuilder resultat)
		{
			return resultat.toString();
		}
		

	}
*/
}
