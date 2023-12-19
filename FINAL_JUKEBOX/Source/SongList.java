/**
 * SongList.java - Fall 2023
 * 
 * List of Song objects are created from the song data in the song details file
 * 
 * CS709 Hunter Fall 2023 - Final Project
 * 
 * @author B.Cornish
 * @date Dec 2023
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SongList {

    // ArrayList of Songs
    ArrayList<Song> songs = new ArrayList<Song>();

    // read text file into ArrayList
    String songFolder;
    File file;
    Scanner fileReader;

    /**
     * Constructor for objects of class SongList
     * @param args
     * @throws FileNotFoundException
     */
    public SongList(String[] args) throws FileNotFoundException {

        this.file = new File(args[0]);
        this.fileReader = new Scanner(file);

        // read each line of the file and create a Song object
        int i = 0;
        while (fileReader.hasNextLine()) {

            // System.out.println(fileReader.nextLine());

            if (i == 0) {
                this.songFolder = fileReader.nextLine();
                i++;
                continue;
            }

            String[] songInfo = fileReader.nextLine().split(",");
            songs.add(new Song(songInfo[0], songInfo[1], songInfo[2], Integer.parseInt(songInfo[3]), songInfo[4], songFolder + songInfo[4]));
            i++;
        }
        fileReader.close();
    }

    /**
     * Returns the songs in the song list
     * @return
     */
    public ArrayList<Song> getSongs() {
        return songs;
    }

    /**
     * Returns the song list as a string
     */
    public String toString() {
        String songList = "\nSong folder is: " + songFolder + "\n\n"+ "Songs are:\n";
        for (Song song : songs) {
            songList += song.toString();
        }
        return songList;
    }
}