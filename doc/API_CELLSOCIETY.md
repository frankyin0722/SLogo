### Internal Simulation
```java
public class Cell{ 
    // get or update states for simulation processing
    public State getCurrentState() 
    public State getNextState() 
    public void setNextState(State nextState) 
    public void updateCell() 
}

public interface GameType{ 
    // determine states and locations for the next iterations 
    public State determineState(Cell currentCell, List<Cell> Neighbors) 
    public Point2D determinePosition(Cell currentCell, List<Cell> Neighbors, int x, int y, Cell[][] grid, int[] xvals, int[] yvals,boolean toroidal) 
    public boolean replaceWithEmpty(Cell cell, int time) 
}
```



### External Simulation
```java
public abstract class Grid { 
    // intiailizes the cell states of the grid 
    public void setCell(int x, int y, Cell cell) 
    // updates the grid for one cycle
    public void updateGrid()
    // change the grid edge type of the simulation 
    public void toggleToroidal() 
}
public class Cell{ 
    //increments the cell state
    public void increment()
}

```


### Internal Configuration
``` java
// Class to save configuration as XML file, called by Reader
public class XMLBuilder { 
    public XMLBuilder (Document doc) 
    public void buildXML (String simulationType, String simulationName, int numRows, int numCols,
    public void saveXMLFile(String filePath) throws TransformerException, IOException 
}
// Throws custom errors for various classes
public class XMLException extends RuntimeException { 
      public XMLException(String message, Object ... values) 
    public XMLException(Throwable problem) 
}

// Called by Reader class to parse file
public class XMLParser { 
    public XMLParser(File file) 
    public File getFile()
    public String getTextFromXML(String tag) 
}
```

### External Configuration


``` java
// Class to parse XML file
public class Reader { 
    // Constructor for reader
    public Reader(File file) 
    // Parse specific components
    public int getRows()
    public int getColumns()
    public GameType getGameType() 
    // initialize grid with components
    public void initializeGrid(Grid myGrid) 
    // save grid to XML file
    public void saveXML(Grid myGrid) 
}
```

### Internal Visualization
``` java
public interface Viewer {
    //initialize the visual grid
  	public Group initGrid(int x, int y, int gridWidth, int gridHeight, int gridRows, int gridCols);
    // displays/hides gridlines
	public void changeLines();
}
public class CellSociety extends Application{ 
    //starts the cell society simulation
  	public void start(Stage stage)
			@Override public void handle(MouseEvent e)
				@Override public void handle(MouseEvent e)
}
public class StateChart { 
    // makes a new chart with population numbers
    public StateChart (Grid gr, GameType gt) 
    //adds necessary data to bar chart
    public void addData() 
    //returns a bar chart of the various states
    public BarChart<Number, String> getBarChart() 
}

```
### External Visualization
Nothing for this project