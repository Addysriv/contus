package com.addy.contus.entity;

public class TestResult {
	
	public BigFiveNeuroticism 			neuroticism;
	
	public BigFiveExtraversion      	extraversion;
	
	public BigFiveOpenness 				openness;
	
	public BigFiveAgreeableness 		agreeableness;
	
	public BigFiveConscientiousness 	conscientiousness;
	
	public Integer						totalScore;
	
	public String toString() {
		
		return "%%%%%% "+neuroticism.toString()+extraversion.toString()+openness.toString()+agreeableness.toString()+conscientiousness.toString()+"  Total Score - "+totalScore;
		
		
	}

}
