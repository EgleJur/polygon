# 3D Polygon Generator

## Overview
This project provides a solution for generating a 3D polygon from a 2D footprint (polygon) on the XY plane, along with slope and azimuth angles. The resulting 3D polygon is oriented based on the provided azimuth angle and tilted according to the slope angle.

## How It Works
The solution operates as follows:

### Input Parameters
The user provides a 2D polygon (such as a rectangle) on the XY plane, a slope angle (the angle between the XY plane and the output polygon), and an azimuth angle (the direction towards which the output polygon should be oriented).

### Height Adjustment
The height of each point in the input polygon is adjusted based on the slope angle. If the azimuth angle is 0 or 180 degrees, the height adjustment is applied to the y-coordinates of points, while for azimuth angles of 90 or 270 degrees, it's applied to the x-coordinates.

### Rotation
If the azimuth angle is not one of the specified values (0, 90, 180, 270 degrees), the input polygon is rotated accordingly using basic trigonometric functions to calculate the new x and y coordinates of each point.

### Output
The resulting 3D polygon is then returned, with each point having its adjusted height and, if necessary, rotated to match the specified azimuth angle.

## Likes and Dislikes
### Likes
- **Simplicity**: The solution provides a straightforward approach to generating the 3D polygon, using basic geometry and trigonometry.
- **Flexibility**: It can handle various input scenarios, including different azimuth and slope angles.

### Dislikes
- **Limited Validation**: The solution assumes valid input parameters and does not include extensive error handling or input validation.
- **Hardcoded Azimuth Conditions**: The conditional statements for adjusting heights based on the azimuth angle could be generalized for better maintainability.

