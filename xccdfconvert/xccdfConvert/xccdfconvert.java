package xccdfConvert;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringReader;
import java.lang.String;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import SCAPBench.RuleResultType;
import SCAPBench.SCAPBench;
import SCAPBench.TestResultType;
import STIGBench.STIGBench;
import checklist.ASSET;
import checklist.CHECKLIST;
import checklist.ISTIG;
import checklist.SIDATA;
import checklist.STIGDATA;
import checklist.VULN;
import xccdfClasses.*;
import STIGBench.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class xccdfconvert {
	SortedMap<String, String> mapFiles = new TreeMap<String, String>();

	public static void main(String[] args) {
		long timestart, timeend, duration;
		SortedMap<String, String> mapFiles = loadStigs();

		switch(args[0])
		{
			case "-d":
				List<File> filelist;
				//ArrayList<File> filelist = new ArrayList<>();
				File top_directory = new File(args[1]);
				if(top_directory.exists()  && top_directory.isDirectory()) {
					//Create a FilenameFilter class for .xml files
					filelist = fileSearch(top_directory.listFiles());
					//filelist = fileSearch(top_directory.listFiles());
					System.out.println(filelist.size());
				}
				break;
			case "-o":
				File xccdffile = new File(args[1]);
				timestart = System.nanoTime();
				convert(xccdffile, null);
				timeend = System.nanoTime();
				duration = timeend - timestart;
				System.out.println(duration);
				break;
			case "-help":
			case "-h":
				System.out.println("For single use: -s <.xccdf file location> -o <output location>");
				System.out.println("For directory search: -d <.xccdf directory> -o <output location>");
				break;
			default:
				System.out.println("Invalid arguments. Use -help or -h for any questions.");
				break;	
		};
		System.exit(0);
	}
	
	
	//This finds and loads each .xccdf from the STIGs to use as template
	//Returns: Editted map of each STIG's file location and name
	private static SortedMap<String, String> loadStigs() {
		File library = new File("./STIG LIBRARY");
		SortedMap<String, String> mapFiles = new TreeMap();
		
		try {
				
			List<File> files =  fileSearch(library.listFiles());
			
			for(File file : files)
			{
				XMLInputFactory factory = XMLInputFactory.newInstance();
				XMLStreamReader reader = factory.createXMLStreamReader(
						new FileReader(library));
				String tagContent = null;
				boolean boolCheck = false;
			
				//We only want to grab the title and add to our map of filenames	
				while(reader.hasNext() && !boolCheck) {
					int event = reader.next();
					switch(event) {
						case XMLStreamConstants.START_ELEMENT:
							break;
			
						case XMLStreamConstants.CHARACTERS:
							tagContent = reader.getText().trim();
							break;
							
						case XMLStreamConstants.END_ELEMENT:
							if(reader.getLocalName().equals("title"))
							{
								mapFiles.put(tagContent, file.getAbsolutePath());
							}	
							break;
					}
				}
			}
		}catch(Exception e)
		{
			
		} finally
		{
		return mapFiles;
		}
	}
	
	private static List<File> fileSearch(File[] contents)
	{ 
		ArrayList<File> files = new ArrayList<>();
		
		
		
		for(File file : contents)
		{
			if(file.isDirectory())
			{
				fileSearch(file.listFiles());
			}else
			{
				if(file.getName().endsWith(".xml"))
					files.add(file);
			}
		}
		return files;
	}
	
	private static ArrayList<File> fileSearch(File[] contents, Pattern p)
	{ 
		ArrayList<File> files = new ArrayList<>();
		
		for(File file : contents)
		{
			if(file.isDirectory())
			{
				fileSearch(file.listFiles(), p);
			}else
			{
				Matcher m = p.matcher(file.getName());
				if(m.find())
					files.add(file);
			}
		}
		return files;
	}
	
	//Converts our results into a checklist
	//Output: checklistfile - locaiton of checklist file we create
	//Check for STIG xml
	//import checklist xml to checklist class
	//import xccdf results
	//merge the two
	//output checklist
	public static void convert(File xccdffile, File checklistfile)
	{
		JAXBContext jaxbContext;
		
		CHECKLIST checklist = new CHECKLIST();
		
		//settings
		//regex for hostname and xccdf
		
		try
		{	
			//for testing
			SortedMap<String, String> mapFiles = new TreeMap<String, String>();
			mapFiles.put("Red Hat Enterprise Linux 7 Security Technical Implementation Guide",
					"C:\\Users\\Emisaurus\\Downloads\\U_SRG-STIG_Library_2021_10v2\\"
					+ "U_RHEL_7_V3R5_Manual_STIG\\U_RHEL_7_STIG_V3R5_Manual-xccdf.xml");
			
			//Change XML to Java Object
		    jaxbContext = JAXBContext.newInstance(SCAPBench.class);
		    Unmarshaller jaxbUnmarshallerXccdf = jaxbContext.createUnmarshaller();
		    SCAPBench benchmark = (SCAPBench) jaxbUnmarshallerXccdf.unmarshal(xccdffile);
			    
			//Import blank checklist
		    File stigFile = new File(mapFiles.get(benchmark.getTitle()));
		    jaxbContext = JAXBContext.newInstance(STIGBench.class);
		    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		    STIGBench stig = (STIGBench) jaxbUnmarshaller.unmarshal(stigFile);
			
		    //Merge the xccdf files
		    stig.setTestResult(benchmark.getTestResult());
		    
		    //CHECKLIST - ASSET
		    ASSET asset = checklist.getASSET();
		    //use java uuid library
		    UUID uuid = UUID.randomUUID();
		    UUID stiguuid = UUID.randomUUID();

		    //Converting Xccdf Elements to Checklist
		    String hostip = null, hostmac = null, hostfqdn = null;
		    for(FactType value : stig.getTestResult().getTargetFacts().getFact())
		    {
		    	if(value.getName().equals("urn:scap:fact:asset:identifier:ipv4") 
		    			&& !value.getValue().equals("127.0.0.1"))
		    		hostip = value.getValue();
		    	if(value.getName().equals("urn:scap:fact:asset:identifier:mac") 
		    			&& !value.getValue().equals("00:00:00:00:00:00"))
		    		hostmac = value.getValue();
		    	if(value.getName().equals("urn:scap:fact:asset:identifier:fqdn"))
					hostfqdn = value.getValue();
		    }
		    asset.setHOSTNAME(stig.getTestResult().getTarget());
		    asset.setROLE("None");
		    asset.setASSETTYPE("Computing");
		    asset.setHOSTIP(hostip);
		    asset.setHOSTMAC(hostmac);
		    asset.setHOSTFQDN(hostfqdn);
		    asset.setTARGETCOMMENT("");
		    asset.setTECHAREA("");
		    asset.setTARGETKEY(stig.getGroup().get(0).getRule().getReference().getIdentifier());
		    asset.setWEBDBSITE("");
		    asset.setWEBDBINSTANCE("");
		    
		    //CHECKLIST - ISTIG
		    List<ISTIG> istiglist = checklist.getSTIGS().getISTIG();
		    ISTIG istig = new ISTIG();
		    
		    //CHECKLIST - ISTIG - STIGINFO
		    List<SIDATA> stiginfo = istig.getSTIGINFO().getSIDATA();
		    //Version
		    //Assumes Plaintext item 0 is the release-info
		    stiginfo.add(new SIDATA("version", stig.getVersion()));
		    //Classification - unused
		    stiginfo.add(new SIDATA("classification", "UNCLASSIFIED"));
		    //customname - unused
		    stiginfo.add(new SIDATA("customname", null));
		    //stigid
		    stiginfo.add(new SIDATA("stigid", stig.getId()));
		    //description
		    stiginfo.add(new SIDATA("description", stig.getDescription()));
		    //filename
		    stiginfo.add(new SIDATA("filename", stigFile.getName()));
		    //releaseinfo
		    stiginfo.add(new SIDATA("releaseinfo", stig.getPlainText().get(0).getValue()));
		    //title
		    stiginfo.add(new SIDATA("title", stig.getTitle()));
		    //uuid
		    stiginfo.add(new SIDATA("uuid", uuid.toString()));
		    //notice
		    stiginfo.add(new SIDATA("notice", stig.getNotice().getId()));
		    //source - unused
		    stiginfo.add(new SIDATA("source", null));
		    
		    //CHECKLIST - ISTIG - VULNS
		    List<GroupType> groups = stig.getGroup();
		    List<VULN> vulns = istig.getVULN();
		    TestResultType testresult = stig.getTestResult();
			for(GroupType group : groups)
			{
				VULN vuln = new VULN();
				List<STIGDATA> stigdata = vuln.getSTIGDATA();
				RuleType rule = group.getRule();
				stigdata.add(new STIGDATA("Vuln_Num", group.getId()));
				stigdata.add(new STIGDATA("Severity", rule.getSeverity().value()));
				stigdata.add(new STIGDATA("Group_Title", group.getTitle()));
				stigdata.add(new STIGDATA("Rule_ID", rule.getId()));
				stigdata.add(new STIGDATA("Rule_Ver", rule.getVersion()));
				stigdata.add(new STIGDATA("Rule_Title", rule.getTitle()));
				
				StringReader sr = new StringReader("<VulnData>" + rule.getDescription() + "</VulnData>");
				jaxbContext = JAXBContext.newInstance(VulnData.class);
				Unmarshaller unmarshallerVuln = jaxbContext.createUnmarshaller();
				VulnData vulndata = (VulnData) unmarshallerVuln.unmarshal(sr);
				
				stigdata.add(new STIGDATA("Vuln_Discuss", vulndata.getVulnDiscuss()));
				stigdata.add(new STIGDATA("IA_Controls", vulndata.getIAControls()));
				stigdata.add(new STIGDATA("Check_Content", rule.getCheck().getCheckContent()));
				stigdata.add(new STIGDATA("Fix_Text", rule.getFixtext().getValue()));
				stigdata.add(new STIGDATA("False_Positives", vulndata.getFalsePositive()));
				stigdata.add(new STIGDATA("False_Negatives", vulndata.getFalseNegative()));
				stigdata.add(new STIGDATA("Documentable", String.valueOf(vulndata.getDocumentable())));
				stigdata.add(new STIGDATA("Mitigations", vulndata.getMitigations()));
				stigdata.add(new STIGDATA("Potential_Impact", vulndata.getImpacts()));
				stigdata.add(new STIGDATA("Third_Party_Tools", vulndata.getThirdParty()));
				stigdata.add(new STIGDATA("Mitigation_Control", vulndata.getMitigations()));
				stigdata.add(new STIGDATA("Responsibility", vulndata.getResponsibility()));
				stigdata.add(new STIGDATA("Security_Override_Guidance", vulndata.getSeverityOverride()));
				stigdata.add(new STIGDATA("Check_Content_Ref", rule.getCheck().getCheckContentRef().getName()));
				stigdata.add(new STIGDATA("Weight", String.valueOf(rule.getWeight())));
				stigdata.add(new STIGDATA("Class", "Unclass"));
				stigdata.add(new STIGDATA("STIGRef", 
						stig.getTitle() + " :: Version " + 
						stig.getVersion() + ", " + 
						stig.getPlainText().get(0).getValue()));
				stigdata.add(new STIGDATA("TargetKey", rule.getReference().getIdentifier()));
				//We have no stig uuid
				stigdata.add(new STIGDATA("STIG_UUID", stiguuid.toString()));
				for(IdentType ident : rule.getIdent())
				{
					if(ident.getSystem().equals("http://cyber.mil/legacy"))
						stigdata.add(new STIGDATA("LEGACY_ID", ident.getValue()));
					if(ident.getSystem().equals("http://cyber.mil/cci"))
						stigdata.add(new STIGDATA("CCI_REF", ident.getValue()));
				}
				
				//Setting default
				vuln.setSTATUS("Not_Reviewed");
				vuln.setFINDINGDETAILS("");
				
				for(RuleResultType ruleresult : testresult.getRuleResult())
				{
					if(ruleresult.getVersion().equals(rule.getVersion()))
					{
						vuln.setSTATUS(ruleresult.getResult().name());
						vuln.setFINDINGDETAILS("Tool: " + testresult.getTestSystem()
								+ "\nTime: " + ruleresult.getTime()
								+ "\nResult: " + ruleresult.getResult().value());
						testresult.getRuleResult().remove(ruleresult);
						break;
					}
					
				}
				vuln.setCOMMENTS("");
				vuln.setSEVERITYOVERRIDE("");
				vuln.setSEVERITYJUSTIFICATION("");
				vulns.add(vuln);
			}
			istiglist.add(istig);
			
		    //Checklist Java to XML
			checklistfile = new File(".\\checklist.ckl");
			jaxbContext = JAXBContext.newInstance(CHECKLIST.class);    
		    Marshaller jaxbmarshaller = jaxbContext.createMarshaller();
		    jaxbmarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
		    jaxbmarshaller.marshal(checklist, checklistfile);
		     
		    System.out.println("woooo?");
		}
		catch (JAXBException e) 
		{
		    e.printStackTrace();
		}
	}
	
	public static void error(Exception e)
	{
		try{
			File errorLog = new File("errorLog.txt");
			FileWriter write = new FileWriter(errorLog, true);
			BufferedWriter error = new BufferedWriter(write);
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			Date date = new Date();
			
			error.write(dateFormat.format(date) + " - ");
			error.write(e.getMessage());
			error.newLine();
			error.flush();
			error.close();
			write.close();
		}catch(Exception ex){}
	}
	
}