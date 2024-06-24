package com.app.pulse_music_sb.Exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String msg){
        super(msg);
    }
}