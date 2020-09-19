package com.addy.contus.utility;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Stroke;
import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleInsets;

import com.addy.contus.entity.BigFiveAgreeableness;
import com.addy.contus.entity.BigFiveConscientiousness;
import com.addy.contus.entity.BigFiveExtraversion;
import com.addy.contus.entity.BigFiveNeuroticism;
import com.addy.contus.entity.BigFiveOpenness;
import com.addy.contus.entity.PdfTextData;
import com.addy.contus.entity.PdfTextDataEnglish;
import com.addy.contus.entity.PdfTextDataSwedish;
import com.addy.contus.entity.TestResult;



public class CreateGraphAsImage {
	
	private static final Logger logger = Logger.getLogger(CreateGraphAsImage.class);
	
	Color color = new Color(255, 136, 42);
	Color lightColor=new Color(255, 220, 186);

	public static void main(String[] args) {
		
		CreateGraphAsImage createImageTest=new CreateGraphAsImage();
		
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
		
		
		createImageTest.createGraphImage("addyPdf",result,"english");
		
		
		
	}
	
	
	public boolean createGraphImage(String orderId,TestResult result,String language) {
		
		boolean graphCreated=true;
		
		PdfTextData text;
		
		if(language.equals("sv"))
			text=new PdfTextDataSwedish();
		else
			text=new PdfTextDataEnglish();
		
		String labelForBar="  Facets according to big five traits ";
		 DefaultCategoryDataset dataset = new DefaultCategoryDataset( );

		// Prining image in graph as integer percentage rounded up
		
		dataset.addValue( result.neuroticism.totalPercentage.intValue() , labelForBar ,"Neuroticism"  );
		dataset.addValue( result.extraversion.totalPercentage.intValue() , labelForBar ,"Extraversion" );
		dataset.addValue( result.openness.totalPercentage.intValue() , labelForBar , "Openness" );
		dataset.addValue( result.agreeableness.totalPercentage.intValue() , labelForBar ,  "Agreeableness" );
		dataset.addValue( result.conscientiousness.totalPercentage.intValue() , labelForBar , "Conscientiousness" );


		JFreeChart barChart = ChartFactory.createBarChart("","", "",dataset,PlotOrientation.VERTICAL,false, false, false);
		
		barChart.setBorderVisible(false);
		barChart.setBackgroundPaint(Color.white);

		final CategoryPlot plot = barChart.getCategoryPlot();
		plot.setAxisOffset(new RectangleInsets(0, 0, 3, 0)); // to stick graph to the axis
		
		final CategoryItemRenderer renderer = plot.getRenderer();
		renderer.setSeriesItemLabelsVisible(0, Boolean.TRUE);
		renderer.setSeriesPaint(0, color);

		((BarRenderer) renderer).setBarPainter(new StandardBarPainter());
		
		Stroke solid = new BasicStroke(6);
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setLowerMargin(0);
		
		ValueAxis valueAxis = plot.getRangeAxis();
		valueAxis.setTickLabelsVisible(false);
		valueAxis.setTickMarkOutsideLength(0); // longer tick lines
		valueAxis.setAxisLinePaint(lightColor); // X and Y axis line color
		valueAxis.setTickMarkPaint(lightColor); // tiny dashes sticking out every Nth value on Y axis
		valueAxis.setAxisLineStroke(solid);
		valueAxis.setTickMarkStroke(solid);
		valueAxis.setTickMarksVisible(false);
		
		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setAxisLineStroke(solid);
		domainAxis.setTickMarkStroke(solid);
		domainAxis.setAxisLinePaint(lightColor); // X and Y axis line color
		domainAxis.setTickMarkPaint(lightColor);
		domainAxis.setTickMarkOutsideLength(0); 
		domainAxis.setCategoryLabelPositionOffset(20);
		domainAxis.setTickLabelFont(new Font("AvenirNext", Font.PLAIN, 9));
		
		plot.getRenderer().setSeriesItemLabelGenerator(0, new StandardCategoryItemLabelGenerator());  // to add values on top of graphs

		  CategoryPlot categoryPlot = barChart.getCategoryPlot();
	       BarRenderer rendererBar = (BarRenderer) categoryPlot.getRenderer();
	       Font font = new Font("AvenirNext", Font.PLAIN, 12);
	       
	       rendererBar.setBaseItemLabelFont(font); 
	       rendererBar.setSeriesItemLabelFont(0, font);
	       
		plot.setBackgroundPaint(Color.white);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);
		plot.setOutlineVisible(false);
		plot.setDrawSharedDomainAxis(false);

		int width = 740;    /* Width of the image */
		int height = 450;   /* Height of the image */ 

		File BarChart = new File( "src/main/resources/chartImages/BarChart"+orderId+".jpeg" ); 

		try {
			ChartUtilities.saveChartAsJPEG( BarChart , barChart , width , height );
		} catch (IOException e) {
			logger.error("!!!!!!!!!! Error occured in creating and saving graph !!!!!!!! \n");
			graphCreated=false;
			logger.error(e);
			e.printStackTrace();
		}
		
		if(createSubFacetGraph("neuroGraph"+orderId,text.getFacetN1(),text.getFacetN2(),text.getFacetN3(),text.getFacetN4(),text.getFacetN5(),text.getFacetN6(),
				result.neuroticism.n1Anxiety,result.neuroticism.n2Anger,result.neuroticism.n3Depression,
				result.neuroticism.n4SelfConsciousness,result.neuroticism.n5Immoderation,result.neuroticism.n6Vulnerability))
			logger.info("!!!!!!!!!!! Graph - neuroGraph"+orderId+" created successfully . \n");
		else 
			logger.error("!!!!!!!!!!! Graph - neuroGraph"+orderId+" not created  . \n");
		
		if(createSubFacetGraph("extraGraph"+orderId,text.getFacetE1(),text.getFacetE2(),text.getFacetE3(),text.getFacetE4(),text.getFacetE5(),text.getFacetE6(),
				result.extraversion.e1Friendliness,result.extraversion.e2Gregariousness,
				result.extraversion.e3Assertiveness,result.extraversion.e4ActivityLevel,result.extraversion.e5ExcitementSeeking,result.extraversion.e6Cheerfulness))
			logger.info("!!!!!!!!!!! Graph - extraGraph"+orderId+" created successfully . \n");
		else 
			logger.error("!!!!!!!!!!! Graph - extraGraph"+orderId+" not created  . \n");
		
		if(createSubFacetGraph("openGraph"+orderId,text.getFacetO1(),text.getFacetO2(),text.getFacetO3(),text.getFacetO4(),text.getFacetO5(),text.getFacetO6(),
				result.openness.o1Imagination,result.openness.o2ArtisticInterests,result.openness.o3Emotionality,
				result.openness.o4Adventurousness,result.openness.o5Intellect,result.openness.o6Liberalism))
			logger.info("!!!!!!!!!!! Graph - openGraph"+orderId+" created successfully . \n");
		else 
			logger.error("!!!!!!!!!!! Graph - openGraph"+orderId+" not created  . \n");
		
		
		if(createSubFacetGraph("agreeGraph"+orderId,text.getFacetA1(),text.getFacetA2(),text.getFacetA3(),text.getFacetA4(),text.getFacetA5(),text.getFacetA6(),
				result.agreeableness.a1Trust,result.agreeableness.a2Morality,result.agreeableness.a3Altruism,
				result.agreeableness.a4Cooperation,result.agreeableness.a5Modesty,result.agreeableness.a6Sympathy))
			logger.info("!!!!!!!!!!! Graph - agreeGraph"+orderId+" created successfully . \n");
		else 
			logger.error("!!!!!!!!!!! Graph - agreeGraph"+orderId+" not created  . \n");
		
		
		if(createSubFacetGraph("conscGraph"+orderId,text.getFacetC1(),text.getFacetC2(),text.getFacetC3(),text.getFacetC4(),text.getFacetC5(),text.getFacetC6(),
				result.conscientiousness.c1SelfEfficacy,result.conscientiousness.c2Orderliness,
				result.conscientiousness.c3Dutifulness,result.conscientiousness.c4AchievementStriving,result.conscientiousness.c5SelfDiscipline,
				result.conscientiousness.c6Cautiousness))
			logger.info("!!!!!!!!!!! Graph - conscGraph"+orderId+" created successfully . \n");
		else 
			logger.error("!!!!!!!!!!! Graph - conscGraph"+orderId+" not created  . \n");
		
		
		return graphCreated;
	
	}
	
	
	public boolean createSubFacetGraph(String fileName,String label1,String label2,String label3,String label4,String label5,String label6,
								int value1,int value2,int value3,int value4,int value5,int value6) {

		
		boolean graphCreated=true;

		 DefaultCategoryDataset dataset = new DefaultCategoryDataset( );

		dataset.addValue( (value1*100)/20 , "" , label1 );
		dataset.addValue( (value2*100)/20 , "" , label2 );
		dataset.addValue( (value3*100)/20 , "" , label3 );
		dataset.addValue( (value4*100)/20 , "" , label4 );
		dataset.addValue( (value5*100)/20 , "" , label5 );
		dataset.addValue( (value6*100)/20 , "" , label6 );

		Color color = new Color(255, 136, 42);
		Color lightColor=new Color(255, 220, 186);

		JFreeChart barChart = ChartFactory.createBarChart(
				"", 
				"", "", 
				dataset,PlotOrientation.VERTICAL, 
				false, false, false);
		barChart.setBorderVisible(false);
		barChart.setBackgroundPaint(Color.white);

		final CategoryPlot plot = barChart.getCategoryPlot();
		plot.setAxisOffset(new RectangleInsets(0, 0, 3, 0)); // to stick graph to the axis
		
		final CategoryItemRenderer renderer = plot.getRenderer();
		renderer.setSeriesItemLabelsVisible(0, Boolean.TRUE);
		renderer.setSeriesPaint(0, color);
		renderer.setSeriesPaint(1, lightColor);
		renderer.getItemPaint(0, 1);
		
		((BarRenderer) renderer).setBarPainter(new StandardBarPainter());
		
		Stroke solid = new BasicStroke(8);
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setLowerMargin(0);
		
		ValueAxis valueAxis = plot.getRangeAxis();
		valueAxis.setTickLabelsVisible(false);
		valueAxis.setTickMarkOutsideLength(0); // longer tick lines
		valueAxis.setAxisLinePaint(lightColor); // X and Y axis line color
		valueAxis.setTickMarkPaint(lightColor); // tiny dashes sticking out every Nth value on Y axis
		valueAxis.setAxisLineStroke(solid);
		valueAxis.setTickMarkStroke(solid);
		valueAxis.setTickMarksVisible(false);
		
		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setAxisLineStroke(solid);
		domainAxis.setTickMarkStroke(solid);
		domainAxis.setAxisLinePaint(lightColor); // X and Y axis line color
		domainAxis.setTickMarkPaint(lightColor);
		domainAxis.setTickMarkOutsideLength(0); 
		domainAxis.setCategoryLabelPositionOffset(20);
		domainAxis.setTickLabelFont(new Font("AvenirNext", Font.PLAIN, 10));
		
		plot.getRenderer().setSeriesItemLabelGenerator(0, new StandardCategoryItemLabelGenerator());  // to add values on top of graphs

		  CategoryPlot categoryPlot = barChart.getCategoryPlot();
	       BarRenderer rendererBar = (BarRenderer) categoryPlot.getRenderer();
	       Font font = new Font("AvenirNext", Font.PLAIN, 14);
	       
	       rendererBar.setBaseItemLabelFont(font); 
	       rendererBar.setSeriesItemLabelFont(0, font);
	       
		plot.setBackgroundPaint(Color.white);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);
		plot.setOutlineVisible(false);
		plot.setDrawSharedDomainAxis(false);

		int width = 740;    /* Width of the image */
		int height = 400;   /* Height of the image */ 

		File BarChart = new File( "src/main/resources/chartImages/"+fileName+".jpeg" ); 

		try {
			ChartUtilities.saveChartAsJPEG( BarChart , barChart , width , height );
		} catch (IOException e) {
			logger.error("!!!!!!!!!! Error occured in creating and saving graph !!!!!!!! \n");
			graphCreated=false;
			logger.error(e);
			e.printStackTrace();
		}

		return graphCreated;
	
	
	}

}
