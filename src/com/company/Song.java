package com.company;

public class Song implements Comparable<Song> {

    String title;
    String artist;
    String length;
    String filename;

    public Song(String t, String a, String l, String fn) {
        title = t;
        artist = a;
        length = l;
        filename = fn;
    }

    public boolean equals(Object aSong) {
        Song s = (Song) aSong;
        return getTitle().equals(s.getTitle());
    }

    public int hashCode() {
        return title.hashCode();
    }

    public int compareTo(Song s) {
        return title.compareTo(s.getTitle());
    }

    public String getArtist() {
        return artist;
    }

    public String getFileName() {
        return filename;
    }

    public String getLen() {
        return length;
    }

    public String getTitle() {
        return title;
    }

    public String toString() {
        return title;
    }
}