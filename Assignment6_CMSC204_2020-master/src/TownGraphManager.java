import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Manager class
 *  * @author Gabriel Martins Figueiredo
 */
public class TownGraphManager implements TownGraphManagerInterface {

    Graph graph = new Graph();

    /**
     * Adds a road with 2 towns and a road name
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @param roadName name of road
     * @return true if the road was added successfully
     */
    @Override
    public boolean addRoad(String town1, String town2, int weight, String roadName) {

        Town townA = new Town(town1);
        Town townB = new Town(town2);

        if (graph.addEdge(townA, townB, weight, roadName) != null)
            return true;

        return false;
    }

    /**
     * Returns the name of the road that both towns are connected through
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @return name of road if town 1 and town2 are in the same road, returns null if not
     */
    @Override
    public String getRoad(String town1, String town2) {

        Town townA = new Town(town1);
        Town townB = new Town(town2);

        return graph.getEdge(townA, townB).name;
    }

    /**
     * Adds a town to the graph
     * @param v the town's name  (lastname, firstname)
     * @return true if the town was successfully added, false if not
     */
    @Override
    public boolean addTown(String v) {

        Town townA = new Town(v);
        return graph.addVertex(townA);

    }

    /**
     * Gets a town with a given name
     * @param name the town's name
     * @return the Town specified by the name, or null if town does not exist
     */
    @Override
    public Town getTown(String name) {
        Town townA = new Town(name);
        return townA;
    }

    /**
     * Determines if a town is already in the graph
     * @param v the town's name
     * @return true if the town is in the graph, false if not
     */
    @Override
    public boolean containsTown(String v) {

        Town townA = new Town(v);
        return graph.containsVertex(townA);
    }

    /**
     * Determines if a road is in the graph
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @return true if the road is in the graph, false if not
     */
    @Override
    public boolean containsRoadConnection(String town1, String town2) {

        Town townA = new Town(town1);
        Town townB = new Town(town2);

        return graph.containsEdge(townA, townB);
    }

    /**
     * Creates an arraylist of all road titles in sorted order by road name
     * @return an arraylist of all road titles in sorted order by road name
     */
    @Override
    public ArrayList<String> allRoads() {

        ArrayList<String> allRoads = new ArrayList<>();

        for (Road road: graph.edgeSet()) {
            allRoads.add(road.name);
        }

        Collections.sort(allRoads);
        return allRoads;

    }

    /**
     * Deletes a road from the graph
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @param road the road name
     * @return true if the road was successfully deleted, false if not
     */
    @Override
    public boolean deleteRoadConnection(String town1, String town2, String road) {

        Town townA = new Town(town1);
        Town townB = new Town(town2);


        if (graph.removeEdge(townA, townB, graph.getEdge(townA,townB).distance, road) != null)
            return true;

        return false;
    }

    /**
     * Deletes a town from the graph
     * @param v name of town (lastname, firstname)
     * @return true if the town was successfully deleted, false if not
     */
    @Override
    public boolean deleteTown(String v) {
        Town townA = new Town(v);

        return graph.removeVertex(townA);
    }

    /**
     * Creates an arraylist of all towns in alphabetical order (last name, first name)
     * @return an arraylist of all towns in alphabetical order (last name, first name)
     */
    @Override
    public ArrayList<String> allTowns() {

        ArrayList<String> allTowns = new ArrayList<>();

        for (Town town: graph.towns) {
            allTowns.add(town.name);
        }

        Collections.sort(allTowns);
        return allTowns;
    }

    /**
     * Returns the shortest path from town 1 to town 2
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @return an Arraylist of roads connecting the two towns together, null if the
     * towns have no path to connect them.
     */
    @Override
    public ArrayList<String> getPath(String town1, String town2) {

        Town townA = new Town(town1);
        Town townB = new Town(town2);

        return graph.shortestPath(townA,townB);
    }

    /**
     * Fill graph with given text file
     * @param selectedFile
     * @throws FileNotFoundException
     */
    public void populateTownGraph(File selectedFile) throws FileNotFoundException {

        Scanner file;
        try {
            file = new Scanner(selectedFile);
        } catch (FileNotFoundException e){
            throw new FileNotFoundException("File not found");
        }

        while (file.hasNextLine()){
            Scanner scanner = new Scanner(file.nextLine());
            String[] strings = scanner.next().split(";");
            String[] strings2 = strings[0].split(",");

            Town townA = new Town(strings[1]);
            Town townB = new Town(strings[2]);

            graph.addVertex(townA);
            graph.addVertex(townB);
            graph.addEdge(townA,townB,Integer.parseInt(strings2[1]),strings2[0]);
        }


    }
}
