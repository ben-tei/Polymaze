package model;

import model.business.Person;
import model.dao.DataBaseFacade;

public class UserManager
{
	private Person currentUser;

	public UserManager()
	{
		super();
		this.currentUser = null;
	}

	public boolean tryLogin(String login, String password)
	{
		Person tmp = DataBaseFacade.tryLogin(login, password);
		if(tmp != null)
		{
			this.currentUser = tmp;
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean createUser(String login, String password)
	{
		Person tmp = DataBaseFacade.createPerson(login, password);
		if(tmp != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public Person getCurrentUser()
	{
		return this.currentUser;
	}

}
