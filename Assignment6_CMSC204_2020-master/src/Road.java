import java.util.Objects;

/**
 * Road class
 * @author Gabriel Martins Figueiredo
 */
public class Road implements Comparable<Road> {

    String name;
    int distance;
    Town sourceTown;
    Town destinationTown;

    /**
     * Constructor
     * @param sourceTown - One town on the road
     * @param destinationTown- Another town on the road
     * @param distance- Weight of the edge, i.e., distance from one town to the other
     * @param name - Name of the road
     */
    public Road(Town sourceTown, Town destinationTown, int distance, String name) {
        this.name = name;
        this.distance = distance;
        this.sourceTown = sourceTown;
        this.destinationTown = destinationTown;
    }

    /**
     * Constructor with weight preset at 1
     * @param sourceTown - One town on the road
     * @param destinationTown - Another town on the road
     * @param name - Name of the road
     */
    public Road(Town sourceTown, Town destinationTown, String name) {
        this.name = name;
        this.distance = 1;
        this.sourceTown = sourceTown;
        this.destinationTown = destinationTown;
    }

    /**
     * Returns true only if the edge contains the given town
     * @param town - a vertex of the graph
     * @return true only if the edge is connected to the given vertex
     */
    public boolean contains(Town town){
        if (sourceTown.equals(town) || destinationTown.equals(town)){
            return true;
        }
        else
            return false;

    }

    /**
     * To string method.
     * @return
     */
    @Override
    public String toString() {
        return "Road{" +
                "name='" + name + '\'' +
                ", distance=" + distance +
                ", sourceTown=" + sourceTown +
                ", destinationTown=" + destinationTown +
                '}';
    }

    /**
     * Name getter
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Source town getter
     * @return
     */
    public Town getSourceTown() {
        return sourceTown;
    }

    /**
     * Destination town getter
     * @return
     */
    public Town getDestinationTown() {
        return destinationTown;
    }

    /**
     * Distance getter
     * @return
     */
    public int getWeight() {
        return distance;
    }

    /**
     *
     * @param secondRoad
     * @return 0 if the road names are the same, a positive or negative number if the road names are not the same
     */
    @Override
    public int compareTo(Road secondRoad) {
        if (((this.sourceTown.compareTo(secondRoad.sourceTown) == 0) &&
                (this.destinationTown.compareTo(secondRoad.destinationTown) == 0))){
            return 0;
        }

        else
            return 1;
    }

    /**
     * Returns true if each of the ends of the road r is the same as the ends of this road. Remember that a road that goes from point A to point B is the same as a road that goes from point B to point A.
     * @param o - road object to compare it to
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Road)) return false;
        Road road = (Road) o;
        if ((Objects.equals(getSourceTown(), road.getSourceTown()) && Objects.equals(getDestinationTown(), road.getDestinationTown()))|| (Objects.equals(getSourceTown(), road.getDestinationTown()) &&
                Objects.equals(getDestinationTown(), road.getSourceTown()))){
            return true;
        }
        else
            return false;

    }


}
