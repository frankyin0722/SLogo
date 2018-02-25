package command.Turtle;

import command.Command;
import turtle.Turtle;

public class HomeCommand implements Command{
	private Turtle myTurtle;
	
	public HomeCommand(Turtle turtle){
		myTurtle = turtle;
	}
	
	public double execute(){
		double delta = Math.sqrt(Math.pow(myTurtle.getX(), 2)+Math.pow(myTurtle.getY(), 2));
		myTurtle.changeX(0);
		myTurtle.changeY(0);
		myTurtle.update();
		return delta;
	}
	
}
