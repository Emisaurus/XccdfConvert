package xccdfconvert;

import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import java.util.regex.Matcher;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat; 

public class xccdfconvert {
/*
	public static void main(String[] args) {
		 //TODO Needs to take in a xccdf file and output name
		// or directory of xccdf files for multiple
		String message = "";

		//File xccdffile = new File(args[0]);
		//File xslfile = new File(args[1]);
		
		try
		{
			long timestart, timeend, duration;
			
			timestart = System.nanoTime();
			//xmlStaxtoSTIG(xccdffile);
			xsl(args[0], "test.ckl", args[1]);
			timeend = System.nanoTime();
			duration = timeend - timestart;
			System.out.println(duration);
		}catch(Exception e) {
			e.printStackTrace();
			message = "An Error has occurred.  Please check the logs.";
			error(e);
		}
	}*/
	
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
	
	public static void convert(File xccdffile, File checklistfile)
	{
		
	}

	public static void xsl(String inFilename, String outFilename, String xslFilename) {
        try {
            // Create transformer factory
            TransformerFactory factory = TransformerFactory.newInstance();

            // Use the factory to create a template containing the xsl file
            Templates template = factory.newTemplates(new StreamSource(
                new FileInputStream(xslFilename)));

            // Use the template to create a transformer
            Transformer xformer = template.newTransformer();

            // Prepare the input and output files
            Source source = new StreamSource(new FileInputStream(inFilename));
            Result result = new StreamResult(new FileOutputStream(outFilename));

            // Apply the xsl file to the source file and write the result
            // to the output file
            xformer.transform(source, result);
        } catch (FileNotFoundException e) {
        } catch (TransformerConfigurationException e) {
            // An error occurred in the XSL file
        } catch (TransformerException e) {
            // An error occurred while applying the XSL file
            // Get location of error in input file
            SourceLocator locator = e.getLocator();
            int col = locator.getColumnNumber();
            int line = locator.getLineNumber();
            String publicId = locator.getPublicId();
            String systemId = locator.getSystemId();
        }
    }
	
	/*
	public static String compareOriginal(SortedMapString, ArrayListString map, File srr, File save, boolean setting)
	{
		String message = ;
		ArrayListSTIG stig;
		try
		{
			File srrcopy = File.createTempFile(srrcopy, );
			Files.copy(srr.toPath(), srrcopy.toPath(), StandardCopyOption.REPLACE_EXISTING);
			long timestart, timeend, duration;
			
			timestart = System.nanoTime();
			if(srr.getName().endsWith(".txt"))
			{ stig = txtToSTIG(srrcopy); }
			else
			{ stig = xmlStaxtoSTIG(srrcopy); }
			timeend = System.nanoTime();
			duration = timeend - timestart;
			
			System.out.println("Adding stig items took " + duration);
			
				timestart = System.nanoTime();
				stigCompare(map, stig);
				timeend = System.nanoTime();
				duration = timeend - timestart;
				System.out.println("Comparing stig items took " + duration);
				
					timestart = System.nanoTime();
					message = outChecklist(new File(.DBChecker12c.ckl), save, stig); 
					timeend = System.nanoTime();
					duration = timeend - timestart;
					System.out.println("Creating checklist took " + duration);
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
		String group_title = null;
		String attribute = "None";
		String display, end = "";
		boolean boolCheck = false, inrule = false;
		ArrayList<String> desc = null;
		StringBuilder build = null;
		STIG stig = null;
		
		try{
			//Separate this into portions to avoid the improper tag name re-use
			ArrayList<String> header = convertHeader(reader);
			
		while(reader.hasNext()) {
			int event = reader.next();
			switch(event) {
				case XMLStreamConstants.START_ELEMENT:
					attribute = reader.getLocalName();
					switch(reader.getLocalName()) {
					case "Group":
						stig = new STIG(reader.getAttributeValue(0), group_title);
						desc = new ArrayList<>();
						break;
					case "Rule":
						inrule = true;
						while(reader.hasNext() && inrule) {
							int subevent = reader.next();

							switch(subevent)
							{
								case XMLStreamConstants.START_ELEMENT:
									break;
									
								case XMLStreamConstants.CHARACTERS:
									tagContent = reader.getText().trim();
									break;
									
								case XMLStreamConstants.END_ELEMENT:
									switch(reader.getLocalName()) {
										case "version":
											stig.setRuleVer(tagContent);
											break;
										case "title":
											stig.setRuleTitle(tagContent);
											break;
										case "description":
											stig.setVulnDiscuss(tagContent);
											break;
										case "ident":
											desc.add(tagContent);
											break;
										case "fixtext":
											stig.setFixText(tagContent);
											break;
										case "fix":
											break;
										case "check-content-ref":
											break;
										case "rule":
											inrule = false;
											break;
									}
									break;
							};
						}
						stig.setSeverity(reader.getAttributeValue(1));
						stig.setWeight(reader.getAttributeValue(3));
						break;
					};			
					break;

				case XMLStreamConstants.CHARACTERS:
					tagContent = reader.getText().trim();
					break;
					
				case XMLStreamConstants.END_ELEMENT:
					end = reader.getLocalName();
					System.out.println("end :" + reader.getLocalName());
					switch(reader.getLocalName()) {
						case "title":
							group_title = tagContent;
							break;
						case "version":
							stig.setVulnNum(tagContent);
							break;
					}
					break;
			}
		}
		} catch(Exception e) {
			System.out.println("nope");
		}
	}
		
	private static ArrayList<String> convertHeader(XMLStreamReader reader) throws Exception {
			String tagContent = null;
			ArrayList<String> header = new ArrayList<>();
			String attribute = "None";
			String display, end = "";
			String[] buildData = new String[7]; //Set to the number of details needed
			boolean resume = true;
			StringBuilder build = null;
			
			while(reader.hasNext() && resume) {
				int event = reader.next();
				switch(event) {
					case XMLStreamConstants.START_ELEMENT:
						switch(reader.getLocalName()) {
						case "Profile":
							resume = false;
							break;
						case "notice":
							header.add(reader.getAttributeValue(0));
							break;
						};
						break;

					case XMLStreamConstants.CHARACTERS:
						tagContent = reader.getText().trim();
						if(!tagContent.isEmpty())
							header.add(tagContent);
						break;
						
					case XMLStreamConstants.END_ELEMENT:
						break;
				}
			}
			 return header;
		}
		
		/*
		ArrayListSTIG stigs = new ArrayList();
		STIG tempstig = null;
		ArrayListString details = null;
		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(srrcopy));
		
		while(xmlEventReader.hasNext())
		{
			XMLEvent xmlEvent = xmlEventReader.nextEvent();
			if(xmlEvent.isStartElement())
			{
				StartElement startElement = xmlEvent.asStartElement();
				System.out.println(startElement.getName().getLocalPart());
				
				if(startElement.getName().getLocalPart().equals(VULN))
				{
					tempstig = new STIG();
					details = new ArrayList();
				}
				else if(startElement.getName().getLocalPart().equals(STIGID))
				{
					xmlEvent = xmlEventReader.nextEvent();
					System.out.println(xmlEvent.asCharacters().getData());
					tempstig.setStigid(xmlEvent.asCharacters().getData());
				}
				else if(startElement.getName().getLocalPart().equals(STATUS))
				{
					xmlEvent = xmlEventReader.nextEvent();
					tempstig.setStatus(xmlEvent.asCharacters().getData());
				}else if(startElement.getName().getLocalPart().equals(FINDING_DETAILS))
				{
					xmlEvent = xmlEventReader.nextEvent();
					Added if() due to FINDING_DETAILSFINDING_DETAILS results on old scripts
					if(!xmlEvent.isEndElement())
						details.add(xmlEvent.asCharacters().getData());
				}
			}
			if(xmlEvent.isEndElement())
			{
				EndElement endElement = xmlEvent.asEndElement();
				if(endElement.getName().getLocalPart().equals(VULN))
				{ 
					tempstig.setDetails(details);
					stigs.add(tempstig);
				}
			}
		}
		return stigs;	
		*/

/*	
	private static void stigCompare(SortedMapString, ArrayListString map, ArrayListSTIG stig) {
		for(int i = 0; i  stig.size(); i++)
		{
			if(map.containsKey(stig.get(i).getStigid()))
			{
				int detailSize = stig.get(i).getDetails().size();
				STIG tempSTIG = stig.get(i);
				ArrayListString details = tempSTIG.getDetails();
				ArrayListString mapList = map.get(tempSTIG.getStigid());
			
				for(int j = 0; j  mapList.size(); j++)
				{
					if(details.contains(mapList.get(j)))
					{ details.remove(mapList.get(j)); }
				}
				Details will be never be empty as details[0] is a definition for STIG
				DetailSize added to prevent empty values from marking O as NF
				if(tempSTIG.getStatus().equals(O) && details.size() == 1  && detailSize != 1)
					tempSTIG.setStatus(NF);
			}
		}
	}
	*/
	
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
					if(item.contains(stig.get(i).getRuleId()))
					{
						System.out.println(nElement.getElementsByTagName("ATTRIBUTE_DATA").item(4)
							.getTextContent());
						System.out.println("FOUND");
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
			DateFormat dateFormat = new SimpleDateFormat("MMddyyyy HHmmss");
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