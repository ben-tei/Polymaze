package test.model.business;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import model.business.Cell;
import model.business.Maze;
import model.business.Person;
import util.exception.model.business.ContentToStringException;

/**
 * test class for class Maze methods tested: - contentToString() -
 * contentFromstring()
 * 
 * @author Loic
 *
 */
public class MazeTest
{

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void constructorTest_01()
	{
		Maze maze = new Maze();
		Cell[][] contentTest = { { new Cell(1, 3) }, { new Cell(1, 2) } };
		java.sql.Date timeNow = new Date(Calendar.getInstance().getTimeInMillis());
		Person creatorTest = new Person(15, "John");

		maze.setId(15);
		assertEquals((Integer) 15, maze.getId());

		maze.setName("Maze");
		assertEquals("Maze", maze.getName());

		maze.setLength(12);
		assertEquals((Integer) 12, maze.getLength());
		maze.setWidth(16);
		assertEquals((Integer) 16, maze.getWidth());

		maze.setStartX(0);
		assertEquals(0, maze.getStartX());
		maze.setStartY(1);
		assertEquals(1, maze.getStartY());
		maze.setEndX(3);
		assertEquals(3, maze.getEndX());
		maze.setEndY(4);
		assertEquals(4, maze.getEndY());

		maze.setStrContent("1011");
		assertEquals("1011", maze.getStrContent());
		maze.setContent(contentTest);
		Assert.assertArrayEquals(contentTest, maze.getContent());

		maze.setCreationDate(timeNow);
		assertEquals(timeNow, maze.getCreationDate());
		maze.setCreator(creatorTest);
		assertEquals(creatorTest, maze.getCreator());

	}

	@Test
	public void constructorTest_02()
	{
		Maze maze = new Maze();
		maze.setWidth(2);
		maze.setLength(1);
		Cell[][] contentTest = { { new Cell(0, 0) }, { new Cell(1, 0, true, true, false, false) } };
		maze.setContent(contentTest);

		assertEquals("11111100", maze.getStrContent());

	}

	@Test
	public void constructorTest_03() throws ContentToStringException
	{
		Maze maze = new Maze();
		maze.setWidth(2);
		maze.setLength(1);
		maze.setStrContent("11111100");

		maze.getContent();
		String strParsed = "FAULT";

		strParsed = maze.contentToString();

		assertEquals("11111100", strParsed);

	}

	@Test
	public void constructorTest_04()
	{
		String name = "Test";
		Integer length = 15;
		Integer width = 16;
		int startX = 1;
		int startY = 0;
		int endX = 11;
		int endY = 10;
		Date creationDate = new Date(Calendar.getInstance().getTimeInMillis());
		Person creator = new Person(15, "Isaaaac");

		Maze maze = new Maze(name, length, width, startX, startY, endX, endY, creationDate, creator);

		assertEquals(name, maze.getName());

		assertEquals(length, maze.getLength());
		assertEquals(width, maze.getWidth());

		assertEquals(startX, maze.getStartX());
		assertEquals(startY, maze.getStartY());
		assertEquals(endX, maze.getEndX());
		assertEquals(endY, maze.getEndY());
		assertEquals(creationDate, maze.getCreationDate());
		assertEquals(creator, maze.getCreator());
	}

	@Test
	public void constructorTest_05()
	{
		Integer id = 165;
		String name = "Test";
		Integer length = 15;
		Integer width = 16;
		int startX = 1;
		int startY = 0;
		int endX = 11;
		int endY = 10;
		String strContent = "1111";
		Date creationDate = new Date(Calendar.getInstance().getTimeInMillis());
		Person creator = new Person(15, "Isaaaac");

		Maze maze = new Maze(id, name, length, width, startX, startY, endX, endY, strContent, creationDate, creator);

		assertEquals(id, maze.getId());
		assertEquals(name, maze.getName());

		assertEquals(length, maze.getLength());
		assertEquals(width, maze.getWidth());

		assertEquals(startX, maze.getStartX());
		assertEquals(startY, maze.getStartY());
		assertEquals(endX, maze.getEndX());
		assertEquals(endY, maze.getEndY());
		assertEquals(strContent, maze.getStrContent());
		assertEquals(creationDate, maze.getCreationDate());
		assertEquals(creator, maze.getCreator());
	}

	@Test
	public void setAllCellToNotVisitedTest_01()
	{
		Maze maze = new Maze();
		maze.setWidth(2);
		maze.setLength(1);
		Cell[][] contentTest = { { new Cell(1, 3) }, { new Cell(1, 2) } };
		contentTest[0][0].setVisited(true);
		contentTest[1][0].setVisited(true);
		maze.setContent(contentTest);

		maze.setAllCellToNotVisited();
		assertEquals(false, contentTest[0][0].isVisited());
		assertEquals(false, contentTest[1][0].isVisited());
	}

	@Test
	public void contentToStringTest_00() throws ContentToStringException
	{
		Maze maze = new Maze();
		exception.expect(ContentToStringException.class);
		maze.contentToString();
	}

	@Test
	public void contentToStringTest_01() throws ContentToStringException
	{
		Integer width = 1;
		Integer length = 1;
		Maze maze = new Maze("test", length, width, null, new Person());
		Cell[][] content = new Cell[width][length];
		content[0][0] = new Cell(0, 0, true, true, true, true);
		maze.setContent(content);
		assertEquals("1111", maze.contentToString());
	}

	@Test
	public void contentToStringTest_02() throws ContentToStringException
	{
		Integer width = 2;
		Integer length = 1;
		Maze maze = new Maze("test", length, width, null, new Person());
		Cell[][] content = new Cell[width][length];
		content[0][0] = new Cell(0, 0, true, false, true, true);
		content[1][0] = new Cell(1, 0, true, true, true, false);
		maze.setContent(content);
		assertEquals("10111110", maze.contentToString());
	}

	@Test
	public void contentToStringTest_03() throws ContentToStringException
	{
		Integer width = 1;
		Integer length = 2;
		Maze maze = new Maze("test", length, width, null, new Person());
		Cell[][] content = new Cell[width][length];
		content[0][0] = new Cell(0, 0, true, true, false, true);
		content[0][1] = new Cell(0, 1, false, true, true, true);
		maze.setContent(content);
		assertEquals("11010111", maze.contentToString());
	}

	@Test
	public void contentToStringTest_04() throws ContentToStringException
	{
		Integer width = 3;
		Integer length = 2;
		Maze maze = new Maze("test", length, width, null, new Person());
		Cell[][] content = new Cell[width][length];
		content[0][0] = new Cell(0, 0, true, false, true, true);
		content[1][0] = new Cell(1, 0, true, false, false, false);
		content[2][0] = new Cell(2, 0, true, true, true, false);
		content[0][1] = new Cell(0, 1, true, false, true, true);
		content[1][1] = new Cell(1, 1, false, false, true, false);
		content[2][1] = new Cell(2, 1, true, true, true, false);
		maze.setContent(content);
		assertEquals("101110001110101100101110", maze.contentToString());
	}

	@Test
	public void contentToStringTest_05() throws ContentToStringException
	{
		class StubCell extends Cell
		{
			StubCell(int x, int y, boolean wallNorth, boolean wallEast, boolean wallSouth, boolean wallWest)
			{
				super(x, y, wallNorth, wallEast, wallSouth, wallWest);
			}

			@Override
			public String wallToString()
			{
				if(this.isWallNorth() == true && this.isWallEast() == true && this.isWallSouth() == true
						&& this.isWallWest() == true)
				{
					return "1111";
				}
				return null;
			}
		}

		Integer width = 1;
		Integer length = 1;
		Maze maze = new Maze("test", length, width, null, new Person());
		StubCell[][] content = new StubCell[width][length];
		content[0][0] = new StubCell(0, 0, true, true, true, true);
		maze.setContent(content);
		assertEquals("1111", maze.contentToString());
	}

	// test contentFromString

	@Test
	public void contentFromStringTest_00() throws Exception
	{ //Maze of size 0 0 
		int width = 0;
		int length = 0;
		Maze maze = new Maze("test", length, width, null, new Person());
		maze.setContent(maze.contentFromString("", width, length));
	}

	@Test
	public void contentFromStringTest_01() throws Exception
	{ // maze with one side equal 0, but string valid (also equal 0)
		int width = 0;
		int length = 1;
		Maze maze = new Maze("test", length, width, null, new Person());
		maze.setContent(maze.contentFromString("", width, length));
	}

	@Test
	public void contentFromStringTest_02() throws Exception
	{ // invalid string size for a maze with one side equal 0
		int width = 0;
		int length = 1;
		Maze maze = new Maze("test", length, width, null, new Person());
		exception.expect(Exception.class);
		maze.setContent(maze.contentFromString("1", width, length));
	}

	@Test
	public void contentFromStringTest_03() throws Exception
	{ // invalid string size for a maze
		int width = 2;
		int length = 1;
		Maze maze = new Maze("test", length, width, null, new Person());
		exception.expect(Exception.class);
		maze.setContent(maze.contentFromString("111100001", width, length));
	}

	@Test
	public void contentFromStringTest_04() throws Exception
	{ // invalid string size for a maze
		int width = 2;
		int length = 2;
		Maze maze = new Maze("test", length, width, null, new Person());
		exception.expect(Exception.class);
		maze.setContent(maze.contentFromString("111100001", width, length));
	}

	@Test
	public void contentFromStringTest_05() throws Exception
	{
		int width = 2;
		int length = 1;
		Maze maze = new Maze("test", length, width, null, new Person());
		maze.setContent(maze.contentFromString("10111110", width, length));
		assertEquals(true, maze.getContent()[0][0].isWallNorth());
		assertEquals(false, maze.getContent()[0][0].isWallEast());
		assertEquals(true, maze.getContent()[0][0].isWallSouth());
		assertEquals(true, maze.getContent()[0][0].isWallWest());
		assertEquals(true, maze.getContent()[1][0].isWallNorth());
		assertEquals(true, maze.getContent()[1][0].isWallEast());
		assertEquals(true, maze.getContent()[1][0].isWallSouth());
		assertEquals(false, maze.getContent()[1][0].isWallWest());
	}

	@Test
	public void contentFromStringTest_06() throws Exception
	{
		int width = 2;
		int length = 2;
		Maze maze = new Maze("test", length, width, null, new Person());
		maze.setContent(maze.contentFromString("1001111000111110", width, length));
		assertEquals(true, maze.getContent()[0][0].isWallNorth());
		assertEquals(false, maze.getContent()[0][0].isWallEast());
		assertEquals(false, maze.getContent()[0][0].isWallSouth());
		assertEquals(true, maze.getContent()[0][0].isWallWest());

		assertEquals(true, maze.getContent()[1][0].isWallNorth());
		assertEquals(true, maze.getContent()[1][0].isWallEast());
		assertEquals(true, maze.getContent()[1][0].isWallSouth());
		assertEquals(false, maze.getContent()[1][0].isWallWest());

		assertEquals(false, maze.getContent()[0][1].isWallNorth());
		assertEquals(false, maze.getContent()[0][1].isWallEast());
		assertEquals(true, maze.getContent()[0][1].isWallSouth());
		assertEquals(true, maze.getContent()[0][1].isWallWest());

		assertEquals(true, maze.getContent()[1][1].isWallNorth());
		assertEquals(true, maze.getContent()[1][1].isWallEast());
		assertEquals(true, maze.getContent()[1][1].isWallSouth());
		assertEquals(false, maze.getContent()[1][1].isWallWest());
	}

}
