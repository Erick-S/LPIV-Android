package br.org.fundatec.linhasonibus;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by tecnico on 06/06/2017.
 */

public class Itinerario  {

    public String idlinha;
    public String nome;
    public String codigo;

    public ArrayList<LatLng> positions = new ArrayList<>();

    public static class LatLng{

        public Double lat;

        public LatLng(Double lat, Double lng) {
            this.lat = lat;
            this.lng = lng;
        }

        public Double lng;

        public String toString(){
            return "Lat: "+lat+"\nLong: "+lng;
        }
    }

}
