package models;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

public class MyFrame implements ActionListener
{
    private JButton login;
    private JButton register;
    private Spotify spotify;
    MyFrame(Spotify spotify)
    {

        this.spotify=spotify;
        JFrame frame = new JFrame();
        frame.setTitle("Spotify");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(400,200);
        frame.setVisible(true);

        Container container= frame.getContentPane();
        container.setLayout(null);

        container.setBackground(new Color(19, 19, 18, 255));

        login = new JButton("Log in");
        login.setBounds(100,20,200,40);
        container.add(login);
        login.setBackground(new Color(41, 159, 41));
        login.addActionListener(this);


        register = new JButton("Register");
        register.setBounds(100,80,200,40);
        container.add(register);
        register.setBackground(new Color(41, 159, 41));
        register.addActionListener(this);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == login){
            int result = spotify.login();
            if(result == 0) {
                /// close the starting frame
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(login);
                frame.dispose();
                Scanner scanner = new Scanner(System.in);

                ///new frame
                JFrame newFrame = new JFrame();
                newFrame.setTitle("Spotify");
                newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                newFrame.setResizable(false);
                newFrame.setSize(450,800);
                newFrame.setVisible(true);

                Container newContainer = newFrame.getContentPane();
                newContainer.setLayout(null);

                newContainer.setBackground(new Color(19, 19, 18, 255));

                JButton logout = new JButton("Log out");
                logout.setBounds(100,110,250,40);
                newContainer.add(logout);
                logout.setBackground(new Color(41, 159, 41));
                logout.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // close the current frame
                        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(logout);
                        frame.dispose();

                        // open a new frame for login or registration
                        MyFrame myFrame = new MyFrame(Spotify.getInstance());
                    }
                });


                JButton releaseSong= new JButton("Release Song");
                releaseSong.setBounds(100,60,250,40);
                newContainer.add(releaseSong);
                releaseSong.setBackground(new Color(41, 159, 41));
                releaseSong.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        spotify.releaseSingle();
                    }
                });

                JButton makeNewAccount = new JButton("Create New Account");
                makeNewAccount.setBounds(100,110,250,40);
                newContainer.add(makeNewAccount);
                makeNewAccount.setBackground(new Color(41, 159, 41));
                makeNewAccount.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        spotify.addUser();
                    }
                });

                JButton ReleaseNewAlbum = new JButton("Release new Album");
                ReleaseNewAlbum.setBounds(100,160,250,40);
                newContainer.add(ReleaseNewAlbum);
                ReleaseNewAlbum.setBackground(new Color(41, 159, 41));
                ReleaseNewAlbum.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        spotify.releaseNewAlbum();
                    }
                });


                JButton deleteSongFromPlaylist= new JButton("Delete Song From Playlist");
                deleteSongFromPlaylist.setBounds(100,210,250,40);
                newContainer.add(deleteSongFromPlaylist);
                deleteSongFromPlaylist.setBackground(new Color(41, 159, 41));
                deleteSongFromPlaylist.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        System.out.println("Enter Playlist Name.");
                        System.out.print(">");
                        String playlistName = scanner.nextLine();
                        System.out.println("Enter Song Name.");
                        System.out.print(">");
                        String songName = scanner.nextLine();
                        spotify.deleteSongFromPlaylist(playlistName, songName);
                    }
                });

                JButton showInfoAboutAlbum= new JButton("Show details about an Album");
                showInfoAboutAlbum.setBounds(100,260,250,40);
                newContainer.add(showInfoAboutAlbum);
                showInfoAboutAlbum.setBackground(new Color(41, 159, 41));
                showInfoAboutAlbum.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        System.out.println("Enter Album Name.");
                        System.out.print(">");
                        String albumName= scanner.nextLine();
                        System.out.println("Enter Artist Name.");
                        System.out.print(">");
                        String artist = scanner.nextLine();
                        spotify.printInfoForAlbumByArtistAndTitle(albumName,artist);

                    }
                });

                JButton createPlaylist= new JButton("Create Playlist");
                createPlaylist.setBounds(100,310,250,40);
                newContainer.add(createPlaylist);
                createPlaylist.setBackground(new Color(41, 159, 41));
                createPlaylist.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        spotify.createNewPlaylist();
                    }
                });

                JButton putSongInQueue= new JButton("Add Song In Queue");
                putSongInQueue.setBounds(100,360,250,40);
                newContainer.add(putSongInQueue);
                putSongInQueue.setBackground(new Color(41, 159, 41));
                putSongInQueue.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        System.out.println("Enter Artist Name.");
                        System.out.print(">");
                        String artist = scanner.nextLine();
                        System.out.println("Enter Song Title.");
                        System.out.print(">");
                        String title = scanner.nextLine();

                        spotify.addSongInQueue(artist,title);
                    }
                });


                JButton showAllSongAlbumsPodcasts= new JButton("View all Songs/Albums/Podcasts");
                showAllSongAlbumsPodcasts.setBounds(100,410,250,40);
                newContainer.add(showAllSongAlbumsPodcasts);
                showAllSongAlbumsPodcasts.setBackground(new Color(41, 159, 41));
                showAllSongAlbumsPodcasts.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        spotify.printAllSongsAlbumsAndPodcasts();
                    }
                });

                JButton showQueue =  new JButton("Show queue");
                showQueue.setBounds(100,460,250,40);
                newContainer.add(showQueue);
                showQueue.setBackground(new Color(41, 159, 41));
                showQueue.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        spotify.printQueue();
                    }
                });

                JButton playFromSongInQueue=  new JButton("Skip to a song in queue");
                playFromSongInQueue.setBounds(100,510,250,40);
                newContainer.add(playFromSongInQueue);
                playFromSongInQueue.setBackground(new Color(41, 159, 41));
                playFromSongInQueue.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        spotify.playSongFromQueue();
                    }
                });

                JButton showMyPlaylists =  new JButton("Show my playlists");
                showMyPlaylists.setBounds(100,560,250,40);
                newContainer.add(showMyPlaylists);
                showMyPlaylists.setBackground(new Color(41, 159, 41));
                showMyPlaylists.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        spotify.showPlaylists();
                    }
                });

                JButton exit=  new JButton("Exit");
                exit.setBounds(100,610,250,40);
                newContainer.add(exit);
                exit.setBackground(new Color(41, 159, 41));
                exit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(exit);
                        frame.dispose();
                        frame = (JFrame) SwingUtilities.getWindowAncestor(login);
                        frame.dispose();
                    }
                });

            }
        }
        else
        {
                /// close the starting frame
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(login);
                frame.dispose();
                Scanner scanner = new Scanner(System.in);

                ///new frame
                JFrame newFrame = new JFrame();
                newFrame.setTitle("Spotify");
                newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                newFrame.setResizable(false);
                newFrame.setSize(450,800);
                newFrame.setVisible(true);

                Container newContainer = newFrame.getContentPane();
                newContainer.setLayout(null);

                newContainer.setBackground(new Color(19, 19, 18, 255));

                JButton logout = new JButton("Log out");
                logout.setBounds(100,110,250,40);
                newContainer.add(logout);
                logout.setBackground(new Color(41, 159, 41));
                logout.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // close the current frame
                        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(logout);
                        frame.dispose();

                        // open a new frame for login or registration
                        MyFrame myFrame = new MyFrame(Spotify.getInstance());
                    }
                });


                JButton releaseSong= new JButton("Release Song");
                releaseSong.setBounds(100,60,250,40);
                newContainer.add(releaseSong);
                releaseSong.setBackground(new Color(41, 159, 41));
                releaseSong.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        spotify.releaseSingle();
                    }
                });

                JButton makeNewAccount = new JButton("Create New Account");
                makeNewAccount.setBounds(100,110,250,40);
                newContainer.add(makeNewAccount);
                makeNewAccount.setBackground(new Color(41, 159, 41));
                makeNewAccount.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        spotify.addUser();
                    }
                });

                JButton ReleaseNewAlbum = new JButton("Release new Album");
                ReleaseNewAlbum.setBounds(100,160,250,40);
                newContainer.add(ReleaseNewAlbum);
                ReleaseNewAlbum.setBackground(new Color(41, 159, 41));
                ReleaseNewAlbum.addActionListener(e110 -> spotify.releaseNewAlbum());


                JButton deleteSongFromPlaylist= new JButton("Delete Song From Playlist");
                deleteSongFromPlaylist.setBounds(100,210,250,40);
                newContainer.add(deleteSongFromPlaylist);
                deleteSongFromPlaylist.setBackground(new Color(41, 159, 41));
                deleteSongFromPlaylist.addActionListener(e19 -> {
                    System.out.println("Enter Playlist Name.");
                    System.out.print(">");
                    String playlistName = scanner.nextLine();
                    System.out.println("Enter Song Name.");
                    System.out.print(">");
                    String songName = scanner.nextLine();
                    spotify.deleteSongFromPlaylist(playlistName, songName);
                });

                JButton showInfoAboutAlbum= new JButton("Show details about an Album");
                showInfoAboutAlbum.setBounds(100,260,250,40);
                newContainer.add(showInfoAboutAlbum);
                showInfoAboutAlbum.setBackground(new Color(41, 159, 41));
                showInfoAboutAlbum.addActionListener(e18 -> {
                    System.out.println("Enter Album Name.");
                    System.out.print(">");
                    String albumName= scanner.nextLine();
                    System.out.println("Enter Artist Name.");
                    System.out.print(">");
                    String artist = scanner.nextLine();
                    spotify.printInfoForAlbumByArtistAndTitle(albumName,artist);

                });

                JButton createPlaylist= new JButton("Create Playlist");
                createPlaylist.setBounds(100,310,250,40);
                newContainer.add(createPlaylist);
                createPlaylist.setBackground(new Color(41, 159, 41));
                createPlaylist.addActionListener(e17 -> spotify.createNewPlaylist());

                JButton putSongInQueue= new JButton("Add Song In Queue");
                putSongInQueue.setBounds(100,360,250,40);
                newContainer.add(putSongInQueue);
                putSongInQueue.setBackground(new Color(41, 159, 41));
                putSongInQueue.addActionListener(e16 -> {
                    System.out.println("Enter Artist Name.");
                    System.out.print(">");
                    String artist = scanner.nextLine();
                    System.out.println("Enter Song Title.");
                    System.out.print(">");
                    String title = scanner.nextLine();

                    spotify.addSongInQueue(artist,title);
                });


                JButton showAllSongAlbumsPodcasts= new JButton("View all Songs/Albums/Podcasts");
                showAllSongAlbumsPodcasts.setBounds(100,410,250,40);
                newContainer.add(showAllSongAlbumsPodcasts);
                showAllSongAlbumsPodcasts.setBackground(new Color(41, 159, 41));
                showAllSongAlbumsPodcasts.addActionListener(e15 -> spotify.printAllSongsAlbumsAndPodcasts());

                JButton showQueue =  new JButton("Show queue");
                showQueue.setBounds(100,460,250,40);
                newContainer.add(showQueue);
                showQueue.setBackground(new Color(41, 159, 41));
                showQueue.addActionListener(e14 -> spotify.printQueue());

                JButton playFromSongInQueue=  new JButton("Skip to a song in queue");
                playFromSongInQueue.setBounds(100,510,250,40);
                newContainer.add(playFromSongInQueue);
                playFromSongInQueue.setBackground(new Color(41, 159, 41));
                playFromSongInQueue.addActionListener(e13 -> spotify.playSongFromQueue());

                JButton showMyPlaylists =  new JButton("Show my playlists");
                showMyPlaylists.setBounds(100,560,250,40);
                newContainer.add(showMyPlaylists);
                showMyPlaylists.setBackground(new Color(41, 159, 41));
                showMyPlaylists.addActionListener(e12 -> spotify.showPlaylists());

                JButton exit=  new JButton("Exit");
                exit.setBounds(100,610,250,40);
                newContainer.add(exit);
                exit.setBackground(new Color(41, 159, 41));
                exit.addActionListener(e1 -> {
                    JFrame frame1 = (JFrame) SwingUtilities.getWindowAncestor(exit);
                    frame1.dispose();
                    frame1 = (JFrame) SwingUtilities.getWindowAncestor(login);
                    frame1.dispose();
                });

            }
    }
}
