package com.learning.java.springIlMioFotoalbum.exeptions;

public class NotUniqueTitleExeption extends RuntimeException{
  public NotUniqueTitleExeption(String message) {
    super(message);
  }
}
