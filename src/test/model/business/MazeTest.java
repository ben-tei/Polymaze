package test.model.business;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import exception.model.business.ExceptionContentToString;
import model.business.Cell;
import model.business.Maze;
import model.business.Person;

public class MazeTest {
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void contentToStringTest_00() throws ExceptionContentToString {
		Maze maze = new Maze();
		exception.expect(ExceptionContentToString.class);
		maze.contentToString();
	}

	@Test
	public void contentToStringTest_01() throws ExceptionContentToString {
		Integer width = 1;
		Integer length = 1;
		Maze maze = new Maze("test", length, width, null , new Person());
		Cell[][] content = new Cell[width][length];
		content[0][0] = new Cell(0,0,true,true,true,true);
		maze.setContent(content);
		assertEquals("1111", maze.contentToString());
	}

	@Test
	public void contentToStringTest_02() throws ExceptionContentToString {
		Integer width = 2;
		Integer length = 1;
		Maze maze = new Maze("test", length, width, null , new Person());
		Cell[][] content = new Cell[width][length];
		content[0][0] = new Cell(0,0,true,false,true,true);
		content[1][0] = new Cell(1,0,true,true,true,false);
		maze.setContent(content);
		assertEquals("10111110", maze.contentToString());
	}

	@Test
	public void contentToStringTest_03() throws ExceptionContentToString {
		Integer width = 1;
		Integer length = 2;
		Maze maze = new Maze("test",  length, width, null , new Person());
		Cell[][] content = new Cell[width][length];
		content[0][0] = new Cell(0,0,true,true,false,true);
		content[0][1] = new Cell(0,1,false,true,true,true);
		maze.setContent(content);
		assertEquals("11010111", maze.contentToString());
	}

	@Test
	public void contentToStringTest_04() throws ExceptionContentToString {
		Integer width = 3;
		Integer length = 2;
		Maze maze = new Maze("test",  length, width, null , new Person());
		Cell[][] content = new Cell[width][length];
		content[0][0] = new Cell(0,0,true,false,true,true);
		content[1][0] = new Cell(1,0,true,false,false,false);
		content[2][0] = new Cell(2,0,true,true,true,false);
		content[0][1] = new Cell(0,1,true,false,true,true);
		content[1][1] = new Cell(1,1,false,false,true,false);
		content[2][1] = new Cell(2,1,true,true,true,false);
		maze.setContent(content);
		assertEquals("101110001110101100101110", maze.contentToString());
	}

	@Test
	public void contentToStringTest_05() throws ExceptionContentToString {
		class StubCell extends Cell {
			StubCell(int x, int y, boolean wallNorth, boolean wallEast, boolean wallSouth, boolean wallWest){
				super(x,y, wallNorth, wallEast, wallSouth, wallWest);
			}
			@Override
			public String wallToString() {
				if (this.isWallNorth() == true && this.isWallEast() == true 
						&& this.isWallSouth() == true && this.isWallWest() == true) 
				{
					return "1111";
				}
				return null;	
			}	
		}
		
		Integer width = 1;
		Integer length = 1;
		Maze maze = new Maze("test", length, width, null , new Person());
		StubCell[][] content = new StubCell[width][length];
		content[0][0] = new StubCell(0,0,true,true,true,true);
		maze.setContent(content);
		assertEquals("1111", maze.contentToString());
	}

}
