
package home;

import java.util.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

public class DisplayMovies extends javax.swing.JPanel {
    private ArrayList<Movie> Movies;        // List of movies (either All or Interested)
    private Movie CurrentMovie;             // Current movie object being displayed
    private DefaultListModel movieModel;    // Model for list of movies
    private final String posterFetchURL;    // URL prefix for movie posters
    private MovieJSONEditor movieReader;    // MovieJSONEditor to read movie data file
    private final boolean selectListAll;    // Boolean: T for AllMovies, F for InterestedMovies
    
    /**
     * Default Constructor
     */
    public DisplayMovies() {
        initComponents();
        posterFetchURL = "https://image.tmdb.org/t/p/w300_and_h450_bestv2";
        selectListAll = false;
    }
    
    /**
     * Overloaded Constructor
     * 
     * @param movieReader to read movie data file
     * @param All Boolean - (T = Get All Movies) (F = Get Interested Movies)
     */
    public DisplayMovies(MovieJSONEditor movieReader, boolean All) {
        initComponents();
        posterFetchURL = "https://image.tmdb.org/t/p/w300_and_h450_bestv2";
        this.movieReader = new MovieJSONEditor();
        this.movieReader = movieReader;
        selectListAll = All;
        
        // Get correct list based on "All" parameter
        this.Movies = getMovieList();
        
        // Create movie list model
        movieModel = new DefaultListModel<String>();
        for(Movie m : Movies) {
            movieModel.addElement(m.getTitle());
        }
        movieList.setModel(movieModel);
        movieList.setSelectedIndex(0);      // Automatically select first item in list
    }
    
    
    /**
     * Get specified movie list: all movies or interested movies
     * 
     * @return Specified movie list
     */
    private ArrayList<Movie> getMovieList() {
        if(selectListAll)
            return movieReader.getAllMovies();
        else
            return movieReader.getInterestedMovies();
    }
    
    /**
     * Setup UI for JPanel
     */
    private void displayMovieInfo() {
        
        // Initialize fields with movie data
        movieInterested.setSelected(CurrentMovie.getInterested());
        movieWatched.setSelected(CurrentMovie.getViewed());
        movieTagline.setText(CurrentMovie.getTagline());
        movieDesc.setText(CurrentMovie.getDescription());
        movieDesc.setEditable(false);
        movieDesc.setLineWrap(true);
        movieDesc.setWrapStyleWord(true);
        movieGenre.setText(CurrentMovie.getGenre());
        movieRDate.setText(CurrentMovie.getRDateString());
        movieRunT.setText(CurrentMovie.getRunTString());
        movieTitle.setText(CurrentMovie.getTitle());
        
        // Setup movie poster
        try {
            String movieURL = posterFetchURL + CurrentMovie.getArt();
            URL imageURL = new URL(movieURL);
            InputStream in = imageURL.openStream();
            Image image = ImageIO.read(in);
            
            int height = (int) movieArt.getSize().getHeight();
            int width = (int) movieArt.getSize().getHeight();
            image = image.getScaledInstance(200, 300, Image.SCALE_DEFAULT);
            
            movieArt.setIcon(new ImageIcon(image));
        }
        catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        
    }

    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        movieListContainer = new javax.swing.JPanel();
        movieListScroll = new javax.swing.JScrollPane();
        movieList = new javax.swing.JList<>();
        displayMovieInfoContainer = new javax.swing.JPanel();
        displayMovieStatus = new javax.swing.JPanel();
        movieArt = new javax.swing.JLabel();
        movieInterested = new javax.swing.JCheckBox();
        movieWatched = new javax.swing.JCheckBox();
        displayMovieInfo = new javax.swing.JPanel();
        movieTitle = new javax.swing.JLabel();
        movieRDate = new javax.swing.JLabel();
        movieRunT = new javax.swing.JLabel();
        movieGenre = new javax.swing.JLabel();
        movieTagline = new javax.swing.JLabel();
        movieDescScroll = new javax.swing.JScrollPane();
        movieDesc = new javax.swing.JTextArea();

        movieList.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        movieList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        movieList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                movieListValueChanged(evt);
            }
        });
        movieListScroll.setViewportView(movieList);

        javax.swing.GroupLayout movieListContainerLayout = new javax.swing.GroupLayout(movieListContainer);
        movieListContainer.setLayout(movieListContainerLayout);
        movieListContainerLayout.setHorizontalGroup(
            movieListContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(movieListContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(movieListScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addContainerGap())
        );
        movieListContainerLayout.setVerticalGroup(
            movieListContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(movieListContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(movieListScroll)
                .addContainerGap())
        );

        movieInterested.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        movieInterested.setText("Interested in this movie?");
        movieInterested.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                movieInterestedActionPerformed(evt);
            }
        });

        movieWatched.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        movieWatched.setText("Watched this movie?");
        movieWatched.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                movieWatchedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout displayMovieStatusLayout = new javax.swing.GroupLayout(displayMovieStatus);
        displayMovieStatus.setLayout(displayMovieStatusLayout);
        displayMovieStatusLayout.setHorizontalGroup(
            displayMovieStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayMovieStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(displayMovieStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(displayMovieStatusLayout.createSequentialGroup()
                        .addComponent(movieArt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(movieInterested, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(movieWatched, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        displayMovieStatusLayout.setVerticalGroup(
            displayMovieStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayMovieStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(movieArt, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(movieInterested)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(movieWatched)
                .addContainerGap(111, Short.MAX_VALUE))
        );

        movieTitle.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        movieTitle.setText("There are no movies in this list.");

        movieRDate.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N

        movieRunT.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N

        movieGenre.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N

        movieTagline.setFont(new java.awt.Font("Helvetica Neue", 3, 14)); // NOI18N

        movieDesc.setColumns(20);
        movieDesc.setRows(5);
        movieDescScroll.setViewportView(movieDesc);

        javax.swing.GroupLayout displayMovieInfoLayout = new javax.swing.GroupLayout(displayMovieInfo);
        displayMovieInfo.setLayout(displayMovieInfoLayout);
        displayMovieInfoLayout.setHorizontalGroup(
            displayMovieInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayMovieInfoLayout.createSequentialGroup()
                .addGroup(displayMovieInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(displayMovieInfoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(displayMovieInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(movieTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(movieRDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(displayMovieInfoLayout.createSequentialGroup()
                                .addComponent(movieRunT, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(movieGenre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(movieTagline, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(movieDescScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE))
                .addContainerGap())
        );
        displayMovieInfoLayout.setVerticalGroup(
            displayMovieInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayMovieInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(movieTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(movieRDate, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(displayMovieInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(movieRunT, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(movieGenre, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(movieTagline, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(movieDescScroll)
                .addContainerGap())
        );

        javax.swing.GroupLayout displayMovieInfoContainerLayout = new javax.swing.GroupLayout(displayMovieInfoContainer);
        displayMovieInfoContainer.setLayout(displayMovieInfoContainerLayout);
        displayMovieInfoContainerLayout.setHorizontalGroup(
            displayMovieInfoContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayMovieInfoContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(displayMovieStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(displayMovieInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        displayMovieInfoContainerLayout.setVerticalGroup(
            displayMovieInfoContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayMovieInfoContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(displayMovieInfoContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(displayMovieStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(displayMovieInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(movieListContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(displayMovieInfoContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(displayMovieInfoContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(movieListContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    
    
    /**
     * Update display of movie information based on selected movie in list
     * @param evt 
     */
    private void movieListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_movieListValueChanged
        CurrentMovie = Movies.get(movieList.getSelectedIndex());
        displayMovieInfo();
    }//GEN-LAST:event_movieListValueChanged

    /**
     * Update movie interest status for current movie
     * @param evt 
     */
    private void movieInterestedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_movieInterestedActionPerformed
        // Get currently selected index
        int currentIdx = movieList.getSelectedIndex();

        // Set movie interest status
        if(movieInterested.isSelected())
            CurrentMovie.setInterested(true);
        else {
            CurrentMovie.setInterested(false);
        }
        
        // Update movie object in JSON file
        try {
            movieReader.updateMovie(CurrentMovie);
        }
        catch (Exception e) {
            System.out.println("Uncaught Exeption - Did not update object");
        }
        
        
        // If displaying for watchlist AND movie was removed
        if(!selectListAll && !movieInterested.isSelected())
            UnsceneGUI.refreshGUI();
        else
            movieList.setSelectedIndex(currentIdx);
    }//GEN-LAST:event_movieInterestedActionPerformed

    /**
     * Update movie watched status for current movie
     * @param evt 
     */
    private void movieWatchedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_movieWatchedActionPerformed
        // Get currently selected index
        int currentIdx = movieList.getSelectedIndex();
        
        // Set movie watched status
        if(movieWatched.isSelected())
            CurrentMovie.setViewed(true);
        else
            CurrentMovie.setViewed(false);
        
        // Update movie object in JSON file
        try {
            movieReader.updateMovie(CurrentMovie);
        }
        catch (Exception e) {
            System.out.println("Uncaught Exeption - Did not update object");
        }
        
        // If displaying for watchlist AND movie was removed
        if(!selectListAll && !movieWatched.isSelected()) {
            UnsceneGUI.refreshGUI();
        }
        else
            movieList.setSelectedIndex(currentIdx);
    }//GEN-LAST:event_movieWatchedActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel displayMovieInfo;
    private javax.swing.JPanel displayMovieInfoContainer;
    private javax.swing.JPanel displayMovieStatus;
    private javax.swing.JLabel movieArt;
    private javax.swing.JTextArea movieDesc;
    private javax.swing.JScrollPane movieDescScroll;
    private javax.swing.JLabel movieGenre;
    private javax.swing.JCheckBox movieInterested;
    private javax.swing.JList<String> movieList;
    private javax.swing.JPanel movieListContainer;
    private javax.swing.JScrollPane movieListScroll;
    private javax.swing.JLabel movieRDate;
    private javax.swing.JLabel movieRunT;
    private javax.swing.JLabel movieTagline;
    private javax.swing.JLabel movieTitle;
    private javax.swing.JCheckBox movieWatched;
    // End of variables declaration//GEN-END:variables
}
