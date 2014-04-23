package webclass;

import interface_web_service.IMonService;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class MonService implements IMonService{
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bankonet.ws.MonServiceInterface#saluer()
	 */
	/* (non-Javadoc)
	 * @see webClass.IMonService#methode()
	 */
	@Override
	@WebMethod
	public String methode() {
		return "Bonjour";
	}
}
	