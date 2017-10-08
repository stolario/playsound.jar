package ua.od.maxz;

import java.io.File;
import java.net.URL;

import javax.sound.sampled.*;


public class Sound {

    public static final String OK = "success.wav";
    public static final String FAIL = "fail.wav";

    public static void ok() {
        play(OK);
    }

    public static void fail() {
        play(FAIL);
    }

    private static void play(String file)  {
        URL resource = Sound.class.getResource("Sound.class");
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream audioInputStream;
            if(resource.toString().startsWith("file")) {
                audioInputStream = AudioSystem.getAudioInputStream(new File("src\\main\\resources\\" + file));
            } else {
                audioInputStream = AudioSystem.getAudioInputStream(Sound.class.getResource("/"+file));
            }
            clip.open(audioInputStream);
            //clip.loop(1);
            clip.start();
            Thread.sleep(1000);
        } catch (Exception e) {
            System.err.println("Error playing sound " + file + ", cause: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        ok();
    }
}
