/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.jademy.domain.service;

/**
 *
 * @author mihai
 */
public class MailNotificationException extends MediaStoreException{
	public MailNotificationException(String message) {
		super(message);
	}
	public MailNotificationException(String message, Throwable cause) {
		super(message, cause);
	}
}
