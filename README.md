# MazePathFinder
Maze Shortest Path Finder

Using Breadth First Search (BFS) Algorithm to find the shortest path in a labyrinth. Current possible moves are only in straight direction. It will be updated to A-Star Algorithm and diagonal moves with more conditions.

## Tech

Java 11, Gradle

## Example IO

Initial maze:
M - start position, X - target, N - walls

```python
 ABCDEFGHIJKLM
1             
2  NNNN NNN   
3  N XN N N   
4  N NN   NNN 
5  N      N   
6  NNNNNNNN   
7  N          
8        M    
```

OUTPUT:
```python
Find path in 27 steps.
(I, 8)(J, 8)(K, 8)(L, 8)(M, 8)(M, 7)(M, 6)(M, 5)(M, 4)(M, 3)(L, 3)(K, 3)(K, 2)(K, 1)(J, 1)(I, 1)(H, 1)(G, 1)(G, 2)(G, 3)(G, 4)(G, 5)(F, 5)(E, 5)(D, 5)(D, 4)(D, 3)(E, 3)

Visualization: 

 ABCDEFGHIJKLM|
1      .....  |
2  NNNN.NNN.  |
3  N.XN.N N...|
4  N.NN.  NNN.|
5  N....  N  .|
6  NNNNNNNN  .|
7  N         .|
8        M....|
```

## Development

Peter Stoilkov.

## License

[MIT](https://choosealicense.com/licenses/mit/)
