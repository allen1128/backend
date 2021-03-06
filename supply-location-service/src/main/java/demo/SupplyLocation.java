package demo;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created on 8/13/2017.
 */

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@Document
@RequiredArgsConstructor(onConstructor = @__(@PersistenceConstructor))
public class SupplyLocation {
    private String id;
    private String address1;
    private String address2;
    private String city;

    @JsonIgnore
    @GeoSpatialIndexed
    private final Point location;

    private String state;
    private String zip;
    private String type;

    public SupplyLocation() {
        this.location = new Point(0.0, 0.0);
    }

    @JsonCreator
    public SupplyLocation(@JsonProperty("latitude") double latitude, @JsonProperty("longitude") double longitude){
        this.location = new Point(longitude, latitude);
    }

    public double getLatitude(){
        return this.location.getX();
    }

    public double getLongtitude(){
        return this.location.getY();
    }
}
