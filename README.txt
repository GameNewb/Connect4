user: 009445384, (Del Castillo, Kyle)

NOTES:

There are 2 copies of the JAR file, one in the main directory, and one in dist folder. Both are the same.

The limit of the board size is 20x20 to prevent an unrealistic connect"4". Also, connection length cannot exceed the rows/column presented.

As far as issues, the only issue I found was the resizing option I implemented. 
If the user decides to change the board size to something bigger, then the game window/panel size will remain the same even though the board has been initialized to the bigger dimensions.

For example:
	Initializing a board of 10x10 in the beginning, then reinitializing it to 12x12.
	The program will create a 12x12 board, but it won't show on the window that it is 12x12, but instead still 10x10.
	This works however if it's smaller, such as 10x10 reinitialized to 6x7 will show.

I am still fairly new to GUI so I couldn't really figure out how to re-draw or re-size it. I would appreciate it if you can give some comments/hints on how to do that!
