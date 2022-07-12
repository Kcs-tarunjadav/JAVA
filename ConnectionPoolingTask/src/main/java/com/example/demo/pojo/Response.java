package com.example.demo.pojo;

public class Response {

	private Connection connection;
	private String status;
	private short noOfConn;

	public Response() {
		super();
	}

	public Response(Connection connection, String status, short noOfConn) {
		super();
		this.connection = connection;
		this.status = status;
		this.noOfConn = noOfConn;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public short getNoOfConn() {
		return noOfConn;
	}

	public void setNoOfConn(short noOfConn) {
		this.noOfConn = noOfConn;
	}

	public void increaseNoOfCount() {

		this.noOfConn++;
	}

	public void decreaseNoOfCount() {

		this.noOfConn--;

	}

	@Override
	public String toString() {
		return "Response [connection=" + connection + ", status=" + status + ", noOfConn=" + noOfConn + "]";
	}

}
