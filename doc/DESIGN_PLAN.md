SLogo Plan
====  

## Introduction //Elizabeth
This section describes the problem your team is trying to solve by writing this program, the primary design goals of the project (i.e., where is it most flexible), and the primary architecture of the design (i.e., what is closed and what is open). Discuss the program at a high-level (i.e., without referencing specific classes, data structures, or code).  

We aim to write a flexible IDE for a simplified version of the Logo language.

## Design Overview //Frank & Samson
This section serves as a map of your design for other programmers to gain a general understanding of how and why the program was divided up, and how the individual parts work together to provide the desired functionality. Describe the four APIs you intend to create (their purpose with regards to the program's functionality, and how they collaborate with each other) focusing specifically on the behavior, not the internal state. Include a picture of how the components are related (these pictures can be hand drawn and scanned in, created with a standard drawing program, or screen shots from a UML design program). Discuss specific classes, methods, and data structures, but not individual lines of code.  

## User Interface //X & Elizabeth
This section describes how the user will interact with your program (keep it simple to start). Describe the overall appearance of program's user interface components and how users interact with these components (especially those specific to your program, i.e., means of input other than menus or toolbars). Include one or more pictures of the user interface (these pictures can be hand drawn and scanned in, created with a standard drawing program, or screen shots from a dummy program that serves as a exemplar). Describe any erroneous situations that are reported to the user (i.e., bad input data, empty data, etc.).  

## API Details // All
This section describes each API introduced in the Overview in detail. Describe how each API supports specific features given in the assignment specification, what resources it might use, how it is intended to be used, and how it could be extended to include additional requirements (from the assignment specification or discussed by your team). Finally, justify the decision to create each class introduced with respect to the design's key goals, principles, and abstractions. Your APIs should be written as Java interfaces, types that cannot contain instance variables or private methods, in appropriate packages. These should be Java code files that compile and contain extensive comments to explain the purpose of each interface and each method within the interface (note this code can be generated directly from a UML diagram). Include any Exceptions you plan to throw because of errors that might occur within your methods. Note, this does not require that all of these types will remain as interfaces in the final implementation, just that the goal is for you to focus on each type's behavior and purpose.  

## API Example Code //All
It is especially important in helping others understand how to use your APIs to provide example code. It should be clear from this code which objects are responsible for completing each part of the task, but you do not have to implement the called functions.
Show an actual "sequence of code" that implements the following use case using only methods described in your APIs: 
The user types 'fd 50' in the command window, and sees the turtle move in the display window leaving a trail, and the command is added to the environment's history.
Note, clearly show the flow of calls to public methods needed to complete this example, indicating which class contains each method called. It is not necessary to understand exactly how parsing works in order to complete this example, just what the result of parsing the command will be.
Additionally, each member of the team should create two use cases of their own (and example code) for the part of the project for which they intend to take responsibility. These can still be done as a group, but should represent a variety of areas of the overall project.  

## Design Considerations //All
This section describes any issues which need to be addressed or resolved before attempting to devise a complete design solution. Include any design decisions that the group discussed at length (include pros and cons from all sides of the discussion) as well as any ambiguities, assumptions, or dependencies regarding the program that impact the overall design.  

## Team Responsibilities //All
This section describes the program components each team member plans to take primary and secondary responsibility for and a high-level plan of how the team will complete the program.

### Front End
#### Xiaolan  
Engine 

#### Elizabeth  
* Engine
    * 
* OptionManager
    * 


### Back End
#### Samson
* Variable 
    * contains type (currently only int), value
* VariableManager
    * contains all the variables, addVariable, getVariable, clearAllVariables()
* Turtle
    * controls its position, writes lines, has its own pen
* Pen
    * attributes: penup vs down, color, width
#### Frank 
* Command Manager
    * deals with the logics of user inputs (for, if, repeat, etc.) and generates a series of command objects 
* Command 
    * contains specific tasks to be carried out (can be turtle related/unrelated) 
* Parser
    * parses the user input to interpretable form that command manager takes 