package com.company;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.LineEvent.Type;

public class playClip {
    public static void playTheClip(File clipFile) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        //Warten bis Audio-Clip fertig ist
        class AudioListener implements LineListener {
            private boolean done = false;
            @Override
            public synchronized void update(LineEvent event) {
                Type eventType = event.getType();
                if (eventType == Type.STOP || eventType == Type.CLOSE) {
                    done = true;
                    notifyAll();
                }
            }
            public synchronized void waitUntilDone() throws InterruptedException {
                while (!done) { wait(); }
            }
        }
        //Audio-Clip ausführen
        AudioListener audioListener = new AudioListener();
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(clipFile)) {
            Clip clip = AudioSystem.getClip();
            try (clip) {
                clip.addLineListener(audioListener);
                clip.open(audioInputStream);
                clip.start();
                audioListener.waitUntilDone();
            }
        }
    }
}

