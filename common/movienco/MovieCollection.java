package com.movienco;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MovieCollection implements Serializable {
    /**
     *
     */
    final public String[] defaultInputCriteria = {"name","coordinates","oscarscount","goldenpalmcount",
        "totalboxoffice","genre","director"};
    private TreeMap<Integer, Movie> movieCollection = new TreeMap<>();
    private java.time.ZonedDateTime creationDate;
    public String[] inputCriteria;
    private String appPath;

    /** the basic one
     *
     * @param movies
     */
    public MovieCollection(Movie ... movies) {
        for (Movie movie : movies) {
            movieCollection.put(movie.getId(), movie);
        }
        creationDate = ZonedDateTime.now();

    }


    /**size getter
     *
     * @return
     */
    public int getSize() {
        return movieCollection.size();
    }

    /**clears the collection
     *
     */
    public void clear() {
        movieCollection.clear();
    }

    /**gets one from collection
     *
     * @param id
     * @return
     */
    public Movie getMovie(Object id) {
        return movieCollection.get(id);
    }

    /**gets a set of keys
     *
     * @return
     */
    public Set getKeys() {
        return movieCollection.keySet();
    }

    /**info
     *
     * @return
     */
    public String getInfo() {

        NavigableSet<Integer> keys = movieCollection.navigableKeySet();
        int i = movieCollection.size(); int k = 0;
        String[] movies = new String[i]; String finalMovies = "";
        Iterator<Integer> iteration = keys.iterator();
        for (Iterator<Integer> it = iteration; it.hasNext(); ) {
            Integer j = it.next();
            movies[k] = movieCollection.get(j).toString();
            finalMovies = finalMovies + movies[k] + "\n"; k += 1;
        }

        return finalMovies;
    }

    /** gets all the values
     *
     * @return
     */
    public Collection<Movie> getValues() {
        return movieCollection.values();
    }

    /**descending map
     *
     * @return
     */
    public NavigableMap getDescendingMap() {
        return movieCollection.descendingMap();
    }

    /** shows the needed info (no doubts)
     *
     * @return
     */

    /**returns the collection
     *
     * @return
     */
    public String getInitializationInfo() {
        return "Тип коллекции: TreeMap\n" +
                "Размер коллекции: " + movieCollection.size() +
                "\nВремя инициализации: " + DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm").format(creationDate) + "\n";
    }

    public String[] getInfoString() {
        NavigableSet<Integer> keys = movieCollection.navigableKeySet();
        int i = movieCollection.size(); int k = 0;
        String[] movies = new String[i];
        Iterator<Integer> iteration = keys.iterator();
        for (Iterator<Integer> it = iteration; it.hasNext(); ) {
            Integer j = it.next();
            movies[k] = movieCollection.get(j).getInfoString(); k += 1;
        }
        return  movies;
    }

    /**updates the element by id
     *
     * @param id нужен ли?
     * @param m
     */
    public void updateElement(Integer id, Movie m) {
        movieCollection.put(id, m);
    }

    /**adds new one
     *
     * @param id
     * @param s
     */
    public void addElement(Integer id, Movie s) {movieCollection.put(id, s);}

    /**sets the app path
     *
     * @param s
     */
    public void setAppPath(String s) {this.appPath = s;}

    /**gets the app path
     *
     * @return
     */
    public String getAppPath() {return appPath;}

    /**some of course useful stuff
     *
     * @param id
     */
    public void deleteElement(Integer id) {movieCollection.remove(id);}
    public boolean containsElement(String value) {return movieCollection.containsValue(value);}
    public boolean containsKey(Integer key) {return movieCollection.containsKey(key);}
    public Integer getKey (String value) {
        Integer h = null;
        for (Integer it : movieCollection.keySet()) {
            if (movieCollection.get(it).getName().equals(value))
                h = it;
        }
        return h;
    }
    public Integer getTheKeyAbove(Integer k) {return  movieCollection.ceilingKey(k);}
    public Integer getTheKeyBelow (Integer k) {return movieCollection.floorKey(k);}
    public String getCreationDate() {return this.creationDate + "\n";}
}
