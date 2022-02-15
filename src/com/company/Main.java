package com.company;

import java.io.*;
import java.util.*;

public class Main {

    ArrayList<Song> songList = new ArrayList<Song>();

    public static void main(String[] args) throws IOException {
        Main obj=new Main();
        obj.getSongs();
        obj.go(0);
    }

    public void go(int ind) throws IOException {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Now Playing - "+songList.get(ind));
        System.out.println("Enter the corresponding numbers to perform functions -");
        System.out.println("1. Next Song");
        System.out.println("2. Previous Song");
        System.out.println("3. List all songs");
        System.out.println("4. Delete Current Song");
        System.out.println("5. Add a new song");
        System.out.println("6. Shuffle Play");
        System.out.println("0. Quit Jukebox");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int ch = Integer.parseInt(br.readLine());
        switch(ch) {
            case 1:
                if(ind<songList.size()-1)
                    ind++;
                else
                    System.out.println("Queue Limit reached.");
                System.out.println(songList.size());
                break;
            case 2:
                if(ind>0)
                    ind--;
                else
                    System.out.println("No previous track available");
                break;

            case 3:
                System.out.println(songList);
                break;

            case 4:
                songList.remove(ind);
                if(ind == songList.size()-1)
                    ind--;
                break;

            case 5:
                System.out.print("Song Title: ");
                String title = br.readLine();
                System.out.println();
                System.out.print("Artist Name: ");
                String artist = br.readLine();
                System.out.println();
                System.out.print("Song Rating: ");
                String rating = br.readLine();
                System.out.println();
                System.out.print("Song BPM: ");
                String bpm = br.readLine();
                System.out.println();
                String str = title+"/"+artist+"/"+rating+"/"+bpm;
                addSong(str);
                break;

            case 6:
                Collections.shuffle(songList);
                break;

            case 0:
                System.out.println("Thank you for your time");
                System.exit(0);
                break;

            default:
                System.out.println("Enter a valid choice");
            }
        go(ind);

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

