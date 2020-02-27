package com.bridgelabz.fundooappbackend.user.response;
import java.io.Serializable;
/************************************************************************************************************************
 * @author 	:Pramila Tawari
 * Purpose 	:Response for Postman
 *
 *****************************************************************************************************************/
public class Response implements Serializable
{
private static final long serialVersionUID = 1L;
	
	private int status;  
	private String message;
	private Object data;
	private Object userData;

	public Response()
	{
		
	}

	public Response(int status, String message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	public Response(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
	public Response(int status, Object data1, Object data) {
		super();
		this.status = status;
		this.data = data1;
		this.data = data;
	}
	
	public Response(int status,String message, Object data, Object userData) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
		this.userData = userData;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Object getUserData() {
		return userData;
	}

	public void setUserData(Object userData) {
		this.userData = userData;
	}

	@Override
	public String toString() {
		return "Response [status=" + status + ", message=" + message + ", data=" + data + "]";
	}
	
}