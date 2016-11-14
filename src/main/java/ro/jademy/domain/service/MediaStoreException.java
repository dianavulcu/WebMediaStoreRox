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
public abstract class MediaStoreException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MediaStoreException(String message) {
		super(message);
	}

	public MediaStoreException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
