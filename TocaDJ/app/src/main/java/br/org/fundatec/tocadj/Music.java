package br.org.fundatec.tocadj;

/**
 * Created by tecnico on 20/06/2017.
 */

public class Music {
    private String key;
    private String music;
    private String user;

    public Music(String key, String music, String user) {
        this.key = key;
        this.music = music;
        this.user = user;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String toString(){
        return "Música: "+this.music+"\nUsuário: "+this.user;
    }
}
