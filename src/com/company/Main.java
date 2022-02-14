package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

public class Main {

    ArrayList<Song> songList = new ArrayList<Song>();

    public static void main(String[] args) {
        new Main().go();
    }

    public void go() {
        getSongs();
        System.out.println("Now Playing - "+songList.get(0));
        System.out.println("Enter the corresponding numbers to perform functions -");
        System.out.println("1. Next Song");
        System.out.println("2. Previous Song");
        System.out.println("3. Replay Current Song");
        System.out.println("4. List all songs");
        System.out.println("5. Delete Current Song");
        System.out.println("6. Add a new song");
        System.out.println("7. Shuffle Play");
        System.out.println("0. Quit Jukebox");


        //System.out.println(songList);
        //Collections.sort(songList);
        //System.out.println(songList);

        TreeSet<Song> songSet = new TreeSet<Song>();
        songSet.addAll(songList);
        //System.out.println(songSet);
    }

    void getSongs() {
        try {
            File file = new File("src/com/company/SongList.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                addSong(line);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void addSong(String lineToParse) {
        String[] tokens = lineToParse.split("/");
        Song nextSong = new Song(tokens[0], tokens[1], tokens[2], tokens[3]);
        songList.add(nextSong);
    }
}

