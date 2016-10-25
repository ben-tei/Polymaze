package model.factory.strategies;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import model.business.Cell;
import model.business.Maze;
import model.business.Person;
import model.business.cell.BacktrackCell;
import model.business.cell.EllerCell;
import model.factory.MazeFactoryStrategy;
import model.factory.MazeFactoryStrategyName;
import util.exception.model.business.ContentToStringException;

public class EllerStrategy extends MazeFactoryStrategy
{
	MazeFactoryStrategyName NAME = MazeFactoryStrategyName.Eller;
	private Maze maze_;
	private EllerCell[][] mazeArray_;
	
	private void initMazeContent(Integer length, Integer width){
		mazeArray_ = new EllerCell[width][length];
		Integer setID = 0;
		for(int y = 0 ; y < length; ++y){
			for(int x = 0 ; x < width ; ++x){
				mazeArray_[x][y] = new EllerCell(x,y,setID);
				mazeArray_[x][y].setSetID(setID);
				setID++;
			}
		}
	}
	
	

	/**
	 * Method to create a Maze using Eller's algorithm.
	 * 
	 * @param name
	 *            the Maze's name
	 * @param length
	 *            the Maze's length
	 * @param width
	 *            the Maze's width
	 * @param creator
	 *            the Person who created the Maze
	 */
	@Override
	public Maze generateMaze(String name, Integer length, Integer width, Person creator) {
		java.sql.Date timeNow = new Date(Calendar.getInstance().getTimeInMillis());
		maze_ = new Maze(name, length, width, timeNow, creator);
		initMazeContent(length, width);
		for(int x = 0 ; x < width; ++x){
			Map<Integer,ArrayList<EllerCell>> setToCell = new HashMap<Integer,ArrayList<EllerCell>>(); // create the set
			mazfiyColumn(mazeArray_[x], setToCell);
			if(x < width - 1){
				joinCellsHorizontally(mazeArray_[x], mazeArray_[x+1], setToCell);
			} else {
				joinDisjointSetsCells(mazeArray_[x]);
			}
		}
		maze_.setContent(mazeArray_);
		
		return maze_;
	}
	
	/**
	 * Method to create a Maze using Eller's algorithm.
	 * 
	 * @param name
	 *            the Maze's name
	 * @param length
	 *            the Maze's length
	 * @param width
	 *            the Maze's width
	 * @param startX
	 *            the Maze's starting point coordinate in X
	 * @param startY
	 *            the Maze's starting point coordinate in Y
	 * @param endX
	 *            the Maze's ending point coordinate in X
	 * @param endY
	 *            the Maze's ending point coordinate in Y
	 * @param creator
	 *            the Person who created the Maze
	 */
	@Override
	public Maze generateMazeWithStartEnd(String name, Integer length, Integer width, int startX, int startY, int endX, int endY,
			Person creator) {
		// TODO EllerStrategy Auto-generated method stub
		return null;
	}
	
	private void mazfiyColumn(EllerCell[] column, Map<Integer,ArrayList<EllerCell>> setToCell){
		int startIndex = -2;
		ArrayList<Integer> cellsToJoinSets = new ArrayList();
		for(int i = 0 ; i < column.length ; ++i){
			startIndex = i;
			cellsToJoinSets.add(column[i].getSetID());
			while(trueOrFalse() && i < column.length - 1){
				i++;
				if(getSmallestNumber(cellsToJoinSets) == column[i].getSetID()){
					i--;
					break;
				}
				cellsToJoinSets.add(column[i].getSetID());
			}
			joinCellsVertically(startIndex,i, column,getSmallestNumber(cellsToJoinSets), setToCell);
			cellsToJoinSets.clear();
		}
	}
	
	private void joinCellsVertically(int startIndex, int endIndex, EllerCell[] column, Integer setNumber, Map<Integer,ArrayList<EllerCell>> setToCell){
		if(!setToCell.containsKey(setNumber)){
			setToCell.put(setNumber, new ArrayList<EllerCell>());
		}
		ArrayList<EllerCell> currentSet = setToCell.get(setNumber);
		if(startIndex < endIndex){
			for(int i = startIndex ; i < endIndex; ++i) {
				column[i].setSetID(setNumber);
				column[i + 1].setSetID(setNumber);
				column[i].setWallSouth(false);
				column[i + 1].setWallNorth(false);
				if(i == startIndex){
					currentSet.add(column[i]);
				}
				currentSet.add(column[i + 1]);
			}
		} else {
			currentSet.add(column[startIndex]);
		}
	}
	
	private void joinCellsHorizontally(EllerCell[] column1,EllerCell[] column2,  Map<Integer,ArrayList<EllerCell>> setToCell){
		Iterator it = setToCell.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        
	        ArrayList<EllerCell> cells = ((ArrayList<EllerCell>)pair.getValue());
	        for(int i = 0 ; i < cells.size() ; ++i) {
	        	boolean hasAtLeastOneOpening = false;
	        	for(int j = 0 ; j < cells.size() ; ++j) {
		        	if(trueOrFalse() || (!hasAtLeastOneOpening && j == cells.size() - 1)){
		        		hasAtLeastOneOpening = true;
		        		column1[cells.get(j).getPositionY()].setWallEast(false);
		        		column2[cells.get(j).getPositionY()].setWallWest(false);
		        		column2[cells.get(j).getPositionY()].setSetID(cells.get(j).getSetID());
		        	}
		        }
	        }
	    }
	}
	
	private void joinDisjointSetsCells(EllerCell[] column){
		for(int i = 0 ; i < column.length ; ++i){
			if(i < column.length - 1 && column[i].getSetID() != column[i+1].getSetID()){
				column[i].setWallSouth(false);
				column[i + 1].setWallNorth(false);
			}
		}
	}
	
	private Boolean trueOrFalse() {
		Double tmp = Math.random();
		if(tmp < 0.5) {
			return false;
		} else{
			return true;
		}
	}
	
	private int getSmallestNumber(ArrayList<Integer> list){
		Integer smallest = Integer.MAX_VALUE;
		for(int i = 0 ; i < list.size() ; ++i) {
			if(list.get(i) < smallest){
				smallest = list.get(i);
			}
		}
		return smallest;
	}
}
