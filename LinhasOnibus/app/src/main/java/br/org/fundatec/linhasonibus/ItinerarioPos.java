package br.org.fundatec.linhasonibus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tecnico on 06/06/2017.
 */

public class ItinerarioPos {
    @SerializedName("lat")
    @Expose
    public String lat;

    @SerializedName("lng")
    @Expose
    public String lng;
}
