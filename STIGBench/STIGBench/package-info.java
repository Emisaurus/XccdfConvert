@XmlSchema(
namespace = "http://checklists.nist.gov/xccdf/1.1",
elementFormDefault = XmlNsForm.QUALIFIED,
xmlns = { 
	@XmlNs(prefix = "", namespaceURI = "http://checklists.nist.gov/xccdf/1.1"),
	@XmlNs(prefix = "cdf", namespaceURI = "http://checklists.nist.gov/xccdf/1.2"),
    @XmlNs(prefix = "dc", namespaceURI = "http://purl.org/dc/elements/1.1/"),
    @XmlNs(prefix = "xsi", namespaceURI = "http://www.w3.org/2001/XMLSchema-instance"),
    @XmlNs(prefix = "cpe", namespaceURI = "http://cpe.mitre.org/language/2.0")
}
)

package STIGBench;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;