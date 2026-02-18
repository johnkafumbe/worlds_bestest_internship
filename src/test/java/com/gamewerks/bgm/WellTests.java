package com.gamewerks.bgm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import com.gamewerks.bgm.engine.Well;

public class WellTests {
    private static boolean x = true;
    private static boolean o = false;

    @Test
    public void emptyWellTest() {
        boolean[][] grid = {
          {o, o, o, o},
          {o, x, x, o},
          {o, x, x, o},
          {o, o, o, o}
        };
        Well well = new Well(grid);
        assertFalse(well.isCompletedRow(0));
        assertFalse(well.isCompletedRow(1));
        assertFalse(well.isCompletedRow(2));
        assertFalse(well.isCompletedRow(3));
    }


    @Test
    public void singleCompletedRowTest(){
      boolean[][] grid = {
          {x, x, x, x}, // row 0 is complete
          {o, x, x, o},
          {o, x, x, o},
          {o, o, o, o}
      };
      Well well = new Well(grid);
      assertTrue(well.isCompletedRow(0));
      assertFalse(well.isCompletedRow(1));
      assertFalse(well.isCompletedRow(2));
      assertFalse(well.isCompletedRow(3));
    }


     @Test
    public void mutipleCompletedRowTest(){
      boolean[][] grid = {
          {x, x, x, x}, // row 0 is complete
          {x, x, x, x},
          {o, x, x, o},
          {x, x, x, x}
      };
      Well well = new Well(grid);
      assertTrue(well.isCompletedRow(0));
      assertTrue(well.isCompletedRow(1));
      assertFalse(well.isCompletedRow(2));
      assertTrue(well.isCompletedRow(3));
    }

    @Test
    public void deleteTopRowTest(){
      boolean[][] grid = {
          {x, x, x, x}, // row deleted
          {o, x, x, o}, // becomes row 0
          {o, x, x, o},
          {o, o, o, o}
      };

      Well well = new Well(grid);
      well.deleteRows(well.getCompletedRows());

      boolean [][] newGrid = well.getGrid();

        assertEquals(o, newGrid[0][0]);
        assertEquals(x, newGrid[0][1]);
        assertEquals(x, newGrid[0][2]);
        assertEquals(o, newGrid[0][3]);
    }

    @Test
    public void deleteMultipleRowTest(){
      boolean[][] grid = {
          {x, x, x, x}, // row deleted
          {x, x, x, x}, // row deleted
          {o, x, x, o}, // become row 0
          {o, o, o, o}
      };

      Well well = new Well(grid);
      well.deleteRows(well.getCompletedRows());

      boolean [][] newGrid = well.getGrid();

        assertEquals(o, newGrid[0][0]);
        assertEquals(x, newGrid[0][1]);
        assertEquals(x, newGrid[0][2]);
        assertEquals(o, newGrid[0][3]);
    }

    @Test
    public void deleteBottomRowTest(){
      boolean[][] grid = {
          {o, o, o, o}, // row 0 is complete
          {o, x, x, o},
          {o, x, x, o},
          {x, x, x, x}
      };

      Well well = new Well(grid);
      well.deleteRows(well.getCompletedRows());

      boolean [][] newGrid = well.getGrid();

      assertFalse(well.isCompletedRow(3));
    }


}
