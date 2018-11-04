package com.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Administrator
 *	Client class creates a client who is a government employee.
 *	clientId is the primary key of table client in database
 *	
 */
@XmlRootElement
public class Client implements Serializable
{
	private int clientId;
	private String clientName;
	private String clientEmail;
	private String clientPassword;
	private String	clientContactNo;
	
	public Client(int clientId, String clientName, String clientEmail, String clientPassword, String clientContactNo) {
		super();
		this.clientId = clientId;
		this.clientName = clientName;
		this.clientEmail = clientEmail;
		this.clientPassword = clientPassword;
		this.clientContactNo = clientContactNo;
	}
	
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Gets the clientId from database table client
	 * @see #setClientId(int)
	 * @return clientId
	 */
	public int getClientId() {
		return clientId;
	}
	
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	
	/**
	 * Gets the clientName from database table client
	 * @see #setClientName(String)
	 * @return clientName
	 */
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	/**
	 * Gets the clientEmail from database table client
	 * @see #setClientEmail(String)
	 * @return clientEmail
	 */
	public String getClientEmail() {
		return clientEmail;
	}
	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}
	
	
	/**
	 * Gets the clientpassword from database table client
	 * @see #setClientPassword(String)
	 * @return clientPasword
	 */
	public String getClientPassword() {
		return clientPassword;
	}
	public void setClientPassword(String clientPassword) {
		this.clientPassword = clientPassword;
	}
	
	/**
	 * Gets the clientContactNo from database table client
	 * @see #setClientContactNo(String)
	 * @return clientContactNo
	 */
	public String getClientContactNo() {
		return clientContactNo;
	}
	public void setClientContactNo(String clientContactNo) {
		this.clientContactNo = clientContactNo;
	}
	
	/**
	 * Overriding tostring method
	 */
	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", clientName=" + clientName + ", clientEmail=" + clientEmail
				+ ", clientPassword=" + clientPassword + ", clientContactNo=" + clientContactNo + "]";
	}
	
	
}
