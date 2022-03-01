package com.company;
import java.io.*;
import java.util.*;
public class Main
{
    MusicPlayer playa = null;
    ArrayList<Song> songList = new ArrayList<Song>();
    boolean playing = false;
    public static void main(String[] args) throws IOException
    {
        Main obj=new Main();
        obj.getSongs();
        obj.go(0);
    }
    public void go(int ind) throws IOException
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        if (playing)
            System.out.println("Now Playing - " + songList.get(ind));
        System.out.println("Enter the corresponding numbers to perform functions -");
        System.out.println("1. Play Song");
        System.out.println("2. Stop Playback");
        System.out.println("3. Next Song");
        System.out.println("4. Previous Song");
        System.out.println("5. List all songs");
        System.out.println("6. Delete Current Song");
        System.out.println("7. Add a new song");
        System.out.println("8. Shuffle Play");
        System.out.println("9. Get Current Song Info");
        System.out.println("0. Quit Jukebox");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int ch = 0, flag = 0;
        while (flag == 0)
        {
            try {
                ch = Integer.parseInt(br.readLine());
                flag = 1;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number between 0 and 7");
                flag = 0;
            }
        }
        while(true) {
            switch (ch) {
                case 1:
                    if (playing) {
                        System.out.println("The song is already playing");
                        break;
                    }
                    playa = new MusicPlayer("src\\mp3\\" + songList.get(ind).getFileName());
                    playa.PlaySong();
                    playing = true;
                    break;

                case 2:
                    if (!playing) {
                        System.out.println("The song is already stopped");
                        break;
                    }
                    playa.StopSong();
                    playing = false;
                    break;

                case 3:
                    if (!playing) {
                        playa = new MusicPlayer("src\\mp3\\" + songList.get(ind).getFileName());
                        playa.PlaySong();
                        playing = true;
                    } else if (ind < songList.size() - 1) {
                        playa.StopSong();
                        playa = new MusicPlayer("src\\mp3\\" + songList.get(++ind).getFileName());
                        playa.PlaySong();
                        playing = true;
                    } else {
                        System.out.println("Queue reached the end");
                    }
                    break;

                case 4:
                    if (!playing) {
                        System.out.println("Playing Current Song");
                        playa = new MusicPlayer("src\\mp3\\" + songList.get(ind).getFileName());
                        playa.PlaySong();
                        playing = true;
                    } else if (ind > 0) {
                        playa.StopSong();
                        playa = new MusicPlayer("src\\mp3\\" + songList.get(--ind).getFileName());
                        playa.PlaySong();
                        playing = true;
                    } else {
                        System.out.println("Queue reached the beginning");
                    }
                    break;

                case 5:
                    System.out.println(songList);
                    break;

                case 6:
                    songList.remove(ind);
                    if (ind == songList.size() - 1)
                        ind--;
                    break;

                case 7:
                    System.out.print("Song Title: ");
                    String title = br.readLine();
                    System.out.println();
                    System.out.print("Artist Name: ");
                    String artist = br.readLine();
                    System.out.println();
                    System.out.print("Song Duration: ");
                    String dur = br.readLine();
                    System.out.println();
                    System.out.print("File Name: ");
                    String fnm = br.readLine();
                    System.out.println();
                    String str = title + "/" + artist + "/" + dur + "/" + fnm;
                    addSong(str);
                    break;

                case 8:
                    Collections.shuffle(songList);
                    WriteFile();
                    ind = 0;
                    playa.StopSong();
                    playa = new MusicPlayer("src\\mp3\\" + songList.get(ind).getFileName());
                    playa.PlaySong();
                    playing = true;
                    break;

                case 9:
                    System.out.println("Title: " + songList.get(ind).getTitle());
                    System.out.println("Artist: " + songList.get(ind).getArtist());
                    System.out.println("BPM: " + songList.get(ind).getLen());
                    System.out.println("Enter 1 to go back");
                    int k = 0;
                    flag = 0;
                    while (flag == 0) {
                        try {
                            k = Integer.parseInt(br.readLine());
                            if (k != 1) {
                                throw new ArithmeticException();
                            }
                            flag = 1;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input");
                            flag = 0;
                        } catch (ArithmeticException e) {
                            System.out.println("Invalid input");
                            flag = 0;
                        }
                    }

                    while (k != 1) {
                        System.out.println("Re-enter the correct number");
                        k = Integer.parseInt(br.readLine());
                    }
                    break;

                case 0:
                    System.out.println("Thank you for your time");
                    System.exit(0);
                    break;
            }
            go(ind);
        }
    }
    void getSongs()
    {
        try
        {
            File file = new File("src/com/company/SongList.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                addSong(line);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    void addSong(String lineToParse)
    {
        String[] tokens = lineToParse.split("/");
        Song nextSong = new Song(tokens[0], tokens[1], tokens[2], tokens[3]);
        songList.add(nextSong);
        WriteFile();
    }

    void WriteFile()
    {
        String content="";
        for (int i=0;i<songList.size();i++)
        {
            content = content + songList.get(i).getTitle() + "/" + songList.get(i).getArtist() + "/" + songList.get(i).getLen() + "/" + songList.get(i).getFileName() + "\n";
        }
        try {
            FileWriter myWriter = new FileWriter("src/com/company/SongList.txt");
            myWriter.write(content);
            myWriter.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred");
        }
    }
}
