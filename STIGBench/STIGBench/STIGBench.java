package STIGBench;

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

import SCAPBench.TestResultType;


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
@XmlAccessorType(XmlAccessType.NONE)

@XmlType(propOrder = {
    "status",
    "title",
    "description",
    "notice",
    "frontMatter",
    "rearMatter",
    "reference",
    "plainText",
    "version",
    "metadata",
    "profile",
    "group",
    "testResult"
})

@XmlRootElement(name = "Benchmark")
public class STIGBench {

	@XmlElement
    protected Status status;  
	@XmlElement
	protected String title;
	@XmlElement
    protected String description;
	@XmlElement
    protected IdType notice;
    @XmlElement(name = "front-matter")
    protected String frontMatter;
    @XmlElement(name = "rear-matter")
    protected String rearMatter;
    @XmlElement
    protected ReferenceType reference;
    @XmlElement(name = "plain-text")
    protected List<IdType> plainText;
    protected ArrayList<IdrefType> platform;
    @XmlElement
    protected String version;
    @XmlElement
    protected List<MetadataType> metadata;
    @XmlElement(name = "Profile")
    protected List<ProfileType> profile;
    @XmlElements({
        @XmlElement(name = "Group", type = GroupType.class),
        @XmlElement(name = "Rule", type = RuleType.class)
    })
    protected List<GroupType> group;
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

    public ReferenceType getReference() {
        if (reference == null) {
            reference = new ReferenceType();
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

public List<GroupType> getGroup() {
  if (group == null) {
      group = new ArrayList<GroupType>();
  }
  return this.group;
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