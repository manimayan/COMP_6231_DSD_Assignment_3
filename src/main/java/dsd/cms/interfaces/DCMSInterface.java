package dsd.cms.interfaces;

import java.io.IOException;

import javax.jws.WebMethod;
import javax.jws.WebService;

// TODO: Auto-generated Javadoc
/**
 * The Interface DCMSInterface.
 */
@WebService
public interface DCMSInterface {

	/**
	 * Creates the T record.
	 *
	 * @param recordId       the record id
	 * @param firstName      the first name
	 * @param lastName       the last name
	 * @param address        the address
	 * @param phone          the phone
	 * @param specialization the specialization
	 * @param location       the location
	 * @param managerID      the manager ID
	 * @param transferStatus the transfer status
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@WebMethod
	public String createTRecord(String recordId, String firstName, String lastName, String address, String phone,
			String specialization, String location, String managerID, boolean transferStatus) throws IOException;

	/**
	 * Creates the S record.
	 *
	 * @param recordId         the record id
	 * @param firstName        the first name
	 * @param lastName         the last name
	 * @param courseRegistered the course registered
	 * @param status           the status
	 * @param statusDate       the status date
	 * @param managerID        the manager ID
	 * @param transferStatus   the transfer status
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@WebMethod
	public String createSRecord(String recordId, String firstName, String lastName, String courseRegistered,
			String status, String statusDate, String managerID, boolean transferStatus) throws IOException;

	/**
	 * Gets the record counts.
	 *
	 * @param managerID the manager ID
	 * @return the record counts
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@WebMethod
	public String getRecordCounts(String managerID) throws IOException;

	/**
	 * Edits the record.
	 *
	 * @param recordID  the record ID
	 * @param fieldName the field name
	 * @param newValue  the new value
	 * @param managerID the manager ID
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@WebMethod
	public String editRecord(String recordID, String fieldName, String newValue, String managerID) throws IOException;

	/**
	 * Transfer record.
	 *
	 * @param userName   the user name
	 * @param recordID   the record ID
	 * @param serverName the server name
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@WebMethod
	public String transferRecord(String userName, String recordID, String serverName) throws IOException;

	/**
	 * List record ID.
	 *
	 * @return the string[]
	 */
	@WebMethod
	public String[] listRecordID();

}
