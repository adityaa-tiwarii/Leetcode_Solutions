class Solution {
    public int countCoveredBuildings(int n, int[][] buildings) {
        // Map to store all y-coordinates for each x-coordinate
        Map<Integer, List<Integer>> buildingsAtX = new HashMap<>();
        // Map to store all x-coordinates for each y-coordinate
        Map<Integer, List<Integer>> buildingsAtY = new HashMap<>();

        // Group buildings by their x and y coordinates
        for (int[] building : buildings) {
            int xCoord = building[0];
            int yCoord = building[1];
          
            // Add y-coordinate to the list of buildings at this x
            buildingsAtX.computeIfAbsent(xCoord, k -> new ArrayList<>()).add(yCoord);
            // Add x-coordinate to the list of buildings at this y
            buildingsAtY.computeIfAbsent(yCoord, k -> new ArrayList<>()).add(xCoord);
        }

        // Sort all y-coordinates for each x to find min and max efficiently
        for (Map.Entry<Integer, List<Integer>> entry : buildingsAtX.entrySet()) {
            Collections.sort(entry.getValue());
        }
      
        // Sort all x-coordinates for each y to find min and max efficiently
        for (Map.Entry<Integer, List<Integer>> entry : buildingsAtY.entrySet()) {
            Collections.sort(entry.getValue());
        }

        int coveredBuildingCount = 0;

        // Check each building to see if it's covered
        for (int[] building : buildings) {
            int xCoord = building[0];
            int yCoord = building[1];
          
            // Get all buildings on the same vertical line (same x)
            List<Integer> buildingsOnSameX = buildingsAtX.get(xCoord);
            // Get all buildings on the same horizontal line (same y)
            List<Integer> buildingsOnSameY = buildingsAtY.get(yCoord);

            // Check if building is covered:
            // - There must be buildings to the left and right (on same horizontal line)
            // - There must be buildings above and below (on same vertical line)
            boolean hasLeftBuilding = buildingsOnSameY.get(0) < xCoord;
            boolean hasRightBuilding = xCoord < buildingsOnSameY.get(buildingsOnSameY.size() - 1);
            boolean hasBelowBuilding = buildingsOnSameX.get(0) < yCoord;
            boolean hasAboveBuilding = yCoord < buildingsOnSameX.get(buildingsOnSameX.size() - 1);
          
            if (hasLeftBuilding && hasRightBuilding && hasBelowBuilding && hasAboveBuilding) {
                coveredBuildingCount++;
            }
        }

        return coveredBuildingCount;
    }
}
