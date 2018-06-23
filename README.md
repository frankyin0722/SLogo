
# SLogo README.md

#### Samson Rao, Elizabeth Shulman, Frank Yin, Xiaolan You

2/16/2018 - 3/9/2018  
Hours worked: ~120

## Roles
#### Samson Rao
* Setting up the support architecture for the backend
    * Turtle related classes (TurtleController, Turtle, Pen)
    * Variable related classes (Variable, Variable Manager)
    * Non-Control, non-user defined commands(Math, Turtle, etc...)
    * Saving and loading files
* Setting up the error system
    * Alerts 

#### Elizabeth Shulman:
* Setting up front-end menus to represent back-end logic, specifically:
    * Building a command window and sending text to the parse
    * Managing modifications to a turtle's pen
    * Storing, presenting, and allowing resubmission of history
    * Storing, presenting, and allowing modifications of variables

#### Frank Yin:
* Setting up the parser and interpreter logic and related classes for the backend 
    * Parser and Parser-related classes (CommandTypes and its extended classes)
    * CommandTreeInterpreter class 
* Creating the following types of commands for the backend
    * Control commands 
    * Multiple Turtle command implementation 
    * User-defined commands 
* Adding some complete implementation features 
    * Recursion
    * Grouping Syntax

#### Xiaolan You:
* Set up front-end organization with Elizabeth
* Created menus to exchange information between front-end (user interaction) and back-end:
    * Process user modifications
        * Entering and executing commands
        * Changing language
        * Changing active turtle avatar
    * Process back-end information
        * Display dynamically updating turtle/pen information
        * Storing, presenting, and allowing calling of user defined commands
        * Update new turtles/turtle status in front-end
    * Facilitate direct user interaction with GUI
        * Allow turtles to be visibly activated/deactived upon click
        * Allow user to directly manipulated turtle location and direction

## Resources Used
Any books, papers, online, or human resources that you used in developing the project  
* Diane Hu
* Previous projects (notably cell society)
* group 2's game of life simulation
* Image sources:
    * http://sweetclipart.com/multisite/sweetclipart/files/turtle_green.png
    * https://twitter.com/brookeflow/status/527882620131348480
    * https://users.cs.duke.edu/~rcd/
    * https://venustygg.deviantart.com/art/VIXX-2-byvenus-631648883
    * Unnamed Snapchat photographer in CS308 class

## Using the Program
#### Running the Program
* File used to start SLogo IDE: Main
* Files used for testing: Parser Tester, example files in data folder
#### Errors
* Known bugs: double precision, history menu only executed English commands
* Errors program should handle without crashing:
    * Invalid color palette resource file
    * Invalid command entered
    * Correct command with invalid syntax
    * Using a defined but not implemented command

#### Resource Files
* Color Palette
    * Map key to Color.PAINT value
* Language
    * Map key to command syntax
* Image file
    * File of available turtle icon images

#### Easter Eggs, Key Inputs, and Interesting Files
Any information about using the program (i.e., command-line/applet arguments, key inputs, interesting example data files, or easter eggs)

* We successfully adapted game of life to work in our version of slogo

## Design Decisions and Assumptions
Any decisions, assumptions, or simplifications you made to handle vague, ambiguous, or conflicting requirements
* The quotient command was decided to be double division not integer division after some discussion
* Turtle queries are considered control as they rely on knowing id
* Tell initializes all turtles up to n, but only n is viewed as active
* Previously defined but unimplemented user-defined commands, upon called, throw an alert for user to notify its lack of sub-commands
* Users can repetitively define commands and override them as long as they do not conflict with already-existing commands (default commands)
* For multiple turtle commands, the variable's value is evaluated each time FD is run (e.g., fd set :x + :x 10 allows all turtles to move different distances because the value of :x gets re-evaluated for each turtle's FD and increments 10 for any FD execution)


## Impressions of the Assignment
Your impressions of the assignment to help improve it in the future
* Samson Rao
    * I wonder if the project could have been more broad and inclusive. 
    * I feel that most extensions were much more tractable, and that harder things like new variable types (maybe variables that have to be int?  Or string variables), different types of objects (maybe a group of turtles that are bound together but can have multiple pens)  
    
* Elizabeth Shulman
    * I really enjoyed this project, although at times it felt like the extensions (for front end, at least) only worked to reinforce my knowledge of specific JavaFX methods. All in all, though, I greatly enjoyed working through this assignment. In particular, I liked the intra-group division between front-end and back-end, and I liked how much the two halves could overlap at times.

* Frank Yin
    * There are differently more extensions to this basic version of SLogo that can be developed, which I find quite interesting and challenging. For instance, instead of implementing only "tail recursion", the SLogo can implement the recursion that people are more familiar with by saving local copies of parameters and restore them when the recursion method returns. 
    * More types of return values could be more challenging and definitely more fun to implement, as it allows SLogo to do more cool things and provides more flexibility for future extensions. I just wish that future CS308 students would have the precious opportunity to gain such an experience.  

* Xiaolan You
    * I found the project topic really facinating and rewarding to work on! Especially with it being my first time on front-end, I really enjoyed the experience
    * The following extension were enjoyable to implement
        * Direct user interaction with GUI
        * Directly editing values of variables
        * Click to execute commands from menus
    * These extensions were enjoyable in that I carefully considered design principles when creating them (e.g. making DraggableImageView that would take care of the mouse events for rotation. This is an example of object oriented programming because the DraggableImageView can self-contain these properties without influence from other classes). I would like to see more of these types of extensions.
    * Other extensions were grindy. I would prefer fewer of them in basic extension, to have the time to implement advanced extensions instead.
    * I hope future extensions will our own custom features (zoom?? "Feeling Lucky" turtle image selection?) to promote creative thought and leave our own brand on the project!

