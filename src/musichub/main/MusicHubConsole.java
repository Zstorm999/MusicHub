package musichub.main;

import java.util.ArrayList;

import musichub.util.AudioXML;
import musichub.business.Playlist;

public class MusicHubConsole {

    AudioXML<Playlist> xmlwriter;

    public MusicHubConsole(){
        xmlwriter = new AudioXML<Playlist>("./files/playlists.xml", Playlist.class);

        ArrayList<Playlist> list = new ArrayList<Playlist>();
        //xmlwriter.loadXML(list);

        /*for(Playlist p : list){
            System.out.println(p);
        }*/

        for(int i=0; i<3; i++){
            list.add(new Playlist("pl_" + i, i));
        }

        xmlwriter.saveXML(list, "Playlists", true);

    }

    public static void main(String[] args) {
        new MusicHubConsole();
    }
}
