public class Song {
    private String title;
    private String artist;
    private String genre;
    private int duration;
    private String path;

    public Song(String title, String artist, String genre, int duration, String path) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.duration = duration;
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }

    public String getPath() {
        return path;
    }

    public String toString() {
        return title + "," + artist + "," + genre + "," + duration + "," + path + "\n";
    }
}