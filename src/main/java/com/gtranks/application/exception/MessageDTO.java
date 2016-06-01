package com.gtranks.application.exception;

public class MessageDTO {
	  private String message;
	  
	  public MessageDTO() {
	    super();
	  }
	  
	  public MessageDTO( Exception ex) {
	    super();
	    this.message = ex.getMessage();
	  }

	  public String getMessage() {
	    return message;
	  }
	  
	  public void setMessage(String message) {
	    this.message = message;
	  }
	  
	 
	}