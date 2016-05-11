# Software Studio Assignment 6

## 1. Operation
+ Hovering on the character: the name of the character will show.
+ By dragging the little circle and drop it in the big circle, the character will be added to the network.
+ Of course, by dragging the little circle out of circle, you can remove it form the circle as well. 
+ Clicking on the button "Add All": users can add all the characters into network to be analyzed.
+ Clicking on the button "Clear": users can remove all the characters from network.
+ Clicking on the button "sound": users can start / pause the back-ground music.
+ By pressing key 1~7 on the keyboard, users can switch between episodes.

## 2. Visualization
+ The node will be highlighted when the mouse is on it.
+ The circle will be highlighted when the mouse is on it.
+ According to JSON	Format resource, if two characters have relationship, they will link each other by a Bezier curve.
+ The thickness of Bezier curve will decide on the value of the link.
+ The main characters,which have color except for gray, will show their name when they're on the circle.
+ The position of each node on the circle will be decided by the Trigonometric function.
+ If the character is dragged while released out of the circle, it will come back to its original position.

###Bonus
1. Add background music.
2. Add sound on/off button. The initial setup is mute.
3. If the mouse moves in the circle, enlarge the circle weight.
4. If the main character is on the circle, show its name.


###遇到的問題:
1.均穎: 因為我程式能力比較弱，所以在剛開始的著手上，對程式架構比較不知從何開始打起；幸好我有好夥伴孜宇，
他先一一想好架構，我如果有不懂的地方，也都會很耐心地跟我講解，所以我們兩個在結構打好後的合作後期算是順暢的吧!

比較大的問題大概是在MainApplet的draw()，要心裡清楚noStroke()或strokeWeight的先後順序，否則會像我們曾經發生，當滑鼠移入圓中，
圓周長的那條線消失不見的情形，所幸經過幾次調整，總算改善這個問題，而且我們還更進一步，設計當滑鼠在圓外或圓中會有不同粗細線條的產生。

於我而言，我學到最多的是更多Ani的應用，尤其是Ani.to(java.lang.Object theTarget, float theDuration, float theDelay, java.lang.String thePropertyList, float theEnd)，讓我印象深刻，真是太方便了~