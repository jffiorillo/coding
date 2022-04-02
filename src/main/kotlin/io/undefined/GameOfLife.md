## Problem

[Conway's Game of Life](https://conwaylife.com/) is a cellular automaton that is played on a 2D square grid.
Each square (or "cell") on the grid can be either alive or dead, and they evolve according to the following rules:
* Any live cell with fewer than two live neighbours dies (referred to as underpopulation).
* Any live cell with more than three live neighbours dies (referred to as overpopulation).
* Any live cell with two or three live neighbours lives, unchanged, to the next generation.
* Any dead cell with exactly three live neighbours comes to life.

## Example:

```
1 1 0 0 0
0 1 0 1 0  
0 0 0 1 1
0 0 0 1 1


1 1 1 0 0
1 1 0 1 1
0 0 0 0 0
0 0 0 1 1
```