package ua.od.maxz;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.*;


public class Sound {

    private static final Object lock = new Object();

    private static final String OK = "success.wav";
    private static final String FAIL = "fail.wav";

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
            try (AudioInputStream audioInputStream = getAudioInputStream(file, resource)) {
                clip.open(audioInputStream);

                clip.addLineListener(event -> {
                    if (event.getType() == LineEvent.Type.STOP || event.getType() == LineEvent.Type.CLOSE) {
                        synchronized (lock) {
                            lock.notify();
                        }
                    }
                });
                clip.start();
                synchronized (lock) {
                    lock.wait();
                }
            }
        } catch (Exception e) {
            System.err.println("Error playing sound " + file + ", cause: " + e.getMessage());
        }
    }

    private static AudioInputStream getAudioInputStream(String file, URL resource) throws UnsupportedAudioFileException, IOException {
        AudioInputStream audioInputStream;
        if(resource.toString().startsWith("file")) {
            audioInputStream = AudioSystem.getAudioInputStream(new File("src\\main\\resources\\" + file));
        } else {
            audioInputStream = AudioSystem.getAudioInputStream(Sound.class.getResource("/"+file));
        }
        return audioInputStream;
    }

    public static void main(String[] args) throws Exception {
        fail();
        ok();
    }
}
