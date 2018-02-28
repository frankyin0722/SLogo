package command.Turtle;

import command.Command;
import turtle.Turtle;

public class BackwardCommand implements Command{
	private Turtle myTurtle;
	private double myMovement;
	
	public BackwardCommand(double movement, Turtle turtle){
		myTurtle = turtle;
		myMovement = movement;
	}
	
	public double execute(){
		myTurtle.changeX(myTurtle.getX() - Math.sin(myTurtle.getDirection())*myMovement);
		myTurtle.changeY(myTurtle.getY() + Math.cos(myTurtle.getDirection())*myMovement);
		myTurtle.update();
		return myMovement;
	}
	
}
