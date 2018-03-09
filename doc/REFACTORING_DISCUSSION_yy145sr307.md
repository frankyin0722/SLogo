Refactoring Discussion

1. Separate sub-methods from long methods that each carries specific tasks (parser and interpreter especially)

2. Formatting (brackets, indentations)

3. Magic Values (mostly strings and numbers that is used to check command type or parameter indexing)

4. Complex logic structures (some methods in the parser and interpreter contain lots of logic structures to ensure that the input gets handled correctly, which are condensed/taken out to be separate methods so that the complexity is reduced)

5. Pass Types (some unnecessary passes of lists are eliminated, and the necessary ones are ensured to be generic type so that it can be handled by any classes it gets passed to)

