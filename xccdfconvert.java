package xccdfconvert;

import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.StringReader;
import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Pattern;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.regex.Matcher;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/*
Assumes the lines follow a certain order: (For TXT only)
*************
policy check: VMSKey:
description:
(description)
severity:
finding status:
(space)
Finding Details:
(explanation)
(findings)
*/

/*
Need to compare which is faster:
1. Breaking down loading xccdf, storing, then writing
2. writing ckl file as we load xccdf
 */

public class xccdfconvert {

	public static void main(String[] args) {
		switch(args[0])
		{
			case "-d":
				try {
					ArrayList<File> filelist = new ArrayList<>();
					File top_directory = new File(args[1]);
					if(top_directory.exists()  && top_directory.isDirectory()) {
						//Create a FilenameFilter class for .xml files
						filelist = fileSearch(top_directory.listFiles());
						System.out.println(filelist.size());
					}
				} catch(Exception e)
				{
					
				}
				break;
			case "-o":
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
		// TODO Needs to take in a xccdf file and output name
		// or directory of xccdf files for multiple
		String message = "";
		ArrayList<STIG> stig;

		File xccdffile = new File(args[0]);
		
		try
		{
			//File srrcopy = File.createTempFile("srrcopy", "");
			//Files.copy(xccdffile.toPath(), srrcopy.toPath(), StandardCopyOption.REPLACE_EXISTING);

			long timestart, timeend, duration;
			
			timestart = System.nanoTime();
			xmlStaxtoSTIG(xccdffile);
			timeend = System.nanoTime();
			duration = timeend - timestart;
			System.out.println(duration);
		}catch(Exception e) {
			e.printStackTrace();
			message = "An Error has occurred.  Please check the logs.";
			error(e);
		}
	}
	
	private static ArrayList<File> fileSearch(File[] contents)
	{ 
		ArrayList<File> files = new ArrayList<>();
		int count = 0;
		
		for(File file : contents)
		{
			if(file.isDirectory())
			{
				files.addAll(fileSearch(file.listFiles()));
			}else
			{
				if(file.getName().endsWith(".xml"))
					files.add(file);
			}
		}
		return files;
	}

/*	public static void main(String[] args)
	{
		File srr;
		File save;
		//Scanner scan = new Scanner(System.in);
		SortedMap<String, ArrayList<String>> mapFinding = new TreeMap<String, ArrayList<String>>();
		
		mapFinding = Utilities.loadXML(new File("C:/dbChecker/DB/12cXMLs/trdm12c.xml"));
		System.out.println("C:/dbChecker/DB\12cXMLs\trdm12c.xml");
		srr = new File("C:/dbChecker/DB/TRDM/2017-12-14/TRDMDEV/TRDMDEV_SRR_Tue_12_12_2017_19_47_15/"
				+ "VMSimport_Tue_12_12_2017_19_47_15.xml");
		System.out.println(srr.getAbsolutePath());
		save = new File("C:/dbChecker/DB/TRDM/2017-12-14/JIRA_DB/JIRADB_SRR_Tue_12_12_2017_19_45_03"
				+ "/a.xml");
		System.out.println(save.getAbsolutePath());
		
		//System.out.println("Please specify the stig comparison map file (.xml)");
		//mapFinding = Utilities.loadXML(new File(scan.nextLine()));
		//System.out.println("Please specify the SRR file (.txt|.xml)");
		//srr = new File(scan.nextLine());
		//System.out.println("Please specify the save location (.ckl)");
		//save = new File(scan.nextLine());
		
		System.out.println("Using Compare with Stax Parser:");
		compareStax(mapFinding, srr, new File(save.getName()+ "a"));
		
		System.out.println("\nUsing Compare normally:");
		compare(mapFinding, srr, save, true);
		
		//scan.close();
		  
	}
*/	
	
	public static void convert(File xccdffile, File checklistfile)
	{
		
	}

	/*
	public static String compareOriginal(SortedMap<String, ArrayList<String>> map, File srr, File save, boolean setting)
	{
		String message = "";
		ArrayList<STIG> stig;

		try
		{
			File srrcopy = File.createTempFile("srrcopy", "");
			Files.copy(srr.toPath(), srrcopy.toPath(), StandardCopyOption.REPLACE_EXISTING);

			long timestart, timeend, duration;
			
			timestart = System.nanoTime();
			if(srr.getName().endsWith(".txt"))
			{ stig = txtToSTIG(srrcopy); }
			else
			{ stig = xmlStaxtoSTIG(srrcopy); }
			timeend = System.nanoTime();
			duration = timeend - timestart;
			
			System.out.println("Adding stig items took: " + duration);
			
				timestart = System.nanoTime();
				stigCompare(map, stig);
				timeend = System.nanoTime();
				duration = timeend - timestart;
				System.out.println("Comparing stig items took: " + duration);
				
					timestart = System.nanoTime();
					message = outChecklist(new File("./DBChecker/12c.ckl"), save, stig); 
					timeend = System.nanoTime();
					duration = timeend - timestart;
					System.out.println("Creating checklist took: " + duration);

			srrcopy.delete();

		}catch(Exception e) {
			e.printStackTrace();
			message = "An Error has occurred.  Please check the logs.";
			Utilities.error(e);
		}
		return message;
	}
	*/
	
	private static void xmlStaxtoSTIG(File fvdlFile) throws Exception {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = factory.createXMLStreamReader(
				new FileReader(fvdlFile));
		String tagContent = null;
		String title; //To grab the stigs/nist/owasp number
		String author; //To grab the name it correlates to
		String attribute = "None";
		String display, end = "";
		String[] buildData = new String[7]; //Set to the number of details needed
		boolean boolCheck = false, srcFiles = false;
		StringBuilder build = null;
		STIG stig;
		
		try{
		//If we pass in map will it save the data?
		while(reader.hasNext()) {
			int event = reader.next();
			switch(event) {
				case XMLStreamConstants.START_ELEMENT:
					attribute = reader.getLocalName();
					switch(reader.getLocalName()) {
					case "Group":
						stig = new STIG();
						break;
					case "Rule":
						//stig.setSeverity(reader.getAttributeValue(0));
						//stig.setWeight(reader.getAttributeValue(2));
					};

						
					/*if(DocxDefaults.VULN.equals(reader.getLocalName())) {
					//	issue = new Issue();
					//} else {
					if(DocxDefaults.DESC.equals(reader.getLocalName())) {
						rule = new Rule(reader.getAttributeValue(1));
					} else {
					if(DocxDefaults.RULE.equals(reader.getLocalName())) {
						attribute = reader.getAttributeValue(0);
						rule = rules.get(attribute);
					} else {
					if(DocxDefaults.GROUP.equals(reader.getLocalName())) {
						attribute = reader.getAttributeValue(0);
					} else {
					if(DocxDefaults.LOC.equals(reader.getLocalName())) {
						attribute = reader.getAttributeValue(0);
					} else {
					if(DocxDefaults.DATE.equals(reader.getLocalName())) {
							buildData[0] = reader.getAttributeValue(0);
					} else {
					if(DocxDefaults.SRCFILES.equals(reader.getLocalName())) {
						srcFiles = true;
					} else {
					if(DocxDefaults.ABSTR.equals(reader.getLocalName())) {
						boolCheck = true;
						build = new StringBuilder();
					} else {
					if(DocxDefaults.RECOMM.equals(reader.getLocalName())) {
						boolCheck = true;
						build = new StringBuilder();
					}}}}}}}}}
									*/
					break;

				case XMLStreamConstants.CHARACTERS:
					tagContent = reader.getText().trim();
					break;
					
				case XMLStreamConstants.END_ELEMENT:
					end = reader.getLocalName();
					System.out.println("end: " + reader.getLocalName());
					switch(reader.getLocalName()) {
						case "title":
							//stig.setTitle(tagContent);
							break;
					}
					break;
			}
		}
		} catch(Exception e) {
			System.out.println("nope");
		}
		
/*		
		ArrayList<STIG> stigs = new ArrayList<>();
		STIG tempstig = null;
		ArrayList<String> details = null;
		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(srrcopy));
		
		while(xmlEventReader.hasNext())
		{
			XMLEvent xmlEvent = xmlEventReader.nextEvent();
			if(xmlEvent.isStartElement())
			{
				StartElement startElement = xmlEvent.asStartElement();
				//System.out.println(startElement.getName().getLocalPart());
				
				if(startElement.getName().getLocalPart().equals("VULN"))
				{
					tempstig = new STIG();
					details = new ArrayList<>();
				}
				else if(startElement.getName().getLocalPart().equals("STIGID"))
				{
					xmlEvent = xmlEventReader.nextEvent();
					//System.out.println(xmlEvent.asCharacters().getData());
					tempstig.setStigid(xmlEvent.asCharacters().getData());
				}
				else if(startElement.getName().getLocalPart().equals("STATUS"))
				{
					xmlEvent = xmlEventReader.nextEvent();
					tempstig.setStatus(xmlEvent.asCharacters().getData());
				}else if(startElement.getName().getLocalPart().equals("FINDING_DETAILS"))
				{
					xmlEvent = xmlEventReader.nextEvent();
					//Added if() due to <FINDING_DETAILS></FINDING_DETAILS> results on old scripts
					if(!xmlEvent.isEndElement())
						details.add(xmlEvent.asCharacters().getData());
				}
			}
			if(xmlEvent.isEndElement())
			{
				EndElement endElement = xmlEvent.asEndElement();
				if(endElement.getName().getLocalPart().equals("VULN"))
				{ 
					tempstig.setDetails(details);
					stigs.add(tempstig);
				}
			}
		}

		return stigs;	
		*/
	}

	/*
	public static String[] fprFvdlRead(
			File fvdlFile, Map<String, Integer> count, 
			Map<String, ArrayList<Issue>> issues, Map<String, Rule> rules) throws Exception {
		
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = factory.createXMLStreamReader(
				new FileReader(fvdlFile));
		String tagContent = null;
		String title; //To grab the stigs/nist/owasp number
		String author; //To grab the name it correlates to
		String attribute = "None";
		String[] buildData = new String[7]; //Set to the number of details needed
		Issue issue = null;
		Rule rule = null;
		ArrayList<Issue> issueList;
		boolean boolCheck = false, srcFiles = false;
		StringBuilder build = null;
		
		try{
		//If we pass in map will it save the data?
		while(reader.hasNext()) {
			int event = reader.next();
			switch(event) {
				case XMLStreamConstants.START_ELEMENT:
					attribute = reader.getLocalName();
					//System.out.println(reader.getLocalName());
					if(DocxDefaults.VULN.equals(reader.getLocalName())) {
						issue = new Issue();
					} else {
					if(DocxDefaults.DESC.equals(reader.getLocalName())) {
						rule = new Rule(reader.getAttributeValue(1));
					} else {
					if(DocxDefaults.RULE.equals(reader.getLocalName())) {
						attribute = reader.getAttributeValue(0);
						rule = rules.get(attribute);
					} else {
					if(DocxDefaults.GROUP.equals(reader.getLocalName())) {
						attribute = reader.getAttributeValue(0);
					} else {
					if(DocxDefaults.LOC.equals(reader.getLocalName())) {
						attribute = reader.getAttributeValue(0);
					} else {
					if(DocxDefaults.DATE.equals(reader.getLocalName())) {
							buildData[0] = reader.getAttributeValue(0);
					} else {
					if(DocxDefaults.SRCFILES.equals(reader.getLocalName())) {
						srcFiles = true;
					} else {
					if(DocxDefaults.ABSTR.equals(reader.getLocalName())) {
						boolCheck = true;
						build = new StringBuilder();
					} else {
					if(DocxDefaults.RECOMM.equals(reader.getLocalName())) {
						boolCheck = true;
						build = new StringBuilder();
					}}}}}}}}}
					break;
				
				case XMLStreamConstants.CHARACTERS:
					if(!boolCheck)
					{
						tagContent = reader.getText().trim();
					} else
					{
						build.append(reader.getText().trim());
					}
					//System.out.println(reader.getText().trim());
					break;
					
				case XMLStreamConstants.END_ELEMENT:
					switch(reader.getLocalName()) {
						case DocxDefaults.CLASSID:
							issue.setClassId(tagContent);
							if(count.containsKey(tagContent)) {
								count.replace(tagContent, count.get(tagContent) + 1);
							}else
								count.put(tagContent, 1);
							break;
						case DocxDefaults.INSTID:
							issue.setId(tagContent);
							break;
						case DocxDefaults.TYPE:
							issue.setName(tagContent);
							break;
						case DocxDefaults.SUBTYPE:
							issue.setSubName(tagContent);
							break;
						case DocxDefaults.CONFID:
							issue.setConfidence(tagContent);
							break;
						case DocxDefaults.VULN:
							//See if addressing the list will insert rather than doing map.replace
							if(issues.containsKey(issue.getClassId())) {
								issueList = issues.get(issue.getClassId());
								issueList.add(issue);
							} else {
								issueList = new ArrayList<>();
								issueList.add(issue);
								issues.put(issue.getClassId(), issueList);
							}
							break;
						case DocxDefaults.ABSTR:
							//the abstract comes in <Content><Paragraph> and we need to strip that
							//System.out.println(build.toString());
							XMLStreamReader abstrReader = factory.createXMLStreamReader(
									new StringReader(build.toString()));
							while(abstrReader.hasNext())
							{
								int subevent = abstrReader.next();
								boolean read = true;
								switch(subevent) {
									case XMLStreamConstants.START_ELEMENT:
										//System.out.println(subreader.getLocalName());
										if(DocxDefaults.PARA.equals(abstrReader.getLocalName())) {
											build = new StringBuilder();
										}
										if(DocxDefaults.ALTPARA.equals(abstrReader.getLocalName())) {
											//System.out.println(build.toString());
											rule.setDetailIssue(build.toString());
										}
										if(DocxDefaults.REPLACE.equals(abstrReader.getLocalName()))
											read = false;
										break;
									case XMLStreamConstants.CHARACTERS:
										if(read)
											build.append(abstrReader.getText() + " ");
										break;
									case XMLStreamConstants.END_ELEMENT:
										if(DocxDefaults.REPLACE.equals(abstrReader.getLocalName()))
											read = true;
										break;
								}
							}
							boolCheck = false;
							break;
						case DocxDefaults.RECOMM:
							//the recomm comes in <Content> and we need to strip that
							//System.out.println(build.toString());
							XMLStreamReader recommReader = factory.createXMLStreamReader(
									new StringReader(build.toString()));
							boolean read = true;
							build = new StringBuilder();
							while(recommReader.hasNext())
							{
								int subevent = recommReader.next();
								if(read) {
									switch(subevent) {
										case XMLStreamConstants.START_ELEMENT:
											//System.out.println(subreader.getLocalName());
											if(DocxDefaults.B.equals(recommReader.getLocalName()))
											{
												//System.out.println(tagContent);
												rule.setDetailRecomm(build.toString());
												read = false;
											}
											break;
										case XMLStreamConstants.CHARACTERS:
											build.append(recommReader.getText().trim());
											break;
										case XMLStreamConstants.END_ELEMENT:
											if(DocxDefaults.CONT.equals(recommReader.getLocalName()))
												rule.setDetailRecomm(build.toString());
											break;
									}
								}
							}
							//System.out.println("Recommendation: " + build.toString());
							//System.out.println();
							//check if it grabs all of the recommendation
							//System.out.println(rule.getId());
							//System.out.println(build.toString());
							//rule.setDetailRecomm(build.toString());
							boolCheck = false;
							break;
						case DocxDefaults.GROUP:
							switch(attribute)
							{
								case DocxDefaults.ACC:
									rule.setAccuracy(tagContent);
									break;
								case DocxDefaults.IMPACT:
									rule.setImpact(tagContent);
									break;
								case DocxDefaults.PROBAB:
									if(rule != null)
										rule.setProbability(tagContent);
									break;
								default:
									Pattern p = Pattern.compile(DocxDefaults.REGEXSTIG);
									Matcher m = p.matcher(attribute);
									//ArrayList<String> stigList = new ArrayList<>();
									if(m.find())
									{
										String stig = "";
										if(!tagContent.equals("None")) {
											//p = Pattern.compile(DocxDefaults.STIGREGEXREORDER);
											for(String split : tagContent.split(","))
											{
												//m = p.matcher(split);
												//m.find();
												//int i = m.start();
												//stig.concat((split.substring(i) 
												//		+ split.substring(0, i)).trim()
												//		+ "\n");
												stig += split.substring(split.indexOf(DocxDefaults.REGEXREORDERSTIG)) + " " 
														+ split.substring(0, split.indexOf(DocxDefaults.REGEXREORDERSTIG)) + "\n";
												//System.out.println(stig);
											}
											rule.setStigid(stig.trim());
										}
									} else {
										p = Pattern.compile(DocxDefaults.REGEXNIST);
										m = p.matcher(attribute);
										if(m.find()) {
											rule.setNist(tagContent);
										}  else {
											p = Pattern.compile(DocxDefaults.REGEXOWASP);
											m = p.matcher(attribute);
											if(m.find()) {
												rule.setOwasp(tagContent);
											}  else {
												p = Pattern.compile(DocxDefaults.REGEXCWE);
												m = p.matcher(attribute);
												if(m.find())
													rule.setCwe(tagContent);
											}
										}
									}
									break;
							}
							break;
						case DocxDefaults.BUILDID:
							buildData[1] = tagContent;
							break;
						case DocxDefaults.NUMFILES:
							buildData[2] = tagContent;
							break;
						case DocxDefaults.LOC:
							if(!srcFiles)
							{
								switch(attribute) {
									case DocxDefaults.EXELOC:
										buildData[3] = tagContent;
										break;
									case DocxDefaults.TOTALLOC:
										buildData[4] = tagContent;
										break;	
								}
							}
							break;
						case DocxDefaults.DESC:
							rules.put(rule.getId(), rule);
							break;
						case DocxDefaults.ENGVER:
							buildData[5] = tagContent;
							break;
						case DocxDefaults.VER:
							buildData[6] = tagContent;
							break;
					}
					break;
			}
		}
		} catch(Exception e) {
			System.out.println("nope");
		}
		return buildData;
		 /* [0] - ScanDate
		  * [1] - BuildID
		  * [2] - Number of Files
		  * [3] - Lines of Code
		  * [4] - Total Lines of Code
		  * [5] - Scan Engine
		  * [6] - RulePack
		  *
	}
*/
	
	private static void stigCompare(SortedMap<String, ArrayList<String>> map, ArrayList<STIG> stig) {
		for(int i = 0; i < stig.size(); i++)
		{
			if(map.containsKey(stig.get(i).getStigid()))
			{
				int detailSize = stig.get(i).getDetails().size();
				STIG tempSTIG = stig.get(i);
				ArrayList<String> details = tempSTIG.getDetails();
				ArrayList<String> mapList = map.get(tempSTIG.getStigid());
			
				for(int j = 0; j < mapList.size(); j++)
				{
					if(details.contains(mapList.get(j)))
					{ details.remove(mapList.get(j)); }
				}
				//Details will be never be empty as details[0] is a definition for STIG
				//DetailSize added to prevent empty values from marking O as NF
				if(tempSTIG.getStatus().equals("O") && details.size() == 1  && detailSize != 1)
					tempSTIG.setStatus("NF");
			}
		}
	}
		
	private static String outChecklist(File checklist, File save, ArrayList<STIG> stig)
	{	
		try{
			boolean found;
			int lastFound = 0;
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(checklist);
			doc.getDocumentElement().normalize();
			doc.getDocumentElement().getNodeName();
			NodeList nList = doc.getElementsByTagName("VULN");

			for(int i = lastFound; i < stig.size(); i++)
			{
				found = false;
				
				//grabs each attribute from ckl file of the corresponding Policy
				for(int j = 0; j < nList.getLength() && !found; j++)
				{
					Element nElement = (Element) nList.item(j);
					//item(4) is the Policy number
					String item = nElement.getElementsByTagName("ATTRIBUTE_DATA").item(4)
							.getTextContent();
					if(item.contains(stig.get(i).getStigid()))
					{
						//System.out.println(nElement.getElementsByTagName("ATTRIBUTE_DATA").item(4)
						//	.getTextContent());
						//System.out.println("FOUND");
						String status;
						if(stig.get(i).getStatus().equals("NF"))
						{ status = "NotAFinding"; }
						else if (stig.get(i).getStatus().equals("NA"))
						{ status = "Not_Applicable"; }
						else if (stig.get(i).getStatus().equals("NR"))
						{ status = "Not_Reviewed"; }
						else
						{ status = "Open"; }
						nElement.getElementsByTagName("STATUS").item(0).setTextContent(status);
						if(status.equals("Open"))
						{
							StringBuilder sb = new StringBuilder();
							for(int k = 0; k < stig.get(i).getDetails().size(); k++)
								sb.append(stig.get(i).getDetails().get(k) + "\n");
							nElement.getElementsByTagName("FINDING_DETAILS").item(0).setTextContent(sb.toString());
						}
						if(status.equals("NotAFinding"))
						{
							nElement.getElementsByTagName("FINDING_DETAILS").item(0).setTextContent(
									"Documentation and Scripts have found this as not a finding.");
						}
						found = true;
						lastFound = i;
					}
				}
			}
			
			//Transforms the template form with the new values
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(save);
			transformer.transform(source, result);
		}
		catch (Exception e)
		{
			error(e);
			return "An error has occurred finding one or more of the findings."; 
		}
		
		return "Successfully created checklist file.";
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
