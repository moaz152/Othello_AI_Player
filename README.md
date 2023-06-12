<br/>
<p align="center">
  
  <p align="center"othello AI Game</p>
</p>
<br/><br/>
<p>othello Game and also an AI Player for AI Competition.</p>
<p>this project consists of a GUI for Playing Othello and also an AI Player.</p>

<br/>
<p align="center">
  <img width="500" height="522" src="/../master/doc/vs_greedy.gif?raw=true"/>
  
  <p align="center"wAI Algorithm (Black) vs. Greedy Algorithm (White)</p>
</p>

Play Modes
----------
* Human VS Human
* Human VS AI
* AI VS AI


![1](https://github.com/moaz152/Othello_AI_Player/assets/85046148/0e30fa59-1c1b-448e-952e-14a880bee488)
![2](https://github.com/moaz152/Othello_AI_Player/assets/85046148/ed5783c8-b927-47ed-afc4-dcac06cafdd2)




<br/>
<br/>
<br/>

# AI Algorithm


Minimax Search
--------------
The search algorithm is a minimax search with alpha-beta pruning.


Heuristic Functions
-------------------
One of the most critical components of the search algorithm is the heuristic function, which evaluates the strength and overall desireability of a given board position. It is implemented as a linear combination of the following statistics, with the weights adapting as the game progresses

* Corner Grab 

* Stability 

* Mobility 

* Placment

* Frontier Discs

* Disc difference 

* Parity 
