package com.learning.java.springIlMioFotoalbum.exeptions;

public class PhotoNotFoundExeption extends RuntimeException {
  public PhotoNotFoundExeption(String message) {
    super(message);
  }
}