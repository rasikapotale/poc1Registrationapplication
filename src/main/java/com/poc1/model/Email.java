package com.poc1.model;

public class Email {

    private String sender;
    private String recipient;
    private String subject;
    private String body;
    
    
	public Email() {
		
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Email(String sender, String recipient, String subject, String body) {
		this.sender = sender;
		this.recipient = recipient;
		this.subject = subject;
		this.body = body;
	}
    
    
}
