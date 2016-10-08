package main;

import controller.UIController;
import view.UIView;

public class Main
{

	public static void main(String[] args)
	{
		UIController uiController = new UIController();

		new UIView(uiController);

	}

}
