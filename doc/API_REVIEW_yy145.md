# SLogo API Review for  Front-end

### Part 1
What about your API/design is intended to be flexible?
- Flexible to add commands to the program. 

How is your API/design encapsulating your implementation decisions?
- Back end only deals with the processing and executing of commands that result in changes in the model objects, while only model objects interact with front end and the back end stays closed. 

What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
- Class not found exception, parsing exception, bracket not matched exception, etc. 

Why do you think your API/design is good (also define what your measure of good is)?
- It's awesome and very flexible in dealing with commands.

Both people should complete Part 1 before continuing on to Part 2.

### Part 2
How do you think Design Patterns are currently represented in the design or could be used to help improve the design?
- We currently use MVC design that separates model, view, and controller. Reflections are used to make the design more flexible in taking in command extensions. 

What feature/design problem are you most excited to work on?
- Command interpreter and parser. 

What feature/design problem are you most worried about working on?
- The correct way to parse and store the command so that it can be interpreted without flaws and logic errors. 

Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).
- The user defines its own function. 
- The user implements control-flow to its input. 
- The user does math with the help of this tool. 
- The user adds commands to the existing list with convenience. 
- The user uses command to control the turtle(s) and other model objects. 