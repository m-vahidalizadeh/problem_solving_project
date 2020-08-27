package leetcode.companies.google;

import java.util.HashSet;
import java.util.Set;

/**
 * Robot Room Cleaner
 * Given a robot cleaner in a room modeled as a grid.
 * <p>
 * Each cell in the grid can be empty or blocked.
 * <p>
 * The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.
 * <p>
 * When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.
 * <p>
 * Design an algorithm to clean the entire room using only the 4 given APIs shown below.
 * <p>
 * interface Robot {
 * // returns true if next cell is open and robot moves into the cell.
 * // returns false if next cell is obstacle and robot stays on the current cell.
 * boolean move();
 * <p>
 * // Robot will stay on the same cell after calling turnLeft/turnRight.
 * // Each turn will be 90 degrees.
 * void turnLeft();
 * void turnRight();
 * <p>
 * // Clean the current cell.
 * void clean();
 * }
 * Example:
 * <p>
 * Input:
 * room = [
 * [1,1,1,1,1,0,1,1],
 * [1,1,1,1,1,0,1,1],
 * [1,0,1,1,1,1,1,1],
 * [0,0,0,1,0,0,0,0],
 * [1,1,1,1,1,1,1,1]
 * ],
 * row = 1,
 * col = 3
 * <p>
 * Explanation:
 * All grids in the room are marked by either 0 or 1.
 * 0 means the cell is blocked, while 1 means the cell is accessible.
 * The robot initially starts at the position of row=1, col=3.
 * From the top left corner, its position is one row below and three columns right.
 * Notes:
 * <p>
 * The input is only given to initialize the room and the robot's position internally. You must solve this problem "blindfolded". In other words, you must control the robot using only the mentioned 4 APIs, without knowing the room layout and the initial robot's position.
 * The robot's initial position will always be in an accessible cell.
 * The initial direction of the robot will be facing up.
 * All accessible cells are connected, which means the all cells marked as 1 will be accessible by the robot.
 * Assume all four edges of the grid are all surrounded by wall.
 */
public class CleanerRobot {

    interface Robot {
        // Returns true if the cell in front is open and robot moves into the cell.
        // Returns false if the cell in front is blocked and robot stays in the current cell.
        public boolean move();

        // Robot will stay in the same cell after calling turnLeft/turnRight.
        // Each turn will be 90 degrees.
        public void turnLeft();

        public void turnRight();

        // Clean the current cell.
        public void clean();
    }

    public void cleanRoom(Robot robot) {
        dfs(robot, 0, 0, 0, new HashSet<>());
    }

    private void dfs(Robot robot, int x, int y, int dir, Set<String> visited) {
        String position = x + ":" + y;
        if (visited.contains(position)) return;
        robot.clean();
        visited.add(position);
        // Explore all four directions up: 0, right: 90, down: 180, left: 270.
        for (int i = 0; i < 4; i++) {
            if (robot.move()) {
                int newX = x;
                int newY = y;
                switch (dir) {
                    case 0 -> newY--;
                    case 90 -> newX++;
                    case 180 -> newY++;
                    default -> newX--;
                }
                dfs(robot, newX, newY, dir, visited);
                // Go back to the previous block.
                stepBack(robot);
            }
            // Rotate 90 degrees clockwise.
            robot.turnRight();
            dir = (dir + 90) % 360;
        }
    }

    private void stepBack(Robot robot) {
        robot.turnLeft();
        robot.turnLeft();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }

}
