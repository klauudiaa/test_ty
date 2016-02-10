package pl.amistad.tychy.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Klaudia on 2016-02-09.
 */
public class NominatimObject {

    @SerializedName("address")
    @Expose
    private Address address;

    @SerializedName("boundingbox")
    @Expose
    private List<String> boundingbox = new ArrayList<String>();

    @SerializedName("class")
    @Expose
    private String _class;

    @SerializedName("display_name")
    @Expose
    private String displayName;

    @SerializedName("importance")
    @Expose
    private double importance;

    @SerializedName("lat")
    @Expose
    private double lat;

    @SerializedName("licence")
    @Expose
    private String licence;

    @SerializedName("lon")
    @Expose
    private double lon;

    @SerializedName("osm_id")
    @Expose
    private long osmId;

    @SerializedName("osm_type")
    @Expose
    private String osmType;

    @SerializedName("place_id")
    @Expose
    private long placeId;

    @SerializedName("svg")
    @Expose
    private String svg;

    @SerializedName("type")
    @Expose
    private String type;

    public String get_class() {
        return _class;
    }

    public Address getAddress() {
        return address;
    }

    public List<String> getBoundingbox() {
        return boundingbox;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getImportance() {
        return importance;
    }

    public double getLat() {
        return lat;
    }

    public String getLicence() {
        return licence;
    }

    public double getLon() {
        return lon;
    }

    public long getOsmId() {
        return osmId;
    }

    public String getOsmType() {
        return osmType;
    }

    public long getPlaceId() {
        return placeId;
    }

    public String getSvg() {
        return svg;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "NominatimObject{" +
                "_class='" + _class + '\'' +
                ", address=" + address +
                ", boundingbox=" + boundingbox +
                ", displayName='" + displayName + '\'' +
                ", importance=" + importance +
                ", lat=" + lat +
                ", licence='" + licence + '\'' +
                ", lon=" + lon +
                ", osmId=" + osmId +
                ", osmType='" + osmType + '\'' +
                ", placeId=" + placeId +
                ", svg='" + svg + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
