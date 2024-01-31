Running this program with “-i” 
main -i
will bring you to the interactive testing environment.
1.	In this mode first you need to select option 1 and type the name of the input file. (gameofcatz)
2.	Under the option two, you can do 4 node operations.
  a.	find – need to enter to enter the node that you want to find. Then you get the relevant value of that node as the output.
  b.	insert – need to enter the node and value. This will insert a new node to your list. Make sure the inserting node is a new node.
  c.	delete – need to enter the node and value. Then this value will remove from you list. Make sure it is a node that already doesn’t exist. Else program will ignore that.
3.	Edge operations
  a.	find – need to enter both nodes. Then this will give you the weight of that edge. Make sure the edge is preexisting one.
  b.	add – need to enter both nodes and the weight code, that wants to add. 
  c.	delete – need to enter both nodes, that wants to delete.
4.	parameter tweaks –

main -s input_fileName output_fileName
  eg. main -s gameofcatz paths.txt
This is the simulation mode, and user gets all the possible paths to the target. Those paths display incremental order.
