import java.util.*;

/**
 * Graph class
 *  * @author Gabriel Martins Figueiredo
 */
public class Graph implements GraphInterface<Town, Road> {

    HashSet<Town> towns;
    HashSet<Road> roads;
    HashMap<Town, Integer> distances = new HashMap<>();
    HashMap<Town, Town> nodesBefore = new HashMap<>();


    /**
     * Standard constructor
     */
    public Graph() {
        towns = new HashSet<>();
        roads = new HashSet<>();
    }

    /**
     * Returns an edge connecting sourceTown vertex to target vertex if such
     * vertices and such edge exist in this graph. Otherwise returns
     * null. If any of the specified vertices is null
     * returns null
     *
     * In undirected graphs, the returned edge may have its sourceTown and target
     * vertices in the opposite order.
     *
     * @param sourceVertex sourceTown vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return an edge connecting sourceTown vertex to target vertex.
     */
    @Override
    public Road getEdge(Town sourceVertex, Town destinationVertex) {

        if (sourceVertex == null || destinationVertex == null) {
            return null;
        }

        for (Road road : roads) {
            if (road.sourceTown.equals(sourceVertex) && road.destinationTown.equals(destinationVertex)) {
                return road;
            } else if (road.sourceTown.equals(destinationVertex) && road.destinationTown.equals(sourceVertex)) {
                return road;
            }
        }

        return null;
    }

    /**
     * Creates a new edge in this graph, going from the sourceTown vertex to the
     * target vertex, and returns the created edge.
     *
     * The sourceTown and target vertices must already be contained in this
     * graph. If they are not found in graph IllegalArgumentException is
     * thrown.
     *
     *
     * @param sourceVertex sourceTown vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description for edge
     *
     * @return The newly created edge if added to the graph, otherwise null.
     *
     * @throws IllegalArgumentException if sourceTown or target vertices are not
     * found in the graph.
     * @throws NullPointerException if any of the specified vertices is null.
     */
    @Override
    public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {

        if (towns.contains(sourceVertex) == false || towns.contains(destinationVertex) == false) {
            throw new IllegalArgumentException("Towns not in map. Create towns first");
        }

        if (sourceVertex == null || destinationVertex == null) {
            throw new NullPointerException("Towns not given. Input information for towns");
        }

        Road newRoad = new Road(sourceVertex, destinationVertex, weight, description);
        roads.add(newRoad);

        for (Town town: towns) {
            if (town.equals(sourceVertex))
                town.addToAdjacentTowns(destinationVertex);
            else if (town.equals(destinationVertex))
                town.addToAdjacentTowns(sourceVertex);
        }

        return newRoad;
    }

    /**
     * Adds the specified vertex to this graph if not already present. More
     * formally, adds the specified vertex, v, to this graph if
     * this graph contains no vertex u such that
     * u.equals(v). If this graph already contains such vertex, the call
     * leaves this graph unchanged and returns false. In combination
     * with the restriction on constructors, this ensures that graphs never
     * contain duplicate vertices.
     *
     * @param town vertex to be added to this graph.
     *
     * @return true if this graph did not already contain the specified
     * vertex.
     *
     * @throws NullPointerException if the specified vertex is null.
     */
    @Override
    public boolean addVertex(Town town) {
        if (town == null) {
            throw new NullPointerException("Town not given. Input information for town");
        }

        if (towns.contains(town)) {
            return false;
        } else {
            towns.add(town);
            return true;
        }

    }

    /**
     * Returns true if and only if this graph contains an edge going
     * from the sourceTown vertex to the target vertex. In undirected graphs the
     * same result is obtained when sourceTown and target are inverted. If any of
     * the specified vertices does not exist in the graph, or if is
     * null, returns false.
     *
     * @param sourceVertex sourceTown vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return true if this graph contains the specified edge.
     */
    @Override
    public boolean containsEdge(Town sourceVertex, Town destinationVertex) {

        if (getEdge(sourceVertex, destinationVertex) == null)
            return false;

        else
            return true;
    }

    /**
     * Returns true if this graph contains the specified vertex. More
     * formally, returns true if and only if this graph contains a
     * vertex u such that u.equals(v). If the
     * specified vertex is null returns false.
     *
     * @param town vertex whose presence in this graph is to be tested.
     *
     * @return true if this graph contains the specified vertex.
     */
    @Override
    public boolean containsVertex(Town town) {
        return towns.contains(town);
    }

    /**
     * Returns a set of the edges contained in this graph. The set is backed by
     * the graph, so changes to the graph are reflected in the set. If the graph
     * is modified while an iteration over the set is in progress, the results
     * of the iteration are undefined.
     *
     *
     * @return a set of the edges contained in this graph.
     */
    @Override
    public Set<Road> edgeSet() {
        return roads;
    }

    /**
     * Returns a set of all edges touching the specified vertex (also
     * referred to as adjacent vertices). If no edges are
     * touching the specified vertex returns an empty set.
     *
     * @param vertex the vertex for which a set of touching edges is to be
     * returned.
     *
     * @return a set of all edges touching the specified vertex.
     *
     * @throws IllegalArgumentException if vertex is not found in the graph.
     * @throws NullPointerException if vertex is null.
     */
    @Override
    public Set<Road> edgesOf(Town vertex) {

        HashSet<Road> roadsOf = new HashSet<>();

        for (Road road : roads) {
            if (road.contains(vertex))
                roadsOf.add(road);
        }
        return roadsOf;
    }

    /**
     * Removes an edge going from sourceTown vertex to target vertex, if such
     * vertices and such edge exist in this graph.
     *
     * If weight >- 1 it must be checked
     * If description != null, it must be checked
     *
     * Returns the edge if removed
     * or null otherwise.
     *
     * @param sourceVertex sourceTown vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description of the edge
     *
     * @return The removed edge, or null if no edge removed.
     */
    @Override
    public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {

        if (towns.contains(sourceVertex) == false || towns.contains(destinationVertex) == false) {
            throw new IllegalArgumentException("Towns not in map. Create towns first");
        }

        if (sourceVertex == null || destinationVertex == null) {
            throw new NullPointerException("Towns not given. Input information for towns");
        }

        Road newRoad = new Road(sourceVertex, destinationVertex, weight, description);

        roads.remove(getEdge(sourceVertex, destinationVertex));
        newRoad.sourceTown.removeFromAdjacentTowns(destinationVertex);
        newRoad.destinationTown.removeFromAdjacentTowns(sourceVertex);

        return newRoad;


    }

    /**
     * Removes the specified vertex from this graph including all its touching
     * edges if present. More formally, if the graph contains a vertex
     * u such that u.equals(v), the call removes all edges
     * that touch u and then removes u itself. If no
     * such u is found, the call leaves the graph unchanged.
     * Returns true if the graph contained the specified vertex. (The
     * graph will not contain the specified vertex once the call returns).
     *
     * If the specified vertex is null returns false.
     *
     * @param town vertex to be removed from this graph, if present.
     *
     * @return true if the graph contained the specified vertex;
     * false otherwise.
     */
    @Override
    public boolean removeVertex(Town town) {
        if (town == null) {
            throw new NullPointerException("Town not given. Input information for town");
        }

        if (!(towns.contains(town))) {
            return false;
        } else {
            towns.remove(town);
            return true;
        }
    }

    /**
     * Returns a set of the vertices contained in this graph. The set is backed
     * by the graph, so changes to the graph are reflected in the set. If the
     * graph is modified while an iteration over the set is in progress, the
     * results of the iteration are undefined.
     *
     *
     * @return a set view of the vertices contained in this graph.
     */
    @Override
    public Set<Town> vertexSet() {
        return towns;
    }

    /**
     * Find the shortest path from the sourceVertex to the destinationVertex
     * call the dijkstraShortestPath with the sourceVertex
     * @param sourceVertex starting vertex
     * @param destinationVertex ending vertex
     * @return An arraylist of Strings that describe the path from sourceVertex
     * to destinationVertex
     * They will be in the format: startVertex "via" Edge "to" endVertex weight
     * As an example: if finding path from Vertex_1 to Vertex_10, the ArrayList<String>
     * would be in the following format(this is a hypothetical solution):
     * Vertex_1 via Edge_2 to Vertex_3 4 (first string in ArrayList)
     * Vertex_3 via Edge_5 to Vertex_8 2 (second string in ArrayList)
     * Vertex_8 via Edge_9 to Vertex_10 2 (third string in ArrayList)
     */
    @Override
    public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {



        dijkstraShortestPath(sourceVertex);

        ArrayList<String> arrayList = new ArrayList<>();


        Town node = destinationVertex;



        while (!(arrayList.contains(sourceVertex.name))) {

            Town stepTown = nodesBefore.get(node);

            if (stepTown == null)
                return new ArrayList<>();

            Road stepRoad = getEdge(stepTown, node);

            arrayList.add(stepTown.name + " via " + stepRoad.name + " to " + node.name + " " + stepRoad.distance + " mi");
            node = stepTown;

            if (stepTown.equals(sourceVertex)) {
                break;
            }

        }



        Collections.reverse(arrayList);
        return arrayList;
    }

    /**
     * Dijkstra's Shortest Path Method.  Internal structures are built which
     * hold the ability to retrieve the path, shortest distance from the
     * sourceVertex to all the other vertices in the graph, etc.
     * @param sourceVertex the vertex to find shortest path from
     *
     */
    @Override
    public void dijkstraShortestPath(Town sourceVertex) {

        HashSet<Town> unsettled = new HashSet<>();
        HashSet<Town> settled = new HashSet<>();


        distances.put(sourceVertex, 0);
        unsettled.add(sourceVertex);

        while (!(unsettled.isEmpty())) {

            Town evaluationNode = getLowestDistanceNode(unsettled);
            unsettled.remove(evaluationNode);
            settled.add(evaluationNode);
            evaluateNeighbors(evaluationNode, distances, nodesBefore, unsettled);
        }

    }

    /**
     * Support method
     * @param unsettledNodes
     * @return
     */
    public Town getLowestDistanceNode(HashSet<Town> unsettledNodes) {

        int shortestDistance = Integer.MAX_VALUE;
        Town shortestTown = null;

        for (Town town : unsettledNodes) {
            if (distances.get(town) < shortestDistance) {
                shortestDistance = distances.get(town);
                shortestTown = town;
            }
        }

        return shortestTown;

    }


    /**
     * Support method
     * @param evaluationNode
     * @param distances
     * @param nodesBefore
     * @param unsettled
     */
    public void evaluateNeighbors(Town evaluationNode, HashMap<Town, Integer> distances, HashMap<Town, Town> nodesBefore, HashSet<Town> unsettled) {

        for (Town town : towns) {
            if (town.equals(evaluationNode)) {
                for (Town town2 : town.getAdjacentTowns()) {
                    for (Road road : roads) {

                        if ((road.contains(evaluationNode)) && road.contains(town2)) {


                            if (distances.containsKey(town2)) {
                                if (distances.get(town2) > (distances.get(evaluationNode) + road.distance)) {
                                    distances.put(town2, distances.get(evaluationNode) + road.distance);
                                    nodesBefore.put(town2, evaluationNode);
                                    unsettled.add(town2);
                                }
                            } else {
                                distances.put(town2, distances.get(evaluationNode) + road.distance);
                                nodesBefore.put(town2, evaluationNode);
                                unsettled.add(town2);
                            }

                        }
                    }
                }
            }
        }
    }


}
