package com.company;

import javazoom.jl.player.Player;
import javazoom.jl.decoder.JavaLayerException;
import java.io.*;

public class MusicPlayer {

    FileInputStream fileInputStream;
    BufferedInputStream bufferedInputStream;
    File myFile = null;
    String filename;
    String filePath;
    long totalLength;
    long pause;
    Player player;
    Thread playThread;
    Thread resumeThread;

    MusicPlayer(String path) {
        playThread = new Thread(runnablePlay);
        myFile = new File(path);
    }

    public void PlaySong() {
        playThread.start();
    }
    public void StopSong() {
        player.close();
    }

    Runnable runnablePlay=new Runnable() {
        @Override
        public void run() {
            try {
                //code for play button
                fileInputStream=new FileInputStream(myFile);
                bufferedInputStream=new BufferedInputStream(fileInputStream);
                player=new Player(bufferedInputStream);
                totalLength=fileInputStream.available();
                player.play();//starting music
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
}