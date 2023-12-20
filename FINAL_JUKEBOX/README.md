# CS709 - Dec 2023 - Final Project - JavaFX JukeBox

@author Ben Cornish <br>
@author Phil Chu

### <u>Final deliverable INSTRUCTIONS / COMMENTARY :</u>

#### PROGRAM FILES:
##### SOURCE CODE:
* Ensure the following class files are within one folder named Source:
  * CoinPayments.java
  * CreditPayments.java
  * CurrencyBox.java
  * Payments.java
  * PurchaseQueue.java
  * Song.java
  * SongList.java
  * SongPlayer.java

##### OTHER:
* Further, ensure the following files are within the same folder:
  * songDetails.txt
  * winamp-intro.mp3

##### MEDIA:
* Additionally, ensure there are the 30 mp3 files contained within a folder (alongside Source), named Songs.

### TO RUN THE PROGRAM:
* Compile all class files in /Source, ensuring to reference the appropriate FX SDK libraries, and include relevant fx modules:
##### To compile:
>\>\> javac --module-path \<JavaFX sdk lib path\> --add-modules javafx.controls,javafx.fxml,javafx.media *.java

##### To run:
* Execute at CLI SongPlayer.java (the primary FX application), including ONE command line argument --> the songDetails.txt file:
>\>\> java --module-path \<JavaFX sdk lib path\> --add-modules javafx.controls,javafx.fxml,javafx.media SongPlayer songDetails.txt

<br>

#### Program Architecture / COMMENTARY
* Upon and throughout the development of the program, the architecture of the design was considered, revised and optimized, taking into consideration the following ideas:
  * Specifications were often ill-defined, and requried interpretation and deisgn decisions to be made as best we knew how.
  * Separate Java classes were developed fo:
    * coin and credit payments;
    * a currency box;
    * a song list, and a song list of purchased songs to be queued for playing;
    * plus the primary FX application named SongPlayer.
* Counter to the original specs, that required ALL mp3s to be brought in on the CL at runtime, it was decided that best design dictate that all media be kept in a separate folder to the source code.
* Further more, that song meta data ought to be held within a separate file also, and not hard-coded into any of the Java class files.
* As such, program design and execution accepts ONE command line argument, the song details file, that contains:
  * file drive location of all mp3 files (ie. the song folder path);
  * the title;
  * artist;
  * genre;
  * duration;
  * and file name of each song in that same folder.

<b>NB.</b> In a prior version of Juke box, in development, it was built and demonstrated that all mp3 files can indeed be brought in on CL runtime execution and successfully listed and queued for playing within an FX media-capable application.
<br>

#### <u>Brief development notes from 5 weeks</u>

##### <u>Dec 19, 2023</u>

* Adjusted SimpleList view of available songs.
* Adjusted label contrast.
* Wrote up a ReadMe file for JukeBox app.
* 
##### <u>Dec 18, 2023</u>

* renamed App to SongPlayer - this is now the FX application - run this iwth one single CL arg - the songDetails file.
* Added header comments everywhere.
* Reworekd and cleaned up the Application code - moved main method to top.  Grouped all methods per functionality and moved towards bottom.
* Adjusted some GUI stuff.


##### <u>Dec 16,17, 2023</u>

* Brought together the final pieces for jukebox into one operational app.  Cleanup still remains.  Fully functional at present given the limited unit tests ran.
* Added an intro sound bite - based on Winamp - this loads as the first audio, and is reset as the audio, both in the presence of an empty playlist (queue). 
* Purchase queue now gets built/populated via song buy buttons.
* Troubleshooted coin based purchases - there was a seperate tally of nickels, dimes and quarters happening that wasnt synchronized with total funds.
* Removed all denom buttons except n, d, q's.
* Added a premium pruchase option that costs an additional 10c per song and adds the song into the next to play position (does not take current playing position).
* Added song list viewed via SimpleList.  Song title and artist now listed.
* Added radio buttons and tied them to existing sort functions.  Sorts happen as direct consequence of radio button selection - no need for additional button to initiate sort.
* To repaint the list, the SimpleList view has to be recreated by the looks.
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
* Worked on reading in parameters from CL at execution for purposes of getting all mp3s.  
* Got that working, and pushed through from main, to launch, to start, and created List of Songs.   SO clunky to do it this way...
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
* Song?
* List to play
* Coinbox
* interface elements?