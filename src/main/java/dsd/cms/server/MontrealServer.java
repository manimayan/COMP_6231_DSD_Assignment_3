package dsd.cms.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import dsd.cms.interfaces.DCMSInterface;
import dsd.cms.model.StudentRecord;
import dsd.cms.model.TeacherRecord;
import dsd.cms.utils.Constants;
import dsd.cms.utils.LogWriter;

// TODO: Auto-generated Javadoc
/**
 * The Class MontrealServer.
 */
@WebService(endpointInterface = Constants.ENDPOINT_INTERFACE)
public class MontrealServer implements DCMSInterface {

	/** The main server. */
	MainServer mainServer;

	/** The teacher student records. */
	public ConcurrentHashMap<String, ConcurrentHashMap<String, Object>> teacherStudentRecords;

	/** The log writer. */
	static LogWriter logWriter = new LogWriter();
	
	/** The Constant TRIM_INPUT. */
	static final String TRANSFER_TRIM_INPUT = "\\s*:\\s*";

		/**
	 * Instantiates a new montreal server.
	 *
	 */
	public MontrealServer() {
		super();
		this.mainServer = new MainServer();
		this.teacherStudentRecords = this.mainServer.loadData("src/main/resources/MontrealData.txt");
	}

	/**
	 * This is used to connect and retrieve data from server with specified port.
	 *
	 * @param port              port of the server to which communication has to be
	 *                          performed
	 * @param montrealServerArg the montreal server arg
	 */
	public void serverConnection(int port, MontrealServer montrealServerArg) {

		MontrealServer montrealServer = montrealServerArg;
		Endpoint montrealServerEndPoint = Endpoint.publish(Constants.MTL_ENDPOINT_URL, montrealServer);
		System.out.println("Montreal Server started : " + montrealServerEndPoint.isPublished());

		System.out.println("Montreal server is loaded with initial data. Map size : " + teacherStudentRecords.size());

		logWriter.serverInfo(LogWriter.MONTREAL, LogWriter.SYSTEM, LogWriter.PHASE_STARTUP, "Montreal server started");
		logWriter.serverInfo(LogWriter.MONTREAL, LogWriter.SYSTEM, LogWriter.PHASE_STARTUP, "Initial data loaded.");

		while (true) {
			try (DatagramSocket ds = new DatagramSocket(port)) {

				byte[] receive = new byte[65535];
				DatagramPacket dp = new DatagramPacket(receive, receive.length);
				ds.receive(dp);
				byte[] data = dp.getData();
				String serviceName = new String(data);
				String outputStr = "";
				if (serviceName.trim().equalsIgnoreCase("getRecordCounts")) {
					outputStr = "MTL : " + this.mainServer.getRecordCount(this.teacherStudentRecords);
				}
				DatagramPacket dp1 = new DatagramPacket(outputStr.getBytes(), outputStr.length(), dp.getAddress(),
						dp.getPort());
				ds.send(dp1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

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
	 * @param clientId       the client id
	 * @param transferStatus the transfer status
	 * @return the string
	 */
	@Override
	public synchronized String createTRecord(String recordId, String firstName, String lastName, String address,
			String phone, String specialization, String location, String clientId, boolean transferStatus) {

		String resultMsg = null;
		String outputType = (transferStatus) ? " transferred to " : " created in ";

		logWriter.serverInfo(LogWriter.MONTREAL, clientId, LogWriter.PHASE_REQUEST,
				"Teacher record creation requested with : (First Name : " + firstName + ", Last Name : " + lastName
						+ ", Address : " + address + ", Phone : " + phone + ", Specialization : " + specialization
						+ ", Location : " + location + ")");

		if (this.mainServer.findDuplicateRecordInmap(recordId, this.teacherStudentRecords)) {
			logWriter.serverInfo(LogWriter.DOLLARD, clientId, LogWriter.PHASE_REQUEST,
					recordId + "is already existing");
			resultMsg = recordId + "is already existing";
		} else {
			TeacherRecord teacherRecord = new TeacherRecord(recordId, firstName, lastName, address, phone,
					specialization, location);

			resultMsg = this.mainServer.persistTeacherRecordInMap(teacherRecord, this.teacherStudentRecords)
					? "Teacher record " + teacherRecord.getRecordID() + outputType + "Montreal location with name : "
							+ firstName + " " + lastName
					: "Error in creating Teacher Record.";
			logWriter.serverInfo(LogWriter.MONTREAL, clientId, LogWriter.PHASE_RESPONSE, resultMsg);
		}
		return resultMsg;
	}

	/**
	 * Creates the S record.
	 *
	 * @param recordId         the record id
	 * @param firstName        the first name
	 * @param lastName         the last name
	 * @param courseRegistered the course registered
	 * @param status           the status
	 * @param statusDate       the status date
	 * @param clientId         the client id
	 * @param transferStatus   the transfer status
	 * @return the string
	 */
	@Override
	public synchronized String createSRecord(String recordId, String firstName, String lastName,
			String courseRegistered, String status, String statusDate, String clientId, boolean transferStatus) {

		String resultMsg = null;
		String outputType = (transferStatus) ? " transferred to " : " created in ";

		logWriter.serverInfo(LogWriter.MONTREAL, clientId, LogWriter.PHASE_REQUEST,
				"Teacher record creation requested with : (First Name : " + firstName + ", Last Name : " + lastName
						+ ", Courses Registered : " + courseRegistered + ", Status : " + status + ", Status Date : "
						+ statusDate + ")");
		if (this.mainServer.findDuplicateRecordInmap(recordId, this.teacherStudentRecords)) {
			logWriter.serverInfo(LogWriter.DOLLARD, clientId, LogWriter.PHASE_REQUEST,
					recordId + "is already existing");
			resultMsg = recordId + "is already existing";
		} else {
			StudentRecord studentRecord = new StudentRecord(recordId, firstName, lastName, courseRegistered, status,
					statusDate);
			resultMsg = this.mainServer.persistStudentRecordInMap(studentRecord, this.teacherStudentRecords)
					? "Student record " + studentRecord.getRecordID() + outputType + "Montreal location with name : "
							+ firstName + " " + lastName
					: "Error in creating Student Record.";
			logWriter.serverInfo(LogWriter.MONTREAL, clientId, LogWriter.PHASE_RESPONSE, resultMsg);
		}
		return resultMsg;
	}

	/**
	 * Edits the record.
	 *
	 * @param recordID  the record ID
	 * @param fieldName the field name
	 * @param newValue  the new value
	 * @param clientId  the client id
	 * @return the string
	 */
	@Override
	public synchronized String editRecord(String recordID, String fieldName, String newValue, String clientId) {

		logWriter.serverInfo(LogWriter.MONTREAL, clientId, LogWriter.PHASE_REQUEST,
				"Edit Record requested for record with ID : " + recordID + ", Edit Field : " + fieldName
						+ ", New Value : " + newValue);

		String resultMsg = this.mainServer.updateRecordInMap(recordID, fieldName, newValue, this.teacherStudentRecords);

		System.out.println(resultMsg);
		logWriter.serverInfo(LogWriter.MONTREAL, clientId, LogWriter.PHASE_RESPONSE, resultMsg);
		return resultMsg;
	}

	/**
	 * Gets the record counts.
	 *
	 * @param clientId the client id
	 * @return the record counts
	 */
	@Override
	public synchronized String getRecordCounts(String clientId) {

		logWriter.serverInfo(LogWriter.MONTREAL, clientId, LogWriter.PHASE_REQUEST,
				"Request to get record count of teachers and student from all three server locations.");

		String server1 = "MTL : " + this.mainServer.getRecordCount(teacherStudentRecords);
		String server2 = this.mainServer.datafromOtherServers(8881);
		String server3 = this.mainServer.datafromOtherServers(8882);

		String resultMsg = (server1 != null && server2 != null && server3 != null)
				? server1 + ", " + server2 + ", " + server3
				: "Error in retrieving record count. Please try again.";

		System.out.println(resultMsg);
		logWriter.serverInfo(LogWriter.MONTREAL, clientId, LogWriter.PHASE_RESPONSE, resultMsg);
		return resultMsg;
	}

	/**
	 * Transfer record.
	 *
	 * @param userName the user name
	 * @param recordID the record ID
	 * @param serverName the server name
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public String transferRecord(String userName, String recordID, String serverName) throws IOException {
		String recordFound = this.mainServer.findAndRemoveRecordInmap(recordID, LogWriter.MONTREAL, this.teacherStudentRecords);
		String transferStatus = null;
		if (!recordFound.equalsIgnoreCase("null")) {

			if (recordID.toUpperCase().startsWith("TR")) {

				String[] teacherRecordArray = recordFound.trim().split(TRANSFER_TRIM_INPUT);
				transferStatus = this.mainServer.getServer(serverName).createTRecord(teacherRecordArray[0], teacherRecordArray[1],
						teacherRecordArray[2], teacherRecordArray[3], teacherRecordArray[4], teacherRecordArray[5],
						teacherRecordArray[6], userName, true);
			}

			if (recordID.toUpperCase().startsWith("SR")) {

				String[] studentRecordArray = recordFound.trim().split(TRANSFER_TRIM_INPUT);
				transferStatus = this.mainServer.getServer(serverName).createSRecord(studentRecordArray[0], studentRecordArray[1],
						studentRecordArray[2], studentRecordArray[3], studentRecordArray[4], studentRecordArray[5],
						userName, true);
			}
		} else {
			transferStatus = recordID.toUpperCase() + " Not Found to transfer";
		}
		return transferStatus;
	}

	/**
	 * List record ID.
	 *
	 * @return the list
	 */
	public String[] listRecordID() {
		List<String> listOfRecordID = new ArrayList<>();
		for (Entry<String, ConcurrentHashMap<String, Object>> entry : teacherStudentRecords.entrySet()) {
			for (Entry<String, Object> innerEntry : entry.getValue().entrySet()) {
				listOfRecordID.add(innerEntry.getKey());
			}
		}
		return listOfRecordID.stream().toArray(String[]::new);
	}
}