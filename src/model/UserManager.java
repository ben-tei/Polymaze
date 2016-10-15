package model;

import model.business.Person;
import model.dao.DataBaseFacade;

public class UserManager
{
	private Person currentUser_;

	public UserManager()
	{
		super();
		currentUser_ = null;
	}
	
	public boolean tryLogin(String login, String password){
		Person tmp = DataBaseFacade.tryLogin(login, password);
		if(tmp != null){
			currentUser_ = tmp;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean createUser(String login, String password){
		Person tmp = DataBaseFacade.createUser(login, password);
		if(tmp != null){
			return true;
		} else {
			return false;
		}
	}
	
	public Person getCurrentUser() {
		return currentUser_;
	}
	
	

}
