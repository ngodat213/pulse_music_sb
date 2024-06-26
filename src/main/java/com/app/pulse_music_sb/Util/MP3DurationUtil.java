package com.app.pulse_music_sb.Util;

import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.Header;

import java.io.InputStream;

public class MP3DurationUtil {
    public static int getDurationInSeconds(InputStream mp3Stream) {
        int totalSeconds = 0;
        try {
            Bitstream bitstream = new Bitstream(mp3Stream);
            Header frameHeader;
            int totalFrames = 0;
            int totalMS = 0;

            while ((frameHeader = bitstream.readFrame()) != null) {
                totalFrames++;
                totalMS += frameHeader.ms_per_frame();
                bitstream.closeFrame();
            }

            totalSeconds = totalMS / 1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalSeconds;
    }
}
