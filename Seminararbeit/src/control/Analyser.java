package control;

import java.util.*;

import model.Group;
import model.Team;



public class Analyser {

	public static int calculateNoOfRounds(int noOfTeams){
		return (int) Math.ceil((Math.log(noOfTeams) / Math.log(2)));
	}

	public static HashSet<Group> createGroups(Collection<Team> contestantsInGame, int groupSize) {
		
		int noOfGroups = (int) Math.ceil(contestantsInGame.size()/groupSize);
		int noOfBots = (noOfGroups * groupSize) - contestantsInGame.size();
		
		
		HashSet<Group> groups = new HashSet<Group>();
		
		for(int i = 1; i <= groupSize; i++)
		{
			//TODO add distribution of teams into groups
		}
		return null;
	}

	public static int calculateNoOfShotsPerMatch(int noOfTeams, int shots) {
		int noOfMatches = 0;
		int noOfRounds = calculateNoOfRounds(noOfTeams);
		for(int i=0; i<noOfRounds-1; i++) //count matches in each round and add
		{
			noOfMatches += Math.pow(2, i);
		}
		noOfMatches += (noOfTeams + noOfTeams%2)/2;
		int noOfShots = (int) shots/noOfMatches;
		if(noOfShots%2 != 0)
		{
			--noOfShots;
		}
		
		return noOfShots;
	}
	
	/**
	 * Generates a random triple of strengths in an array of integers. All three values 
	 * are element of the interval [40,80]. The sum of the three values equals exactly 180.
	 * @return Triple of Strengths
	 */
	public static int[] generateStrength(int lowerBound, int upperBound, int strengthSum)
	{
		int[] strength = new int[3];
		int testSum  = 0;
		
		for(int i = 0; i < strength.length; i++) //generate random values between Bounds
		{
			int thisSide;
			thisSide = lowerBound + (int) (Math.random() * (upperBound - lowerBound + 1));
			strength[i] = thisSide;
			testSum += thisSide;
		}
		int dif = strengthSum - testSum; //calculate difference to correct so strengthSum is met
		int changer = 0;
		if(dif != 0)
		{
		changer = dif / Math.abs(dif);
		}
		while(dif != 0){ //add the difference on the strengths randomly and assure Bounds
			int correctedSide = (int) (Math.random() * 3);
			if((strength[correctedSide] + changer) >= lowerBound && (strength[correctedSide] + changer) <= upperBound)
			{
				strength[correctedSide] += changer;
				dif -= changer;
			}
		}
		
		return strength;
	}
}
