package dsd.cms.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dsd.cms.utils.LogWriter;
import dsd.cms.utils.Validator;
import dsd.cms.wsdlclasses.DCMSInterface;
import dsd.cms.wsdlclasses.DollardServerService;
import dsd.cms.wsdlclasses.LavalServerService;
import dsd.cms.wsdlclasses.MontrealServerService;

/**
 * The Class ManagerClient.
 */
public class ManagerClient {

	/** The Constant TRIM_INPUT. */
	static final String TRANSFER_TRIM_INPUT = "\\s*:\\s*";

	/** The buffer reader. */
	static BufferedReader bufferReader;

	/** The server instance. */
	static DCMSInterface serverInstance;

	/** The validate. */
	static Validator validate = new Validator();

	/** The log. */
	static LogWriter log = new LogWriter();

	/**
	 * main method to run the client side of Class Management System.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			bufferReader = new BufferedReader(new InputStreamReader(System.in));

			while (true) {
				System.out.println("\nDistributed Class Management System\n===================================\n"
						+ "\nCreate an User to interact with the system" + "\n Format: MTL**** or LVL**** or DDO****"
						+ "\n\t *****:numbers\n" + "\nEnter an User Name:");
				String userName = bufferReader.readLine().trim();
				if (userName != null && validate.userName(userName)) {
					String managerPrefix = userName.substring(0, 3).toUpperCase();
					String choice;
					Matcher matcher = null;
					do {
						System.out.println(
								"\nChoose any of the options below to access the system\n1 : Create a Teacher Record\n2 : Create a Student Record"
										+ "\n3 : Edit a Record\n4 : Get Record Count\n5 : Transfer Record\nC : To create new user\nSelect : ");
						choice = bufferReader.readLine().trim().toUpperCase();
						log.clientInfo(userName, LogWriter.ACCESS_SYSTEM, choice);
						switch (choice) {
						case "1":
							// create teacher record
							log.clientInfo(userName, LogWriter.PHASE_TR, "selected Teacher Record");
							createTeacherRecord(userName, managerPrefix, bufferReader);
							break;
						case "2":
							// create student record
							log.clientInfo(userName, LogWriter.PHASE_SR, "selected Student Record");
							createStudentRecord(userName, managerPrefix, bufferReader);
							break;
						case "3":
							// edit a record
							log.clientInfo(userName, LogWriter.PHASE_ER, "selected Edit Record");
							editRecord(userName, managerPrefix, bufferReader);
							break;
						case "4":
							// get record count
							log.clientInfo(userName, LogWriter.PHASE_GRC, "selected getRecordCount");
							createServerInstance(managerPrefix);
							String recordCount = serverInstance.getRecordCounts(userName);
							System.out.println(recordCount);
							log.clientInfo(userName, LogWriter.PHASE_GRC, recordCount);
							break;
						case "5":
							// transfer record
							log.clientInfo(userName, LogWriter.PHASE_TRFR, "selected transferRecord");
							transferRecord(userName, managerPrefix, bufferReader);
							break;
						case "C":
							break;
						default:
							System.out.println("Invalid Choice");
							log.clientInfo(userName, LogWriter.ACCESS_SYSTEM, "Invalid Choice");
							continue;
						}
						Pattern pattern = Pattern.compile("^[1-2-3-4]{1}");
						matcher = pattern.matcher(choice);
					} while (matcher != null && matcher.matches() && !choice.equalsIgnoreCase("C"));
				} else {
					System.out.println("===== user name is invalid =====");
				}
			}
		} catch (Exception e) {
			System.out.println("Something went wrong while running manager client.");
			e.printStackTrace();
		}
	}

	/**
	 * Creates the teacher record.
	 *
	 * @param userName      the user name
	 * @param managerPrefix the manager prefix
	 * @param bufferReader  the buffer reader
	 * @throws IOException                            Signals that an I/O exception
	 *                                                has occurred.
	 * @throws IOException                            Signals that an I/O exception
	 *                                                has occurred.
	 * @throws dsd.cms.wsdlclasses.IOException 
	 */
	private static void createTeacherRecord(String userName, String managerPrefix, BufferedReader bufferReader)
			throws IOException, dsd.cms.wsdlclasses.IOException {
		System.out.println("Enter teacher record inputs in the following format\n");
		System.out.println(
				"FirstName, LastName, Address, Phone, Specialization(Format: JAVA|HTML5|PHP), location (Format : MTL or LVL or DDO)");
		System.out.println("Example: Kevin, Peterson, 23,Lords street,London-987, 9023123445, Java|Python|Scala, MTL");
		System.out.println("\nenter a teacher record : ");
		String trInput = bufferReader.readLine().trim();

		List<String> trOutput = validate.teacherRecord(userName, trInput);

		if (trOutput != null) {
			createServerInstance(managerPrefix);
			String teacherSaveStatus = serverInstance.createTRecord("AutoCreate", trOutput.get(0), trOutput.get(1),
					trOutput.get(2), trOutput.get(3), trOutput.get(4), trOutput.get(5), userName, false);
			System.out.println(teacherSaveStatus);
			log.clientInfo(userName, LogWriter.PHASE_TR, teacherSaveStatus);
		}
	}

	/**
	 * Creates the student record.
	 *
	 * @param userName      the user name
	 * @param managerPrefix the manager prefix
	 * @param bufferReader  the buffer reader
	 * @throws IOException                            Signals that an I/O exception
	 *                                                has occurred.
	 * @throws IOException                            Signals that an I/O exception
	 *                                                has occurred.
	 * @throws dsd.cms.wsdlclasses.IOException 
	 */
	private static void createStudentRecord(String userName, String managerPrefix, BufferedReader bufferReader)
			throws IOException, dsd.cms.wsdlclasses.IOException {
		System.out.println("Enter Student record inputs in the following format\n");
		System.out.println(
				"FirstName, LastName, CourseRegistered(Format: JAVA|HTML5|PHP), Status(Format: 1-Active or 0-InActive), StatusDate(Format: yyyy-mm-dd)");
		System.out.println("Example: James, Anderson, Java|Python|Scala, 1, 2021-12-22");
		System.out.println("\nenter a Student record : ");
		String stInput = bufferReader.readLine().trim();

		List<String> stOutput = validate.studentRecord(userName, stInput);

		if (stOutput != null) {
			createServerInstance(managerPrefix);
			String studentSaveStatus = serverInstance.createSRecord("AutoCreate", stOutput.get(0), stOutput.get(1),
					stOutput.get(2), stOutput.get(3), stOutput.get(4), userName, false);
			System.out.println(studentSaveStatus);
			log.clientInfo(userName, LogWriter.PHASE_SR, studentSaveStatus);
		}
	}

	/**
	 * Edits the record.
	 *
	 * @param userName      the user name
	 * @param managerPrefix the manager prefix
	 * @param bufferReader  the buffer reader
	 * @throws IOException                            Signals that an I/O exception
	 *                                                has occurred.
	 * @throws IOException                            Signals that an I/O exception
	 *                                                has occurred.
	 * @throws dsd.cms.wsdlclasses.IOException 
	 */
	private static void editRecord(String userName, String managerPrefix, BufferedReader bufferReader)
			throws IOException, dsd.cms.wsdlclasses.IOException {
		System.out.println("\nEnter input to edit a record in the following format");
		System.out.println("\nRecordId, fieldName, newValue");
		System.out.println("\nTeacher Record Format: (\"TR*****\", \"ADDRESS or PHONE or LOCATION\", \"newValue\")");
		System.out.println("Example : (TR12345, PHONE, 9023412231)");
		System.out.println(
				"\nStudent Record Format: (\"SR*****\", \"COURSEREGISTERED or STATUS or STATUSDATE\", \"newValue\")");
		System.out.println("Example: (SR12345, COURSEREGISTERED, JAVA|HTML5|PHP)");
		System.out.println("\nenter a record to edit : ");
		String erInput = bufferReader.readLine().trim();

		List<String> erOutput = validate.editRecord(userName, erInput);

		if (erOutput != null) {
			createServerInstance(managerPrefix);
			String editStatus = serverInstance.editRecord(erOutput.get(0), erOutput.get(1), erOutput.get(2), userName);
			System.out.println(editStatus);
			log.clientInfo(userName, LogWriter.PHASE_ER, editStatus);
		}
	}

	/**
	 * Transfer record.
	 *
	 * @param userName      the user name
	 * @param managerPrefix the manager prefix
	 * @param bufferReader  the buffer reader
	 * @throws IOException                            Signals that an I/O exception
	 *                                                has occurred.
	 * @throws IOException                            Signals that an I/O exception
	 *                                                has occurred.
	 * @throws dsd.cms.wsdlclasses.IOException 
	 */
	private static void transferRecord(String userName, String managerPrefix, BufferedReader bufferReader)
			throws IOException, dsd.cms.wsdlclasses.IOException {

		System.out.println("Enter inputs in the following format to Transfer Record\n");
		System.out.println("recordID (Format : TR9999/SR9999), remoteCenterServerName(Format : MTL or LVL or DDO)");
		System.out.println("Example: TR9999, LVL\n");
		createServerInstance(managerPrefix);
		List<String> listOfRecordID = serverInstance.listRecordID();
		System.out.println("Available records to transfer: " + String.join(",", listOfRecordID));
		System.out.println("\nenter details to transfer record :");

		String tRFInput = bufferReader.readLine().trim();

		List<String> tRFOutput = validate.transferRecord(userName, tRFInput);

		if (tRFOutput != null) {

			String transferStatus = serverInstance.transferRecord(userName, tRFOutput.get(0).toUpperCase(), tRFOutput.get(1).toUpperCase());
			System.out.println(transferStatus);
			log.clientInfo(userName, LogWriter.PHASE_TRFR, transferStatus);
		}

	}

	/**
	 * This method is used to set the server instance based on location.
	 *
	 * @param server the server
	 */
	public static void createServerInstance(String server) {
		if ("MTL".equalsIgnoreCase(server)) {
			MontrealServerService serverObject = new MontrealServerService();
			serverInstance = serverObject.getMontrealServerPort();
		}
		if ("LVL".equalsIgnoreCase(server)) {
			LavalServerService serverObject = new LavalServerService();
			serverInstance = serverObject.getLavalServerPort();
		}
		if ("DDO".equalsIgnoreCase(server)) {
			DollardServerService serverObject = new DollardServerService();
			serverInstance = serverObject.getDollardServerPort();
		}
	}

}
