# CS709_LOCAL_ASYNC
###### Async work with Phil

(Private repo because github classroom has issues...)

Async work built herein, that will then be copied across for, and at submission time...


## FINAL PROJECT THOUGHTS / PLANNING

##### <u>Dec 18,19, 2023</u>

* renamed App to SongPlayer - this is now the FX application - run this iwth one single CL arg - the songDetails file.
* Added header comments everywhere.
* Reworekd and cleaned up the Application code - moved main method to top.  Grouped all methods per functionality and moved towards bottom.
* Adjusted some GUI stuff


##### <u>Dec 16,17, 2023</u>

* Brought together the final pieces for jukebox into one operational app.  Cleanup still remains.  Fully functional at present given the limited unit tests ran.
* Added an intro sound bite - based on Winamp - this loads as the first audio, and is reset as the audio, both in the presence of an empty playlist (queue). 
* Purchase queue now gets built/populated via song buy buttons.
* Troubleshooted coin based purchases - there was a seperate tally of nickels, dimes and quarters happening that wasnt synchronized with total funds.
* Removed all denom buttons except n, d, q's.
* Added a premium pruchase option that costs an additional 10c per song and adds the song into the next to play position (does not take current playing position).
* Added song list viewed via SimpleList.  Song title and artist now listed.
* Added radio buttons and tied them to existing sort functions.  Sorts happen as direct consequence of radio button selection - no need for additional button to initiate sort.
* To repain the list, the SimpleList view has to be recreated by the looks
* Realized that objects appear in the scene in the order they are created in code - so earlier nodes, like rectangles can be created first and they will now remain behind buttons etc. 
* Added playlist queue using same SimpleList view.  Populates and behaves correctly per purchaseQueue.

* <b>TODO:</b>
* Check on removal of additional / updated SimpleList views.
* Add confetti in background
* Cleanup positioning of buttons
* Check if play on media end can be integrated

##### <u>Dec 10, 2023</u>

* Built out currencyBox GUI elements and implemented associated logic for funds addition and balance
* Built a confetti animation using Rectangle arrays and multiple transitions.
* Sort functionality for songlist implemented via title, artist, genre, duration
* 

##### <u>Dec 9, 2023</u>

* Refactored after group discussion:
* SongPlayer becomes SongList - creates and holds list of available songs, via song details text file entered on CL at runtime.
* Only arg at runtime is no SongDetails.txt.  MP3s are listed therein along with path for media.
* Song price field added to Song: uniformly set to 25c.
* SongPlayer is now the main FX application.
* PurchaseQueue : extension of Queue to make use of methods therein.
* FX app window built using Group with currently available functionality all set as buttons.  Further functionality and form still required for final.

##### <u>Dec 8, 2023</u>

* Added/modified animations to song player
* Included buttons to sort incoming songs by title, artist, genre or duration
* Still to build queue and purchasing


##### <u>Dec 7, 2023</u>

* Built out Song class: attributes include artist, title, genre, duration and path
* SongPlayer class - essentially just a Song list - not sure how yet and why this would access a seperate queue - maybe this class becomes the queue... Spec is not at all clear on many a design front.
* Built FX application that can read mp3 file names from CLI upon execution, match them with the respective song details incoming from a text file that holds all that detail.
* App can now play songs, pause, stop, restart, and cue the the next song in the list.
* App contains basic animation elements as well as a neon font label holding the artist name of currently playing song :-)
* https://hunterteacher-vyz6159.slack.com/files/U05QES27NSC/F069WRAC7GQ/screenshare_-_2023-12-07_11_15_40_pm.webm 


##### <u>Dec 3, 2023</u>

* (Phil) created several classes as per spec for Goal 1: Funds. (owned by Phil)
* (Ben) added some audio FX code to experiement with.
* We spent a good time this week struggling with what the framework for the funds will look like
* The spec is not entirely clear on what is what - and can't help but think some of this is just down to naming of objects/classes/etc and doing it in isolation away from the 'client' rather than sitting with them and nutting out the requirements.
* Current take on Funds: 
  * An interface called Payments that define a void method addPayments() and a String method returnFunds().
  * Two implementing classes : CoinPayments and CreditPayments, having respectively:
    * denomination variables for each coin, plus a total user balance
    * a total user balance
  * And each implements the above methods respecitvely:
    * addPayments() for coins:  increments denomination fields
    * returnFunds() for coins: returns (decrements) total user balance fully (attempted) or as close as possible given coin limitations
    * addPayments() for credit: increments total user balance
    * returnFunds() for credit: returns user balance - 3% MC fee
  * At the app level:
    * Create CoinPayments() object named coins
    * Create CreditPayments() object named credit
    * int variable currencyBox to hold total user balance composed of:
      * balance from coins PLUS balance from credit
  * May need additional set and get methods where requried throughout

* Attempting music player: Goal set 2:  (owned by Ben)
* Continually running into "ava.lang.reflect.InvocationTargetException" at runtime
* Turns out - it doesnt seem to play nice within VSC - don't know how to set the dependencies there to make the media work.
* But comiple and run at CLI works just fine...  go figure



 
##### <u>Nov 26, 2023</u>

* Perhaps implement Maven for handling dependancies/references?
  * perhaps more efficient to simply comment/uncomment when/where requried

Implementation:
* Graphical only or with text boxes as well. 
* Buttons for each coin deposited to the machine
* Typing in coordinate of the song. 
* GUI all in one box without switching states.
* Each song should display the title, artist, and cost to play.
* Starting in command line to input songs
* Sort by radio button
* The queue should only add songs if user has enough funds
* Build out each 'Goal set' individually, and then decide on connectivity

  - Classes
    * CoinBox  > holds balance, setters, getters, 
    * Each song will be a class with attributes: Title, Genre, Cost, etc.
    * SongArrays.java which will be extended to a PurchaseQueue.java <ArrayList> 


Questions to be questioned:
* What is creditPayments vs coinPayments?
* If (credit == credit card) {have user input amount in a textbox.}
* Can we use dictionaries in Java?
* Does the program need to be linked to external csv?


Objects:
* 