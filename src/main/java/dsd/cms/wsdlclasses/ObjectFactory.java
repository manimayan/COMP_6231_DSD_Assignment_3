
package dsd.cms.wsdlclasses;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the dsd.cms.interfaces package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ListRecordIDResponse_QNAME = new QName("http://interfaces.cms.dsd/", "listRecordIDResponse");
    private final static QName _IOException_QNAME = new QName("http://interfaces.cms.dsd/", "IOException");
    private final static QName _TransferRecord_QNAME = new QName("http://interfaces.cms.dsd/", "transferRecord");
    private final static QName _CreateTRecord_QNAME = new QName("http://interfaces.cms.dsd/", "createTRecord");
    private final static QName _TransferRecordResponse_QNAME = new QName("http://interfaces.cms.dsd/", "transferRecordResponse");
    private final static QName _CreateSRecordResponse_QNAME = new QName("http://interfaces.cms.dsd/", "createSRecordResponse");
    private final static QName _GetRecordCounts_QNAME = new QName("http://interfaces.cms.dsd/", "getRecordCounts");
    private final static QName _CreateSRecord_QNAME = new QName("http://interfaces.cms.dsd/", "createSRecord");
    private final static QName _EditRecord_QNAME = new QName("http://interfaces.cms.dsd/", "editRecord");
    private final static QName _GetRecordCountsResponse_QNAME = new QName("http://interfaces.cms.dsd/", "getRecordCountsResponse");
    private final static QName _EditRecordResponse_QNAME = new QName("http://interfaces.cms.dsd/", "editRecordResponse");
    private final static QName _CreateTRecordResponse_QNAME = new QName("http://interfaces.cms.dsd/", "createTRecordResponse");
    private final static QName _ListRecordID_QNAME = new QName("http://interfaces.cms.dsd/", "listRecordID");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: dsd.cms.interfaces
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ListRecordIDResponse }
     * 
     */
    public ListRecordIDResponse createListRecordIDResponse() {
        return new ListRecordIDResponse();
    }

    /**
     * Create an instance of {@link IOException }
     * 
     */
    public IOException createIOException() {
        return new IOException(null, null);
    }

    /**
     * Create an instance of {@link TransferRecord }
     * 
     */
    public TransferRecord createTransferRecord() {
        return new TransferRecord();
    }

    /**
     * Create an instance of {@link CreateTRecord }
     * 
     */
    public CreateTRecord createCreateTRecord() {
        return new CreateTRecord();
    }

    /**
     * Create an instance of {@link TransferRecordResponse }
     * 
     */
    public TransferRecordResponse createTransferRecordResponse() {
        return new TransferRecordResponse();
    }

    /**
     * Create an instance of {@link CreateSRecordResponse }
     * 
     */
    public CreateSRecordResponse createCreateSRecordResponse() {
        return new CreateSRecordResponse();
    }

    /**
     * Create an instance of {@link GetRecordCounts }
     * 
     */
    public GetRecordCounts createGetRecordCounts() {
        return new GetRecordCounts();
    }

    /**
     * Create an instance of {@link EditRecord }
     * 
     */
    public EditRecord createEditRecord() {
        return new EditRecord();
    }

    /**
     * Create an instance of {@link GetRecordCountsResponse }
     * 
     */
    public GetRecordCountsResponse createGetRecordCountsResponse() {
        return new GetRecordCountsResponse();
    }

    /**
     * Create an instance of {@link CreateSRecord }
     * 
     */
    public CreateSRecord createCreateSRecord() {
        return new CreateSRecord();
    }

    /**
     * Create an instance of {@link ListRecordID }
     * 
     */
    public ListRecordID createListRecordID() {
        return new ListRecordID();
    }

    /**
     * Create an instance of {@link EditRecordResponse }
     * 
     */
    public EditRecordResponse createEditRecordResponse() {
        return new EditRecordResponse();
    }

    /**
     * Create an instance of {@link CreateTRecordResponse }
     * 
     */
    public CreateTRecordResponse createCreateTRecordResponse() {
        return new CreateTRecordResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListRecordIDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.cms.dsd/", name = "listRecordIDResponse")
    public JAXBElement<ListRecordIDResponse> createListRecordIDResponse(ListRecordIDResponse value) {
        return new JAXBElement<ListRecordIDResponse>(_ListRecordIDResponse_QNAME, ListRecordIDResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.cms.dsd/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransferRecord }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.cms.dsd/", name = "transferRecord")
    public JAXBElement<TransferRecord> createTransferRecord(TransferRecord value) {
        return new JAXBElement<TransferRecord>(_TransferRecord_QNAME, TransferRecord.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateTRecord }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.cms.dsd/", name = "createTRecord")
    public JAXBElement<CreateTRecord> createCreateTRecord(CreateTRecord value) {
        return new JAXBElement<CreateTRecord>(_CreateTRecord_QNAME, CreateTRecord.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransferRecordResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.cms.dsd/", name = "transferRecordResponse")
    public JAXBElement<TransferRecordResponse> createTransferRecordResponse(TransferRecordResponse value) {
        return new JAXBElement<TransferRecordResponse>(_TransferRecordResponse_QNAME, TransferRecordResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateSRecordResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.cms.dsd/", name = "createSRecordResponse")
    public JAXBElement<CreateSRecordResponse> createCreateSRecordResponse(CreateSRecordResponse value) {
        return new JAXBElement<CreateSRecordResponse>(_CreateSRecordResponse_QNAME, CreateSRecordResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRecordCounts }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.cms.dsd/", name = "getRecordCounts")
    public JAXBElement<GetRecordCounts> createGetRecordCounts(GetRecordCounts value) {
        return new JAXBElement<GetRecordCounts>(_GetRecordCounts_QNAME, GetRecordCounts.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateSRecord }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.cms.dsd/", name = "createSRecord")
    public JAXBElement<CreateSRecord> createCreateSRecord(CreateSRecord value) {
        return new JAXBElement<CreateSRecord>(_CreateSRecord_QNAME, CreateSRecord.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditRecord }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.cms.dsd/", name = "editRecord")
    public JAXBElement<EditRecord> createEditRecord(EditRecord value) {
        return new JAXBElement<EditRecord>(_EditRecord_QNAME, EditRecord.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRecordCountsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.cms.dsd/", name = "getRecordCountsResponse")
    public JAXBElement<GetRecordCountsResponse> createGetRecordCountsResponse(GetRecordCountsResponse value) {
        return new JAXBElement<GetRecordCountsResponse>(_GetRecordCountsResponse_QNAME, GetRecordCountsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditRecordResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.cms.dsd/", name = "editRecordResponse")
    public JAXBElement<EditRecordResponse> createEditRecordResponse(EditRecordResponse value) {
        return new JAXBElement<EditRecordResponse>(_EditRecordResponse_QNAME, EditRecordResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateTRecordResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.cms.dsd/", name = "createTRecordResponse")
    public JAXBElement<CreateTRecordResponse> createCreateTRecordResponse(CreateTRecordResponse value) {
        return new JAXBElement<CreateTRecordResponse>(_CreateTRecordResponse_QNAME, CreateTRecordResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListRecordID }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.cms.dsd/", name = "listRecordID")
    public JAXBElement<ListRecordID> createListRecordID(ListRecordID value) {
        return new JAXBElement<ListRecordID>(_ListRecordID_QNAME, ListRecordID.class, null, value);
    }

}
