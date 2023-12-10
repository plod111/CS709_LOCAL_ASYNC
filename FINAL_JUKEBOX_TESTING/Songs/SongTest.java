import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SongTest {
    public static void main(String[] args) throws FileNotFoundException {

        // create ArrayList of Songs
        ArrayList<Song> songs = new ArrayList<Song>();

        // read text file into ArrayList
        String songDetailFile = "/home/plod/Documents/CS-709/CS709_LOCAL_ASYNC/FINAL_JUKEBOX/Songs/songDetails.txt";
        String songFolder = "/home/plod/Documents/CS-709/CS709_LOCAL_ASYNC/FINAL_JUKEBOX/Songs/";
        File file = new File(songDetailFile);
        Scanner fileReader = new Scanner(file);

        // read each line of the file and create a Song object, reading in the song file name from the command line
        int i = 0;
        while (fileReader.hasNextLine()) {
            String[] songInfo = fileReader.nextLine().split(",");
            String trackFile = args[i];
            songs.add(new Song(songInfo[0], songInfo[1], songInfo[2], Integer.parseInt(songInfo[3]), songFolder+trackFile));
            i++;
        }
        fileReader.close();


        // print out the songs
        for (Song song : songs) {
            System.out.println(song.toString());
        }
    }
}
