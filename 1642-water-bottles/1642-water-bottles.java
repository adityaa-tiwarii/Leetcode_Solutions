class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        // Total bottles drunk, starting with initial full bottles
        int totalBottlesDrunk = numBottles;
      
        // Keep exchanging while we have enough empty bottles
        // Each iteration: exchange empty bottles for 1 new full bottle
        while (numBottles >= numExchange) {
            // Drink one more bottle (increment total)
            totalBottlesDrunk++;
          
            // Exchange numExchange empty bottles for 1 full bottle
            // Net effect: reduce empty bottles by (numExchange - 1)
            numBottles = numBottles - (numExchange - 1);
        }
      
        return totalBottlesDrunk;
    }
}
