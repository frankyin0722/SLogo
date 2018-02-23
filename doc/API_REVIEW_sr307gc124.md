Part 1
======

1.  What about your API/design is intended to be flexible?
    * adding new commands, new variable types, more turtles
2.  How is your API/design encapsulating your implementation decisions?
    * the fact the turtle has no forward/backward, etc... is showing that we plan to implement commands outside the turtle, rather than having the turtle need 100 methods  
3.  What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
    * assigning a variable data it cant handle, an invalid command (wrongly typed or with the wrong amount of parameters). An error is handled by an alert popping up on the screen with a description of the error.  
4.  Why do you think your API/design is _good_ (also define what your measure of good is)?
    * Its flexible, with mostly intelligent classes (turtle is somewhat passive, but it still draws its own lines. each command does an action)

Part 2
1.  How do you think Design Patterns are currently represented in the design or could be used to help improve the design?
    * Model View controller was used with inheritence (abstract general class for Commands with specific command subclasses). 
    * Command Design Pattern for the commands, with the sole execute() command
2.  What feature/design problem are you most excited to work on?
    *  Implementing the command design pattern and its interaction with the Mediator/other components. 
3.  What feature/design problem are you most worried about working on?
    * Implementing the command design pattern and its interaction with the Mediator/other components
4.  Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).
    * Running fd 50
    * Running sin 50
    * Being compatible with Parser such that commands that are called run and return a double
    * Being compatible with GUI such that command run and also return double
    * Being able to implement a command so it runs correctly when given a variable

