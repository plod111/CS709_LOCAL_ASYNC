import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SongList {

    // ArrayList of Songs
    ArrayList<Song> songs = new ArrayList<Song>();

    // read text file into ArrayList
    String songFolder;
    // File file; 
    Scanner fileReader; 

    // constructor
    public SongList(String[] args) throws FileNotFoundException {

        this.fileReader = new Scanner(args[0]);

        // read each line of the file and create a Song object, reading in the song file
        // name from the command line
        int i = 0;
        while (fileReader.hasNextLine()) {
            
            if (i==0){
                this.songFolder = fileReader.nextLine();
                i++;
                continue;
            }
            
            String[] songInfo = fileReader.nextLine().split(",");
            // String trackFileName = args[i];
            songs.add(new Song(songInfo[0], songInfo[1], songInfo[2], Integer.parseInt(songInfo[3]), songInfo[4], songFolder + songInfo[4]));
            i++;
        }
        fileReader.close();
    }


    public ArrayList<Song> getSongs() {
        return songs;
    }
}