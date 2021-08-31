package docxtemplate;

import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.docx4j.Docx4J;
import org.docx4j.jaxb.Context;
import org.docx4j.model.table.TblFactory;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.DocumentSettingsPart;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.samples.AbstractSample;
import org.docx4j.wml.BooleanDefaultFalse;
import org.docx4j.wml.BooleanDefaultTrue;
import org.docx4j.wml.CTBorder;
import org.docx4j.wml.CTColumns;
import org.docx4j.wml.CTCompat;
import org.docx4j.wml.CTDocGrid;
import org.docx4j.wml.CTHeight;
import org.docx4j.wml.CTOdsoFieldMapData.Column;
import org.docx4j.wml.CTTabStop;
import org.docx4j.wml.CTTblLayoutType;
import org.docx4j.wml.CTTblLook;
import org.docx4j.wml.CTTblPrBase;
import org.docx4j.wml.Color;
import org.docx4j.wml.FldChar;
import org.docx4j.wml.FooterReference;
import org.docx4j.wml.HeaderReference;
import org.docx4j.wml.HpsMeasure;
import org.docx4j.wml.Jc;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.PPr;
import org.docx4j.wml.PPrBase;
import org.docx4j.wml.ParaRPr;
import org.docx4j.wml.R;
import org.docx4j.wml.RPr;
import org.docx4j.wml.RStyle;
import org.docx4j.wml.SectPr;
import org.docx4j.wml.Tabs;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.TblGrid;
import org.docx4j.wml.TblGridCol;
import org.docx4j.wml.TblPr;
import org.docx4j.wml.TblWidth;
import org.docx4j.wml.Tc;
import org.docx4j.wml.TcPr;
import org.docx4j.wml.TcPrInner;
import org.docx4j.wml.Text;
import org.docx4j.wml.Tr;
import org.docx4j.wml.TrPr;
import org.docx4j.wml.U;

import javafx.scene.control.TablePosition;

public class DocxTemplate extends AbstractSample {
    //Global Docx Declarations
	static ObjectFactory factory = Context.getWmlObjectFactory();
	static RPr rprText = factory.createRPr(); 
	static HpsMeasure hpsmeasure39 = factory.createHpsMeasure();
	static HpsMeasure hpsmeasure28 = factory.createHpsMeasure();
	static HpsMeasure hpsmeasure24 = factory.createHpsMeasure();
	static HpsMeasure hpsmeasure20 = factory.createHpsMeasure();
	static HpsMeasure hpsmeasure14 = factory.createHpsMeasure();
	static RPr rprHeading = factory.createRPr(); 
	static PPr pprHeading = factory.createPPr(); 
	static CTBorder borderNormal = factory.createCTBorder(); 
    static TcPrInner.TcBorders tcprinnertcbordersNormal = factory.createTcPrInnerTcBorders(); 
//	static HpsMeasure hpsmeasureTitle = factory.createHpsMeasure();
//	static HpsMeasure hpsmeasureHeadings = factory.createHpsMeasure();
//	static HpsMeasure hpsmeasureText = factory.createHpsMeasure(); 
//	static HpsMeasure hpsmeasureTableTitle = factory.createHpsMeasure(); 
//	static HpsMeasure hpsmeasureTableText = factory.createHpsMeasure(); 
	static PPrBase.Spacing pprbasespacingText = factory.createPPrBaseSpacing(); 
    static PPr pprText = factory.createPPr();
    static BooleanDefaultTrue booltrue = factory.createBooleanDefaultTrue(); 
    static Jc jcCenter = factory.createJc();
    static Jc jcRight = factory.createJc();
    static RPr rprTableTitle = factory.createRPr(); 

	public static void main(String[] args) {
		File fprFile = null;
		File location = null;
		//Collection<ArrayList<Issue>> issueColl;
		
		//args[1] = "C:/Users/Fortify Team/Desktop/Missing Baseline FPRs/ScottsWIP/COREMODEL_14_16_0_uat2.fpr";
		//args[1] = fpr
		//args[2] = template?

		//args[0] = fpr
		//args[1] = template?
		
		Map<String, Integer> count = new HashMap(); //ClassID and Occurrences
		Map<String, ArrayList<Issue>> issues = new HashMap(); //ClassID and Issue
		Map<String, Rule> rules = new HashMap(); //ClassID and Rule
		Map<String, Issue> xmlList = new HashMap();
		String[] details = null; //Fortify information
		int countCat1 = 0;
		int countCat2 = 0;
		int countLow = 0;
		
		//Check if the filepath for the FPR is empty
		if(args[0].isEmpty()) {
			System.out.println("The FPR file cannot be empty.");
			System.exit(0);
		}

		try {
			//Check if the FPR file exists ***TODO***
			fprFile = new File(args[0]);
			
			location = FprRead.unZip(fprFile);
			if(location.exists())
			{
				//System.out.println(location.getAbsolutePath());
				File fvdlFile = new File(location.getAbsolutePath() + '/' + DocxDefaults.NAMEFVDL);
				File xmlFile = new File(location.getAbsolutePath() + '/' + DocxDefaults.NAMEXML);
				if(fvdlFile.exists() && xmlFile.exists())
				{
					//FprRead.fprRead(fvdlFile, xmlFile, location.getName());
					details = FprRead.fprFvdlRead(fvdlFile, count, issues, rules);
					details[1] = fprFile.getName();
				} else
					throw new Exception("The .fvdl/.xml file failed to extract.");
				
				delete(location);
				
			}
					
				try {
					//Check if the FPR file exists ***TODO***
					//File fprFile = new File(args[1]);

					
					/*
					for(int i = 0; i < details.length; i++)
					{
						System.out.println(details[i]);
					}
					*/
					
					//UNTESTED
					//This will find exclusively "CAT I"
					//Pattern p = Pattern.compile("CAT I[[:blank:]]");
					//Matcher m = p.matcher(attribute);

					//for(Rule rule : rules.Values())
					//{
					//	if(rule.getStigId().isEmpty())
					//	{	countLow++; }
					//	else if(m.find())
					//	{	countCat1++; }
					//	else
					//		countCat2++;
					//}
					//System.out.println("CAT I Count: " + countCat1);
					//System.out.println("CAT II Count: " + countCat2);
					//System.out.println("Low Risk Count: " + countLow);
					//UNTESTED
					
					Collection<ArrayList<Issue>> issueColl = issues.values();
					Iterator it = issueColl.iterator();
					String[] check = new String[600];
					int count1 = 0;
					while(it.hasNext())
					{
						Issue issue;
						ArrayList<Issue> issueList = (ArrayList<Issue>) it.next();
						for(int i = 0; i < issueList.size(); i++)
						{
							issue = issueList.get(i);
							xmlList.put(issue.getId(), issue);
							check[count1] = issue.getId();
							count1++;
						}
					}
					/*
					for(String str : check)
					{
						int moo = 0;
						if(str.isEmpty())
							throw new Exception("Empty Inst Id");
						for(int i = 0; i< check.length; i++)
							if(check.equals(str))
								moo++;
						if(moo > 1)
							throw new Exception("we have a duplicate inst id");
								
					}
					*/
					//fprXmlRead(xmlReader, xmlList);

				} catch(Exception e) {
					//The file could not open. We already checked if the filename was empty
					System.out.println("Could not open the FPR file.");
				} finally {
					//if(fprFile != null)
					 //fprFile.close();
				}
			
			//We want to pass the file over and create the docx here ***TODO***
			try {
				//Create the document's docx
				WordprocessingMLPackage wordPackage = WordprocessingMLPackage.createPackage();
				MainDocumentPart mainDocumentPart = wordPackage.getMainDocumentPart();
							
				//Broke off Title, Intro, and Risk Assessment for Clarity
				documentSettings(mainDocumentPart);
	            titleIntroRisk(mainDocumentPart, details[1]);
				overallFindings(mainDocumentPart, 0, 0, 0);
				findingsTable(mainDocumentPart, rules, issues);
				findingAnalysis(mainDocumentPart, rules, issues);	
				
				//Word End Section
		        SectPr sectpr = factory.createSectPr();
		        mainDocumentPart.getContents().getBody().setSectPr(sectpr);
		            HeaderReference headerreference = factory.createHeaderReference(); 
		            sectpr.getEGHdrFtrReferences().add( headerreference); 
		                headerreference.setType(org.docx4j.wml.HdrFtrRef.DEFAULT);
		                headerreference.setId( "rId7"); 
		            FooterReference footerreference = factory.createFooterReference(); 
		            sectpr.getEGHdrFtrReferences().add( footerreference); 
		                footerreference.setType(org.docx4j.wml.HdrFtrRef.DEFAULT);
		                footerreference.setId( "rId8"); 
		            SectPr.PgSz sectprpgsz = factory.createSectPrPgSz(); 
		            sectpr.setPgSz(sectprpgsz); 
		                sectprpgsz.setW( BigInteger.valueOf( 12240) ); 
		                sectprpgsz.setH( BigInteger.valueOf( 15840) ); 
		            SectPr.PgMar sectprpgmar = factory.createSectPrPgMar(); 
		            sectpr.setPgMar(sectprpgmar); 
		                sectprpgmar.setTop( BigInteger.valueOf( 810) ); 
		                sectprpgmar.setRight( BigInteger.valueOf( 1350) ); 
		                sectprpgmar.setBottom( BigInteger.valueOf( 810) ); 
		                sectprpgmar.setLeft( BigInteger.valueOf( 1440) ); 
		                sectprpgmar.setHeader( BigInteger.valueOf( 720) ); 
		                sectprpgmar.setFooter( BigInteger.valueOf( 720) ); 
		                sectprpgmar.setGutter( BigInteger.valueOf( 0) ); 
		            CTColumns columns = factory.createCTColumns(); 
		            sectpr.setCols(columns); 
		                columns.setSpace( BigInteger.valueOf( 720) ); 
		            CTDocGrid docgrid = factory.createCTDocGrid(); 
		            sectpr.setDocGrid(docgrid); 
		                docgrid.setLinePitch( BigInteger.valueOf( 360) ); 
	            //document.setIgnorable( "w14 w15 w16se w16cid w16 w16cex wp14"); 

				
				
				File exportFile = new File("welcome2.docx");
				wordPackage.save(exportFile);
			}catch(Exception e)
			{
				//Fuck your errors}
			}
		} catch(Exception e) {
			//The file could not open. We already checked if the filename was empty
			System.out.println("Could not open the FPR file.");
		} finally {
			if(location.exists())
				delete(location);
		}	
	}
	
	public static void delete(File location) {
		try {
		if(location.isDirectory()) {
			for(File file : location.listFiles())
				delete(file);
		}
		location.delete();
		} catch(Exception e){
			System.out.println("The location failed to delete. Please review " 
					+ location.getAbsolutePath() 
					+ "to see if you need to clear it out.");
		}
	}
	
	public static P createBasicP(String text, RPr rpr, PPr ppr)
	{
        P p = factory.createP(); 
        R r = factory.createR(); 
        	p.getContent().add( r); 
    	Text t = factory.createText(); 
    		r.getContent().add( t); 
        		t.setValue(text);
        		t.setSpace("preserve");
    		r.setRPr(rpr); 
    		p.setPPr(ppr); 
		return p;
	}

	public static void documentSettings(MainDocumentPart mainDoc)
	{
		//Font Size
    	hpsmeasure39.setVal(BigInteger.valueOf(39));
    	hpsmeasure28.setVal(BigInteger.valueOf(28));
    	hpsmeasure24.setVal( BigInteger.valueOf( 24) ); 
    	hpsmeasure20.setVal( BigInteger.valueOf( 20) ); 
    	hpsmeasure14.setVal( BigInteger.valueOf( 14) );
	    
    	//Justification
    	jcCenter.setVal(org.docx4j.wml.JcEnumeration.CENTER);
    	jcRight.setVal(org.docx4j.wml.JcEnumeration.RIGHT);
    	
	    rprText.setSz(hpsmeasure20); 
	    rprText.setSzCs(hpsmeasure20); 
	    
	    pprText.setSpacing(pprbasespacingText); 
	    	pprbasespacingText.setBefore( BigInteger.valueOf( 0) ); 
            pprbasespacingText.setAfter( BigInteger.valueOf( 0) ); 
            
        borderNormal.setVal(org.docx4j.wml.STBorder.SINGLE);
        borderNormal.setColor( "auto"); 
        borderNormal.setSz( BigInteger.valueOf( 4) ); 
        borderNormal.setSpace( BigInteger.valueOf( 0) ); 
        tcprinnertcbordersNormal.setTop(borderNormal); 
        tcprinnertcbordersNormal.setBottom(borderNormal); 
        tcprinnertcbordersNormal.setLeft(borderNormal); 
        tcprinnertcbordersNormal.setRight(borderNormal); 
            
		//Heading Settings
        rprHeading.setB(booltrue); 
        rprHeading.setBCs(booltrue); 
        rprHeading.setSz(hpsmeasure28); 
        rprHeading.setSzCs(hpsmeasure28); 

        PPrBase.Spacing pprbasespacingHeading = factory.createPPrBaseSpacing(); 
        pprHeading.setSpacing(pprbasespacingHeading); 
            pprbasespacingHeading.setBefore( BigInteger.valueOf( 120) ); 
            pprbasespacingHeading.setAfter( BigInteger.valueOf( 120) ); 
                    
        rprTableTitle.setB(booltrue); 
        rprTableTitle.setSz(hpsmeasure24); 
        rprTableTitle.setSzCs(hpsmeasure24); 
	}
	
	public static void titleIntroRisk(MainDocumentPart mainDoc, String module)
	{
		//TODO - (Module), (Date Created)
		//Baseline Title
		RPr rprtitle = factory.createRPr();
            rprtitle.setSz(hpsmeasure39); 
            rprtitle.setSzCs(hpsmeasure39); 
        PPr pprtitle = factory.createPPr(); 
            pprtitle.setSpacing(pprbasespacingText); 
            pprtitle.setJc(jcCenter); 
        mainDoc.getContent().add(createBasicP("Fortify Risk Management Framework (RMF) Baseline",
    			rprtitle, pprtitle));
        mainDoc.getContent().add(createBasicP("(Program) " + module,
    			rprtitle, pprtitle));
   
        //Prepared By/Date Prepared Settings
        PPr ppr2 = factory.createPPr(); 
	        ppr2.setSpacing(pprbasespacingText); 
	        ppr2.setJc(jcCenter);  
        
        //Prepared By
        mainDoc.getContent().add(createBasicP("Prepared by: (Reviewer Name)", rprText, ppr2));
            
        //Date Prepared
        mainDoc.getContent().add(createBasicP("Date Prepared: (Date Created)", rprText, ppr2));               
            
        //Intro Heading
        mainDoc.getContent().add(createBasicP("Introduction", rprHeading, pprHeading));               
            
        //Intro Text
        mainDoc.getContent().add(createBasicP("This risk assessment is a Risk Management Framework (RMF) baseline of (New Module)."
        		+ " Risk was determined by analyzing vulnerabilities against Defense Information Systems "
        		+ "Agency (DISA) Application Security and Development Security Technical Implementation "
        		+ "Guide (STIG), Open Web Application Security Project (OWASP) Top 10, and the Common "
        		+ "Weakness Enumeration (CWE)/System Administration, Networking, and Security (SANS) "
        		+ "Institute Top 25 dangerous programming errors.",
        		rprText, pprText));   
            
        //Risk Assess Heading
     	mainDoc.getContent().add(createBasicP("Risk Assessment/Summary", rprHeading, pprHeading));
    	
        //Risk Assess Text
     	mainDoc.getContent().add(createBasicP( "The risk of " + module + " RMF baseline is (Risk) based on the "
     			+ "impact and likelihood variables prescribed by the Fortify scan tool.",
     			rprText, pprText));
	}
    
	public static void overallFindings(MainDocumentPart mainDoc, Integer countCatI, 
			Integer countCatII, Integer countNone)
	{		
		Integer countTotal = countCatI + countCatII + countNone;
		
		
		//Summary Table Title
        //PPr pprTableTitle = factory.createPPr(); 
        //PPrBase.Spacing pprbasespacing = factory.createPPrBaseSpacing(); 
        //pprTableTitle.setSpacing(pprbasespacingHeading); 
        //    pprbasespacing.setBefore( BigInteger.valueOf( 120) ); 
        //    pprbasespacing.setAfter( BigInteger.valueOf( 120) ); 
        //PPrBase.Ind pprbaseind = factory.createPPrBaseInd(); 
        //pprTableTitle.setInd(pprbaseind); 
        //    pprbaseind.setLeft( BigInteger.valueOf( 720) ); 
        //    pprbaseind.setHanging( BigInteger.valueOf( 360) ); 
     	mainDoc.getContent().add(createBasicP("Overall Findings",
     			rprTableTitle, pprHeading));
        
        //Summary Table Settings
        RPr rprTableHeading = factory.createRPr(); 
            rprTableHeading.setB(booltrue); 
        	rprTableHeading.setSz(hpsmeasure24); 
        	rprTableHeading.setSzCs(hpsmeasure24); 
	    PPr pprHeadingLeft = factory.createPPr(); 
	    PPr pprHeadingRight = factory.createPPr(); 
	    	pprHeadingRight.setJc(jcRight); 
	    PPr pprHeadingCenter = factory.createPPr(); 
	    	pprHeadingCenter.setJc(jcCenter); 
        TcPr tcprTable = factory.createTcPr(); 
        //TblWidth tblwidthTable = factory.createTblWidth(); 
          //  tcprTable.setTcW(tblwidthTable); 
            //    tblwidthTable.setType( "dxa"); 
            tcprTable.setTcBorders(tcprinnertcbordersNormal); 
            //tcprTable.setHideMark(booltrue); 
    	TrPr trprHeading = factory.createTrPr(); 	
    		CTHeight heightHeading = factory.createCTHeight(); 
    			heightHeading.setVal( BigInteger.valueOf( 224) ); 
			JAXBElement<org.docx4j.wml.CTHeight> heightWrapped = factory.createCTTrPrBaseTrHeight(heightHeading); 
			trprHeading.getCnfStyleOrDivIdOrGridBefore().add( heightWrapped); 
            
        TrPr trprBody = factory.createTrPr(); 
            CTHeight heightBody = factory.createCTHeight(); 
            JAXBElement<org.docx4j.wml.CTHeight> heightWrappedBody = factory.createCTTrPrBaseTrHeight(heightBody); 
            heightBody.setVal( BigInteger.valueOf( 165) ); 
            trprBody.getCnfStyleOrDivIdOrGridBefore().add( heightWrappedBody); 
            
            TcPr tcprTableBody = factory.createTcPr(); 
        	tcprTableBody.setTcBorders(tcprinnertcbordersNormal);     
        	tcprTableBody.setHideMark(booltrue); 
        PPr pprTableHeading = factory.createPPr(); 
    		pprTableHeading.setJc(jcCenter); 
        RPr rprTablesHeading = factory.createRPr(); 
                    //RFonts rfonts = factory.createRFonts(); 
                    //rpr103.setRFonts(rfonts); 
                    //    rfonts.setCstheme(org.docx4j.wml.STTheme.MINOR_H_ANSI);
        	rprTablesHeading.setB(booltrue); 
        	rprTablesHeading.setSz(hpsmeasure14); 
        	rprTablesHeading.setSzCs(hpsmeasure14); 
                
                
        //Summary Table        
        Tbl tbl = factory.createTbl(); 
        mainDoc.getContent().add( tbl); 
        Tr tr = factory.createTr(); 
        	tbl.getContent().add( tr); 
        	tr.setTrPr(trprHeading); 
        Tr tr2 = factory.createTr(); 
            tbl.getContent().add( tr2); 
            tr2.setTrPr(trprBody);
        Tr tr3 = factory.createTr(); 
        	tbl.getContent().add( tr3); 
            tr3.setTrPr(trprBody);
        Tr tr4 = factory.createTr(); 
        	tbl.getContent().add( tr4); 
            tr4.setTrPr(trprBody);  
        Tr tr5 = factory.createTr(); 
        	tbl.getContent().add( tr5); 
            tr5.setTrPr(trprBody); 
            
        //Summary Table - Headings Row
        tr.getContent().add(tcTableHelper("DISA STIG CATEGORY", rprTablesHeading, 
        		pprHeadingLeft, tcprTable));
        tr.getContent().add(tcTableHelper("Raw Baseline Findings", rprTablesHeading, 
        		pprHeadingCenter, tcprTable));
        tr.getContent().add(tcTableHelper("False Positives", rprTablesHeading, 
        		pprHeadingCenter, tcprTable));
        tr.getContent().add(tcTableHelper("Mitigated Findings", rprTablesHeading, 
        		pprHeadingCenter, tcprTable));
        tr.getContent().add(tcTableHelper("Mitigated to Low Risk", rprTablesHeading, 
        		pprHeadingCenter, tcprTable));
        tr.getContent().add(tcTableHelper("Remaining Findings (Requiring remediation or further mitigation)",
        		rprTablesHeading, pprHeadingCenter, tcprTable));

        //Summary Table - 2nd Row
        tr2.getContent().add(tcTableHelper("CATEGORY I", rprTablesHeading, 
        		pprHeadingLeft, tcprTable));
        tr2.getContent().add(tcTableHelper(countCatI.toString(), rprTablesHeading, 
        		pprHeadingCenter, tcprTable));
        tr2.getContent().add(tcTableHelper("", rprTablesHeading, 
        		pprHeadingCenter, tcprTable));
        tr2.getContent().add(tcTableHelper("", rprTablesHeading, 
        		pprHeadingCenter, tcprTable));
        tr2.getContent().add(tcTableHelper("", rprTablesHeading, 
        		pprHeadingCenter, tcprTable));
        tr2.getContent().add(tcTableHelper("", rprTablesHeading, 
        		pprHeadingCenter, tcprTable));
        
        //Summary Table - 3rd Row
        tr3.getContent().add(tcTableHelper("CATEGORY II", rprTablesHeading, 
        		pprHeadingLeft, tcprTable));
        tr3.getContent().add(tcTableHelper(countCatII.toString(), rprTablesHeading, 
        		pprHeadingCenter, tcprTable));    
        tr3.getContent().add(tcTableHelper("", rprTablesHeading, 
        		pprHeadingCenter, tcprTable));
        tr3.getContent().add(tcTableHelper("", rprTablesHeading, 
        		pprHeadingCenter, tcprTable));
        tr3.getContent().add(tcTableHelper("", rprTablesHeading, 
        		pprHeadingCenter, tcprTable));
        tr3.getContent().add(tcTableHelper("", rprTablesHeading, 
        		pprHeadingCenter, tcprTable));
        
        //Summary Table - 4th Row
        tr4.getContent().add(tcTableHelper("NO STIG/OWASP/SANS MAPPING", rprTablesHeading, 
        		pprHeadingLeft, tcprTable));
        tr4.getContent().add(tcTableHelper(countNone.toString(), rprTablesHeading, 
        		pprHeadingCenter, tcprTable));    
        tr4.getContent().add(tcTableHelper("", rprTablesHeading, 
        		pprHeadingCenter, tcprTable));
        tr4.getContent().add(tcTableHelper("", rprTablesHeading, 
        		pprHeadingCenter, tcprTable));
        tr4.getContent().add(tcTableHelper("", rprTablesHeading, 
        		pprHeadingCenter, tcprTable));
        tr4.getContent().add(tcTableHelper("", rprTablesHeading, 
        		pprHeadingCenter, tcprTable));   
        
        //Summary Table - 5th Row
        tr5.getContent().add(tcTableHelper("TOTAL", rprTablesHeading, 
        		pprHeadingRight, tcprTable));
        tr5.getContent().add(tcTableHelper(countTotal.toString(), rprTablesHeading, 
        		pprHeadingCenter, tcprTable));    
        tr5.getContent().add(tcTableHelper("", rprTablesHeading, 
        		pprHeadingCenter, tcprTable));
        tr5.getContent().add(tcTableHelper("", rprTablesHeading, 
        		pprHeadingCenter, tcprTable));
        tr5.getContent().add(tcTableHelper("", rprTablesHeading, 
        		pprHeadingCenter, tcprTable));
        tr5.getContent().add(tcTableHelper("", rprTablesHeading, 
        		pprHeadingCenter, tcprTable));  
        

            // Create object for tblPr
            TblPr tblpr = factory.createTblPr(); 
            tbl.setTblPr(tblpr); 
                // Create object for tblStyle
                CTTblPrBase.TblStyle tblprbasetblstyle = factory.createCTTblPrBaseTblStyle(); 
                tblpr.setTblStyle(tblprbasetblstyle); 
                    tblprbasetblstyle.setVal( "TableGrid"); 
                // Create object for tblInd
                TblWidth tblwidth32 = factory.createTblWidth(); 
                tblpr.setTblInd(tblwidth32); 
                    tblwidth32.setW( BigInteger.valueOf( 355) ); 
                    tblwidth32.setType( "dxa"); 
                // Create object for tblLayout
                CTTblLayoutType tbllayouttype = factory.createCTTblLayoutType(); 
                tblpr.setTblLayout(tbllayouttype); 
                    tbllayouttype.setType(org.docx4j.wml.STTblLayoutType.FIXED);
                // Create object for tblLook
                CTTblLook tbllook = factory.createCTTblLook(); 
                tblpr.setTblLook(tbllook); 
                    tbllook.setFirstRow(org.docx4j.sharedtypes.STOnOff.ONE);
                    tbllook.setLastRow(org.docx4j.sharedtypes.STOnOff.ZERO);
                    tbllook.setFirstColumn(org.docx4j.sharedtypes.STOnOff.ONE);
                    tbllook.setLastColumn(org.docx4j.sharedtypes.STOnOff.ZERO);
                    tbllook.setNoHBand(org.docx4j.sharedtypes.STOnOff.ZERO);
                    tbllook.setNoVBand(org.docx4j.sharedtypes.STOnOff.ONE);
                    tbllook.setVal( "04A0"); 
                    
            // Create object for tblGrid
            TblGrid tblgrid = factory.createTblGrid(); 
            tbl.setTblGrid(tblgrid); 
                // Create object for gridCol
                TblGridCol tblgridcol = factory.createTblGridCol(); 
                tblgrid.getGridCol().add( tblgridcol); 
                    tblgridcol.setW( BigInteger.valueOf( 2250) ); 
                // Create object for gridCol
                TblGridCol tblgridcol2 = factory.createTblGridCol(); 
                tblgrid.getGridCol().add( tblgridcol2); 
                    tblgridcol2.setW( BigInteger.valueOf( 900) ); 
                // Create object for gridCol
                TblGridCol tblgridcol3 = factory.createTblGridCol(); 
                tblgrid.getGridCol().add( tblgridcol3); 
                    tblgridcol3.setW( BigInteger.valueOf( 990) ); 
                // Create object for gridCol
                TblGridCol tblgridcol4 = factory.createTblGridCol(); 
                tblgrid.getGridCol().add( tblgridcol4); 
                    tblgridcol4.setW( BigInteger.valueOf( 1260) ); 
                // Create object for gridCol
                TblGridCol tblgridcol5 = factory.createTblGridCol(); 
                tblgrid.getGridCol().add( tblgridcol5); 
                    tblgridcol5.setW( BigInteger.valueOf( 1260) ); 
                // Create object for gridCol
                TblGridCol tblgridcol6 = factory.createTblGridCol(); 
                tblgrid.getGridCol().add( tblgridcol6); 
                    tblgridcol6.setW( BigInteger.valueOf( 2520) );       
        
        //Skip Line
        P p40 = factory.createP(); 
            mainDoc.getContent().add( p40); 
                p40.setPPr(pprText);   
        
        //Risk Assess Settings
        PPr pprRisk = factory.createPPr(); 
            PPrBase.PStyle pprbasepstyleList = factory.createPPrBasePStyle(); 
            pprRisk.setPStyle(pprbasepstyleList); 
                pprbasepstyleList.setVal( "ListParagraph"); 
            PPrBase.NumPr pprbasenumpr = factory.createPPrBaseNumPr(); 
                pprRisk.setNumPr(pprbasenumpr); 
            PPrBase.NumPr.Ilvl pprbasenumprilvl = factory.createPPrBaseNumPrIlvl(); 
                pprbasenumpr.setIlvl(pprbasenumprilvl); 
                    pprbasenumprilvl.setVal( BigInteger.valueOf( 2) ); 
            PPrBase.NumPr.NumId pprbasenumprnumid = factory.createPPrBaseNumPrNumId(); 
                pprbasenumpr.setNumId(pprbasenumprnumid); 
                    pprbasenumprnumid.setVal( BigInteger.valueOf( 6) ); 
                pprRisk.setSpacing(pprbasespacingText); 

        //Risk Assess Text
        PPr ppr4１ = factory.createPPr(); 
                ppr4１.setPStyle(pprbasepstyleList); 
            PPrBase.NumPr pprbasenumpr2 = factory.createPPrBaseNumPr(); 
                ppr4１.setNumPr(pprbasenumpr2); 
            PPrBase.NumPr.Ilvl pprbasenumprilvl2 = factory.createPPrBaseNumPrIlvl(); 
                    pprbasenumpr2.setIlvl(pprbasenumprilvl2); 
                        pprbasenumprilvl2.setVal( BigInteger.valueOf( 0) ); 
                    pprbasenumpr2.setNumId(pprbasenumprnumid); 
                ppr4１.setSpacing(pprbasespacingText); 
     	mainDoc.getContent().add(createBasicP("Security Engineering reviewed the developer’s analysis of these "
        		+ "findings and concludes this release contains the following vulnerabilities",
        		rprText, ppr4１));
            
        P p42 = factory.createP(); 
            mainDoc.getContent().add( p42); 
        R r73 = factory.createR(); 
            p42.getContent().add( r73); 
        Text text73 = factory.createText(); 
        	r73.getContent().add( text73); 
            text73.setValue( "Overall after security review");
            r73.setRPr(rprText); 
            p42.setPPr(pprRisk);     
        PPr ppr42 = factory.createPPr(); 
            p42.setPPr(ppr42); 
                ppr42.setPStyle(pprbasepstyleList); 
            PPrBase.NumPr pprbasenumpr3 = factory.createPPrBaseNumPr(); 
                ppr42.setNumPr(pprbasenumpr3); 
                    PPrBase.NumPr.Ilvl pprbasenumprilvl3 = factory.createPPrBaseNumPrIlvl(); 
                    pprbasenumpr3.setIlvl(pprbasenumprilvl3); 
                        pprbasenumprilvl3.setVal( BigInteger.valueOf( 1) ); 
                    pprbasenumpr3.setNumId(pprbasenumprnumid); 
                ppr42.setSpacing(pprbasespacingText); 

        mainDoc.getContent().add(createBasicP("CAT I severity findings: " + countCatI,
        		rprText, pprRisk));
        mainDoc.getContent().add(createBasicP("CAT II severity findings: " + countCatII,
        		rprText, pprRisk));
        mainDoc.getContent().add(createBasicP("Mitigated to Low Risk findings: ",
        		rprText, pprRisk));
        mainDoc.getContent().add(createBasicP("Low Risk findings: " + countNone,
        		rprText, pprRisk));
        mainDoc.getContent().add(createBasicP("False Positives: ",
        		rprText, pprRisk));
            
        //Skip Line
        P p49 = factory.createP(); 
            mainDoc.getContent().add( p49); 
                p49.setPPr(pprText);            

        P p50 = factory.createP(); 
            mainDoc.getContent().add( p50); 
        R r83 = factory.createR(); 
            p50.getContent().add( r83); 
        Text text83 = factory.createText(); 
        	r83.getContent().add( text83); 
            	text83.setValue( "According to Fortify reported ");
            	text83.setSpace( "preserve");
            r83.setRPr(rprText); 
        R r84 = factory.createR(); 
            p50.getContent().add( r84); 
        Text text84 = factory.createText(); 
        	r84.getContent().add( text84); 
            	text84.setValue( "raw");            
            RPr rpr84 = factory.createRPr(); 
                r84.setRPr(rpr84); 
                rpr84.setB(booltrue);
                rpr84.setSz(hpsmeasure20);
                rpr84.setSzCs(hpsmeasure20);
            U u = factory.createU(); 
            	rpr84.setU(u); 
                	u.setVal(org.docx4j.wml.UnderlineEnumeration.SINGLE);           
        R r85 = factory.createR(); 
            p50.getContent().add( r85); 
        Text text85 = factory.createText(); 
        	r85.getContent().add( text85); 
            	text85.setValue( " findings, this version");
            	text85.setSpace( "preserve");
            r85.setRPr(rprText); 
           p50.setPPr(ppr4１); 
            
       mainDoc.getContent().add(createBasicP("Includes " + countTotal + " findings",
           		rprText, ppr42));
       mainDoc.getContent().add(createBasicP(countCatI + " CAT I DISA STIG findings",
          		rprText, pprRisk));     
       mainDoc.getContent().add(createBasicP(countCatII + " CAT II DISA STIG findings",
         		rprText, pprRisk));  
       mainDoc.getContent().add(createBasicP(countNone + " Low Risk findings (No STIG Category)",
         		rprText, pprRisk));  
	}
	
	public static Tc tcTableHelper(String text, RPr rpr, PPr ppr, TcPr tcpr)
	{
		Tc tc = factory.createTc(); 
		P p = factory.createP(); 
        	tc.getContent().add( p); 
    	R r = factory.createR(); 
            p.getContent().add( r); 
        Text t = factory.createText(); 
        	r.getContent().add( t); 
            	t.setValue(text); 
        r.setRPr(rpr); 
        p.setPPr(ppr); 
        tc.setTcPr(tcpr); 
        return tc;
	} 
	
	public static Tr trTableHelper(TrPr trpr, String name, String stigid, String nist, String owasp, String cwe, Double impact, Double likelihood)
	{
            // Create object for tcPr
        TcPr tcprTableBody = factory.createTcPr(); 
        	tcprTableBody.setTcBorders(tcprinnertcbordersNormal);     
        	tcprTableBody.setHideMark(booltrue); 
        PPr pprHeading = factory.createPPr(); 
    		pprHeading.setJc(jcCenter); 
		PPr pprBody = factory.createPPr();
        PPr pprCategory = factory.createPPr(); 
        	PPrBase.PStyle pprbasepstyle22 = factory.createPPrBasePStyle(); 
        	pprCategory.setPStyle(pprbasepstyle22); 
            	pprbasepstyle22.setVal( "ListParagraph"); 
        	PPrBase.Ind pprbaseind60 = factory.createPPrBaseInd(); 
        		pprCategory.setInd(pprbaseind60); 
        			pprbaseind60.setLeft( BigInteger.valueOf( -16) ); 
        RPr rprHeading = factory.createRPr(); 
                    //RFonts rfonts = factory.createRFonts(); 
                    //rpr103.setRFonts(rfonts); 
                    //    rfonts.setCstheme(org.docx4j.wml.STTheme.MINOR_H_ANSI);
        	rprHeading.setB(booltrue); 
        	rprHeading.setSz(hpsmeasure14); 
        	rprHeading.setSzCs(hpsmeasure14); 
    	RPr rprCategory = factory.createRPr(); 
        	RStyle rstyle = factory.createRStyle(); 
        	rprCategory.setRStyle(rstyle); 
	            rstyle.setVal( "Hyperlink"); 
	        //RFonts rfonts17 = factory.createRFonts(); 
	        //rpr111.setRFonts(rfonts17); 
	        //    rfonts17.setEastAsia( "Times New Roman"); 
	        rprCategory.setSz(hpsmeasure14); 
	        rprCategory.setSzCs(hpsmeasure14); 
    	RPr rprBody = factory.createRPr(); 
            //RFonts rfonts31 = wmlObjectFactory.createRFonts(); 
            //rprBody.setRFonts(rfonts31); 
            //    rfonts31.setCstheme(org.docx4j.wml.STTheme.MINOR_H_ANSI);
            rprBody.setSz(hpsmeasure14); 
            rprBody.setSzCs(hpsmeasure14); 
        
        //Body  
        Tr tr = factory.createTr();    
            
	        Tc tc = factory.createTc(); 
	    	tr.getContent().add( tc); 
		        tc.getContent().add(createBasicP(name,
		         		rprCategory, pprCategory));  
		        tc.getContent().add(createBasicP("Impact: " + impact + " Likelihood: " + likelihood,
		         		rprBody, pprCategory));  
		        tc.setTcPr(tcprTableBody);
	        Tc tc2 = factory.createTc(); 
	        tr.getContent().add( tc2); 
	        	tc2.getContent().add(createBasicP(stigid,
	        			rprBody, pprBody));  
	        	//tc2.getContent().add(createBasicP("CAT I APSC-DV-003280",
	        	//		rprBody, pprBody));  
	        	//tc2.getContent().add(createBasicP("CAT II APSC-DV-002330",
	        	//		rprBody, pprBody));  
	        	//tc2.getContent().add(createBasicP("CAT II APSC-DV-003270",
	        	//		rprBody, pprBody));  
	        	tc2.setTcPr(tcprTableBody);
	        Tc tc3 = factory.createTc(); 
	    	tr.getContent().add( tc3); 
		        tc3.getContent().add(createBasicP(nist,
		        		rprBody, pprBody));  
		        tc3.setTcPr(tcprTableBody);
	        Tc tc4 = factory.createTc(); 
	    	tr.getContent().add( tc4); 
		        tc4.getContent().add(createBasicP(owasp,
		        		rprBody, pprBody));  
		        tc4.setTcPr(tcprTableBody);
	        Tc tc5 = factory.createTc(); 
	    	tr.getContent().add( tc5); 
		        tc5.getContent().add(createBasicP(cwe,
		        		rprBody, pprBody));  
		        tc5.setTcPr(tcprTableBody);		    
	        Tc tc6 = factory.createTc(); 
	    	tr.getContent().add( tc6); 
		        tc6.getContent().add(createBasicP("(Analysis)",
		        		rprBody, pprBody));  
		        tc6.setTcPr(tcprTableBody);		 
	        Tc tc7 = factory.createTc(); 
	    	tr.getContent().add( tc7); 
		        tc7.getContent().add(createBasicP("4",
		        		rprBody, pprBody));  
		        tc7.setTcPr(tcprTableBody);		 
	        Tc tc8 = factory.createTc(); 
	    	tr.getContent().add( tc8); 
		        tc8.getContent().add(createBasicP("",
		        		rprBody, pprBody));  
		        tc8.setTcPr(tcprTableBody);	
		        
        return tr;
	} 
	
	public static void findingsTable(MainDocumentPart mainDoc, Map<String, Rule> rules, Map<String, ArrayList<Issue>> issues)
	{
            // Create object for tcPr
        TcPr tcprTableBody = factory.createTcPr(); 
        	tcprTableBody.setTcBorders(tcprinnertcbordersNormal);     
        	tcprTableBody.setHideMark(booltrue); 
        PPr pprHeading = factory.createPPr(); 
    		pprHeading.setJc(jcCenter); 
        RPr rprHeading = factory.createRPr(); 
                    //RFonts rfonts = factory.createRFonts(); 
                    //rpr103.setRFonts(rfonts); 
                    //    rfonts.setCstheme(org.docx4j.wml.STTheme.MINOR_H_ANSI);
        	rprHeading.setB(booltrue); 
        	rprHeading.setSz(hpsmeasure14); 
        	rprHeading.setSzCs(hpsmeasure14); 
		  
            
		//Header Settings
    	// Create object for trPr
    	TrPr trprHeadings = factory.createTrPr(); 
            CTHeight heightHeadings = factory.createCTHeight(); 
            JAXBElement<org.docx4j.wml.CTHeight> heightWrappedHeadings = factory.createCTTrPrBaseTrHeight(heightHeadings); 
            trprHeadings.getCnfStyleOrDivIdOrGridBefore().add( heightWrappedHeadings); 
                heightHeadings.setVal( BigInteger.valueOf( 242) ); 
            JAXBElement<org.docx4j.wml.BooleanDefaultTrue> booleandefaulttrueWrappedHeadings = factory.createCTTrPrBaseTblHeader(booltrue); 
            trprHeadings.getCnfStyleOrDivIdOrGridBefore().add( booleandefaulttrueWrappedHeadings); 
    	TrPr trprBody = factory.createTrPr(); 
            CTHeight heightBody = factory.createCTHeight(); 
            JAXBElement<org.docx4j.wml.CTHeight> heightWrappedBody = factory.createCTTrPrBaseTrHeight(heightBody); 
            trprBody.getCnfStyleOrDivIdOrGridBefore().add( heightWrappedBody); 
                heightBody.setVal( BigInteger.valueOf( 242) ); 
            JAXBElement<org.docx4j.wml.BooleanDefaultTrue> booleandefaulttrueWrappedBody = factory.createCTTrPrBaseTblHeader(booltrue); 
            trprBody.getCnfStyleOrDivIdOrGridBefore().add( booleandefaulttrueWrappedBody); 
           
        //FindingTables & Table Header
		Tbl tbl2 = factory.createTbl(); 
			mainDoc.getContent().add( tbl2); 
	        Tr tr6 = factory.createTr(); 
	        	tbl2.getContent().add( tr6); 
	        	tr6.setTrPr(trprHeadings); 
	        Tr tr7 = factory.createTr(); 
	            tbl2.getContent().add( tr7); 
	            tr7.setTrPr(trprHeadings);
		
		//Table Heading Settings
	            
	            
	            //tr5.getContent().add(tcTableHelper("", rprHeading, 
	            //		pprHeading, tcprTableBody));  
		//Table Finding Header
            Tc tc31 = factory.createTc(); 
            	tr6.getContent().add( tc31); 
                P p62 = factory.createP(); 
                	tc31.getContent().add( p62); 
                R r102 = factory.createR(); 
                    p62.getContent().add( r102); 
                Text text102 = factory.createText(); 
                    r102.getContent().add( text102); 
                        text102.setValue( "Findings"); 
                RPr rpr102 = factory.createRPr(); 
                    r102.setRPr(rpr102); 
                    rpr102.setB(booltrue); 
                    rpr102.setSz(hpsmeasure24); 
                    rpr102.setSzCs(hpsmeasure24); 
                PPr ppr62 = factory.createPPr(); 
                    p62.setPPr(ppr62); 
                TcPr tcpr31 = factory.createTcPr(); 
                    tc31.setTcPr(tcpr31); 
                TblWidth tblwidth33 = factory.createTblWidth(); 
                    tcpr31.setTcW(tblwidth33); 
                        tblwidth33.setW( BigInteger.valueOf( 9900) ); 
                        tblwidth33.setType( "dxa"); 
                TcPrInner.GridSpan tcprinnergridspan = factory.createTcPrInnerGridSpan(); 
                    tcpr31.setGridSpan(tcprinnergridspan); 
                        tcprinnergridspan.setVal( BigInteger.valueOf( 8) ); 
                    tcpr31.setTcBorders(tcprinnertcbordersNormal); 
                    tcpr31.setHideMark(booltrue); 

		//Table Headings
    	tr7.getContent().add(tcTableHelper("CATEGORY", rprHeading, 
    			pprHeading, tcprTableBody));              
    	tr7.getContent().add(tcTableHelper("DISA STIG", rprHeading, 
    			pprHeading, tcprTableBody));  
    	tr7.getContent().add(tcTableHelper("NIST Control(s)", rprHeading, 
    			pprHeading, tcprTableBody));  
    	tr7.getContent().add(tcTableHelper("OWASP TOP 10", rprHeading, 
    			pprHeading, tcprTableBody));  
    	tr7.getContent().add(tcTableHelper("CWE/SANS TOP 25", rprHeading, 
    			pprHeading, tcprTableBody));  
    	tr7.getContent().add(tcTableHelper("Analysis / Recommendation", rprHeading, 
    			pprHeading, tcprTableBody));  
    	tr7.getContent().add(tcTableHelper("Instances", rprHeading, 
    			pprHeading, tcprTableBody));  
    	tr7.getContent().add(tcTableHelper("Government Concurrence", rprHeading, 
    			pprHeading, tcprTableBody));  


    	
    	Collection<ArrayList<Issue>> issueColl = issues.values();
		Iterator it = issueColl.iterator();
		it = issueColl.iterator();
		Tr trBody;
		while(it.hasNext())
		{
			Issue issue;
			ArrayList<Issue> issueList = (ArrayList<Issue>) it.next();
			issue = issueList.get(0);
			trBody = trTableHelper(trprBody, issue.getName(),
					rules.get(issue.getClassId()).getStigid(),
					rules.get(issue.getClassId()).getNist(),
					rules.get(issue.getClassId()).getOwasp(),
					rules.get(issue.getClassId()).getCwe(),
					rules.get(issue.getClassId()).getImpact(),
					rules.get(issue.getClassId()).getAccuracy()*rules.get(issue.getClassId()).getProbability()/issue.getConfidence());
			tbl2.getContent().add(trBody);
		}
		
    	//Table Total
        Tr tr36 = factory.createTr(); 
        tbl2.getContent().add( tr36); 
            Tc tc264 = factory.createTc(); 
            tr36.getContent().add( tc264); 
                P p339 = factory.createP(); 
                tc264.getContent().add( p339); 
                    PPr ppr339 = factory.createPPr(); 
                    p339.setPPr(ppr339); 
                        ppr339.setJc(jcCenter); 
                TcPr tcpr264 = factory.createTcPr(); 
                tc264.setTcPr(tcpr264); 
                    TblWidth tblwidth266 = factory.createTblWidth(); 
                    tcpr264.setTcW(tblwidth266); 
                        tblwidth266.setW( BigInteger.valueOf( 6840) ); 
                        tblwidth266.setType( "dxa"); 
                    TcPrInner.GridSpan tcprinnergridspan31 = factory.createTcPrInnerGridSpan(); 
                    tcpr264.setGridSpan(tcprinnergridspan31); 
                        tcprinnergridspan31.setVal( BigInteger.valueOf( 5) ); 
                    tcpr264.setTcBorders(tcprinnertcbordersNormal); 
            Tc tc265 = factory.createTc(); 
            tr36.getContent().add( tc265); 
                P p340 = factory.createP(); 
                tc265.getContent().add( p340); 
                    R r382 = factory.createR(); 
                    p340.getContent().add( r382); 
                       Text text374 = factory.createText(); 
                        r382.getContent().add( text374); 
                            text374.setValue( "TOTAL"); 
                        RPr rpr382 = factory.createRPr(); 
                        r382.setRPr(rpr382); 
                            rpr382.setB(booltrue); 
                            rpr382.setSz(hpsmeasure14); 
                            rpr382.setSzCs(hpsmeasure14); 
                    PPr ppr340 = factory.createPPr(); 
                    p340.setPPr(ppr340); 
                        Jc jc152 = factory.createJc(); 
                        ppr340.setJc(jc152); 
                            jc152.setVal(org.docx4j.wml.JcEnumeration.CENTER);
                TcPr tcpr265 = factory.createTcPr(); 
                tc265.setTcPr(tcpr265); 
                    TblWidth tblwidth267 = factory.createTblWidth(); 
                    tcpr265.setTcW(tblwidth267); 
                        tblwidth267.setW( BigInteger.valueOf( 1269) ); 
                        tblwidth267.setType( "dxa"); 
                    tcpr265.setTcBorders(tcprinnertcbordersNormal); 
            Tc tc266 = factory.createTc(); 
            tr36.getContent().add( tc266); 
                P p341 = factory.createP(); 
                tc266.getContent().add( p341); 
                    R r383 = factory.createR(); 
                    p341.getContent().add( r383); 
                        R.Tab rtab = factory.createRTab(); 
                        JAXBElement<org.docx4j.wml.R.Tab> rtabWrapped = factory.createRTab(rtab); 
                        r383.getContent().add( rtabWrapped); 
                        // Create object for rPr
                        RPr rpr383 = factory.createRPr(); 
                        r383.setRPr(rpr383); 
                            // Create object for b
                            BooleanDefaultTrue booleandefaulttrue256 = factory.createBooleanDefaultTrue(); 
                            rpr383.setB(booleandefaulttrue256); 
                            // Create object for sz
                            HpsMeasure hpsmeasure1405 = factory.createHpsMeasure(); 
                            rpr383.setSz(hpsmeasure1405); 
                                hpsmeasure1405.setVal( BigInteger.valueOf( 14) ); 
                    R r384 = factory.createR(); 
                    p341.getContent().add( r384); 
                        FldChar fldchar7 = factory.createFldChar(); 
                        JAXBElement<org.docx4j.wml.FldChar> fldcharWrapped7 = factory.createRFldChar(fldchar7); 
                        r384.getContent().add( fldcharWrapped7); 
                            fldchar7.setFldCharType(org.docx4j.wml.STFldCharType.BEGIN);
                        // Create object for rPr
                        RPr rpr384 = factory.createRPr(); 
                        r384.setRPr(rpr384); 
                            // Create object for b
                            BooleanDefaultTrue booleandefaulttrue257 = factory.createBooleanDefaultTrue(); 
                            rpr384.setB(booleandefaulttrue257); 
                            // Create object for sz
                            HpsMeasure hpsmeasure1406 = factory.createHpsMeasure(); 
                            rpr384.setSz(hpsmeasure1406); 
                                hpsmeasure1406.setVal( BigInteger.valueOf( 14) ); 
                    // Create object for r
                    R r385 = factory.createR(); 
                    p341.getContent().add( r385); 
                        // Create object for instrText (wrapped in JAXBElement) 
                        Text text375 = factory.createText(); 
                        JAXBElement<org.docx4j.wml.Text> textWrapped375 = factory.createRInstrText(text375); 
                        r385.getContent().add( textWrapped375); 
                            text375.setValue( " =SUM(ABOVE) "); 
                            text375.setSpace( "preserve"); 
                        // Create object for rPr
                        RPr rpr385 = factory.createRPr(); 
                        r385.setRPr(rpr385); 
                            // Create object for b
                            BooleanDefaultTrue booleandefaulttrue258 = factory.createBooleanDefaultTrue(); 
                            rpr385.setB(booleandefaulttrue258); 
                            // Create object for color
                            Color color473 = factory.createColor(); 
                            rpr385.setColor(color473); 
                                color473.setVal( "00B0F0"); 
                            // Create object for sz
                            HpsMeasure hpsmeasure1407 = factory.createHpsMeasure(); 
                            rpr385.setSz(hpsmeasure1407); 
                                hpsmeasure1407.setVal( BigInteger.valueOf( 14) ); 
                    // Create object for r
                    R r386 = factory.createR(); 
                    p341.getContent().add( r386); 
                        // Create object for fldChar (wrapped in JAXBElement) 
                        FldChar fldchar8 = factory.createFldChar(); 
                        JAXBElement<org.docx4j.wml.FldChar> fldcharWrapped8 = factory.createRFldChar(fldchar8); 
                        r386.getContent().add( fldcharWrapped8); 
                            fldchar8.setFldCharType(org.docx4j.wml.STFldCharType.SEPARATE);
                        // Create object for rPr
                        RPr rpr386 = factory.createRPr(); 
                        r386.setRPr(rpr386); 
                            // Create object for b
                            BooleanDefaultTrue booleandefaulttrue259 = factory.createBooleanDefaultTrue(); 
                            rpr386.setB(booleandefaulttrue259); 
                            // Create object for color
                            Color color474 = factory.createColor(); 
                            rpr386.setColor(color474); 
                                color474.setVal( "00B0F0"); 
                            // Create object for sz
                            HpsMeasure hpsmeasure1408 = factory.createHpsMeasure(); 
                            rpr386.setSz(hpsmeasure1408); 
                                hpsmeasure1408.setVal( BigInteger.valueOf( 14) ); 
                    // Create object for r
                    R r387 = factory.createR(); 
                    p341.getContent().add( r387); 
                        // Create object for t (wrapped in JAXBElement) 
                        Text text376 = factory.createText(); 
                        JAXBElement<org.docx4j.wml.Text> textWrapped376 = factory.createRT(text376); 
                        r387.getContent().add( textWrapped376); 
                            text376.setValue( "504"); 
                        // Create object for rPr
                        RPr rpr387 = factory.createRPr(); 
                        r387.setRPr(rpr387); 
                            // Create object for b
                            BooleanDefaultTrue booleandefaulttrue260 = factory.createBooleanDefaultTrue(); 
                            rpr387.setB(booleandefaulttrue260); 
                            // Create object for noProof
                            BooleanDefaultTrue booleandefaulttrue261 = factory.createBooleanDefaultTrue(); 
                            rpr387.setNoProof(booleandefaulttrue261); 
                            // Create object for color
                            Color color475 = factory.createColor(); 
                            rpr387.setColor(color475); 
                                color475.setVal( "00B0F0"); 
                            // Create object for sz
                            HpsMeasure hpsmeasure1409 = factory.createHpsMeasure(); 
                            rpr387.setSz(hpsmeasure1409); 
                                hpsmeasure1409.setVal( BigInteger.valueOf( 14) ); 
                    // Create object for r
                    R r388 = factory.createR(); 
                    p341.getContent().add( r388); 
                        // Create object for fldChar (wrapped in JAXBElement) 
                        FldChar fldchar9 = factory.createFldChar(); 
                        JAXBElement<org.docx4j.wml.FldChar> fldcharWrapped9 = factory.createRFldChar(fldchar9); 
                        r388.getContent().add( fldcharWrapped9); 
                            fldchar9.setFldCharType(org.docx4j.wml.STFldCharType.END);
                        // Create object for rPr
                        RPr rpr388 = factory.createRPr(); 
                        r388.setRPr(rpr388); 
                            // Create object for b
                            BooleanDefaultTrue booleandefaulttrue262 = factory.createBooleanDefaultTrue(); 
                            rpr388.setB(booleandefaulttrue262); 
                            // Create object for color
                            Color color476 = factory.createColor(); 
                            rpr388.setColor(color476); 
                                color476.setVal( "00B0F0"); 
                            // Create object for sz
                            HpsMeasure hpsmeasure1410 = factory.createHpsMeasure(); 
                            rpr388.setSz(hpsmeasure1410); 
                                hpsmeasure1410.setVal( BigInteger.valueOf( 14) ); 
                    // Create object for pPr
                    PPr ppr341 = factory.createPPr(); 
                    p341.setPPr(ppr341); 
                        // Create object for rPr
                        ParaRPr pararpr341 = factory.createParaRPr(); 
                        ppr341.setRPr(pararpr341); 
                            // Create object for b
                            BooleanDefaultTrue booleandefaulttrue263 = factory.createBooleanDefaultTrue(); 
                            pararpr341.setB(booleandefaulttrue263); 
                            // Create object for sz
                            HpsMeasure hpsmeasure1411 = factory.createHpsMeasure(); 
                            pararpr341.setSz(hpsmeasure1411); 
                                hpsmeasure1411.setVal( BigInteger.valueOf( 14) ); 
                        // Create object for tabs
                        Tabs tabs = factory.createTabs(); 
                        ppr341.setTabs(tabs); 
                            // Create object for tab
                            CTTabStop tabstop = factory.createCTTabStop(); 
                            tabs.getTab().add( tabstop); 
                                tabstop.setVal(org.docx4j.wml.STTabJc.CENTER);
                                tabstop.setPos( BigInteger.valueOf( 312) ); 
                // Create object for tcPr
                TcPr tcpr266 = factory.createTcPr(); 
                tc266.setTcPr(tcpr266); 
                    // Create object for tcW
                    TblWidth tblwidth268 = factory.createTblWidth(); 
                    tcpr266.setTcW(tblwidth268); 
                        tblwidth268.setW( BigInteger.valueOf( 840) ); 
                        tblwidth268.setType( "dxa"); 
                    // Create object for tcBorders
                    tcpr266.setTcBorders(tcprinnertcbordersNormal); 
            // Create object for tc (wrapped in JAXBElement) 
            Tc tc267 = factory.createTc(); 
            JAXBElement<org.docx4j.wml.Tc> tcWrapped267 = factory.createTrTc(tc267); 
            tr36.getContent().add( tcWrapped267); 
                // Create object for p
                P p342 = factory.createP(); 
                tc267.getContent().add( p342); 
                    // Create object for pPr
                    PPr ppr342 = factory.createPPr(); 
                    p342.setPPr(ppr342); 
                        // Create object for rPr
                        ParaRPr pararpr342 = factory.createParaRPr(); 
                        ppr342.setRPr(pararpr342); 
                            // Create object for sz
                            HpsMeasure hpsmeasure1412 = factory.createHpsMeasure(); 
                            pararpr342.setSz(hpsmeasure1412); 
                                hpsmeasure1412.setVal( BigInteger.valueOf( 14) ); 
                            // Create object for szCs
                            HpsMeasure hpsmeasure1413 = factory.createHpsMeasure(); 
                            pararpr342.setSzCs(hpsmeasure1413); 
                                hpsmeasure1413.setVal( BigInteger.valueOf( 14) ); 
                        ppr342.setJc(jcCenter); 
                // Create object for tcPr
                TcPr tcpr267 = factory.createTcPr(); 
                tc267.setTcPr(tcpr267); 
                    // Create object for tcW
                    TblWidth tblwidth269 = factory.createTblWidth(); 
                    tcpr267.setTcW(tblwidth269); 
                        tblwidth269.setW( BigInteger.valueOf( 951) ); 
                        tblwidth269.setType( "dxa"); 
                    // Create object for tcBorders
                    tcpr267.setTcBorders(tcprinnertcbordersNormal); 
            // Create object for trPr
            TrPr trpr36 = factory.createTrPr(); 
            tr36.setTrPr(trpr36); 
                // Create object for trHeight (wrapped in JAXBElement) 
                CTHeight height36 = factory.createCTHeight(); 
                JAXBElement<org.docx4j.wml.CTHeight> heightWrapped36 = factory.createCTTrPrBaseTrHeight(height36); 
                trpr36.getCnfStyleOrDivIdOrGridBefore().add( heightWrapped36); 
                    height36.setVal( BigInteger.valueOf( 224) ); 
    	
    	//Table Extra Settings
        // Create object for tblPr
        TblPr tblpr2 = factory.createTblPr(); 
        tbl2.setTblPr(tblpr2); 
            CTTblPrBase.TblStyle tblprbasetblstyle2 = factory.createCTTblPrBaseTblStyle(); 
            tblpr2.setTblStyle(tblprbasetblstyle2); 
                tblprbasetblstyle2.setVal( "TableGrid"); 
            TblWidth tblwidth270 = factory.createTblWidth(); 
            tblpr2.setTblW(tblwidth270); 
                tblwidth270.setW( BigInteger.valueOf( 9900) ); 
                tblwidth270.setType( "dxa"); 
            TblWidth tblwidth271 = factory.createTblWidth(); 
            tblpr2.setTblInd(tblwidth271); 
                tblwidth271.setW( BigInteger.valueOf( -455) ); 
                tblwidth271.setType( "dxa"); 
            CTTblLayoutType tbllayouttype2 = factory.createCTTblLayoutType(); 
            tblpr2.setTblLayout(tbllayouttype2); 
                tbllayouttype2.setType(org.docx4j.wml.STTblLayoutType.FIXED);
            CTTblLook tbllook2 = factory.createCTTblLook(); 
            tblpr2.setTblLook(tbllook2); 
                tbllook2.setFirstRow(org.docx4j.sharedtypes.STOnOff.ONE);
                tbllook2.setLastRow(org.docx4j.sharedtypes.STOnOff.ZERO);
                tbllook2.setFirstColumn(org.docx4j.sharedtypes.STOnOff.ONE);
                tbllook2.setLastColumn(org.docx4j.sharedtypes.STOnOff.ZERO);
                tbllook2.setNoHBand(org.docx4j.sharedtypes.STOnOff.ZERO);
                tbllook2.setNoVBand(org.docx4j.sharedtypes.STOnOff.ONE);
                tbllook2.setVal( "04A0"); 
        TblGrid tblgrid2 = factory.createTblGrid(); 
        tbl2.setTblGrid(tblgrid2); 
            TblGridCol tblgridcol7 = factory.createTblGridCol(); 
            tblgrid2.getGridCol().add( tblgridcol7); 
                tblgridcol7.setW( BigInteger.valueOf( 1794) ); 
            TblGridCol tblgridcol8 = factory.createTblGridCol(); 
            tblgrid2.getGridCol().add( tblgridcol8); 
                tblgridcol8.setW( BigInteger.valueOf( 1618) ); 
            TblGridCol tblgridcol9 = factory.createTblGridCol(); 
            tblgrid2.getGridCol().add( tblgridcol9); 
                tblgridcol9.setW( BigInteger.valueOf( 810) ); 
            TblGridCol tblgridcol10 = factory.createTblGridCol(); 
            tblgrid2.getGridCol().add( tblgridcol10); 
                tblgridcol10.setW( BigInteger.valueOf( 1268) ); 
            TblGridCol tblgridcol11 = factory.createTblGridCol(); 
            tblgrid2.getGridCol().add( tblgridcol11); 
                tblgridcol11.setW( BigInteger.valueOf( 1350) ); 
            TblGridCol tblgridcol12 = factory.createTblGridCol(); 
            tblgrid2.getGridCol().add( tblgridcol12); 
                tblgridcol12.setW( BigInteger.valueOf( 1277) ); 
            TblGridCol tblgridcol13 = factory.createTblGridCol(); 
            tblgrid2.getGridCol().add( tblgridcol13); 
                tblgridcol13.setW( BigInteger.valueOf( 832) ); 
            TblGridCol tblgridcol14 = factory.createTblGridCol(); 
            tblgrid2.getGridCol().add( tblgridcol14); 
                tblgridcol14.setW( BigInteger.valueOf( 951) ); 
	}

	public static void findingAnalysis(MainDocumentPart mainDoc, Map<String, Rule> rules, Map<String, ArrayList<Issue>> issues)
	{
        RPr rprUnderline = factory.createRPr(); 
            Color colorUnderline = factory.createColor(); 
            rprUnderline.setColor(colorUnderline); 
                colorUnderline.setVal( "0000FF");  
            rprUnderline.setSz(hpsmeasure20); 
            rprUnderline.setSzCs(hpsmeasure20); 
            U uUnderline = factory.createU(); 
            rprUnderline.setU(uUnderline); 
                uUnderline.setVal(org.docx4j.wml.UnderlineEnumeration.SINGLE);
            RStyle rstyleHyperlink = factory.createRStyle(); 
            	rstyleHyperlink.setVal( "Hyperlink"); 
        RPr rprText = factory.createRPr(); 
            Color color484 = factory.createColor(); 
            rprText.setColor(color484); 
                color484.setVal( "000000"); 
                color484.setThemeColor(org.docx4j.wml.STThemeColor.TEXT_1);
            rprText.setSz(hpsmeasure20); 
            rprText.setSzCs(hpsmeasure20); 
        PPr pprStigName = factory.createPPr(); 
            PPrBase.PStyle pprbasepstyleStigName = factory.createPPrBasePStyle(); 
            pprStigName.setPStyle(pprbasepstyleStigName); 
                pprbasepstyleStigName.setVal( "Heading1"); 
            pprStigName.setKeepNext(booltrue); 
            pprStigName.setKeepLines(booltrue); 
            pprStigName.setSpacing(pprbasespacingText); 

        //Skip Line
        P p40 = factory.createP(); 
            mainDoc.getContent().add( p40); 
                p40.setPPr(pprText);  

        mainDoc.getContent().add(createBasicP("Findings Analysis", rprHeading, pprHeading));     

/*
            PPr ppr344 = factory.createPPr(); 
            p344.setPPr(ppr344); 
                PPrBase.Spacing pprbasespacing35 = factory.createPPrBaseSpacing(); 
                ppr344.setSpacing(pprbasespacing35); 
                    pprbasespacing35.setAfter( BigInteger.valueOf( 120) ); 
                    pprbasespacing35.setLine( BigInteger.valueOf( 240) ); 
                    pprbasespacing35.setLineRule(org.docx4j.wml.STLineSpacingRule.AUTO);
                PPrBase.Ind pprbaseind104 = factory.createPPrBaseInd(); 
                ppr344.setInd(pprbaseind104); 
                    pprbaseind104.setLeft( BigInteger.valueOf( 360) ); 
                    pprbaseind104.setHanging( BigInteger.valueOf( 360) ); 
        */
        
    	Collection<ArrayList<Issue>> issueColl = issues.values();
		Iterator it = issueColl.iterator();
		it = issueColl.iterator();
		Tr trBody;
		while(it.hasNext())
		{
			Issue issue;
			ArrayList<Issue> issueList = (ArrayList<Issue>) it.next();
			issue = issueList.get(0);
			
			P p345 = factory.createP(); 
	        mainDoc.getContent().add( p345);
	            R r390 = factory.createR(); 
	            p345.getContent().add( r390); 
	                //FldChar fldchar10 = factory.createFldChar(); 
	                //JAXBElement<org.docx4j.wml.FldChar> fldcharWrapped10 = factory.createRFldChar(fldchar10); 
	                //r390.getContent().add( fldcharWrapped10); 
	                //    fldchar10.setFldCharType(org.docx4j.wml.STFldCharType.BEGIN);
	                r390.setRPr(rprUnderline); 
	/*            R r391 = factory.createR(); 
	            p345.getContent().add( r391); 
	                Text text378 = factory.createText(); 
	                r391.getContent().add( text378); 
	                    text378.setValue( " HYPERLINK  \\l "_Risk_Assessment/Summary" "); 
	                    text378.setSpace( "preserve"); 
	                r391.setRPr(rprUnderline); 
	                    // Create object for rFonts
	                    RFonts rfonts549 = factory.createRFonts(); 
	                    rpr391.setRFonts(rfonts549); 
	                        rfonts549.setAsciiTheme(org.docx4j.wml.STTheme.MINOR_H_ANSI);
	                        rfonts549.setHAnsiTheme(org.docx4j.wml.STTheme.MINOR_H_ANSI);
	                        rfonts549.setCstheme(org.docx4j.wml.STTheme.MINOR_H_ANSI);
	            */
	                //FldChar fldchar11 = factory.createFldChar(); 
	                //JAXBElement<org.docx4j.wml.FldChar> fldcharWrapped11 = factory.createRFldChar(fldchar11); 
	                //r392.getContent().add( fldcharWrapped11); 
	                //    fldchar11.setFldCharType(org.docx4j.wml.STFldCharType.SEPARATE);
	                
	                    // Create object for rStyle
	                    //RStyle rstyle36 = factory.createRStyle(); 
	                    //rpr393.setRStyle(rstyle36); 
	                    //    rstyle36.setVal( "Hyperlink");           
	            R rTitleDetailed = factory.createR(); 
	            p345.getContent().add( rTitleDetailed); 
	                Text textTitleDetailed = factory.createText(); 
	                rTitleDetailed.getContent().add( textTitleDetailed); 
	                    textTitleDetailed.setValue( issue.getName() + " (" + issueList.size() 
	                    + " Low Risk – " + issueList.size() + " (Analysis))"); 
	                    textTitleDetailed.setSpace( "preserve"); 
	                rTitleDetailed.setRPr(rprUnderline); 
	                p345.setPPr(pprStigName);
	            R r399 = factory.createR(); 
	            p345.getContent().add( r399); 
	                // Create object for fldChar (wrapped in JAXBElement) 
	                FldChar fldchar12 = factory.createFldChar(); 
	                JAXBElement<org.docx4j.wml.FldChar> fldcharWrapped12 = factory.createRFldChar(fldchar12); 
	                r399.getContent().add( fldcharWrapped12); 
	                    fldchar12.setFldCharType(org.docx4j.wml.STFldCharType.END);
	                r399.setRPr(rprUnderline); 
	        // Create object for p
	        P p346 = factory.createP(); 
	        mainDoc.getContent().add( p346); 
	            R r400 = factory.createR(); 
	            p346.getContent().add( r400); 
	                Text text385 = factory.createText(); 
	                r400.getContent().add( text385); 
	                    text385.setValue( "ISSUE: " + rules.get(issue.getClassId()).getDetailIssue()); 
	                    text385.setSpace( "preserve"); 
	                r400.setRPr(rprText); 
	            PPr ppr346 = factory.createPPr(); 
	            p346.setPPr(ppr346); 
	                PPrBase.NumPr pprbasenumpr14 = factory.createPPrBaseNumPr(); 
	                ppr346.setNumPr(pprbasenumpr14); 
	                    PPrBase.NumPr.Ilvl pprbasenumprilvl14 = factory.createPPrBaseNumPrIlvl(); 
	                    pprbasenumpr14.setIlvl(pprbasenumprilvl14); 
	                        pprbasenumprilvl14.setVal( BigInteger.valueOf( 0) ); 
	                    PPrBase.NumPr.NumId pprbasenumprnumid14 = factory.createPPrBaseNumPrNumId(); 
	                    pprbasenumpr14.setNumId(pprbasenumprnumid14); 
	                        pprbasenumprnumid14.setVal( BigInteger.valueOf( 16) ); 
	                ppr346.setAutoSpaceDE(booltrue); 
	                ppr346.setAutoSpaceDN(booltrue); 
	                ppr346.setAdjustRightInd(booltrue); 
	                PPrBase.Spacing pprbasespacing37 = factory.createPPrBaseSpacing(); 
	                ppr346.setSpacing(pprbasespacing37); 
	                    pprbasespacing37.setAfter( BigInteger.valueOf( 0) ); 
	                    pprbasespacing37.setLine( BigInteger.valueOf( 240) ); 
	                    pprbasespacing37.setLineRule(org.docx4j.wml.STLineSpacingRule.AUTO);
	                ppr346.setContextualSpacing(booltrue); 
	        P p347 = factory.createP(); 
	        mainDoc.getContent().add( p347); 
	            R r402 = factory.createR(); 
	            p347.getContent().add( r402); 
	                Text text387 = factory.createText(); 
	                r402.getContent().add( text387); 
	                    text387.setValue( "FORTIFY RECOMMENDED MITIGATION: "
	                    		+ rules.get(issue.getClassId()).getDetailRecomm());
	                    text387.setSpace( "preserve"); 
	                r402.setRPr(rprText); 
	            PPr ppr347 = factory.createPPr(); 
	            p347.setPPr(ppr347); 
	                PPrBase.NumPr pprbasenumpr15 = factory.createPPrBaseNumPr(); 
	                ppr347.setNumPr(pprbasenumpr15); 
	                    // Create object for ilvl
	                    PPrBase.NumPr.Ilvl pprbasenumprilvl15 = factory.createPPrBaseNumPrIlvl(); 
	                    pprbasenumpr15.setIlvl(pprbasenumprilvl15); 
	                        pprbasenumprilvl15.setVal( BigInteger.valueOf( 0) ); 
	                    // Create object for numId
	                    PPrBase.NumPr.NumId pprbasenumprnumid15 = factory.createPPrBaseNumPrNumId(); 
	                    pprbasenumpr15.setNumId(pprbasenumprnumid15); 
	                        pprbasenumprnumid15.setVal( BigInteger.valueOf( 16) ); 
	                ppr347.setAutoSpaceDE(booltrue); 
	                ppr347.setAutoSpaceDN(booltrue); 
	                ppr347.setAdjustRightInd(booltrue); 
	                PPrBase.Spacing pprbasespacing38 = factory.createPPrBaseSpacing(); 
	                ppr347.setSpacing(pprbasespacing38); 
	                    pprbasespacing38.setAfter( BigInteger.valueOf( 0) ); 
	                    pprbasespacing38.setLine( BigInteger.valueOf( 240) ); 
	                    pprbasespacing38.setLineRule(org.docx4j.wml.STLineSpacingRule.AUTO);
	                ppr347.setContextualSpacing(booltrue); 
	        P p348 = factory.createP(); 
	        mainDoc.getContent().add( p348); 
	            R r404 = factory.createR(); 
	            p348.getContent().add( r404); 
	                Text text389 = factory.createText(); 
	                r404.getContent().add( text389); 
	                    text389.setValue( "DEVELOPER ANALYSIS: " + issue.getDevComm()); 
	                    text389.setSpace( "preserve"); 
	                RPr rpr404 = factory.createRPr(); 
	                r404.setRPr(rprText); 
	            PPr ppr348 = factory.createPPr(); 
	            p348.setPPr(ppr348); 
	                PPrBase.NumPr pprbasenumpr16 = factory.createPPrBaseNumPr(); 
	                ppr348.setNumPr(pprbasenumpr16); 
	                    PPrBase.NumPr.Ilvl pprbasenumprilvl16 = factory.createPPrBaseNumPrIlvl(); 
	                    pprbasenumpr16.setIlvl(pprbasenumprilvl16); 
	                        pprbasenumprilvl16.setVal( BigInteger.valueOf( 0) ); 
	                    PPrBase.NumPr.NumId pprbasenumprnumid16 = factory.createPPrBaseNumPrNumId(); 
	                    pprbasenumpr16.setNumId(pprbasenumprnumid16); 
	                        pprbasenumprnumid16.setVal( BigInteger.valueOf( 16) ); 
	                        ppr348.setAutoSpaceDE(booltrue); 
	                        ppr348.setAutoSpaceDN(booltrue); 
	                        ppr348.setAdjustRightInd(booltrue); 
	                        PPrBase.Spacing pprbasespacing39 = factory.createPPrBaseSpacing(); 
	                        ppr348.setSpacing(pprbasespacing39); 
	                            pprbasespacing39.setAfter( BigInteger.valueOf( 0) ); 
	                            pprbasespacing39.setLine( BigInteger.valueOf( 240) ); 
	                            pprbasespacing39.setLineRule(org.docx4j.wml.STLineSpacingRule.AUTO);
	                        ppr348.setContextualSpacing(booltrue);
	        P p349 = factory.createP(); 
	        mainDoc.getContent().add( p349); 
	            R r405 = factory.createR(); 
	            p349.getContent().add( r405); 
	                Text text390 = factory.createText(); 
	                r405.getContent().add( text390); 
	                    text390.setValue( "Security Engineering Recommendation: "); 
	                RPr rpr405 = factory.createRPr(); 
	                r405.setRPr(rpr405); 
	                    rpr405.setB(booltrue); 
	                    Color color491 = factory.createColor(); 
	                    rpr405.setColor(color491); 
	                        color491.setVal( "000000"); 
	                        color491.setThemeColor(org.docx4j.wml.STThemeColor.TEXT_1);
	                    rpr405.setSz(hpsmeasure20); 
	                    rpr405.setSzCs(hpsmeasure20); 
	            PPr ppr349 = factory.createPPr(); 
	            p349.setPPr(ppr349); 
	                PPrBase.NumPr pprbasenumpr17 = factory.createPPrBaseNumPr(); 
	                ppr349.setNumPr(pprbasenumpr17); 
	                    PPrBase.NumPr.Ilvl pprbasenumprilvl17 = factory.createPPrBaseNumPrIlvl(); 
	                    pprbasenumpr17.setIlvl(pprbasenumprilvl17); 
	                        pprbasenumprilvl17.setVal( BigInteger.valueOf( 0) ); 
	                    PPrBase.NumPr.NumId pprbasenumprnumid17 = factory.createPPrBaseNumPrNumId(); 
	                    pprbasenumpr17.setNumId(pprbasenumprnumid17); 
	                        pprbasenumprnumid17.setVal( BigInteger.valueOf( 16) ); 
	                        ppr349.setAutoSpaceDE(booltrue); 
	                        ppr349.setAutoSpaceDN(booltrue); 
	                        ppr349.setAdjustRightInd(booltrue); 
	                        PPrBase.Spacing pprbasespacing40 = factory.createPPrBaseSpacing(); 
	                        ppr349.setSpacing(pprbasespacing40); 
	                            pprbasespacing40.setAfter( BigInteger.valueOf( 0) ); 
	                            pprbasespacing40.setLine( BigInteger.valueOf( 240) ); 
	                            pprbasespacing40.setLineRule(org.docx4j.wml.STLineSpacingRule.AUTO);
	                        ppr349.setContextualSpacing(booltrue);
	                        
	                        // Create object for p
	                        P p150 = factory.createP(); 
	                        body.getContent().add( p150); 
	                            // Create object for r
	                            R r162 = factory.createR(); 
	                            p150.getContent().add( r162); 
	                                // Create object for t (wrapped in JAXBElement) 
	                                Text text149 = factory.createText();  
	                                r162.getContent().add( text149); 
	                                    text149.setValue( "DEVELOPER ANALYSIS: "); 
	                                    text149.setSpace( "preserve"); 
	                                // Create object for rPr
	                                RPr rpr162 = factory.createRPr(); 
	                                r162.setRPr(rpr162); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure596 = factory.createHpsMeasure(); 
	                                    rpr162.setSz(hpsmeasure596); 
	                                        hpsmeasure596.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure597 = factory.createHpsMeasure(); 
	                                    rpr162.setSzCs(hpsmeasure597); 
	                                        hpsmeasure597.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for pPr
	                            PPr ppr150 = factory.createPPr(); 
	                            p150.setPPr(ppr150); 
	                                // Create object for rPr
	                                // Create object for numPr
	                                PPrBase.NumPr pprbasenumpr24 = factory.createPPrBaseNumPr(); 
	                                ppr150.setNumPr(pprbasenumpr24); 
	                                    // Create object for ilvl
	                                    PPrBase.NumPr.Ilvl pprbasenumprilvl24 = factory.createPPrBaseNumPrIlvl(); 
	                                    pprbasenumpr24.setIlvl(pprbasenumprilvl24); 
	                                        pprbasenumprilvl24.setVal( BigInteger.valueOf( 0) ); 
	                                    // Create object for numId
	                                    PPrBase.NumPr.NumId pprbasenumprnumid24 = factory.createPPrBaseNumPrNumId(); 
	                                    pprbasenumpr24.setNumId(pprbasenumprnumid24); 
	                                        pprbasenumprnumid24.setVal( BigInteger.valueOf( 21) ); 
	                                // Create object for autoSpaceDE
	                                BooleanDefaultTrue booleandefaulttrue167 = factory.createBooleanDefaultTrue(); 
	                                ppr150.setAutoSpaceDE(booleandefaulttrue167); 
	                                // Create object for autoSpaceDN
	                                BooleanDefaultTrue booleandefaulttrue168 = factory.createBooleanDefaultTrue(); 
	                                ppr150.setAutoSpaceDN(booleandefaulttrue168); 
	                                // Create object for adjustRightInd
	                                BooleanDefaultTrue booleandefaulttrue169 = factory.createBooleanDefaultTrue(); 
	                                ppr150.setAdjustRightInd(booleandefaulttrue169); 
	                                // Create object for spacing
	                                PPrBase.Spacing pprbasespacing46 = factory.createPPrBaseSpacing(); 
	                                ppr150.setSpacing(pprbasespacing46); 
	                                    pprbasespacing46.setAfter( BigInteger.valueOf( 0) ); 
	                                    pprbasespacing46.setLine( BigInteger.valueOf( 240) ); 
	                                    pprbasespacing46.setLineRule(org.docx4j.wml.STLineSpacingRule.AUTO);
	                                // Create object for contextualSpacing
	                                BooleanDefaultTrue booleandefaulttrue170 = factory.createBooleanDefaultTrue(); 
	                                ppr150.setContextualSpacing(booleandefaulttrue170); 
	                        // Create object for p
	                        P p151 = factory.createP(); 
	                        body.getContent().add( p151); 
	                            // Create object for r
	                            R r163 = factory.createR(); 
	                            p151.getContent().add( r163); 
	                                // Create object for t (wrapped in JAXBElement) 
	                                Text text150 = factory.createText(); 
	                                JAXBElement<org.docx4j.wml.Text> textWrapped150 = factory.createRT(text150); 
	                                r163.getContent().add( textWrapped150); 
	                                    text150.setValue( "Security Engineering Recommendation"); 
	                                // Create object for rPr
	                                RPr rpr163 = factory.createRPr(); 
	                                r163.setRPr(rpr163); 
	                                    // Create object for b
	                                    BooleanDefaultTrue booleandefaulttrue171 = factory.createBooleanDefaultTrue(); 
	                                    rpr163.setB(booleandefaulttrue171); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure600 = factory.createHpsMeasure(); 
	                                    rpr163.setSz(hpsmeasure600); 
	                                        hpsmeasure600.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure601 = factory.createHpsMeasure(); 
	                                    rpr163.setSzCs(hpsmeasure601); 
	                                        hpsmeasure601.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for r
	                            R r164 = factory.createR(); 
	                            p151.getContent().add( r164); 
	                                // Create object for t (wrapped in JAXBElement) 
	                                Text text151 = factory.createText(); 
	                                JAXBElement<org.docx4j.wml.Text> textWrapped151 = factory.createRT(text151); 
	                                r164.getContent().add( textWrapped151); 
	                                    text151.setValue( ":  "); 
	                                    text151.setSpace( "preserve"); 
	                                // Create object for rPr
	                                RPr rpr164 = factory.createRPr(); 
	                                r164.setRPr(rpr164); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure602 = factory.createHpsMeasure(); 
	                                    rpr164.setSz(hpsmeasure602); 
	                                        hpsmeasure602.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure603 = factory.createHpsMeasure(); 
	                                    rpr164.setSzCs(hpsmeasure603); 
	                                        hpsmeasure603.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for pPr
	                            PPr ppr151 = factory.createPPr(); 
	                            p151.setPPr(ppr151); 
	                                // Create object for rPr
	                                ParaRPr pararpr151 = factory.createParaRPr(); 
	                                ppr151.setRPr(pararpr151); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure604 = factory.createHpsMeasure(); 
	                                    pararpr151.setSz(hpsmeasure604); 
	                                        hpsmeasure604.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure605 = factory.createHpsMeasure(); 
	                                    pararpr151.setSzCs(hpsmeasure605); 
	                                        hpsmeasure605.setVal( BigInteger.valueOf( 20) ); 
	                                // Create object for numPr
	                                PPrBase.NumPr pprbasenumpr25 = factory.createPPrBaseNumPr(); 
	                                ppr151.setNumPr(pprbasenumpr25); 
	                                    // Create object for ilvl
	                                    PPrBase.NumPr.Ilvl pprbasenumprilvl25 = factory.createPPrBaseNumPrIlvl(); 
	                                    pprbasenumpr25.setIlvl(pprbasenumprilvl25); 
	                                        pprbasenumprilvl25.setVal( BigInteger.valueOf( 0) ); 
	                                    // Create object for numId
	                                    PPrBase.NumPr.NumId pprbasenumprnumid25 = factory.createPPrBaseNumPrNumId(); 
	                                    pprbasenumpr25.setNumId(pprbasenumprnumid25); 
	                                        pprbasenumprnumid25.setVal( BigInteger.valueOf( 21) ); 
	                                // Create object for autoSpaceDE
	                                BooleanDefaultTrue booleandefaulttrue172 = factory.createBooleanDefaultTrue(); 
	                                ppr151.setAutoSpaceDE(booleandefaulttrue172); 
	                                // Create object for autoSpaceDN
	                                BooleanDefaultTrue booleandefaulttrue173 = factory.createBooleanDefaultTrue(); 
	                                ppr151.setAutoSpaceDN(booleandefaulttrue173); 
	                                // Create object for adjustRightInd
	                                BooleanDefaultTrue booleandefaulttrue174 = factory.createBooleanDefaultTrue(); 
	                                ppr151.setAdjustRightInd(booleandefaulttrue174); 
	                                // Create object for spacing
	                                PPrBase.Spacing pprbasespacing47 = factory.createPPrBaseSpacing(); 
	                                ppr151.setSpacing(pprbasespacing47); 
	                                    pprbasespacing47.setAfter( BigInteger.valueOf( 0) ); 
	                                    pprbasespacing47.setLine( BigInteger.valueOf( 240) ); 
	                                    pprbasespacing47.setLineRule(org.docx4j.wml.STLineSpacingRule.AUTO);
	                                // Create object for contextualSpacing
	                                BooleanDefaultTrue booleandefaulttrue175 = factory.createBooleanDefaultTrue(); 
	                                ppr151.setContextualSpacing(booleandefaulttrue175); 
	                        // Create object for p
	                        P p152 = factory.createP(); 
	                        body.getContent().add( p152); 
	                            // Create object for bookmarkStart (wrapped in JAXBElement) 
	                            CTBookmark bookmark18 = factory.createCTBookmark(); 
	                            JAXBElement<org.docx4j.wml.CTBookmark> bookmarkWrapped18 = factory.createPBookmarkStart(bookmark18); 
	                            p152.getContent().add( bookmarkWrapped18); 
	                                bookmark18.setName( "_Weak_XML_Schema:_2"); 
	                                bookmark18.setId( BigInteger.valueOf( 17) ); 
	                            // Create object for bookmarkEnd (wrapped in JAXBElement) 
	                            CTMarkupRange markuprange18 = factory.createCTMarkupRange(); 
	                            JAXBElement<org.docx4j.wml.CTMarkupRange> markuprangeWrapped18 = factory.createPBookmarkEnd(markuprange18); 
	                            p152.getContent().add( markuprangeWrapped18); 
	                                markuprange18.setId( BigInteger.valueOf( 17) ); 
	                            // Create object for pPr
	                            PPr ppr152 = factory.createPPr(); 
	                            p152.setPPr(ppr152); 
	                                // Create object for rPr
	                                ParaRPr pararpr152 = factory.createParaRPr(); 
	                                ppr152.setRPr(pararpr152); 
	                                    // Create object for rFonts
	                                    RFonts rfonts156 = factory.createRFonts(); 
	                                    pararpr152.setRFonts(rfonts156); 
	                                        rfonts156.setAsciiTheme(org.docx4j.wml.STTheme.MAJOR_H_ANSI);
	                                        rfonts156.setHAnsiTheme(org.docx4j.wml.STTheme.MAJOR_H_ANSI);
	                                        rfonts156.setEastAsiaTheme(org.docx4j.wml.STTheme.MAJOR_EAST_ASIA);
	                                        rfonts156.setCstheme(org.docx4j.wml.STTheme.MAJOR_BIDI);
	                                    // Create object for b
	                                    BooleanDefaultTrue booleandefaulttrue176 = factory.createBooleanDefaultTrue(); 
	                                    pararpr152.setB(booleandefaulttrue176); 
	                                    // Create object for bCs
	                                    BooleanDefaultTrue booleandefaulttrue177 = factory.createBooleanDefaultTrue(); 
	                                    pararpr152.setBCs(booleandefaulttrue177); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure606 = factory.createHpsMeasure(); 
	                                    pararpr152.setSz(hpsmeasure606); 
	                                        hpsmeasure606.setVal( BigInteger.valueOf( 28) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure607 = factory.createHpsMeasure(); 
	                                    pararpr152.setSzCs(hpsmeasure607); 
	                                        hpsmeasure607.setVal( BigInteger.valueOf( 28) ); 
	                                // Create object for keepNext
	                                BooleanDefaultTrue booleandefaulttrue178 = factory.createBooleanDefaultTrue(); 
	                                ppr152.setKeepNext(booleandefaulttrue178); 
	                                // Create object for keepLines
	                                BooleanDefaultTrue booleandefaulttrue179 = factory.createBooleanDefaultTrue(); 
	                                ppr152.setKeepLines(booleandefaulttrue179); 
	                                // Create object for spacing
	                                PPrBase.Spacing pprbasespacing48 = factory.createPPrBaseSpacing(); 
	                                ppr152.setSpacing(pprbasespacing48); 
	                                    pprbasespacing48.setAfter( BigInteger.valueOf( 120) ); 
	                                    pprbasespacing48.setLine( BigInteger.valueOf( 240) ); 
	                                    pprbasespacing48.setLineRule(org.docx4j.wml.STLineSpacingRule.AUTO);
	                                // Create object for ind
	                                PPrBase.Ind pprbaseind70 = factory.createPPrBaseInd(); 
	                                ppr152.setInd(pprbaseind70); 
	                                    pprbaseind70.setLeft( BigInteger.valueOf( 720) ); 
	                                    pprbaseind70.setHanging( BigInteger.valueOf( 360) ); 
	                                // Create object for outlineLvl
	                                PPrBase.OutlineLvl pprbaseoutlinelvl = factory.createPPrBaseOutlineLvl(); 
	                                ppr152.setOutlineLvl(pprbaseoutlinelvl); 
	                                    pprbaseoutlinelvl.setVal( BigInteger.valueOf( 0) ); 
	                        // Create object for p
	                        P p153 = factory.createP(); 
	                        body.getContent().add( p153); 
	                            // Create object for r
	                            R r165 = factory.createR(); 
	                            p153.getContent().add( r165); 
	                                // Create object for t (wrapped in JAXBElement) 
	                                Text text152 = factory.createText(); 
	                                JAXBElement<org.docx4j.wml.Text> textWrapped152 = factory.createRT(text152); 
	                                r165.getContent().add( textWrapped152); 
	                                    text152.setValue( "FPR Fortify Code Scan Tool Information"); 
	                                // Create object for rPr
	                                RPr rpr165 = factory.createRPr(); 
	                                r165.setRPr(rpr165); 
	                                    // Create object for rFonts
	                                    RFonts rfonts157 = factory.createRFonts(); 
	                                    rpr165.setRFonts(rfonts157); 
	                                        rfonts157.setAsciiTheme(org.docx4j.wml.STTheme.MAJOR_H_ANSI);
	                                        rfonts157.setHAnsiTheme(org.docx4j.wml.STTheme.MAJOR_H_ANSI);
	                                        rfonts157.setEastAsiaTheme(org.docx4j.wml.STTheme.MAJOR_EAST_ASIA);
	                                        rfonts157.setCstheme(org.docx4j.wml.STTheme.MAJOR_BIDI);
	                                    // Create object for b
	                                    BooleanDefaultTrue booleandefaulttrue180 = factory.createBooleanDefaultTrue(); 
	                                    rpr165.setB(booleandefaulttrue180); 
	                                    // Create object for bCs
	                                    BooleanDefaultTrue booleandefaulttrue181 = factory.createBooleanDefaultTrue(); 
	                                    rpr165.setBCs(booleandefaulttrue181); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure608 = factory.createHpsMeasure(); 
	                                    rpr165.setSz(hpsmeasure608); 
	                                        hpsmeasure608.setVal( BigInteger.valueOf( 28) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure609 = factory.createHpsMeasure(); 
	                                    rpr165.setSzCs(hpsmeasure609); 
	                                        hpsmeasure609.setVal( BigInteger.valueOf( 28) ); 
	                            // Create object for pPr
	                            PPr ppr153 = factory.createPPr(); 
	                            p153.setPPr(ppr153); 
	                                // Create object for rPr
	                                ParaRPr pararpr153 = factory.createParaRPr(); 
	                                ppr153.setRPr(pararpr153); 
	                                    // Create object for rFonts
	                                    RFonts rfonts158 = factory.createRFonts(); 
	                                    pararpr153.setRFonts(rfonts158); 
	                                        rfonts158.setAsciiTheme(org.docx4j.wml.STTheme.MAJOR_H_ANSI);
	                                        rfonts158.setHAnsiTheme(org.docx4j.wml.STTheme.MAJOR_H_ANSI);
	                                        rfonts158.setEastAsiaTheme(org.docx4j.wml.STTheme.MAJOR_EAST_ASIA);
	                                        rfonts158.setCstheme(org.docx4j.wml.STTheme.MAJOR_BIDI);
	                                    // Create object for b
	                                    BooleanDefaultTrue booleandefaulttrue182 = factory.createBooleanDefaultTrue(); 
	                                    pararpr153.setB(booleandefaulttrue182); 
	                                    // Create object for bCs
	                                    BooleanDefaultTrue booleandefaulttrue183 = factory.createBooleanDefaultTrue(); 
	                                    pararpr153.setBCs(booleandefaulttrue183); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure610 = factory.createHpsMeasure(); 
	                                    pararpr153.setSz(hpsmeasure610); 
	                                        hpsmeasure610.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure611 = factory.createHpsMeasure(); 
	                                    pararpr153.setSzCs(hpsmeasure611); 
	                                        hpsmeasure611.setVal( BigInteger.valueOf( 20) ); 
	                                // Create object for keepNext
	                                BooleanDefaultTrue booleandefaulttrue184 = factory.createBooleanDefaultTrue(); 
	                                ppr153.setKeepNext(booleandefaulttrue184); 
	                                // Create object for keepLines
	                                BooleanDefaultTrue booleandefaulttrue185 = factory.createBooleanDefaultTrue(); 
	                                ppr153.setKeepLines(booleandefaulttrue185); 
	                                // Create object for spacing
	                                PPrBase.Spacing pprbasespacing49 = factory.createPPrBaseSpacing(); 
	                                ppr153.setSpacing(pprbasespacing49); 
	                                    pprbasespacing49.setAfter( BigInteger.valueOf( 120) ); 
	                                    pprbasespacing49.setLine( BigInteger.valueOf( 240) ); 
	                                    pprbasespacing49.setLineRule(org.docx4j.wml.STLineSpacingRule.AUTO);
	                                // Create object for ind
	                                PPrBase.Ind pprbaseind71 = factory.createPPrBaseInd(); 
	                                ppr153.setInd(pprbaseind71); 
	                                    pprbaseind71.setLeft( BigInteger.valueOf( 720) ); 
	                                    pprbaseind71.setHanging( BigInteger.valueOf( 360) ); 
	                                // Create object for outlineLvl
	                                PPrBase.OutlineLvl pprbaseoutlinelvl2 = factory.createPPrBaseOutlineLvl(); 
	                                ppr153.setOutlineLvl(pprbaseoutlinelvl2); 
	                                    pprbaseoutlinelvl2.setVal( BigInteger.valueOf( 0) ); 
	                        // Create object for p
	                        P p154 = factory.createP(); 
	                        body.getContent().add( p154); 
	                            // Create object for r
	                            R r166 = factory.createR(); 
	                            p154.getContent().add( r166); 
	                                // Create object for t (wrapped in JAXBElement) 
	                                Text text153 = factory.createText(); 
	                                JAXBElement<org.docx4j.wml.Text> textWrapped153 = factory.createRT(text153); 
	                                r166.getContent().add( textWrapped153); 
	                                    text153.setValue( "User Tool Info"); 
	                                // Create object for rPr
	                                RPr rpr166 = factory.createRPr(); 
	                                r166.setRPr(rpr166); 
	                                    // Create object for b
	                                    BooleanDefaultTrue booleandefaulttrue186 = factory.createBooleanDefaultTrue(); 
	                                    rpr166.setB(booleandefaulttrue186); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure612 = factory.createHpsMeasure(); 
	                                    rpr166.setSz(hpsmeasure612); 
	                                        hpsmeasure612.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure613 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr166.setSzCs(hpsmeasure613); 
	                                        hpsmeasure613.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for pPr
	                            PPr ppr154 = wmlObjectFactory.createPPr(); 
	                            p154.setPPr(ppr154); 
	                                // Create object for rPr
	                                ParaRPr pararpr154 = wmlObjectFactory.createParaRPr(); 
	                                ppr154.setRPr(pararpr154); 
	                                    // Create object for b
	                                    BooleanDefaultTrue booleandefaulttrue187 = wmlObjectFactory.createBooleanDefaultTrue(); 
	                                    pararpr154.setB(booleandefaulttrue187); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure614 = wmlObjectFactory.createHpsMeasure(); 
	                                    pararpr154.setSz(hpsmeasure614); 
	                                        hpsmeasure614.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure615 = wmlObjectFactory.createHpsMeasure(); 
	                                    pararpr154.setSzCs(hpsmeasure615); 
	                                        hpsmeasure615.setVal( BigInteger.valueOf( 20) ); 
	                                // Create object for spacing
	                                PPrBase.Spacing pprbasespacing50 = wmlObjectFactory.createPPrBaseSpacing(); 
	                                ppr154.setSpacing(pprbasespacing50); 
	                                    pprbasespacing50.setAfter( BigInteger.valueOf( 0) ); 
	                                    pprbasespacing50.setLine( BigInteger.valueOf( 240) ); 
	                                    pprbasespacing50.setLineRule(org.docx4j.wml.STLineSpacingRule.AUTO);
	                                // Create object for ind
	                                PPrBase.Ind pprbaseind72 = wmlObjectFactory.createPPrBaseInd(); 
	                                ppr154.setInd(pprbaseind72); 
	                                    pprbaseind72.setLeft( BigInteger.valueOf( 720) ); 
	                                    pprbaseind72.setHanging( BigInteger.valueOf( 360) ); 
	                        // Create object for p
	                        P p155 = wmlObjectFactory.createP(); 
	                        body.getContent().add( p155); 
	                            // Create object for r
	                            R r167 = wmlObjectFactory.createR(); 
	                            p155.getContent().add( r167); 
	                                // Create object for t (wrapped in JAXBElement) 
	                                Text text154 = wmlObjectFactory.createText(); 
	                                JAXBElement<org.docx4j.wml.Text> textWrapped154 = wmlObjectFactory.createRT(text154); 
	                                r167.getContent().add( textWrapped154); 
	                                    text154.setValue( "Fortify Engine / SCA Version in Use "); 
	                                    text154.setSpace( "preserve"); 
	                                // Create object for rPr
	                                RPr rpr167 = wmlObjectFactory.createRPr(); 
	                                r167.setRPr(rpr167); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure616 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr167.setSz(hpsmeasure616); 
	                                        hpsmeasure616.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure617 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr167.setSzCs(hpsmeasure617); 
	                                        hpsmeasure617.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for r
	                            R r168 = wmlObjectFactory.createR(); 
	                            p155.getContent().add( r168); 
	                                // Create object for tab (wrapped in JAXBElement) 
	                                R.Tab rtab2 = wmlObjectFactory.createRTab(); 
	                                JAXBElement<org.docx4j.wml.R.Tab> rtabWrapped2 = wmlObjectFactory.createRTab(rtab2); 
	                                r168.getContent().add( rtabWrapped2); 
	                                // Create object for rPr
	                                RPr rpr168 = wmlObjectFactory.createRPr(); 
	                                r168.setRPr(rpr168); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure618 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr168.setSz(hpsmeasure618); 
	                                        hpsmeasure618.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure619 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr168.setSzCs(hpsmeasure619); 
	                                        hpsmeasure619.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for r
	                            R r169 = wmlObjectFactory.createR(); 
	                            p155.getContent().add( r169); 
	                                // Create object for tab (wrapped in JAXBElement) 
	                                R.Tab rtab3 = wmlObjectFactory.createRTab(); 
	                                JAXBElement<org.docx4j.wml.R.Tab> rtabWrapped3 = wmlObjectFactory.createRTab(rtab3); 
	                                r169.getContent().add( rtabWrapped3); 
	                                // Create object for rPr
	                                RPr rpr169 = wmlObjectFactory.createRPr(); 
	                                r169.setRPr(rpr169); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure620 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr169.setSz(hpsmeasure620); 
	                                        hpsmeasure620.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure621 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr169.setSzCs(hpsmeasure621); 
	                                        hpsmeasure621.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for r
	                            R r170 = wmlObjectFactory.createR(); 
	                            p155.getContent().add( r170); 
	                                // Create object for tab (wrapped in JAXBElement) 
	                                R.Tab rtab4 = wmlObjectFactory.createRTab(); 
	                                JAXBElement<org.docx4j.wml.R.Tab> rtabWrapped4 = wmlObjectFactory.createRTab(rtab4); 
	                                r170.getContent().add( rtabWrapped4); 
	                                // Create object for t (wrapped in JAXBElement) 
	                                Text text155 = wmlObjectFactory.createText(); 
	                                JAXBElement<org.docx4j.wml.Text> textWrapped155 = wmlObjectFactory.createRT(text155); 
	                                r170.getContent().add( textWrapped155); 
	                                    text155.setValue( "18.20.1071"); 
	                                // Create object for rPr
	                                RPr rpr170 = wmlObjectFactory.createRPr(); 
	                                r170.setRPr(rpr170); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure622 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr170.setSz(hpsmeasure622); 
	                                        hpsmeasure622.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure623 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr170.setSzCs(hpsmeasure623); 
	                                        hpsmeasure623.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for pPr
	                            PPr ppr155 = wmlObjectFactory.createPPr(); 
	                            p155.setPPr(ppr155); 
	                                // Create object for rPr
	                                ParaRPr pararpr155 = wmlObjectFactory.createParaRPr(); 
	                                ppr155.setRPr(pararpr155); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure624 = wmlObjectFactory.createHpsMeasure(); 
	                                    pararpr155.setSz(hpsmeasure624); 
	                                        hpsmeasure624.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure625 = wmlObjectFactory.createHpsMeasure(); 
	                                    pararpr155.setSzCs(hpsmeasure625); 
	                                        hpsmeasure625.setVal( BigInteger.valueOf( 20) ); 
	                                // Create object for spacing
	                                PPrBase.Spacing pprbasespacing51 = wmlObjectFactory.createPPrBaseSpacing(); 
	                                ppr155.setSpacing(pprbasespacing51); 
	                                    pprbasespacing51.setBefore( BigInteger.valueOf( 120) ); 
	                                    pprbasespacing51.setAfter( BigInteger.valueOf( 0) ); 
	                                    pprbasespacing51.setLine( BigInteger.valueOf( 240) ); 
	                                    pprbasespacing51.setLineRule(org.docx4j.wml.STLineSpacingRule.AUTO);
	                                // Create object for ind
	                                PPrBase.Ind pprbaseind73 = wmlObjectFactory.createPPrBaseInd(); 
	                                ppr155.setInd(pprbaseind73); 
	                                    pprbaseind73.setLeft( BigInteger.valueOf( 720) ); 
	                                    pprbaseind73.setHanging( BigInteger.valueOf( 360) ); 
	                        // Create object for p
	                        P p156 = wmlObjectFactory.createP(); 
	                        body.getContent().add( p156); 
	                            // Create object for r
	                            R r171 = wmlObjectFactory.createR(); 
	                            p156.getContent().add( r171); 
	                                // Create object for t (wrapped in JAXBElement) 
	                                Text text156 = wmlObjectFactory.createText(); 
	                                JAXBElement<org.docx4j.wml.Text> textWrapped156 = wmlObjectFactory.createRT(text156); 
	                                r171.getContent().add( textWrapped156); 
	                                    text156.setValue( "Fortify Rule Pack Version in Use"); 
	                                // Create object for rPr
	                                RPr rpr171 = wmlObjectFactory.createRPr(); 
	                                r171.setRPr(rpr171); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure626 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr171.setSz(hpsmeasure626); 
	                                        hpsmeasure626.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure627 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr171.setSzCs(hpsmeasure627); 
	                                        hpsmeasure627.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for r
	                            R r172 = wmlObjectFactory.createR(); 
	                            p156.getContent().add( r172); 
	                                // Create object for tab (wrapped in JAXBElement) 
	                                R.Tab rtab5 = wmlObjectFactory.createRTab(); 
	                                JAXBElement<org.docx4j.wml.R.Tab> rtabWrapped5 = wmlObjectFactory.createRTab(rtab5); 
	                                r172.getContent().add( rtabWrapped5); 
	                                // Create object for t (wrapped in JAXBElement) 
	                                Text text157 = wmlObjectFactory.createText(); 
	                                JAXBElement<org.docx4j.wml.Text> textWrapped157 = wmlObjectFactory.createRT(text157); 
	                                r172.getContent().add( textWrapped157); 
	                                    text157.setValue( " "); 
	                                    text157.setSpace( "preserve"); 
	                                // Create object for rPr
	                                RPr rpr172 = wmlObjectFactory.createRPr(); 
	                                r172.setRPr(rpr172); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure628 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr172.setSz(hpsmeasure628); 
	                                        hpsmeasure628.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure629 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr172.setSzCs(hpsmeasure629); 
	                                        hpsmeasure629.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for r
	                            R r173 = wmlObjectFactory.createR(); 
	                            p156.getContent().add( r173); 
	                                // Create object for tab (wrapped in JAXBElement) 
	                                R.Tab rtab6 = wmlObjectFactory.createRTab(); 
	                                JAXBElement<org.docx4j.wml.R.Tab> rtabWrapped6 = wmlObjectFactory.createRTab(rtab6); 
	                                r173.getContent().add( rtabWrapped6); 
	                                // Create object for rPr
	                                RPr rpr173 = wmlObjectFactory.createRPr(); 
	                                r173.setRPr(rpr173); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure630 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr173.setSz(hpsmeasure630); 
	                                        hpsmeasure630.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure631 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr173.setSzCs(hpsmeasure631); 
	                                        hpsmeasure631.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for r
	                            R r174 = wmlObjectFactory.createR(); 
	                            p156.getContent().add( r174); 
	                                // Create object for tab (wrapped in JAXBElement) 
	                                R.Tab rtab7 = wmlObjectFactory.createRTab(); 
	                                JAXBElement<org.docx4j.wml.R.Tab> rtabWrapped7 = wmlObjectFactory.createRTab(rtab7); 
	                                r174.getContent().add( rtabWrapped7); 
	                                // Create object for t (wrapped in JAXBElement) 
	                                Text text158 = wmlObjectFactory.createText(); 
	                                JAXBElement<org.docx4j.wml.Text> textWrapped158 = wmlObjectFactory.createRT(text158); 
	                                r174.getContent().add( textWrapped158); 
	                                    text158.setValue( "2019.1.0.0009"); 
	                                // Create object for rPr
	                                RPr rpr174 = wmlObjectFactory.createRPr(); 
	                                r174.setRPr(rpr174); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure632 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr174.setSz(hpsmeasure632); 
	                                        hpsmeasure632.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure633 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr174.setSzCs(hpsmeasure633); 
	                                        hpsmeasure633.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for r
	                            R r175 = wmlObjectFactory.createR(); 
	                            p156.getContent().add( r175); 
	                                // Create object for tab (wrapped in JAXBElement) 
	                                R.Tab rtab8 = wmlObjectFactory.createRTab(); 
	                                JAXBElement<org.docx4j.wml.R.Tab> rtabWrapped8 = wmlObjectFactory.createRTab(rtab8); 
	                                r175.getContent().add( rtabWrapped8); 
	                                // Create object for rPr
	                                RPr rpr175 = wmlObjectFactory.createRPr(); 
	                                r175.setRPr(rpr175); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure634 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr175.setSz(hpsmeasure634); 
	                                        hpsmeasure634.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure635 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr175.setSzCs(hpsmeasure635); 
	                                        hpsmeasure635.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for pPr
	                            PPr ppr156 = wmlObjectFactory.createPPr(); 
	                            p156.setPPr(ppr156); 
	                                // Create object for rPr
	                                ParaRPr pararpr156 = wmlObjectFactory.createParaRPr(); 
	                                ppr156.setRPr(pararpr156); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure636 = wmlObjectFactory.createHpsMeasure(); 
	                                    pararpr156.setSz(hpsmeasure636); 
	                                        hpsmeasure636.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure637 = wmlObjectFactory.createHpsMeasure(); 
	                                    pararpr156.setSzCs(hpsmeasure637); 
	                                        hpsmeasure637.setVal( BigInteger.valueOf( 20) ); 
	                                // Create object for spacing
	                                PPrBase.Spacing pprbasespacing52 = wmlObjectFactory.createPPrBaseSpacing(); 
	                                ppr156.setSpacing(pprbasespacing52); 
	                                    pprbasespacing52.setAfter( BigInteger.valueOf( 0) ); 
	                                    pprbasespacing52.setLine( BigInteger.valueOf( 240) ); 
	                                    pprbasespacing52.setLineRule(org.docx4j.wml.STLineSpacingRule.AUTO);
	                                // Create object for ind
	                                PPrBase.Ind pprbaseind74 = wmlObjectFactory.createPPrBaseInd(); 
	                                ppr156.setInd(pprbaseind74); 
	                                    pprbaseind74.setLeft( BigInteger.valueOf( 720) ); 
	                                    pprbaseind74.setHanging( BigInteger.valueOf( 360) ); 
	                        // Create object for p
	                        P p157 = wmlObjectFactory.createP(); 
	                        body.getContent().add( p157); 
	                            // Create object for r
	                            R r176 = wmlObjectFactory.createR(); 
	                            p157.getContent().add( r176); 
	                                // Create object for t (wrapped in JAXBElement) 
	                                Text text159 = wmlObjectFactory.createText(); 
	                                JAXBElement<org.docx4j.wml.Text> textWrapped159 = wmlObjectFactory.createRT(text159); 
	                                r176.getContent().add( textWrapped159); 
	                                    text159.setValue( "Scan Date"); 
	                                // Create object for rPr
	                                RPr rpr176 = wmlObjectFactory.createRPr(); 
	                                r176.setRPr(rpr176); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure638 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr176.setSz(hpsmeasure638); 
	                                        hpsmeasure638.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure639 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr176.setSzCs(hpsmeasure639); 
	                                        hpsmeasure639.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for r
	                            R r177 = wmlObjectFactory.createR(); 
	                            p157.getContent().add( r177); 
	                                // Create object for tab (wrapped in JAXBElement) 
	                                R.Tab rtab9 = wmlObjectFactory.createRTab(); 
	                                JAXBElement<org.docx4j.wml.R.Tab> rtabWrapped9 = wmlObjectFactory.createRTab(rtab9); 
	                                r177.getContent().add( rtabWrapped9); 
	                                // Create object for rPr
	                                RPr rpr177 = wmlObjectFactory.createRPr(); 
	                                r177.setRPr(rpr177); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure640 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr177.setSz(hpsmeasure640); 
	                                        hpsmeasure640.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure641 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr177.setSzCs(hpsmeasure641); 
	                                        hpsmeasure641.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for r
	                            R r178 = wmlObjectFactory.createR(); 
	                            p157.getContent().add( r178); 
	                                // Create object for tab (wrapped in JAXBElement) 
	                                R.Tab rtab10 = wmlObjectFactory.createRTab(); 
	                                JAXBElement<org.docx4j.wml.R.Tab> rtabWrapped10 = wmlObjectFactory.createRTab(rtab10); 
	                                r178.getContent().add( rtabWrapped10); 
	                                // Create object for rPr
	                                RPr rpr178 = wmlObjectFactory.createRPr(); 
	                                r178.setRPr(rpr178); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure642 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr178.setSz(hpsmeasure642); 
	                                        hpsmeasure642.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure643 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr178.setSzCs(hpsmeasure643); 
	                                        hpsmeasure643.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for r
	                            R r179 = wmlObjectFactory.createR(); 
	                            p157.getContent().add( r179); 
	                                // Create object for tab (wrapped in JAXBElement) 
	                                R.Tab rtab11 = wmlObjectFactory.createRTab(); 
	                                JAXBElement<org.docx4j.wml.R.Tab> rtabWrapped11 = wmlObjectFactory.createRTab(rtab11); 
	                                r179.getContent().add( rtabWrapped11); 
	                                // Create object for rPr
	                                RPr rpr179 = wmlObjectFactory.createRPr(); 
	                                r179.setRPr(rpr179); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure644 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr179.setSz(hpsmeasure644); 
	                                        hpsmeasure644.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure645 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr179.setSzCs(hpsmeasure645); 
	                                        hpsmeasure645.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for r
	                            R r180 = wmlObjectFactory.createR(); 
	                            p157.getContent().add( r180); 
	                                // Create object for tab (wrapped in JAXBElement) 
	                                R.Tab rtab12 = wmlObjectFactory.createRTab(); 
	                                JAXBElement<org.docx4j.wml.R.Tab> rtabWrapped12 = wmlObjectFactory.createRTab(rtab12); 
	                                r180.getContent().add( rtabWrapped12); 
	                                // Create object for rPr
	                                RPr rpr180 = wmlObjectFactory.createRPr(); 
	                                r180.setRPr(rpr180); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure646 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr180.setSz(hpsmeasure646); 
	                                        hpsmeasure646.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure647 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr180.setSzCs(hpsmeasure647); 
	                                        hpsmeasure647.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for r
	                            R r181 = wmlObjectFactory.createR(); 
	                            p157.getContent().add( r181); 
	                                // Create object for tab (wrapped in JAXBElement) 
	                                R.Tab rtab13 = wmlObjectFactory.createRTab(); 
	                                JAXBElement<org.docx4j.wml.R.Tab> rtabWrapped13 = wmlObjectFactory.createRTab(rtab13); 
	                                r181.getContent().add( rtabWrapped13); 
	                                // Create object for rPr
	                                RPr rpr181 = wmlObjectFactory.createRPr(); 
	                                r181.setRPr(rpr181); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure648 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr181.setSz(hpsmeasure648); 
	                                        hpsmeasure648.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure649 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr181.setSzCs(hpsmeasure649); 
	                                        hpsmeasure649.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for r
	                            R r182 = wmlObjectFactory.createR(); 
	                            p157.getContent().add( r182); 
	                                // Create object for tab (wrapped in JAXBElement) 
	                                R.Tab rtab14 = wmlObjectFactory.createRTab(); 
	                                JAXBElement<org.docx4j.wml.R.Tab> rtabWrapped14 = wmlObjectFactory.createRTab(rtab14); 
	                                r182.getContent().add( rtabWrapped14); 
	                                // Create object for t (wrapped in JAXBElement) 
	                                Text text160 = wmlObjectFactory.createText(); 
	                                JAXBElement<org.docx4j.wml.Text> textWrapped160 = wmlObjectFactory.createRT(text160); 
	                                r182.getContent().add( textWrapped160); 
	                                    text160.setValue( "May 42, 2019"); 
	                                // Create object for rPr
	                                RPr rpr182 = wmlObjectFactory.createRPr(); 
	                                r182.setRPr(rpr182); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure650 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr182.setSz(hpsmeasure650); 
	                                        hpsmeasure650.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure651 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr182.setSzCs(hpsmeasure651); 
	                                        hpsmeasure651.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for pPr
	                            PPr ppr157 = wmlObjectFactory.createPPr(); 
	                            p157.setPPr(ppr157); 
	                                // Create object for rPr
	                                ParaRPr pararpr157 = wmlObjectFactory.createParaRPr(); 
	                                ppr157.setRPr(pararpr157); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure652 = wmlObjectFactory.createHpsMeasure(); 
	                                    pararpr157.setSz(hpsmeasure652); 
	                                        hpsmeasure652.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure653 = wmlObjectFactory.createHpsMeasure(); 
	                                    pararpr157.setSzCs(hpsmeasure653); 
	                                        hpsmeasure653.setVal( BigInteger.valueOf( 20) ); 
	                                // Create object for spacing
	                                PPrBase.Spacing pprbasespacing53 = wmlObjectFactory.createPPrBaseSpacing(); 
	                                ppr157.setSpacing(pprbasespacing53); 
	                                    pprbasespacing53.setAfter( BigInteger.valueOf( 0) ); 
	                                    pprbasespacing53.setLine( BigInteger.valueOf( 240) ); 
	                                    pprbasespacing53.setLineRule(org.docx4j.wml.STLineSpacingRule.AUTO);
	                                // Create object for ind
	                                PPrBase.Ind pprbaseind75 = wmlObjectFactory.createPPrBaseInd(); 
	                                ppr157.setInd(pprbaseind75); 
	                                    pprbaseind75.setLeft( BigInteger.valueOf( 720) ); 
	                                    pprbaseind75.setHanging( BigInteger.valueOf( 360) ); 
	                        // Create object for p
	                        P p158 = wmlObjectFactory.createP(); 
	                        body.getContent().add( p158); 
	                            // Create object for pPr
	                            PPr ppr158 = wmlObjectFactory.createPPr(); 
	                            p158.setPPr(ppr158); 
	                                // Create object for rPr
	                                ParaRPr pararpr158 = wmlObjectFactory.createParaRPr(); 
	                                ppr158.setRPr(pararpr158); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure654 = wmlObjectFactory.createHpsMeasure(); 
	                                    pararpr158.setSz(hpsmeasure654); 
	                                        hpsmeasure654.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure655 = wmlObjectFactory.createHpsMeasure(); 
	                                    pararpr158.setSzCs(hpsmeasure655); 
	                                        hpsmeasure655.setVal( BigInteger.valueOf( 20) ); 
	                                // Create object for spacing
	                                PPrBase.Spacing pprbasespacing54 = wmlObjectFactory.createPPrBaseSpacing(); 
	                                ppr158.setSpacing(pprbasespacing54); 
	                                    pprbasespacing54.setAfter( BigInteger.valueOf( 0) ); 
	                                    pprbasespacing54.setLine( BigInteger.valueOf( 240) ); 
	                                    pprbasespacing54.setLineRule(org.docx4j.wml.STLineSpacingRule.AUTO);
	                                // Create object for ind
	                                PPrBase.Ind pprbaseind76 = wmlObjectFactory.createPPrBaseInd(); 
	                                ppr158.setInd(pprbaseind76); 
	                                    pprbaseind76.setLeft( BigInteger.valueOf( 720) ); 
	                                    pprbaseind76.setHanging( BigInteger.valueOf( 360) ); 
	                        // Create object for p
	                        P p159 = wmlObjectFactory.createP(); 
	                        body.getContent().add( p159); 
	                            // Create object for r
	                            R r183 = wmlObjectFactory.createR(); 
	                            p159.getContent().add( r183); 
	                                // Create object for t (wrapped in JAXBElement) 
	                                Text text161 = wmlObjectFactory.createText(); 
	                                JAXBElement<org.docx4j.wml.Text> textWrapped161 = wmlObjectFactory.createRT(text161); 
	                                r183.getContent().add( textWrapped161); 
	                                    text161.setValue( "Fortify Audit Team Tool Info"); 
	                                // Create object for rPr
	                                RPr rpr183 = wmlObjectFactory.createRPr(); 
	                                r183.setRPr(rpr183); 
	                                    // Create object for b
	                                    BooleanDefaultTrue booleandefaulttrue188 = wmlObjectFactory.createBooleanDefaultTrue(); 
	                                    rpr183.setB(booleandefaulttrue188); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure656 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr183.setSz(hpsmeasure656); 
	                                        hpsmeasure656.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure657 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr183.setSzCs(hpsmeasure657); 
	                                        hpsmeasure657.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for pPr
	                            PPr ppr159 = wmlObjectFactory.createPPr(); 
	                            p159.setPPr(ppr159); 
	                                // Create object for rPr
	                                ParaRPr pararpr159 = wmlObjectFactory.createParaRPr(); 
	                                ppr159.setRPr(pararpr159); 
	                                    // Create object for b
	                                    BooleanDefaultTrue booleandefaulttrue189 = wmlObjectFactory.createBooleanDefaultTrue(); 
	                                    pararpr159.setB(booleandefaulttrue189); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure658 = wmlObjectFactory.createHpsMeasure(); 
	                                    pararpr159.setSz(hpsmeasure658); 
	                                        hpsmeasure658.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure659 = wmlObjectFactory.createHpsMeasure(); 
	                                    pararpr159.setSzCs(hpsmeasure659); 
	                                        hpsmeasure659.setVal( BigInteger.valueOf( 20) ); 
	                                // Create object for spacing
	                                PPrBase.Spacing pprbasespacing55 = wmlObjectFactory.createPPrBaseSpacing(); 
	                                ppr159.setSpacing(pprbasespacing55); 
	                                    pprbasespacing55.setAfter( BigInteger.valueOf( 0) ); 
	                                    pprbasespacing55.setLine( BigInteger.valueOf( 240) ); 
	                                    pprbasespacing55.setLineRule(org.docx4j.wml.STLineSpacingRule.AUTO);
	                                // Create object for ind
	                                PPrBase.Ind pprbaseind77 = wmlObjectFactory.createPPrBaseInd(); 
	                                ppr159.setInd(pprbaseind77); 
	                                    pprbaseind77.setLeft( BigInteger.valueOf( 720) ); 
	                                    pprbaseind77.setHanging( BigInteger.valueOf( 360) ); 
	                        // Create object for p
	                        P p160 = wmlObjectFactory.createP(); 
	                        body.getContent().add( p160); 
	                            // Create object for r
	                            R r184 = wmlObjectFactory.createR(); 
	                            p160.getContent().add( r184); 
	                                // Create object for t (wrapped in JAXBElement) 
	                                Text text162 = wmlObjectFactory.createText(); 
	                                JAXBElement<org.docx4j.wml.Text> textWrapped162 = wmlObjectFactory.createRT(text162); 
	                                r184.getContent().add( textWrapped162); 
	                                    text162.setValue( "Fortify Engine / SCA Version in Use "); 
	                                    text162.setSpace( "preserve"); 
	                                // Create object for rPr
	                                RPr rpr184 = wmlObjectFactory.createRPr(); 
	                                r184.setRPr(rpr184); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure660 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr184.setSz(hpsmeasure660); 
	                                        hpsmeasure660.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure661 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr184.setSzCs(hpsmeasure661); 
	                                        hpsmeasure661.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for r
	                            R r185 = wmlObjectFactory.createR(); 
	                            p160.getContent().add( r185); 
	                                // Create object for tab (wrapped in JAXBElement) 
	                                R.Tab rtab15 = wmlObjectFactory.createRTab(); 
	                                JAXBElement<org.docx4j.wml.R.Tab> rtabWrapped15 = wmlObjectFactory.createRTab(rtab15); 
	                                r185.getContent().add( rtabWrapped15); 
	                                // Create object for rPr
	                                RPr rpr185 = wmlObjectFactory.createRPr(); 
	                                r185.setRPr(rpr185); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure662 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr185.setSz(hpsmeasure662); 
	                                        hpsmeasure662.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure663 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr185.setSzCs(hpsmeasure663); 
	                                        hpsmeasure663.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for r
	                            R r186 = wmlObjectFactory.createR(); 
	                            p160.getContent().add( r186); 
	                                // Create object for tab (wrapped in JAXBElement) 
	                                R.Tab rtab16 = wmlObjectFactory.createRTab(); 
	                                JAXBElement<org.docx4j.wml.R.Tab> rtabWrapped16 = wmlObjectFactory.createRTab(rtab16); 
	                                r186.getContent().add( rtabWrapped16); 
	                                // Create object for rPr
	                                RPr rpr186 = wmlObjectFactory.createRPr(); 
	                                r186.setRPr(rpr186); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure664 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr186.setSz(hpsmeasure664); 
	                                        hpsmeasure664.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure665 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr186.setSzCs(hpsmeasure665); 
	                                        hpsmeasure665.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for r
	                            R r187 = wmlObjectFactory.createR(); 
	                            p160.getContent().add( r187); 
	                                // Create object for tab (wrapped in JAXBElement) 
	                                R.Tab rtab17 = wmlObjectFactory.createRTab(); 
	                                JAXBElement<org.docx4j.wml.R.Tab> rtabWrapped17 = wmlObjectFactory.createRTab(rtab17); 
	                                r187.getContent().add( rtabWrapped17); 
	                                // Create object for t (wrapped in JAXBElement) 
	                                Text text163 = wmlObjectFactory.createText(); 
	                                JAXBElement<org.docx4j.wml.Text> textWrapped163 = wmlObjectFactory.createRT(text163); 
	                                r187.getContent().add( textWrapped163); 
	                                    text163.setValue( "18.20.1071"); 
	                                // Create object for rPr
	                                RPr rpr187 = wmlObjectFactory.createRPr(); 
	                                r187.setRPr(rpr187); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure666 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr187.setSz(hpsmeasure666); 
	                                        hpsmeasure666.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure667 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr187.setSzCs(hpsmeasure667); 
	                                        hpsmeasure667.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for pPr
	                            PPr ppr160 = wmlObjectFactory.createPPr(); 
	                            p160.setPPr(ppr160); 
	                                // Create object for rPr
	                                ParaRPr pararpr160 = wmlObjectFactory.createParaRPr(); 
	                                ppr160.setRPr(pararpr160); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure668 = wmlObjectFactory.createHpsMeasure(); 
	                                    pararpr160.setSz(hpsmeasure668); 
	                                        hpsmeasure668.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure669 = wmlObjectFactory.createHpsMeasure(); 
	                                    pararpr160.setSzCs(hpsmeasure669); 
	                                        hpsmeasure669.setVal( BigInteger.valueOf( 20) ); 
	                                // Create object for spacing
	                                PPrBase.Spacing pprbasespacing56 = wmlObjectFactory.createPPrBaseSpacing(); 
	                                ppr160.setSpacing(pprbasespacing56); 
	                                    pprbasespacing56.setBefore( BigInteger.valueOf( 120) ); 
	                                    pprbasespacing56.setAfter( BigInteger.valueOf( 0) ); 
	                                    pprbasespacing56.setLine( BigInteger.valueOf( 240) ); 
	                                    pprbasespacing56.setLineRule(org.docx4j.wml.STLineSpacingRule.AUTO);
	                                // Create object for ind
	                                PPrBase.Ind pprbaseind78 = wmlObjectFactory.createPPrBaseInd(); 
	                                ppr160.setInd(pprbaseind78); 
	                                    pprbaseind78.setLeft( BigInteger.valueOf( 720) ); 
	                                    pprbaseind78.setHanging( BigInteger.valueOf( 360) ); 
	                        // Create object for p
	                        P p161 = wmlObjectFactory.createP(); 
	                        body.getContent().add( p161); 
	                            // Create object for r
	                            R r188 = wmlObjectFactory.createR(); 
	                            p161.getContent().add( r188); 
	                                // Create object for t (wrapped in JAXBElement) 
	                                Text text164 = wmlObjectFactory.createText(); 
	                                JAXBElement<org.docx4j.wml.Text> textWrapped164 = wmlObjectFactory.createRT(text164); 
	                                r188.getContent().add( textWrapped164); 
	                                    text164.setValue( "Fortify Rule Pack Version in Use "); 
	                                    text164.setSpace( "preserve"); 
	                                // Create object for rPr
	                                RPr rpr188 = wmlObjectFactory.createRPr(); 
	                                r188.setRPr(rpr188); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure670 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr188.setSz(hpsmeasure670); 
	                                        hpsmeasure670.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure671 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr188.setSzCs(hpsmeasure671); 
	                                        hpsmeasure671.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for r
	                            R r189 = wmlObjectFactory.createR(); 
	                            p161.getContent().add( r189); 
	                                // Create object for tab (wrapped in JAXBElement) 
	                                R.Tab rtab18 = wmlObjectFactory.createRTab(); 
	                                JAXBElement<org.docx4j.wml.R.Tab> rtabWrapped18 = wmlObjectFactory.createRTab(rtab18); 
	                                r189.getContent().add( rtabWrapped18); 
	                                // Create object for rPr
	                                RPr rpr189 = wmlObjectFactory.createRPr(); 
	                                r189.setRPr(rpr189); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure672 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr189.setSz(hpsmeasure672); 
	                                        hpsmeasure672.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure673 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr189.setSzCs(hpsmeasure673); 
	                                        hpsmeasure673.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for r
	                            R r190 = wmlObjectFactory.createR(); 
	                            p161.getContent().add( r190); 
	                                // Create object for tab (wrapped in JAXBElement) 
	                                R.Tab rtab19 = wmlObjectFactory.createRTab(); 
	                                JAXBElement<org.docx4j.wml.R.Tab> rtabWrapped19 = wmlObjectFactory.createRTab(rtab19); 
	                                r190.getContent().add( rtabWrapped19); 
	                                // Create object for rPr
	                                RPr rpr190 = wmlObjectFactory.createRPr(); 
	                                r190.setRPr(rpr190); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure674 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr190.setSz(hpsmeasure674); 
	                                        hpsmeasure674.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure675 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr190.setSzCs(hpsmeasure675); 
	                                        hpsmeasure675.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for r
	                            R r191 = wmlObjectFactory.createR(); 
	                            p161.getContent().add( r191); 
	                                // Create object for tab (wrapped in JAXBElement) 
	                                R.Tab rtab20 = wmlObjectFactory.createRTab(); 
	                                JAXBElement<org.docx4j.wml.R.Tab> rtabWrapped20 = wmlObjectFactory.createRTab(rtab20); 
	                                r191.getContent().add( rtabWrapped20); 
	                                // Create object for t (wrapped in JAXBElement) 
	                                Text text165 = wmlObjectFactory.createText(); 
	                                JAXBElement<org.docx4j.wml.Text> textWrapped165 = wmlObjectFactory.createRT(text165); 
	                                r191.getContent().add( textWrapped165); 
	                                    text165.setValue( "2018.4.0.0008"); 
	                                // Create object for rPr
	                                RPr rpr191 = wmlObjectFactory.createRPr(); 
	                                r191.setRPr(rpr191); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure676 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr191.setSz(hpsmeasure676); 
	                                        hpsmeasure676.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure677 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr191.setSzCs(hpsmeasure677); 
	                                        hpsmeasure677.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for pPr
	                            PPr ppr161 = wmlObjectFactory.createPPr(); 
	                            p161.setPPr(ppr161); 
	                                // Create object for rPr
	                                ParaRPr pararpr161 = wmlObjectFactory.createParaRPr(); 
	                                ppr161.setRPr(pararpr161); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure678 = wmlObjectFactory.createHpsMeasure(); 
	                                    pararpr161.setSz(hpsmeasure678); 
	                                        hpsmeasure678.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure679 = wmlObjectFactory.createHpsMeasure(); 
	                                    pararpr161.setSzCs(hpsmeasure679); 
	                                        hpsmeasure679.setVal( BigInteger.valueOf( 20) ); 
	                                // Create object for spacing
	                                PPrBase.Spacing pprbasespacing57 = wmlObjectFactory.createPPrBaseSpacing(); 
	                                ppr161.setSpacing(pprbasespacing57); 
	                                    pprbasespacing57.setAfter( BigInteger.valueOf( 0) ); 
	                                    pprbasespacing57.setLine( BigInteger.valueOf( 240) ); 
	                                    pprbasespacing57.setLineRule(org.docx4j.wml.STLineSpacingRule.AUTO);
	                                // Create object for ind
	                                PPrBase.Ind pprbaseind79 = wmlObjectFactory.createPPrBaseInd(); 
	                                ppr161.setInd(pprbaseind79); 
	                                    pprbaseind79.setLeft( BigInteger.valueOf( 720) ); 
	                                    pprbaseind79.setHanging( BigInteger.valueOf( 360) ); 
	                        // Create object for p
	                        P p162 = wmlObjectFactory.createP(); 
	                        body.getContent().add( p162); 
	                            // Create object for r
	                            R r192 = wmlObjectFactory.createR(); 
	                            p162.getContent().add( r192); 
	                                // Create object for t (wrapped in JAXBElement) 
	                                Text text166 = wmlObjectFactory.createText(); 
	                                JAXBElement<org.docx4j.wml.Text> textWrapped166 = wmlObjectFactory.createRT(text166); 
	                                r192.getContent().add( textWrapped166); 
	                                    text166.setValue( "Scan Audited"); 
	                                // Create object for rPr
	                                RPr rpr192 = wmlObjectFactory.createRPr(); 
	                                r192.setRPr(rpr192); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure680 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr192.setSz(hpsmeasure680); 
	                                        hpsmeasure680.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure681 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr192.setSzCs(hpsmeasure681); 
	                                        hpsmeasure681.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for r
	                            R r193 = wmlObjectFactory.createR(); 
	                            p162.getContent().add( r193); 
	                                // Create object for tab (wrapped in JAXBElement) 
	                                R.Tab rtab21 = wmlObjectFactory.createRTab(); 
	                                JAXBElement<org.docx4j.wml.R.Tab> rtabWrapped21 = wmlObjectFactory.createRTab(rtab21); 
	                                r193.getContent().add( rtabWrapped21); 
	                                // Create object for rPr
	                                RPr rpr193 = wmlObjectFactory.createRPr(); 
	                                r193.setRPr(rpr193); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure682 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr193.setSz(hpsmeasure682); 
	                                        hpsmeasure682.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure683 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr193.setSzCs(hpsmeasure683); 
	                                        hpsmeasure683.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for r
	                            R r194 = wmlObjectFactory.createR(); 
	                            p162.getContent().add( r194); 
	                                // Create object for tab (wrapped in JAXBElement) 
	                                R.Tab rtab22 = wmlObjectFactory.createRTab(); 
	                                JAXBElement<org.docx4j.wml.R.Tab> rtabWrapped22 = wmlObjectFactory.createRTab(rtab22); 
	                                r194.getContent().add( rtabWrapped22); 
	                                // Create object for rPr
	                                RPr rpr194 = wmlObjectFactory.createRPr(); 
	                                r194.setRPr(rpr194); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure684 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr194.setSz(hpsmeasure684); 
	                                        hpsmeasure684.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure685 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr194.setSzCs(hpsmeasure685); 
	                                        hpsmeasure685.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for r
	                            R r195 = wmlObjectFactory.createR(); 
	                            p162.getContent().add( r195); 
	                                // Create object for tab (wrapped in JAXBElement) 
	                                R.Tab rtab23 = wmlObjectFactory.createRTab(); 
	                                JAXBElement<org.docx4j.wml.R.Tab> rtabWrapped23 = wmlObjectFactory.createRTab(rtab23); 
	                                r195.getContent().add( rtabWrapped23); 
	                                // Create object for rPr
	                                RPr rpr195 = wmlObjectFactory.createRPr(); 
	                                r195.setRPr(rpr195); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure686 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr195.setSz(hpsmeasure686); 
	                                        hpsmeasure686.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure687 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr195.setSzCs(hpsmeasure687); 
	                                        hpsmeasure687.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for r
	                            R r196 = wmlObjectFactory.createR(); 
	                            p162.getContent().add( r196); 
	                                // Create object for tab (wrapped in JAXBElement) 
	                                R.Tab rtab24 = wmlObjectFactory.createRTab(); 
	                                JAXBElement<org.docx4j.wml.R.Tab> rtabWrapped24 = wmlObjectFactory.createRTab(rtab24); 
	                                r196.getContent().add( rtabWrapped24); 
	                                // Create object for rPr
	                                RPr rpr196 = wmlObjectFactory.createRPr(); 
	                                r196.setRPr(rpr196); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure688 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr196.setSz(hpsmeasure688); 
	                                        hpsmeasure688.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure689 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr196.setSzCs(hpsmeasure689); 
	                                        hpsmeasure689.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for r
	                            R r197 = wmlObjectFactory.createR(); 
	                            p162.getContent().add( r197); 
	                                // Create object for tab (wrapped in JAXBElement) 
	                                R.Tab rtab25 = wmlObjectFactory.createRTab(); 
	                                JAXBElement<org.docx4j.wml.R.Tab> rtabWrapped25 = wmlObjectFactory.createRTab(rtab25); 
	                                r197.getContent().add( rtabWrapped25); 
	                                // Create object for rPr
	                                RPr rpr197 = wmlObjectFactory.createRPr(); 
	                                r197.setRPr(rpr197); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure690 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr197.setSz(hpsmeasure690); 
	                                        hpsmeasure690.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure691 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr197.setSzCs(hpsmeasure691); 
	                                        hpsmeasure691.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for r
	                            R r198 = wmlObjectFactory.createR(); 
	                            p162.getContent().add( r198); 
	                                // Create object for tab (wrapped in JAXBElement) 
	                                R.Tab rtab26 = wmlObjectFactory.createRTab(); 
	                                JAXBElement<org.docx4j.wml.R.Tab> rtabWrapped26 = wmlObjectFactory.createRTab(rtab26); 
	                                r198.getContent().add( rtabWrapped26); 
	                                // Create object for t (wrapped in JAXBElement) 
	                                Text text167 = wmlObjectFactory.createText(); 
	                                JAXBElement<org.docx4j.wml.Text> textWrapped167 = wmlObjectFactory.createRT(text167); 
	                                r198.getContent().add( textWrapped167); 
	                                    text167.setValue( "FPR-filename.fpr"); 
	                                // Create object for rPr
	                                RPr rpr198 = wmlObjectFactory.createRPr(); 
	                                r198.setRPr(rpr198); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure692 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr198.setSz(hpsmeasure692); 
	                                        hpsmeasure692.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure693 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr198.setSzCs(hpsmeasure693); 
	                                        hpsmeasure693.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for pPr
	                            PPr ppr162 = wmlObjectFactory.createPPr(); 
	                            p162.setPPr(ppr162); 
	                                // Create object for ind
	                                PPrBase.Ind pprbaseind80 = wmlObjectFactory.createPPrBaseInd(); 
	                                ppr162.setInd(pprbaseind80); 
	                                    pprbaseind80.setLeft( BigInteger.valueOf( 720) ); 
	                                    pprbaseind80.setHanging( BigInteger.valueOf( 360) ); 
	                        // Create object for p
	                        P p163 = wmlObjectFactory.createP(); 
	                        body.getContent().add( p163); 
	                            // Create object for r
	                            R r199 = wmlObjectFactory.createR(); 
	                            p163.getContent().add( r199); 
	                                // Create object for t (wrapped in JAXBElement) 
	                                Text text168 = wmlObjectFactory.createText(); 
	                                JAXBElement<org.docx4j.wml.Text> textWrapped168 = wmlObjectFactory.createRT(text168); 
	                                r199.getContent().add( textWrapped168); 
	                                    text168.setValue( "Way Ahead"); 
	                                // Create object for rPr
	                                RPr rpr199 = wmlObjectFactory.createRPr(); 
	                                r199.setRPr(rpr199); 
	                                    // Create object for color
	                                    Color color42 = wmlObjectFactory.createColor(); 
	                                    rpr199.setColor(color42); 
	                                        color42.setVal( "auto"); 
	                            // Create object for pPr
	                            PPr ppr163 = wmlObjectFactory.createPPr(); 
	                            p163.setPPr(ppr163); 
	                                // Create object for rPr
	                                ParaRPr pararpr162 = wmlObjectFactory.createParaRPr(); 
	                                ppr163.setRPr(pararpr162); 
	                                    // Create object for color
	                                    Color color43 = wmlObjectFactory.createColor(); 
	                                    pararpr162.setColor(color43); 
	                                        color43.setVal( "auto"); 
	                                // Create object for pStyle
	                                PPrBase.PStyle pprbasepstyle34 = wmlObjectFactory.createPPrBasePStyle(); 
	                                ppr163.setPStyle(pprbasepstyle34); 
	                                    pprbasepstyle34.setVal( "Heading1"); 
	                                // Create object for spacing
	                                PPrBase.Spacing pprbasespacing58 = wmlObjectFactory.createPPrBaseSpacing(); 
	                                ppr163.setSpacing(pprbasespacing58); 
	                                    pprbasespacing58.setBefore( BigInteger.valueOf( 0) ); 
	                                    pprbasespacing58.setAfter( BigInteger.valueOf( 120) ); 
	                                    pprbasespacing58.setLine( BigInteger.valueOf( 240) ); 
	                                    pprbasespacing58.setLineRule(org.docx4j.wml.STLineSpacingRule.AUTO);
	                                // Create object for ind
	                                PPrBase.Ind pprbaseind81 = wmlObjectFactory.createPPrBaseInd(); 
	                                ppr163.setInd(pprbaseind81); 
	                                    pprbaseind81.setLeft( BigInteger.valueOf( 720) ); 
	                                    pprbaseind81.setHanging( BigInteger.valueOf( 360) ); 
	                        // Create object for p
	                        P p164 = wmlObjectFactory.createP(); 
	                        body.getContent().add( p164); 
	                            // Create object for r
	                            R r200 = wmlObjectFactory.createR(); 
	                            p164.getContent().add( r200); 
	                                // Create object for t (wrapped in JAXBElement) 
	                                Text text169 = wmlObjectFactory.createText(); 
	                                JAXBElement<org.docx4j.wml.Text> textWrapped169 = wmlObjectFactory.createRT(text169); 
	                                r200.getContent().add( textWrapped169); 
	                                    text169.setValue( "Contact the Fortify Team to schedule your next planned release. The Fortify Team will establish the scan engine and rule pack required for your next projected deployment date. If deployment timelines slip to the right, a new scan engine and/or rule pack may be required. "); 
	                                    text169.setSpace( "preserve"); 
	                                // Create object for rPr
	                                RPr rpr200 = wmlObjectFactory.createRPr(); 
	                                r200.setRPr(rpr200); 
	                                    // Create object for color
	                                    Color color44 = wmlObjectFactory.createColor(); 
	                                    rpr200.setColor(color44); 
	                                        color44.setVal( "auto"); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure694 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr200.setSz(hpsmeasure694); 
	                                        hpsmeasure694.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure695 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr200.setSzCs(hpsmeasure695); 
	                                        hpsmeasure695.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for pPr
	                            PPr ppr164 = wmlObjectFactory.createPPr(); 
	                            p164.setPPr(ppr164); 
	                                // Create object for rPr
	                                ParaRPr pararpr163 = wmlObjectFactory.createParaRPr(); 
	                                ppr164.setRPr(pararpr163); 
	                                    // Create object for color
	                                    Color color45 = wmlObjectFactory.createColor(); 
	                                    pararpr163.setColor(color45); 
	                                        color45.setVal( "auto"); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure696 = wmlObjectFactory.createHpsMeasure(); 
	                                    pararpr163.setSz(hpsmeasure696); 
	                                        hpsmeasure696.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure697 = wmlObjectFactory.createHpsMeasure(); 
	                                    pararpr163.setSzCs(hpsmeasure697); 
	                                        hpsmeasure697.setVal( BigInteger.valueOf( 20) ); 
	                                // Create object for pStyle
	                                PPrBase.PStyle pprbasepstyle35 = wmlObjectFactory.createPPrBasePStyle(); 
	                                ppr164.setPStyle(pprbasepstyle35); 
	                                    pprbasepstyle35.setVal( "Default"); 
	                                // Create object for ind
	                                PPrBase.Ind pprbaseind82 = wmlObjectFactory.createPPrBaseInd(); 
	                                ppr164.setInd(pprbaseind82); 
	                                    pprbaseind82.setLeft( BigInteger.valueOf( 360) ); 
	                        // Create object for p
	                        P p165 = wmlObjectFactory.createP(); 
	                        body.getContent().add( p165); 
	                            // Create object for pPr
	                            PPr ppr165 = wmlObjectFactory.createPPr(); 
	                            p165.setPPr(ppr165); 
	                                // Create object for rPr
	                                ParaRPr pararpr164 = wmlObjectFactory.createParaRPr(); 
	                                ppr165.setRPr(pararpr164); 
	                                    // Create object for color
	                                    Color color46 = wmlObjectFactory.createColor(); 
	                                    pararpr164.setColor(color46); 
	                                        color46.setVal( "auto"); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure698 = wmlObjectFactory.createHpsMeasure(); 
	                                    pararpr164.setSz(hpsmeasure698); 
	                                        hpsmeasure698.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure699 = wmlObjectFactory.createHpsMeasure(); 
	                                    pararpr164.setSzCs(hpsmeasure699); 
	                                        hpsmeasure699.setVal( BigInteger.valueOf( 20) ); 
	                                // Create object for pStyle
	                                PPrBase.PStyle pprbasepstyle36 = wmlObjectFactory.createPPrBasePStyle(); 
	                                ppr165.setPStyle(pprbasepstyle36); 
	                                    pprbasepstyle36.setVal( "Default"); 
	                                // Create object for ind
	                                PPrBase.Ind pprbaseind83 = wmlObjectFactory.createPPrBaseInd(); 
	                                ppr165.setInd(pprbaseind83); 
	                                    pprbaseind83.setLeft( BigInteger.valueOf( 360) ); 
	                        // Create object for p
	                        P p166 = wmlObjectFactory.createP(); 
	                        body.getContent().add( p166); 
	                            // Create object for r
	                            R r201 = wmlObjectFactory.createR(); 
	                            p166.getContent().add( r201); 
	                                // Create object for t (wrapped in JAXBElement) 
	                                Text text170 = wmlObjectFactory.createText(); 
	                                JAXBElement<org.docx4j.wml.Text> textWrapped170 = wmlObjectFactory.createRT(text170); 
	                                r201.getContent().add( textWrapped170); 
	                                    text170.setValue( "Fortify rule pack and scan engine versions must be within six (6) months of the projected deployment date and current as of development start to be valid. Ensure you are maintaining up-to-date rule packs and scan engines. Ensure all new vulnerabilities are remediated based on Risk Management Framework (RMF) STIGs (4.8 or better) to meet RMF Authority to Operate (ATO) requirements. "); 
	                                    text170.setSpace( "preserve"); 
	                                // Create object for rPr
	                                RPr rpr201 = wmlObjectFactory.createRPr(); 
	                                r201.setRPr(rpr201); 
	                                    // Create object for color
	                                    Color color47 = wmlObjectFactory.createColor(); 
	                                    rpr201.setColor(color47); 
	                                        color47.setVal( "auto"); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure700 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr201.setSz(hpsmeasure700); 
	                                        hpsmeasure700.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure701 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr201.setSzCs(hpsmeasure701); 
	                                        hpsmeasure701.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for pPr
	                            PPr ppr166 = wmlObjectFactory.createPPr(); 
	                            p166.setPPr(ppr166); 
	                                // Create object for rPr
	                                ParaRPr pararpr165 = wmlObjectFactory.createParaRPr(); 
	                                ppr166.setRPr(pararpr165); 
	                                    // Create object for color
	                                    Color color48 = wmlObjectFactory.createColor(); 
	                                    pararpr165.setColor(color48); 
	                                        color48.setVal( "auto"); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure702 = wmlObjectFactory.createHpsMeasure(); 
	                                    pararpr165.setSz(hpsmeasure702); 
	                                        hpsmeasure702.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure703 = wmlObjectFactory.createHpsMeasure(); 
	                                    pararpr165.setSzCs(hpsmeasure703); 
	                                        hpsmeasure703.setVal( BigInteger.valueOf( 20) ); 
	                                // Create object for pStyle
	                                PPrBase.PStyle pprbasepstyle37 = wmlObjectFactory.createPPrBasePStyle(); 
	                                ppr166.setPStyle(pprbasepstyle37); 
	                                    pprbasepstyle37.setVal( "Default"); 
	                                // Create object for ind
	                                PPrBase.Ind pprbaseind84 = wmlObjectFactory.createPPrBaseInd(); 
	                                ppr166.setInd(pprbaseind84); 
	                                    pprbaseind84.setLeft( BigInteger.valueOf( 360) ); 
	                        // Create object for p
	                        P p167 = wmlObjectFactory.createP(); 
	                        body.getContent().add( p167); 
	                            // Create object for pPr
	                            PPr ppr167 = wmlObjectFactory.createPPr(); 
	                            p167.setPPr(ppr167); 
	                                // Create object for rPr
	                                ParaRPr pararpr166 = wmlObjectFactory.createParaRPr(); 
	                                ppr167.setRPr(pararpr166); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure704 = wmlObjectFactory.createHpsMeasure(); 
	                                    pararpr166.setSz(hpsmeasure704); 
	                                        hpsmeasure704.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure705 = wmlObjectFactory.createHpsMeasure(); 
	                                    pararpr166.setSzCs(hpsmeasure705); 
	                                        hpsmeasure705.setVal( BigInteger.valueOf( 20) ); 
	                                // Create object for spacing
	                                PPrBase.Spacing pprbasespacing59 = wmlObjectFactory.createPPrBaseSpacing(); 
	                                ppr167.setSpacing(pprbasespacing59); 
	                                    pprbasespacing59.setAfter( BigInteger.valueOf( 0) ); 
	                                    pprbasespacing59.setLine( BigInteger.valueOf( 240) ); 
	                                    pprbasespacing59.setLineRule(org.docx4j.wml.STLineSpacingRule.AUTO);
	                                // Create object for ind
	                                PPrBase.Ind pprbaseind85 = wmlObjectFactory.createPPrBaseInd(); 
	                                ppr167.setInd(pprbaseind85); 
	                                    pprbaseind85.setLeft( BigInteger.valueOf( 360) ); 
	                        // Create object for p
	                        P p168 = wmlObjectFactory.createP(); 
	                        body.getContent().add( p168); 
	                            // Create object for r
	                            R r202 = wmlObjectFactory.createR(); 
	                            p168.getContent().add( r202); 
	                                // Create object for t (wrapped in JAXBElement) 
	                                Text text171 = wmlObjectFactory.createText(); 
	                                JAXBElement<org.docx4j.wml.Text> textWrapped171 = wmlObjectFactory.createRT(text171); 
	                                r202.getContent().add( textWrapped171); 
	                                    text171.setValue( "All remaining valid findings should be remediated or mitigated in order of severity (highest severity to lowest severity)."); 
	                                // Create object for rPr
	                                RPr rpr202 = wmlObjectFactory.createRPr(); 
	                                r202.setRPr(rpr202); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure706 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr202.setSz(hpsmeasure706); 
	                                        hpsmeasure706.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure707 = wmlObjectFactory.createHpsMeasure(); 
	                                    rpr202.setSzCs(hpsmeasure707); 
	                                        hpsmeasure707.setVal( BigInteger.valueOf( 20) ); 
	                            // Create object for pPr
	                            PPr ppr168 = wmlObjectFactory.createPPr(); 
	                            p168.setPPr(ppr168); 
	                                // Create object for rPr
	                                ParaRPr pararpr167 = wmlObjectFactory.createParaRPr(); 
	                                ppr168.setRPr(pararpr167); 
	                                    // Create object for sz
	                                    HpsMeasure hpsmeasure708 = wmlObjectFactory.createHpsMeasure(); 
	                                    pararpr167.setSz(hpsmeasure708); 
	                                        hpsmeasure708.setVal( BigInteger.valueOf( 20) ); 
	                                    // Create object for szCs
	                                    HpsMeasure hpsmeasure709 = wmlObjectFactory.createHpsMeasure(); 
	                                    pararpr167.setSzCs(hpsmeasure709); 
	                                        hpsmeasure709.setVal( BigInteger.valueOf( 20) ); 
	                                // Create object for spacing
	                                PPrBase.Spacing pprbasespacing60 = wmlObjectFactory.createPPrBaseSpacing(); 
	                                ppr168.setSpacing(pprbasespacing60); 
	                                    pprbasespacing60.setAfter( BigInteger.valueOf( 0) ); 
	                                    pprbasespacing60.setLine( BigInteger.valueOf( 240) ); 
	                                    pprbasespacing60.setLineRule(org.docx4j.wml.STLineSpacingRule.AUTO);
	                                // Create object for ind
	                                PPrBase.Ind pprbaseind86 = wmlObjectFactory.createPPrBaseInd(); 
	                                ppr168.setInd(pprbaseind86); 
	                                    pprbaseind86.setLeft( BigInteger.valueOf( 360) ); 
		}    
	}
}
