package command.Turtle;

import command.Command;
import turtle.Turtle;

public class SetPositionCommand implements Command{
	private Turtle myTurtle;
	private double newX;
	private double newY;
	
	public SetPositionCommand(double x, double y, Turtle turtle){
		myTurtle = turtle;
		newX = x;
		newY = -1 * y;
	}
	
	public double execute(){
		double delta = Math.sqrt(Math.pow(newX - myTurtle.getX(), 2)+Math.pow(newY - myTurtle.getY(), 2));
		myTurtle.changeX(newX);
		myTurtle.changeY(newY);
		myTurtle.update();
		return delta;
	}
	
}
