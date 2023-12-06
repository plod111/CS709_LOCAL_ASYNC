import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SongTest {
    public static void main(String[] args) throws FileNotFoundException {

        // create ArrayList of Songs
        ArrayList<Song> songs = new ArrayList<Song>();

        // read text file into ArrayList
        String filename = "/home/plod/Documents/CS-709/CS709_LOCAL_ASYNC/FINAL_JUKEBOX/MusicMP3/songDetails.txt";
        File file = new File(filename);
        Scanner fileReader = new Scanner(file);
        while (fileReader.hasNextLine()) {
            String[] songInfo = fileReader.nextLine().split(",");
            songs.add(new Song(songInfo[0], songInfo[1], songInfo[2], Integer.parseInt(songInfo[3]), songInfo[4]));
        }
        fileReader.close();

        // add songs to the ArrayList from args
        // int numSongs = args.length;
        // for (int i = 0; i < numSongs; i++) {
        //     String[] songInfo = args[i].split(",");
        //     songs.add(new Song(songInfo[0], songInfo[1], Integer.parseInt(songInfo[2]), Integer.parseInt(songInfo[3])));
        // }

        // print out the songs
        for (Song song : songs) {
            System.out.println(song.toString());
        }
    }
}
