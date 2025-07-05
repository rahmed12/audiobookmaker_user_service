package com.agent404.audiobook.userservice.userservice.exception;

public class DuplicateEmailExceptoin extends RuntimeException {
    public DuplicateEmailExceptoin( String msg ) {
        super(msg);
    }

}
