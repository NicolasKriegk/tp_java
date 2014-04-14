import java.security.InvalidParameterException;
import java.util.List;


public class Distributeur {
	
	private Compartiment compartiment;
	
	
	public Distributeur()
	{
		compartiment = new Compartiment100();
	}
	
	
	
	
	public String retirer(int somme)
	{
		if (somme%10 != 0)
		{
			throw new IllegalArgumentException("La somme doit être divisible par 10");
		}
		else 
		{
			StringBuilder resultat = new StringBuilder();
			return this.compartiment.rendreSomme(somme, resultat);
		}
	}

}
