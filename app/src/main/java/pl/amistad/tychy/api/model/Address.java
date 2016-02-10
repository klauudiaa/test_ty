package pl.amistad.tychy.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Klaudia on 2016-02-09.
 */
public class Address {

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("city_district")
    @Expose
    private String cityDistrict;

    @SerializedName("construction")
    @Expose
    private String construction;

    @SerializedName("continent")
    @Expose
    private String continent;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("country_code")
    @Expose
    private String countryCode;

    @SerializedName("road")
    @Expose
    private String road;


    @SerializedName("house_number")
    @Expose
    private String houseNumber;

    @SerializedName("neighbourhood")
    @Expose
    private String neighbourhood;

    @SerializedName("postcode")
    @Expose
    private String postcode;

    @SerializedName("public_building")
    @Expose
    private String publicBuilding;

    @SerializedName("state")
    @Expose
    private String state;

    @SerializedName("suburb")
    @Expose
    private String suburb;

    public String getCity() {
        return city;
    }

    public String getCityDistrict() {
        return cityDistrict;
    }

    public String getConstruction() {
        return construction;
    }

    public String getContinent() {
        return continent;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getPublicBuilding() {
        return publicBuilding;
    }

    public String getState() {
        return state;
    }

    public String getSuburb() {
        return suburb;
    }

    public String getRoad() {
        return road;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", cityDistrict='" + cityDistrict + '\'' +
                ", construction='" + construction + '\'' +
                ", continent='" + continent + '\'' +
                ", country='" + country + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", neighbourhood='" + neighbourhood + '\'' +
                ", postcode='" + postcode + '\'' +
                ", publicBuilding='" + publicBuilding + '\'' +
                ", state='" + state + '\'' +
                ", suburb='" + suburb + '\'' +
                '}';
    }
}
