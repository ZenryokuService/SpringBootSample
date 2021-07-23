package com.example.demo.data;

public class DataObject {
	private int id;
	private String name;
	private String mail;

	public DataObject(int id, String name, String mail) {
		this.id = id;
		this.name = name;
		this.mail = mail;
	}
	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id セットする id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name セットする name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return mail
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @param mail セットする mail
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}


}
