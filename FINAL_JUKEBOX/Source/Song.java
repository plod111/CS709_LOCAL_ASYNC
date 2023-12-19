/**
 * Song.java - Fall 2023
 * 
 * Song objects are created from the song data in the song details file
 * 
 * CS709 Hunter Fall 2023 - Final Project
 * 
 * @author B.Cornish
 * @date Dec 2023
 * 
 */

public class Song {

    // song details
    private String title;
    private String artist;
    private String genre;
    private int duration;
    private String fileName;
    private String path;
    private int price;

    /**
     * Constructor for objects of class Song
     * @param title
     * @param artist
     * @param genre
     * @param duration
     * @param fileName
     * @param path
     */
    public Song(String title, String artist, String genre, int duration, String fileName, String path) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.duration = duration;
        this.fileName = fileName;
        this.path = path;
        this.price = 25;  // uniform price for all songs: 25 cents
    }

    /**
     * Returns the song title
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the song artist
     * @return
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Returns the song genre
     * @return
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Returns the song duration
     * @return
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Returns the song file name
     * @return
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Returns the song path
     * @return
     */
    public String getPath() {
        return path;
    }

    /**
     * Returns the song price
     * @return
     */
    public int getPrice() {
        return price;
    }

    /**
     * Returns the song details as a string
     */
    public String toString() {
        return title + "," + artist + "," + genre + "," + duration + "," + fileName + "\n";
    }
}