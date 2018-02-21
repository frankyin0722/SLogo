package command.Turtle;

import command.Command;
import turtle.Turtle;

public class SetPositionCommand implements Command{
	private Turtle myTurtle;
	private double newX;
	private double newY;
	
	public SetPositionCommand(Turtle turtle, double x, double y){
		myTurtle = turtle;
		newX = x;
		newY = y;
	}
	
	public double execute(){
		double delta = Math.sqrt(Math.pow(newX - myTurtle.getX(), 2)+Math.pow(newY - myTurtle.getY(), 2));
		myTurtle.setX(newX);
		myTurtle.setY(newY);
		return delta;
	}
	
}
