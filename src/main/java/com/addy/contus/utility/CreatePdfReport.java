package com.addy.contus.utility;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;

import org.apache.log4j.Logger;

import com.addy.contus.entity.BigFiveAgreeableness;
import com.addy.contus.entity.BigFiveConscientiousness;
import com.addy.contus.entity.BigFiveExtraversion;
import com.addy.contus.entity.BigFiveNeuroticism;
import com.addy.contus.entity.BigFiveOpenness;
import com.addy.contus.entity.PdfTextData;
import com.addy.contus.entity.PdfTextDataEnglish;
import com.addy.contus.entity.PdfTextDataSwedish;
import com.addy.contus.entity.TestResult;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

public class CreatePdfReport {

	private static final Logger logger = Logger.getLogger(CreatePdfReport.class);

	private static Font mainHeading = FontFactory.getFont("Avenir next, sans-serif", 30,Font.BOLD);

	private static Font facetHeading = FontFactory.getFont("Avenir next, sans-serif", 22,Font.BOLD);

	private static Font subFacetHeading = FontFactory.getFont("Avenir next, sans-serif", 16,Font.BOLD);

	private static Font subFacetScore = FontFactory.getFont("Avenir next, sans-serif", 15,Font.NORMAL);

	private static Font smallNormal = FontFactory.getFont("Avenir next, sans-serif", 15, Font.NORMAL);

	private static Font smallNormalBold = FontFactory.getFont("Avenir next, sans-serif", 15, Font.BOLD);

	private static Font smallBold = FontFactory.getFont("Avenir next, sans-serif", 16, Font.BOLD);

	public static void main(String[] args) {
		System.out.println("Hiiiii ");

		CreatePdfReport report=new CreatePdfReport();
		TestResult result = new TestResult();
		result.neuroticism=new BigFiveNeuroticism();
		result.neuroticism.n1Anxiety=14;
		result.neuroticism.n2Anger=7;
		result.neuroticism.n3Depression=20;
		result.neuroticism.n4SelfConsciousness=10;
		result.neuroticism.n5Immoderation=18;
		result.neuroticism.n6Vulnerability=2;
		result.neuroticism.totalPercentage=75.0;

		result.extraversion=new BigFiveExtraversion();
		result.extraversion.e1Friendliness=13;
		result.extraversion.e2Gregariousness=12;
		result.extraversion.e3Assertiveness=15;
		result.extraversion.e4ActivityLevel=19;
		result.extraversion.e5ExcitementSeeking=5;
		result.extraversion.e6Cheerfulness=8;
		result.extraversion.totalPercentage=18.0;

		result.openness=new BigFiveOpenness();
		result.openness.o1Imagination=5;
		result.openness.o2ArtisticInterests=8;
		result.openness.o3Emotionality=14;
		result.openness.o4Adventurousness=16;
		result.openness.o5Intellect=17;
		result.openness.o6Liberalism=12;
		result.openness.totalPercentage=49.0;

		result.agreeableness=new BigFiveAgreeableness();
		result.agreeableness.a1Trust=6;
		result.agreeableness.a2Morality=4;
		result.agreeableness.a3Altruism=17;
		result.agreeableness.a4Cooperation=20;
		result.agreeableness.a5Modesty=11;
		result.agreeableness.a6Sympathy=9;
		result.agreeableness.totalPercentage=65.0;

		result.conscientiousness=new BigFiveConscientiousness();
		result.conscientiousness.c1SelfEfficacy=3;
		result.conscientiousness.c2Orderliness=7;
		result.conscientiousness.c3Dutifulness=15;
		result.conscientiousness.c4AchievementStriving=19;
		result.conscientiousness.c5SelfDiscipline=20;
		result.conscientiousness.c6Cautiousness=0;
		result.conscientiousness.totalPercentage=50.0;


		report.createPdf("addyPdf","sv",result,"Aditya Srivastava","addysriv27@gmail.com");

	}

	public boolean createPdf(String orderId,String language,TestResult result,String userName,String email)
	{
		boolean isPdfCreated=true;

		PdfTextData text;
		/*language="en";
		*/
		if(language.equals("sv"))
			text=new PdfTextDataSwedish();
		else
			text=new PdfTextDataEnglish();

		Rectangle pdfSize = new Rectangle(1000,11100);

		logger.info("%%%%%%%%%% Pdf creation  started with language "+language);
		try {
			Document document = new Document(pdfSize);
			PdfWriter writer=PdfWriter.getInstance(document, new FileOutputStream("src/main/resources/pdfReport/resultReport"+orderId+".pdf"));
			document.open();
			addMetaData(document,orderId,text);
			addTitlePage(document,orderId,text,userName,language,email);
			addContent(document,orderId,text,result);
			addFooter(document,text,writer);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}


		logger.info("%%%%%%%% Pdf created");


		return isPdfCreated;

	}


	private void addFooter(Document document, PdfTextData text,PdfWriter writer) {
		Image contusLogo;

		try {
			contusLogo = Image.getInstance("src/main/resources/footer.jpg");
			contusLogo.scaleToFit(1000f, 1800f);
			contusLogo.setAbsolutePosition(0, 0);
			writer.getDirectContent().addImage(contusLogo);

		}

		catch (BadElementException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addMetaData(Document document,String orderId,PdfTextData lang) {
		document.addTitle("Contus");
		document.addSubject("Personality Test");
		document.addKeywords("Facets, PDF, iText");
		document.addAuthor("Contus");
		document.addCreator("Aditya Srivastava (Developer) - addysriv27@gmail.com");
	}

	private void addTitlePage(Document document,String orderId,PdfTextData text,String userName,String language,String userEmail)
	{
		Image contusLogo;

		try {

			contusLogo = Image.getInstance("src/main/resources/logo2.png");

			contusLogo.scaleToFit(200f, 300f);
			document.add(contusLogo);

			Paragraph preface1 = new Paragraph(text.getResult(), mainHeading);

			preface1.setAlignment(Element.ALIGN_CENTER);

			document.add(preface1);
			
			String userNameText="";
			document.add(new Paragraph(" "));
			
			if(language.equals("sv"))
				userNameText=PdfTextDataSwedish.entryText+userName+" skapad vid "+(new Date()).toString() +" och skickas till "+userEmail;
			//userNameText="Denna rapport tillh�r "+userName+" skapad vid "+(new Date()).toString() +" och skickas till "+userEmail;
				
			else
				userNameText="This report belongs to "+userName+" created at "+(new Date()).toString() +" and sent to "+userEmail;
			
			document.add(new Paragraph(" "));
			
			Paragraph preface2 = new Paragraph(userNameText, FontFactory.getFont("Avenir next, sans-serif", 16,Font.BOLD));

			preface2.setAlignment(Element.ALIGN_LEFT);
			preface2.setIndentationLeft(104);
			preface2.setIndentationRight(104);

			document.add(preface2);
			//document.add(new Paragraph(" "));
			
			Paragraph resultStatements = new Paragraph();
			resultStatements.setIndentationLeft(5);

			resultStatements.add(new Paragraph(" "));
			Paragraph stmnt1=new Paragraph(text.getResultStatement1(),smallNormal);
			stmnt1.setAlignment(Element.ALIGN_LEFT);
			stmnt1.setIndentationLeft(100);
			stmnt1.setIndentationRight(100);

			resultStatements.add(stmnt1);

			resultStatements.add(new Paragraph(" "));
			Paragraph stmnt2=new Paragraph(text.getResultStatement2(),smallNormal);
			stmnt2.setAlignment(Element.ALIGN_LEFT);
			stmnt2.setIndentationLeft(100);
			stmnt2.setIndentationRight(100);

			resultStatements.add(stmnt2);

			document.add(resultStatements);
		} 
		catch (BadElementException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Start a new page
		//  document.newPage();
	}


	private void addContent(Document document,String orderId,PdfTextData text,TestResult result) {

		String neuro="Neuroticism";		
		String extr="Extraversion";
		String open="Openness";
		String agree="Agreeableness";
		String cons="Conscientiousness";

		Image barGraph;

		try {
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));
			barGraph = Image.getInstance("src/main/resources/chartImages/BarChart"+orderId+".jpeg");
			barGraph.setIndentationLeft(200);
			barGraph.scaleToFit(500f, 700f);
			document.add(barGraph);

			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));

			Paragraph preface = new Paragraph(neuro, facetHeading);
			setDefaultFormatting(preface);
			document.add(preface);

			int score=result.neuroticism.totalPercentage.intValue();

			String facetScoreDesc="";
			if(score>50)
				facetScoreDesc=text.getnHigh();
			else if(score==50)
				facetScoreDesc=text.getnNeutral();
			else
				facetScoreDesc=text.getnLow();

			addFacetData(preface, document, text, score, text.getnDef(), facetScoreDesc, "neuroGraph"+orderId,text.getnInfo());

			// Adding Neuro sub facet

			addSubFacet(preface, document, text, result.neuroticism.n1Anxiety, text.getFacetN1(), text.getN1Info());
			addSubFacet(preface, document, text, result.neuroticism.n2Anger, text.getFacetN2(), text.getN2Info());
			addSubFacet(preface, document, text, result.neuroticism.n3Depression, text.getFacetN3(), text.getN3Info());
			addSubFacet(preface, document, text, result.neuroticism.n4SelfConsciousness, text.getFacetN4(), text.getN4Info());
			addSubFacet(preface, document, text, result.neuroticism.n5Immoderation, text.getFacetN5(), text.getN5Info());
			addSubFacet(preface, document, text, result.neuroticism.n6Vulnerability, text.getFacetN6(), text.getN6Info());


			Paragraph emptyLine=new Paragraph();
			addEmptyLine(emptyLine,8);
			document.add(emptyLine);

			// Extraversion Facet block 

			preface = new Paragraph(extr, facetHeading);
			setDefaultFormatting(preface);
			document.add(preface);

			score=result.extraversion.totalPercentage.intValue();

			facetScoreDesc="";
			if(score>50)
				facetScoreDesc=text.geteHigh();
			else if(score==50)
				facetScoreDesc=text.geteNeutral();
			else
				facetScoreDesc=text.geteLow();

			addFacetData(preface, document, text, score, text.geteDef(), facetScoreDesc, "extraGraph"+orderId,text.geteInfo());

			// Adding Extraversion sub facet

			addSubFacet(preface, document, text, result.extraversion.e1Friendliness, text.getFacetE1(), text.getE1Info());
			addSubFacet(preface, document, text, result.extraversion.e2Gregariousness, text.getFacetE2(), text.getE2Info());
			addSubFacet(preface, document, text, result.extraversion.e3Assertiveness, text.getFacetE3(), text.getE3Info());
			addSubFacet(preface, document, text, result.extraversion.e4ActivityLevel, text.getFacetE4(), text.getE4Info());
			addSubFacet(preface, document, text, result.extraversion.e5ExcitementSeeking, text.getFacetE5(), text.getE5Info());
			addSubFacet(preface, document, text, result.extraversion.e6Cheerfulness, text.getFacetE6(), text.getE6Info());


			emptyLine=new Paragraph();
			addEmptyLine(emptyLine,8);
			document.add(emptyLine);

			// Openness Facet block 

			preface = new Paragraph(open, facetHeading);
			setDefaultFormatting(preface);
			document.add(preface);

			score=result.openness.totalPercentage.intValue();

			facetScoreDesc="";
			if(score>50)
				facetScoreDesc=text.getoHigh();
			else if(score==50)
				facetScoreDesc=text.getoNeutral();
			else
				facetScoreDesc=text.getoLow();

			addFacetData(preface, document, text, score, text.getoDef(), facetScoreDesc, "openGraph"+orderId,text.getoInfo());

			// Adding Openness sub facet

			addSubFacet(preface, document, text, result.openness.o1Imagination, text.getFacetO1(), text.getO1Info());
			addSubFacet(preface, document, text, result.openness.o2ArtisticInterests, text.getFacetO2(), text.getO2Info());
			addSubFacet(preface, document, text, result.openness.o3Emotionality, text.getFacetO3(), text.getO3Info());
			addSubFacet(preface, document, text, result.openness.o4Adventurousness, text.getFacetO4(), text.getO4Info());
			addSubFacet(preface, document, text, result.openness.o5Intellect, text.getFacetO5(), text.getO5Info());
			addSubFacet(preface, document, text, result.openness.o6Liberalism, text.getFacetO6(), text.getO6Info());


			emptyLine=new Paragraph();
			addEmptyLine(emptyLine,8);
			document.add(emptyLine);


			// Agreeableness Facet block 

			preface = new Paragraph(agree, facetHeading);
			setDefaultFormatting(preface);
			document.add(preface);

			score=result.agreeableness.totalPercentage.intValue();

			facetScoreDesc="";
			if(score>50)
				facetScoreDesc=text.getaHigh();
			else if(score==50)
				facetScoreDesc=text.getaNeutral();
			else
				facetScoreDesc=text.getaLow();

			addFacetData(preface, document, text, score, text.getaDef(), facetScoreDesc, "agreeGraph"+orderId,text.getaInfo());

			// Adding Agreeableness sub facet

			addSubFacet(preface, document, text, result.agreeableness.a1Trust, text.getFacetA1(), text.getA1Info());
			addSubFacet(preface, document, text, result.agreeableness.a2Morality, text.getFacetA2(), text.getA2Info());
			addSubFacet(preface, document, text, result.agreeableness.a3Altruism, text.getFacetA3(), text.getA3Info());
			addSubFacet(preface, document, text, result.agreeableness.a4Cooperation, text.getFacetA4(), text.getA4Info());
			addSubFacet(preface, document, text, result.agreeableness.a5Modesty, text.getFacetA5(), text.getA5Info());
			addSubFacet(preface, document, text, result.agreeableness.a6Sympathy, text.getFacetA6(), text.getA6Info());

			emptyLine=new Paragraph();
			addEmptyLine(emptyLine,8);
			document.add(emptyLine);


			// Conscientiousness Facet block 

			preface = new Paragraph(cons, facetHeading);
			setDefaultFormatting(preface);
			document.add(preface);

			score=result.conscientiousness.totalPercentage.intValue();

			facetScoreDesc="";
			if(score>50)
				facetScoreDesc=text.getcHigh();
			else if(score==50)
				facetScoreDesc=text.getcNeutral();
			else
				facetScoreDesc=text.getcLow();

			addFacetData(preface, document, text, score, text.getcDef(), facetScoreDesc, "conscGraph"+orderId,text.getcInfo());

			// Adding Conscientiousness sub facet

			addSubFacet(preface, document, text, result.conscientiousness.c1SelfEfficacy, text.getFacetC1(), text.getC1Info());
			addSubFacet(preface, document, text, result.conscientiousness.c2Orderliness, text.getFacetC2(), text.getC2Info());
			addSubFacet(preface, document, text, result.conscientiousness.c3Dutifulness, text.getFacetC3(), text.getC3Info());
			addSubFacet(preface, document, text, result.conscientiousness.c4AchievementStriving, text.getFacetC4(), text.getC4Info());
			addSubFacet(preface, document, text, result.conscientiousness.c5SelfDiscipline, text.getFacetC5(), text.getC5Info());
			addSubFacet(preface, document, text, result.conscientiousness.c6Cautiousness, text.getFacetC6(), text.getC6Info());

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

	public void addFacetData(Paragraph preface,Document document,PdfTextData text,int score,String facetDef,String facetScoreDesc,String graphName,String facetInfo) {
		Image barGraph;

		try {
			Paragraph facetData = new Paragraph();
			//addEmptyLine(subFacetData, 1);
			setDefaultFormatting(facetData);
			facetData.add(new Paragraph(facetDef, smallNormal));

			String finalScore=String.valueOf(score)+" [";
			String category=returnScoreCategory(score,text);
			finalScore=finalScore+category+"]";

			facetData.add(new Paragraph(text.getScore()+finalScore, smallBold));
			addEmptyLine(facetData, 1);

			facetData.add(new Paragraph(facetInfo, smallNormal));
			facetData.add(new Paragraph(" "));
			facetData.add(new Paragraph(facetScoreDesc, smallNormalBold));
			facetData.add(new Paragraph(" "));

			document.add(facetData);

			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));
			barGraph = Image.getInstance("src/main/resources/chartImages/"+graphName+".jpeg");
			barGraph.setIndentationLeft(200);
			barGraph.scaleToFit(500f, 700f);
			document.add(barGraph);

		} 
		catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	private void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	public void setDefaultFormatting(Paragraph preface)
	{
		preface.setAlignment(Element.ALIGN_MIDDLE);
		preface.setIndentationLeft(100);
		preface.setIndentationRight(90);
	}

	public String returnScoreCategory(double percent,PdfTextData lang)
	{
		String category="";

		if(percent>=75)
		{
			category=lang.getVeryHigh();
		}
		else if(percent>50 && percent<75)
		{
			category=lang.getHigh();
		}
		else if(percent==50)
		{
			category=lang.getNeutral();
		}
		else if(percent>25 && percent<50)
		{
			category=lang.getLow();
		}
		else
			category=lang.getVeryLow();

		return category;
	}

	public void addSubFacet(Paragraph preface,Document document,PdfTextData text,int score,String facetHeading,String facetDef) {


		try {

			score=(score*100)/20;

			preface = new Paragraph(facetHeading, subFacetHeading);
			setDefaultFormatting(preface);

			document.add(preface);

			Paragraph subFacetData = new Paragraph();
			//addEmptyLine(subFacetData, 1);
			setDefaultFormatting(subFacetData);

			String finalScore=String.valueOf(score)+" [";
			String category=returnScoreCategory(score,text);
			finalScore=finalScore+category+"]";

			subFacetData.add(new Paragraph(text.getScore()+finalScore, subFacetScore));

			addEmptyLine(subFacetData, 1);

			subFacetData.add(new Paragraph(facetDef, smallNormal));

			addEmptyLine(subFacetData, 2);

			document.add(subFacetData);


		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
