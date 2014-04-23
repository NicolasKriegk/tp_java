package interface_web_service;


import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace="http://webclass")
public interface IMonService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bankonet.ws.MonServiceInterface#saluer()
	 */
	@WebMethod
	public abstract String methode();

}