import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Town class
 * @author Gabriel Martins Figueiredo
 */
public class Town implements Comparable<Town>{

    protected String name;
    private List<Town> adjacentTowns;

    /**
     * Constructor. Requires town's name.
     * @param name
     */
    public Town(String name) {
        this.name = name;
        this.adjacentTowns = new LinkedList<>();
    }

    /**
     * Copy constructor.
     * @param templateTown - an instance of Town
     */
    public Town(Town templateTown){
        this.name = templateTown.name;
        this.adjacentTowns = templateTown.adjacentTowns;
    }

    /**
     * Name getter
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Adjacent towns list getter
     * @return
     */
    public List<Town> getAdjacentTowns() {
        return adjacentTowns;
    }

    /**
     * Adds town to adjacency list
     * @param adjacentTown
     */
    public void addToAdjacentTowns(Town adjacentTown) {
        this.adjacentTowns.add(adjacentTown);
    }

    /**
     * Removes town from adjacency list
     * @param adjacentTown
     */
    public void removeFromAdjacentTowns(Town adjacentTown) {
        this.adjacentTowns.remove(adjacentTown);
    }


    /**
     *
     * @return the hashcode for the name of the town
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     *
     * @param obj
     * @return true if the town names are equal, false if not
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Town town = (Town) obj;
        return Objects.equals(name, town.name);
    }

    /**
     * to string method
     * @return
     */
    @Override
    public String toString() {
        return "Town: " + name;
    }

    /**
     *
     * @param secondTown
     * @return
     */
    @Override
    public int compareTo(Town secondTown) {
        return this.name.compareTo(secondTown.name);
    }
}
