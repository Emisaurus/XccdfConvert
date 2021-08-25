package xccdfconvert;

import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Pattern;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
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
		// TODO Needs to take in a xccdf file and output name
		// or directory of xccdf files for multiple
		String message = "";
		ArrayList<STIG> stig;

		File xccdffile;
		
		try
		{
			File srrcopy = File.createTempFile("srrcopy", "");
			Files.copy(xccdffile.toPath(), srrcopy.toPath(), StandardCopyOption.REPLACE_EXISTING);

			long timestart, timeend, duration;
			
			timestart = System.nanoTime();
			stig = xmlStaxtoSTIG(srrcopy);
			timeend = System.nanoTime();
			duration = timeend - timestart;
			
			System.out.println("Adding stig items took: " + duration);
			
				timestart = System.nanoTime();
				stigCompare(map, stig);
				timeend = System.nanoTime();
				duration = timeend - timestart;
				System.out.println("Comparing stig items took: " + duration);
				
					timestart = System.nanoTime();
					message = outChecklist(new File("./DBChecker/12c.ckl"), checklistfile, stig); 
					timeend = System.nanoTime();
					duration = timeend - timestart;
					System.out.println("Creating checklist took: " + duration);
			
			srrcopy.delete();

		}catch(Exception e) {
			e.printStackTrace();
			message = "An Error has occurred.  Please check the logs.";
			error(e);
		}
		System.out.println(message);

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
	
	public static String convert(File xccdffile, File checklistfile)
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
	
	private static ArrayList<STIG> xmlStaxtoSTIG(File srrcopy) throws Exception {
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
	}

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