# IslandsCounter
This is one of my first apps, I did it mostly to try and build an app with a pretty and clean OOP project. The main thing here is the paint button that paints all the islands in O(n) time.

Islands Counter is an app that given a dimension - creates a board in that dimension. It then lets you choose if you want to fill it manually or let the random algorithm do it for you. 
when the board is filled with islands, the app will efficiently paint the islands in different colors.

# IslandsPainter and the problem with big dimension matrixes
When we try to handle matrixes bigger then 200x200, the memory can't handle it, and this is why I made the Islands painter.
In my git you can also find the IslandsPainter app that is very similar. The main difference is that it uses a recycler view and an adapter so the screen doesn't have to hold the entire matrix and it can pull cells from the memory when scrolling.
