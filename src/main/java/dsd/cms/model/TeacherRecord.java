package dsd.cms.model;

import java.io.Serializable;

// TODO: Auto-generated Javadoc

/**
 * The Class TeacherRecord.
 */

public class TeacherRecord implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The first name. */
	private String firstName;

	/** The last name. */
	private String lastName;

	/** The address. */
	private String address;

	/** The phone. */
	private String phone;

	/** The specialization. */
	private String specialization;

	/** The location. */
	private String location;

	/** The id counter. */
	private static int idCounter = 10000;

	/** The record ID. */
	private String recordID;

	/**
	 * Instantiates a new teacher record.
	 *
	 * @param recordID      the record ID
	 * @param firstName     the first name
	 * @param lastName      the last name
	 * @param address       the address
	 * @param phone         the phone
	 * @param specialization the specialization
	 * @param location      the location
	 */
	public TeacherRecord(String recordID, String firstName, String lastName, String address, String phone,
			String specialization, String location) {

		this.recordID = recordID.equalsIgnoreCase("AutoCreate") ? "TR" + idCounter++ : recordID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
		this.specialization = specialization;
		this.location = location;
	}

	/**
	 * Convert to string.
	 *
	 * @param tr the tr
	 * @return the string
	 */
	public String convertToString(TeacherRecord tr) {
		return tr.getRecordID() + ":" + tr.getFirstName() + ":" + tr.getLastName() + ":" + tr.getAddress() + ":"
				+ tr.getPhone() + ":" + tr.getSpecialization() + ":" + tr.getLocation();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public static int getIdCounter() {
		return idCounter;
	}

	public static void setIdCounter(int idCounter) {
		TeacherRecord.idCounter = idCounter;
	}

	public String getRecordID() {
		return recordID;
	}

	public void setRecordID(String recordID) {
		this.recordID = recordID;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
