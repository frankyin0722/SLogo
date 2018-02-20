SLogo Plan
====  

## Introduction   
We aim to write a flexible IDE for a simplified version of the Logo language. Our goal is for a user to have the ability to draw and store information using commands entered into our console window. We hope to display these drawings in a canvas, updated at every command, adjacent to the console. We also intend to allow for customization through use of buttons; these will be visible on-screen adjacent to the canvas window and console.   

Flexibility will be most emphasized in our commands -- while we will have built-in a variety of commands available, a user will be easily able to add their own instructions for turtle movement and information storage, without disrupting the functionality of the rest of our program.   

Our front-end functionality as a whole will remain relatively closed, as a user should have little reason to modify it. That said, if a user wants to add additional features to the visibility, that will be readily possible through extension of our customization buttons.   

Our back-end is intended to be relatively closed as well, though extension here will be possible through addition of commands, addition of variable types, and addition of turle properties.   

## Design Overview   
The internal API for back end focuses on the interpretation of the root CommandTreeNode into sub-commands that can be initialized and executed during the interpretation process by our program. By our design, command is divided into four categories (turtle command, math command, boolean command, and control command), under which every specific command is extended from an abstract Command class. If a command needs to be executed according to the interpreter, then a Command class instance is created based on the CommandType string that the node contains and the parameter list it associates with. The internal API for back end also focuses on the communications between the commands and other back end classes. For instance, a turtle command needs to interact with the turtle class to change its position, direction, or pen status. A control command (specifically, the "make variable" command) needs to interact with the VariableManager class to initialize, modify or remove variables. 

The external API for back end primarily focuses on the processing of the user input command from a String to a CommandTreeNode that contains all the necessary information the TreeNodeInterpreter needs to unravel its logic and execute its sub-commands. The parse method of the Parser class is called by front end whenever the user inputs the command. It also includes necessary methods for the user to modify visual traits on backend objects like turtle and pen. 

The internal API for front end focuses on GUI-specific interactions. It is what gives the user the capability to change visuals, such as turtle image, as well as giving the user the ability to review information, from previous commands to current variables. Specific classes will include Canvas, which holds and displays the image created by the turtle; MenuManager, which creates pop-up menus displaying listed information; OptionManager, which creates buttons allowing a user to customize the image displayed in the canvas; Console, which works as a command window for users to input turtle commands; and Visualization, which launches the image in the canvas.

Currently there is no external front-end API, because there is no call provided by the front-end. 

## User Interface   
The user interacts with the program through an interface that includes the (A) canvas, where the turtle and its path are visualized (B) console, where user inputs commands, and (C ) control panel, which includes customizable options, active variables, and past history.

![](https://i.imgur.com/5023Mzj.jpg)

The user types commands in the console, and at the press of a "run" button or "enter" key, the commands are executed. The commands are then visualized on the canvas window with movement of the turtle illustrated by a line (drawn by pen). In addition, the user can customize settings such as pen color, turtle image, background color, and system language. In addition, the menus for command history, active variables, and a help screen with available commands will be accessible with the help button. These settings will be featured in the control panel as a series of images, with the relevent selection menu appearing if the user clicks on the image. 

Currently the user does not directly interact with the canvas, although we will make our design flexible enough to incorportate this option.

Alerts will be displayed to the user with text at the top of the screen, in the corner ofthe canvas. Input error such as nonexistent, overly-large, or empty commands will trigger specific alerts, and halt the current program.

## API Details   
#### Internal API Details (Front End)
```java 
/**
* Initializes a design window for turtle movement and drawing
* Closes canvas from rest of interface, especially as canvas
* currently does not communicate with back-end while some other front-end classes do.
*/
public interface Canvas {
}

/**
* Initializes a Console, effectively a command window for a user
* to input turtle commands. 
* Closes console from rest of interface.
*/
public interface Console {    
    // method to pass commands typed in input area to parser
    public void read();
}

/**
* Initializes a control panel, which serves as a space for OptionManager
* buttons. Closes panel of buttons from rest of interface.
*/
public interface ControlPanel {
}

/**
* Allows a user to access a list of information relevant to their query. 
* Examples of a MenuManager extension are 
* (a) helper menu, allowing a user to see a list of possible commands; 
* and (b) history menu, allowing a user to see their most recent commands.
*/
public interface MenuManager {
}

/**
* Allows a user to customize drawing options. 
* Effectively a button, an extension of OptionManager would 
* display an ImageView object corresponding to the OptionManager 
* action (e.g. a pen image for changing the pen color). Clicking 
* on it would enable the user to complete that action.
*/
public interface OptionManager {
}

/**
* Displays user-interface screen containing
* (1) canvas
* (2) console
* (3) options & menus
* Initialized by Engine with primary stage
* This section is separate to allow for separate/multiple screens
*/
public interface Visualization {
}
```

#### External API Details (Front End)   
```java
// none as of yet
```
#### Internal API Details (Back End)   
```java
public interface Main {
    //this method initializes an engine and prepares the IDE for launch
    public void start(Stage initialStage);
    
    //this method launches the IDE
    public static void main(String[] args);
}

/**
* This Parser interface implements the method to parse the user input string to a CommandTreeNode root and returns it to the CommandTreeInterpreter. 
*/
public interface Parser {
    // this method exports the parsed result to the CommandTreeNode class, which can be interpreted and executed by the TreeInterpret class
    public CommandTreeNode export();
}

/**
* This CommandTreeInterpreter interface implements the method to visit all the CommandTreeNodes contained in the root, initialize all sub-commands, and executes them in the right order.
*/
public interface CommandTreeInterpreter{
    // this method interprets the command root, unravels its logic and executes all sub-commands 
    public void interpretCommandTree();

}

/**
* This CommandManager interface defines a type of Command class based on the information contained in the currently-executing command. 
*/
public interface CommandManager {
    // this method returns a class type that is used to create commands 
    public Class<?> createCommand();
}


/**
* This Command interface defines the tasks each type of Command should carry out and its returned value. 
*/
public interface Command {
    // this method is called by back end to execute the command and to return 
    public double calculateValue();
}


/**
*
*/
public interface Variable {
    // gives the type of the current variable
    public String getType();
    
    // returns the stored value 
    public Object getValue();
    
    //changes value to new value, throws TypeException if the type doesn't match the variable type. 
    public void setValue(Object newValue) throws TypeException;
}

/**
* Manages a map of current variables to their names
*/
public interface VariableManager {
    //returns the variable with a given name, or NoSuchVariableException if it doesn't exist.
    public Variable getVariable(String name);
    
    //adds a new variable. returns DuplicatedVariableException if the variable already exists
    public void addVariable(Variable var);
    
    //changes the variable that matches the name
    public void setVariable(Object data);
}

/**
* Controls the pen a turtle has
*/
public interface Pen {
    //returns if the pen is up
    public boolean PenUp();
    
    //sets pen to up for true, down for false
    public void setPen(boolean penUp);
    
    //sets pen color
    public void setColor(Color color);
    
    //returns color of the pen
    public Color getColor();
}
```



#### External API Details (Back End)
```java
/**
* Processes user input
*/
public interface Parser {
    // this method is called by the front end to parse the user input (unprocessed commands)
    public void parse(String rawcommand);
}

/**
*  Engine is the highest level program that initializes all relevent classes
*/
public interface Engine {
    //this method initializes the IDE, preparing each of the visual and back-end components necessary for use
    public void initializeIDE(Stage stage)
}

/**
* The turtle, the main object, which moves draws and turns 
*/
public interface Turtle extends ImageView {
    //returns the turtle's pen
    public Pen getPen();
    
    //updates turtle's position, returns line that should be drawn, or null if no line should be drawn
    public Line update();
    
    //check the direction the turtle is facing in radians
    public double getDirection();
    
    //change the turtle's bearing
    public void setDirection(double direction);
}
```



## API Example Code   

To move a turtle forward 50:
* User enters "fd(turtle, 50)" into the console.
* This command is stored by console, accessible 
* Parser.parse("fd 50") returns a CommandTreeNode "root" that contains the forwardCommand(50)
* CommandTreeInterpreter.interpretCommandTree(root) interprets the root and creates the forwardCommand(50)
* The command forwardCommand(50) uses trig to figure out what to do with the turtle, then turtle.setX(xdelta), turtle.setY(ydelta)
* If the pen is down, the turtle draws a line and adds it to the group.
* Turtle is now displayed at new location on the canvas; if the pen was down, there is also a visible line.

![](https://i.imgur.com/BtPkj73.jpg)

* Use Cases
    * Xiaolan:
        * Change pen color
            * User clicks "change pen color" button, which will have a picture of a pen, and a display menu appears. The user selects a color, and all lines change to the chosen color.
        * Adding new option
            * Create new pen color option with OptionManager, with button image, text, and selection.
            * Add button to ControlPanel with addButton(penColorObjectManager);
    * Elizabeth
        * Launch program
            * public static void main(String[] args) {
		    &ensp;&ensp;launch(args);
	            }
        * Access history menu
            * user clicks Command-h
    * Frank
        * typing nested commands and getting the final result according to the exact control structure 
            * Parser.parse(REPEAT times [REPEAT times [a.fd(50)]]);
            * CommandTreeNode myRoot = Parser.export();
            * CommandTreeInterpreter.interpretCommandTree(myRoot);
        * opeating math and/or logic operations for the user upon request
            * Parser.parse(SUM expr1 expr2);
            * CommandTreeNode myRoot = Parser.export();
            * CommandTreeInterpreter.interpretCommandTree(myRoot);
    * Samson
        * adding a new int variable called size, with value 3
            * map.add("size", IntVariable(3));
        * moving the turtle 50 in the x direction
            * turtle.setX(turtle.getX() + 50);
            * turle.update()

## Design Considerations

Our three major design considerations were:
1. Structuring commands to be classes or methods.
2. Centralized vs decentralized storage of information
3. Structure/order of parsing

First, we considered how to structure command calls—whether they were called by a turtle (turtle.fd(50)) or on a turtle (fd(turtle, 50)). The first approach seems more intuitive, but requires all the commands to be implemented within the turtle. The second approach is easier to implement on different turtles and across multiple commands with reflection, but requires the parser to access extra information (active turtle). In the end we decided on the second implementation with a syntax change, for its flexibility in adding methods. Moving forward 50 would be input as "fd 50", on the active turtle "a", and this input would be translated to fd(a, 50). This way the user could easily change active turtles, and the code maintainer could easily add new commands. 

Second, another design consideration was where data such as turtle x should go. It makes some sense to put in with a front end class, as the front end displays the turtle. At the same time, it also makes sense for backend to keep track of it, as the backend is what actually modifies the turtle's x position. We decided that no one object should hold directly onto all the data. Instead a VariableManager object will hold onto the current variables, and turtle and pen will each track their own data. That way, commands can modify the turtle, and frontend can have the turtle displayed correctly simulataneously, by having the necessary objects in Main's group. 

Our last important design consideration was the way the user input shall be processed. One choice is to have a CommandManager that takes in the parsed command and processes it to get all sub-commands as its instance variable and execute them in the right order. However, it is very hard to implement and its flexibility is limited to a certain extent. Another choice is to parse the user input command as a CommandTreeNode "root" that contains all the necessary information for the entire command to process. Then, a CommandTreeInterpreter visits the root and its children and executes the sub-commands in a particular order (the inner-most node that contains one single sub-command is executed first). The execution of sub-commands continues during the interpretation process and finishes as the process terminates.   

## Team Responsibilities

### Front End
#### Xiaolan  
* Visualization
    * Class that creates screen that the user will see. Will encompass canvas, control panel, and console
* ControlPanel
    * Manages specific location and orientation of canvas, console, customization options, and menus on the screen.
* Canvas
    * Visually represents the turtle and its path
* Console
    * Accepts user command input, and passes text to parser at program run

#### Elizabeth  
* Main
    * Highest level of the program. Extends Application; creates an instance of the Engine class; and launches the program.
* Engine
    * Initializes and manages the high-level game objects. Also responsible for managing interactions between the front-end and back-end of our IDE.
* OptionManager
    * Example extensions: PenOption, BackgroundOption, TurtleImageOption
* MenuManager
    * Example extensions: HelperMenu, HistoryMenu


### Back End
#### Samson
* Variable 
    * inheritance hierachy
    * contains type (currently only int), value
    * will have IntVariable, StringVariable
* VariableManager
    * contains all the variables, addVariable, getVariable
* Turtle
    * controls its position, writes lines, has its own pen
* Pen
    * attributes: penup vs down, color, width
#### Frank 
* CommandManager
    * contains only the createCommand method that returns a class with respect to the command name

* CommandTreeInterpreter
    * contains variables, turtles, command manager and all necessary classes for the execution of the command
    * contains interpretCommandTree method that loops through all CommandTreeNode and its children, initializes specific sub-commands when each node is checked out, and executes them in the order they are unravelled 
    * contains update method that takes in both the CommandTreeNode and its parameters and passes in corresponding parameters based on the type of the command for the executeCommand operation 
    * contains the executeCommand method that is called by the interpretCommandTree method as the interpreting process continues

* Command 
    * contains the calculateValue method that carries out specific tasks the command is designed for and returns the right value after its execution
 
* Parser
    * parses the user input to CommandTreeNode forms that a specfic type of Command can take in
    * returns the processed CommandTreeNode root to the CommandTreeInterpreter 