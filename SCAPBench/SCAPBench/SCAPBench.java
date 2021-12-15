package SCAPBench;

import xccdfClasses.*;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import STIGBench.ReferenceType;


/**
 * <Benchmark>
<status>
<title>
<description>
<notice>
<front-matter> - not used
<rear-matter> - not used
<reference>
	<publisher>
	<source>
<plain-text>
<plain-text>
<plain-text>
<version>
<metadata>
	<creator>
	<publisher>
	<contributor>
	<source>
<Profile>
	<title>
	<description>
	<select>
<Group>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)

@XmlType(propOrder = {
    "title",
    "testResult"
})

@XmlRootElement(name = "Benchmark")
public class SCAPBench {

	@XmlTransient
    protected Status status;  
	@XmlElement
	protected String title;
	@XmlTransient
    protected String description;
	@XmlTransient
    protected IdType notice;
	@XmlTransient
    protected String frontMatter;
    @XmlTransient
    protected String rearMatter;
    @XmlTransient
    protected List<ReferenceType> reference;
    @XmlTransient
    protected List<IdType> plainText;
    @XmlTransient
    protected ArrayList<IdrefType> platform;
    @XmlTransient
    protected String version;
    @XmlTransient
    protected List<MetadataType> metadata;
    @XmlTransient
    protected List<ProfileType> profile;
    @XmlElement(name = "TestResult")
    protected TestResultType testResult;
    
    @XmlAttribute(name = "id", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String id;
    @XmlAttribute(name = "resolved")
    protected Boolean resolved;
    @XmlAttribute(name = "style")
    protected String style;
    @XmlAttribute(name = "style-href")
    @XmlSchemaType(name = "anyURI")
    protected String styleHref;

    //GETTERS
    public Status getStatus() {
        if (status == null) {
            status = new Status();
        }
        return this.status;
    }

    public String getTitle() {
        return (title == null? "" : this.title);
    }

    public String getDescription() {
        return (description == null? "" : this.description);
    }

    public IdType getNotice() {
        if (notice == null) {
            notice = new IdType();
        }
        return this.notice;
    }

    public String getFrontMatter() {
    	return (frontMatter == null? "" : this.frontMatter);
    }

    public String getRearMatter() {
    	return (rearMatter == null? "" : this.rearMatter);
    }

    public List<ReferenceType> getReference() {
        if (reference == null) {
            reference = new ArrayList<ReferenceType>();
        }
        return this.reference;
    }

    public List<IdType> getPlainText() {
        if (plainText == null) {
            plainText = new ArrayList<IdType>();
        }
        return this.plainText;
    }

    public List<IdrefType> getPlatform() {
        if (platform == null) {
            platform = new ArrayList<IdrefType>();
        }
        return this.platform;
    }
    
    public String getVersion() {
        return version;
    }
    
  public List<ProfileType> getProfile() {
  if (profile == null) {
      profile = new ArrayList<ProfileType>();
  }
  return this.profile;
}

public TestResultType getTestResult() {
  if (testResult == null) {
      testResult = new TestResultType();
  }
  return this.testResult;
}

	public String getId() {
	  return id;
	}
    
  //SETTERS

    public void setTitle(String value) {
        this.title = value;
    }
    
    public void setDescription(String value) {
        this.description = value;
    }

    public void setFrontMatter(String value) {
    	this.frontMatter = value;
    }

    public void setRearMatter(String value) {
    	this.rearMatter = value;
    }

    public void setVersion(String value) {
        this.version = value;
    }

    public List<MetadataType> getMetadata() {
        if (metadata == null) {
            metadata = new ArrayList<MetadataType>();
        }
        return this.metadata;
    }
    
    public void setId(String value) {
        this.id = value;
    }

    public boolean isResolved() {
        if (resolved == null) {
            return false;
        } else {
            return resolved;
        }
    }

    public void setResolved(Boolean value) {
        this.resolved = value;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String value) {
        this.style = value;
    }
    
    public void setTestResult(TestResultType value) {
    	  this.testResult = value;
    	}

    public String getStyleHref() {
        return styleHref;
    }

    public void setStyleHref(String value) {
        this.styleHref = value;
    }

}
