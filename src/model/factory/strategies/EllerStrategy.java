package model.factory.strategies;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import model.business.Maze;
import model.business.Person;
import model.business.cell.EllerCell;
import model.factory.MazeFactoryStrategy;
import model.factory.MazeFactoryStrategyName;

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
			if(x < width - 1){
				mazfiyColumn(mazeArray_[x], setToCell);
				joinCellsHorizontally(mazeArray_[x], mazeArray_[x+1], setToCell);
			} else {
				joinDisjointSetsCells(mazeArray_[x]);
			}
		}
		maze_.setContent(mazeArray_);
		
		//drawMaze();
		System.out.println("done");
		
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
			while(shouldMakeVerticalOpening() && i < column.length - 1){
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
		if(column1[0].getPositionX() == 1){
			System.out.println("");
		}
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        
	        ArrayList<EllerCell> cells = ((ArrayList<EllerCell>)pair.getValue());
	        boolean hasAtLeastOneOpening = false;
	        for(int i = 0 ; i < cells.size() ; ++i) {
		        if(shouldMakeHorizontalOpening() || (!hasAtLeastOneOpening && i == cells.size() - 1)){
		        	hasAtLeastOneOpening = true;
		        	column1[cells.get(i).getPositionY()].setWallEast(false);
		        	column2[cells.get(i).getPositionY()].setWallWest(false);
		        	column2[cells.get(i).getPositionY()].setSetID(column1[cells.get(i).getPositionY()].getSetID());
		        }
	        }
	    }
	}
	
	private void joinDisjointSetsCells(EllerCell[] column){
		for(int i = 0 ; i < column.length ; ++i){
			if(i < column.length - 1 && column[i].getSetID() != column[i+1].getSetID()){
				column[i].setWallSouth(false);
				column[i + 1].setWallNorth(false);
				
				ArrayList<Integer> tmpArray = new ArrayList();
				tmpArray.add(column[i].getSetID());
				tmpArray.add(column[i + 1].getSetID());
				int tmpSet = getSmallestNumber(tmpArray);
				
				column[i].setSetID(tmpSet);
				column[i + 1].setSetID(tmpSet);
			}
		}
	}
	
	private Boolean shouldMakeVerticalOpening() {
		Double tmp = Math.random();
		if(tmp < 0.2) {
			return false;
		} else{
			return true;
		} 
	}
	
	private Boolean shouldMakeHorizontalOpening() {
		Double tmp = Math.random();
		if(tmp < 0.8) {
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
	
		public void drawMaze() {
			int outputHeight = 2 * this.maze_.getLength() + 1;
			int outputWidth = 2 * this.maze_.getWidth() + 1;
	
			char[][] outputMaze = new char[outputWidth][outputHeight];
	
			for (int y = 0; y < outputHeight; y++) {
				for (int x = 0; x < outputWidth; x++) {
	
					if (y == 0 || y == outputHeight - 1 || x == 0
							|| x == outputWidth - 1) {
						// border wall
						outputMaze[x][y] = 'M';
					} else if (y % 2 == 0 && x % 2 == 0) {
						// intern wall
						outputMaze[x][y] = 'M';
					}
	
					if (y % 2 == 1 && x % 2 == 1) {
						outputMaze[x][y] = ' ';
						if (this.mazeArray_[(x - 1) / 2][(y - 1) / 2].isWallEast()) {
							outputMaze[x + 1][y] = 'M';
						} else {
							outputMaze[x + 1][y] = ' ';
						}
	
						if (this.mazeArray_[(x - 1) / 2][(y - 1) / 2].isWallSouth()) {
							outputMaze[x][y + 1] = 'M';
						} else {
							outputMaze[x][y + 1] = ' ';
						}
					}
				}
			}
	
			outputMaze[this.maze_.getStartX() * 2 + 1][this.maze_.getStartY() * 2 + 1] = 'S';
			outputMaze[this.maze_.getEndX() * 2 + 1][this.maze_.getEndY() * 2 + 1] = 'E';
	
			// now we can print it.
			for (int y = 0; y < outputHeight; y++) {
				for (int x = 0; x < outputWidth; x++) {
					System.out.print(outputMaze[x][y]);
				}
				System.out.println("");
			}
	
		}
}
