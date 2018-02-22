# SLogo API Review for  Front-end

### Part 1
What about your API/design is intended to be flexible?
- Will be specific, useful, and have few dependencies

How is your API/design encapsulating your implementation decisions?
- Front end only displays the status of the turtle, everything else is encapsulated by backend.

What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
- Turtle going off the screen, in which case we could implement infinite screen 

Why do you think your API/design is good (also define what your measure of good is)?
- It's fine. It's simple but will work (hopefully)

Both people should complete Part 1 before continuing on to Part 2.

### Part 2
How do you think Design Patterns are currently represented in the design or could be used to help improve the design?
- We currently use MVC design that separates model, view, and controller. For front-end specific structure, we don't have a specific design pattern.

What feature/design problem are you most excited to work on?
- Front end GUI visualization

What feature/design problem are you most worried about working on?
- How to implement the turtle and lines on DrawingWindow

Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).
- The turtle moves forward 50 and drops a line
- The user changes line color from black to blue, and all the current lines also change color
- The turtle goes off screen, and the screen follows it
- The user enters a command and clicks "run"
- The user selects an active turtle from a list