package dsd.cms.client;

import dsd.cms.wsdlclasses.DCMSInterface;
import dsd.cms.wsdlclasses.DollardServerService;
import dsd.cms.wsdlclasses.IOException;
import dsd.cms.wsdlclasses.LavalServerService;
import dsd.cms.wsdlclasses.MontrealServerService;

public class TestManagerClient {

	public static void main(String[] args) throws IOException, InterruptedException {

		TestManagerClient testManagerClient = new TestManagerClient();
		System.out.println("******************* Basic Test Cases *******************\n");

		System.out.println("\nTest 1 : Create teacher record on Montreal Server");
		testManagerClient.createTeacherRecord("MTL", "MTL1111");
		System.out.println("\nTest 2 : Create student record on Montreal Server");
		testManagerClient.createStudentRecord("MTL", "MTL1111");
		System.out.println("\nTest 3 : Edit existing record on Montreal Server");
		testManagerClient.editRecord("MTL", "MTL1111");
		System.out.println("\nTest 4 : Get Record Count from Montreal location");
		testManagerClient.getRecordCount("MTL", "MTL1111");
		System.out.println("\nTest 5 : Transfer teacher record to Laval");
		testManagerClient.transferRecord("MTL", "MTL1111", "LVL", "SR10000");
		System.out.println("\nTest 6 : Get Record Count from Laval location");
		testManagerClient.getRecordCount("LVL", "LVL1111");
		System.out.println("\nTest 7 : Edit transferred record on Laval Server");
		testManagerClient.editRecord2("LVL", "LVL1111");

		System.out.println("\n\n******************* Concurrency Test Cases *******************\n");

		System.out.println(
				"\n** Initially 1 Teacher record with first name : fEditTransfer1 and 1 Student record with first name : fEditTransfer2 will be created **");
		System.out.println(
				"** There are 2 threads trying to attempt edit record and transfer record on these 2 records **");
		System.out.println("** Thread 1 tries to edit teacher record and transfer student record to laval **");
		System.out.println("** Thread 2 tries to transfer teacher record and edit student record to laval **");
		System.out.println(
				"** Application will run without any error. When edit and transfer both are attempted on single record, only 1 of them will be executed. **");

		System.out.println("\nThread " + Thread.currentThread().getId() + " Method : createPlayerAccount() , "
				+ " First Name : fEditTransfer1 , "
				+ createServerInstance("MTL").createTRecord("AutoCreate", "fEditTransfer1", "lEditTransfer1",
						"abc-12, Montreal", "8888888888", "Python|Java", "MTL", "MTL1111", false));
		System.out.println("\nThread " + Thread.currentThread().getId() + " Method : createPlayerAccount() , "
				+ " First Name : fEditTransfer2 , " + createServerInstance("MTL").createSRecord("AutoCreate",
						"fEditTransfer1", "lEditTransfer1", "ML|APP", "Active", "2021-12-24", "MTL1111", false));

		Runnable run1 = () -> {
			try {
				System.out.println("\nThread " + Thread.currentThread().getId()
						+ " Executing Edit Record on TR10001, First Name : fEditTransfer1");
				System.out.println("\nThread " + Thread.currentThread().getId() + " Edit Record Result for TR10001:: "
						+ createServerInstance("MTL").editRecord("TR10001", "PHONE", "9191919191", "MTL1111"));

				System.out.println("\nThread " + Thread.currentThread().getId()
						+ " Executing Transfer Record on SR10001, First Name : fEditTransfer2");

				String transferStatus = createServerInstance("MTL").transferRecord("MTL1111", "SR10001", "LVL");
				System.out.println("\nThread " + Thread.currentThread().getId()
						+ " Transfer Record Result for SR10001 :: " + transferStatus);

			} catch (IOException e) {
				e.printStackTrace();
			}
		};
		Thread t1 = new Thread(run1);

		Runnable run2 = () -> {
			try {
				System.out.println("\nThread " + Thread.currentThread().getId()
						+ " Executing Transfer Record on TR10001, First Name : fEditTransfer1");

				String transferStatus = createServerInstance("MTL").transferRecord("MTL1111", "TR10001", "LVL");
				System.out.println("\nThread " + Thread.currentThread().getId()
						+ " Transfer Record Result for TR10001 :: " + transferStatus);

				System.out.println("\nThread " + Thread.currentThread().getId()
						+ " Executing Edit Record on SR10001, First Name : fEditTransfer2");
				System.out.println("\nThread " + Thread.currentThread().getId() + " Edit Record Result for SR10001 :: "
						+ createServerInstance("MTL").editRecord("SR10001", "COURSEREGISTERED", "JAVA|DSD", "MTL1000"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
		Thread t2 = new Thread(run2);

		t1.start();
		t2.start();

		Thread.sleep(2000);
	}

	public void createTeacherRecord(String location, String managerID) throws IOException {
		System.out.println("Executing : createTeacherRecord() , "
				+ createServerInstance(location).createTRecord("AutoCreate", "testFName1", "testLName1",
						"abc-12, Montreal", "9999999999", "Python|Java", "MTL", managerID, false));
	}

	public void createStudentRecord(String location, String managerID) throws IOException {
		System.out.println("Executing : createStudentRecord() , " + createServerInstance(location).createSRecord(
				"AutoCreate", "testFName1", "testLName1", "ML|APP", "Active", "2021-12-24", managerID, false));
	}

	public void editRecord(String location, String managerID) throws IOException {
		System.out.println("Executing : editRecord() , "
				+ createServerInstance(location).editRecord("SR10000", "COURSEREGISTERED", "JAVA|HTML5", managerID));
	}

	public void getRecordCount(String location, String managerID) throws IOException {
		System.out
				.println("Executing : getRecordCount() , " + createServerInstance(location).getRecordCounts(managerID));
	}

	public void transferRecord(String location, String managerID, String targetLocation, String recordToTransfer)
			throws IOException {
		String transferStatus = createServerInstance(location).transferRecord(managerID, "recordToTransfer",
				targetLocation);
		System.out.println("Executing : transferRecord() , " + transferStatus);

	}

	public void editRecord2(String location, String managerID) throws IOException {
		System.out.println("Executing : editRecord2() , "
				+ createServerInstance(location).editRecord("SR10000", "COURSEREGISTERED", "JAVA|DSD", managerID));
	}

	/**
	 * This method is used to set the server instance based on location.
	 *
	 * @param server the server
	 */
	public static DCMSInterface createServerInstance(String server) {
		if ("MTL".equalsIgnoreCase(server)) {
			MontrealServerService serverObject = new MontrealServerService();
			return serverObject.getMontrealServerPort();
		}
		if ("LVL".equalsIgnoreCase(server)) {
			LavalServerService serverObject = new LavalServerService();
			return serverObject.getLavalServerPort();
		}
		if ("DDO".equalsIgnoreCase(server)) {
			DollardServerService serverObject = new DollardServerService();
			return serverObject.getDollardServerPort();
		}
		return null;
	}
}