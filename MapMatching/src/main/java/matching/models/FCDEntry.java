package matching.models;

import com.graphhopper.util.GPXEntry;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class FCDEntry extends GPXEntry {
    public static final String HEADER = "tid,latitude,longitude,date_time,edge_id";
    private double speed;
    private int edge_id;

    public FCDEntry(GPXEntry e) {
        this(e.lat, e.lon, e.ele, e.getTime(), 0);
    }

    public FCDEntry(double lat, double lon, double ele, long millis, double speed) {
        super(lat, lon, ele, millis);
        this.speed = speed;
    }

    public FCDEntry(double lat, double lon, double ele, long millis, double speed, int edge_id) {
        super(lat, lon, ele, millis);
        this.speed = speed;
        this.edge_id = edge_id;
    }

    /**
     * The speed in kilometers per hour.
     */
    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public int hashCode() {
        return 59 * super.hashCode() + ((int)speed ^ ((int)speed >>> 32));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        final FCDEntry other = (FCDEntry) obj;
        return speed == other.speed && super.equals(obj);
    }

    @Override
    public String toString() {
        LocalDateTime ldt = LocalDateTime.ofInstant(Instant.ofEpochMilli(this.getTime()), ZoneId.of("GMT+8"));
        return this.lat + ", " + this.lon + ", " + ldt + ", " + edge_id;
    }
}
