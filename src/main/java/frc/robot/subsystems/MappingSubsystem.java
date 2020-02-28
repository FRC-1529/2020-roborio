/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import java.lang.Math;

public class MappingSubsystem extends SubsystemBase {
  // Lets define our resources!
  int grid_height = Constants.kMapping_FieldHeight / Constants.kMapping_Resolution;
  int grid_length = Constants.kMapping_FieldLength / Constants.kMapping_Resolution;
  public int map[][] = new int[grid_length][grid_height];
  public int IsEmpty = 0;
  public int IsOccupied = 1;
  public int IsPowerCell = 2;
  public int robot_x;
  public int robot_y;

  //Functions for map coordinate converstions and utils
  int convertCMToPoint(double cm) {
    return (int) Math.round(cm) / Constants.kMapping_Resolution;
  };
  boolean isInCircle(int center_x, int center_y, int radius, int point_x, int point_y) {
    double dist_x = Math.pow((point_x - center_x), 2);
    double dist_y = Math.pow((point_y - center_y), 2);
    double dist = Math.sqrt(dist_x+dist_y);
    return (dist < radius);
  };
  int[] getRobotBoundingBox() {
    
  }

  //Functions for map marking
  void markPoint(int x, int y, int value) {
    map[x][y] = value;
  };
  void markPoint(double x_cm, double y_cm, int value) {
    markPoint(convertCMToPoint(x_cm), convertCMToPoint(y_cm), value);
  };
  void markRect(int x0, int y0, int x1, int y1, int value) {
    for (int x = x0; x < x1; x++) {
      for (int y = y0; y < y1; y++) {
        map[x][y] = value;
      }
    }
  }
  void markRect(double x0_cm, double y0_cm, double x1_cm, double y1_cm, int value) {
    markRect(convertCMToPoint(x0_cm), convertCMToPoint(y0_cm), convertCMToPoint(x1_cm), convertCMToPoint(y1_cm), value);
  }
  void markCircle(int x, int y, int r, int value) {
    //Create a bounding box around the circle;
    int left = x-r;
    int right = x+r;
    int top = y-r;
    int bottom = y+r;
    //Check each point in the bounding box, and mark the point if in the circle;
    for (int t_x = left; t_x < right; t_x++) {
      for (int t_y = top; t_y < bottom; t_y++) {
        if (isInCircle(x, y, r, t_x, t_y) {
          map[t_x][t_y] = value;
        }
      }
    }
  };
  void markCircle(double x_cm, int y_cm, int r_cm, int value) {
    markCircle(convertCMToPoint(x_cm), convertCMToPoint(y_cm), convertCMToPoint(r_cm), value);
  };

  //Functions for pathing on the map;


  //Functions for updating positions
  void updateRobotPosition(x_cm, y_cm) {
    robot_x = convertCMToPoint(x_cm);
    robot_y = convertCMToPoint(y_cm);
  };
  public MappingSubsystem() {
    //Lets go ahead and mark out of bounds areas that are known values;

    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
