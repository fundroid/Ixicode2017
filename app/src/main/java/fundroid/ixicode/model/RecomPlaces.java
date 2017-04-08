package fundroid.ixicode.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Sagar Sagar Verma on 08-04-2017.
 */

public class RecomPlaces implements Serializable {

    private ArrayList<Place> flight;

    public ArrayList<Place> getFlight() {
        return flight;
    }

    public void setFlight(ArrayList<Place> flight) {
        this.flight = flight;
    }
}
