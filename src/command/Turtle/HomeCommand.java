package command.Turtle;

import command.Command;
import turtle.Turtle;

public class HomeCommand implements Command{
	private Turtle myTurtle;
	
	public HomeCommand(Turtle turtle, double x, double y){
		myTurtle = turtle;
	}
	
	public double execute(){
		double delta = Math.sqrt(Math.pow(myTurtle.getX(), 2)+Math.pow(myTurtle.getY(), 2));
		myTurtle.setX(0);
		myTurtle.setY(0);
		return delta;
	}
	
}
