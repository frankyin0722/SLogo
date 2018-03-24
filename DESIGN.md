DESIGN.MD
===
## High-level design goals
Our project goal is to create a program that simulates SLogo commands on a turtle graphic in a customizable setting. 

At the highest level, our program implements MVC design pattern. 
1. User -> Controller: the user provides input in the front end (e.g. calling on a turtle image in a menu, or entering commands in the console).
2. Controller -> Model: this information is then interpreted by the backend, either dynamically (e.g. a mouse click event handler on the front-end might reference the back-end external API to directly call changeImage() on turtle), or through use of the parser and interpreter to interpret commands and update turtles. 
3. Model -> View: our view components dynamically updates and displays the results on front-end (e.g. a turtle moves to its updated location, or a turtle's avatar changes).
4. View -> User: user observes new state, and the program is ready for the next interations starting with (1) user -> controller

## Adding new features
* New language
    * Add a properties file for the language to resources.languages. Use English for the property names, and the desired language for the property values.
* New turtle image
    * Add the image to resources.images.
* New color palette
    * Add a properties file for the palette to resources.colors. Use the text you would like displayed as the property name, and the CSS color name as the property value.
* New command
    * Add new command subclass with name and effect
    * Add command to the desired language properties files, using corresponding regular expression
* New UI
	* Navigate to the existing menu/layout in front end
		* Within view/vis_elements, navigate to either ControlPanelLeft or ControlPanelRight, depending on the desired location of the object
	* Instantiate the object within the initializeMenus() method
		* e.g. this.getChildren().addAll(new ExampleObject());
	* Include valid references to information it needs or modifies 
		* e.g. if the new object needs active turtle location to transport turtle x paces away, include a reference to TurtleController.
## Major design choices


#### Decision 1. Command structure: classes vs. methods

One of our first design conversations was focused on how we should build each command -- should they be stored as individual methods within the Turtle class, or should they be their own classes?  

Our first consideration was on the structure of our calls to specific commands, specfically whether commands would be called by a turtle,
```
turtle.fd(50)
```
or on a turtle,
```
fd(turtle, 50)
```
when we modified a user's command window text for parsing.  

The former approach seemed more intuitive syntactically to us, though it would require the turtle to store each command implementation within itself. The latter approach seemed easier in some aspects of implementation (e.g. we could use reflection to access each command), though it would require our Parser to access information about the state of turtles on our canvas.  

Most importantly, the second approach seemed significantly more extendable: Rather than altering the Turtle class itself, which would ideally be closed to modification, a user would be able to build new commands with minimal contact with existing code. Limiting what interactions are necessary between extension and existing code would be safer, as well as easier for debugging.  

Ultimately, it became an easy decision to use separate classes rather than methods.


#### Decision 2: The turtle

We spent considerable time debating whether we should make the turtle passive or active (i.e. whether it performed its own actions, drew its own lines, etc... or if some other class handled those things for it).
*   For the active turtle (what we settled upon)
    *   The turtle does more and the interpreter does less.
    *   The turtle should be a group, to include the lines
*   For the passive turtle 
    *   The interpreter does more, and the turtle does very little (basically a data structure, with properties)
    *   The turtle should extend imageview
We found the active turtle approach to make the most sense, especially when we considered extension and increased demands on the program. With more turtles, the active turtle approach minimizes load on the interpreter, keeping it from becoming overloaded and slow.


#### Decision 3: Data storage
*   There was quite a bit of discussion on where various objects should be placed (front-end vs back-end vs independent classes)
    *   For front end:
        *   Most things need to be visualized, so storing them in front end can help with not needing to constantly call for data
        *   However, this leaves the issue that if backend needs data now it has to call for it. 
    *   For back end:
        *   Most actual manipulating of data, so storing them in back end also makes a decent amount of sense. 
        *   However, again this leaves the issue of having to call data from a different location (here front end)
    *   For a separate data storing all turtle-related data
        *   This way makes it easy for both frontend and backend, as frontend could keep an instance of Turtle and actions could be performed on a Turtle by backend
        *   It does require making a new class though, as well as figuring out clear limits on what exactly a turtle should know (like its id)
Again, we agree that we made the correct decision here, but with a caveat: The choice to make Turtle (and subsequently TurtleController) its own class was definitely correct, but we aren't sure we really hit the limit. Maybe turtles should've known about their own id, so turtle queries would not need to be lumped under control.
    
## Assumptions or decisions made
(to simplify or resolve ambiguities in the project's functionality)
* Grouping 
    * One line in grouping said "If the command already takes multiple arguments then it is just like calling the command multiple times"
    * This made sense for setxy, but it was arguable whether for sum, you would also want it like this
    * We ended up not making exceptions, so all multiple argument commands could only be executed with the right amount of arguments (e.g. sum has to take an even number of parameters)
* Multiple turtle commands and evaluation
    * "fd set :x + :x 10" causes turtles to move a different amount, as the set is evaluated for each fd.
* None of the user input lines consists of purely empty spaces
    * The ".trim()" method fails to function properly on lines consisting of only spaces, which makes the preliminary parsing of the user input tricky to deal with. 
    * If the user wants to skip a line, just press enter and do not add any spaces in that empty line for fun. 