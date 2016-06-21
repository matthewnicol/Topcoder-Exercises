package ellyscandygame;

/* https://community.topcoder.com/tc?module=ProblemDetail&rd=15839&pm=12394

    Two players play a game, each picks a chest full of sweets then adds the quantity
    of sweets to their score, the chest on either side of the one they picked has the
    quantity of sweets within doubled.

    Elly always goes first.
    
    Given an array of n chests of sweets, find out who will win the game, assuming
    both players play the best move at all times. Diary at the bottom.
*/

public class EllysCandyGame {
    
    /* findScore 
        For each available chest, call findScore() recursively and then compare
        all of the results to see which works out best at this position. */
    private int[] findScore(int[] chests, int[] scores, int turn) {
        int[] bestScores = null;
        for (int i = 0; i < chests.length; i++) {
            if (chests[i] > 0) {
                int[] newChests = chests.clone();
                int[] newScores = scores.clone();
                newScores[turn] += newChests[i];
                newChests[i] = 0;
                try { newChests[i-1] *= 2; } catch (Exception e) {}
                try { newChests[i+1] *= 2; } catch (Exception e) {}
                
                if (bestScores == null) 
                    bestScores = findScore(newChests, newScores, (turn+1)%2);
                else {
                    int[] tempScores = findScore(newChests, newScores, (turn+1)%2);
                    if (tempScores[turn] - tempScores[(turn+1)%2] > 
                            bestScores[turn] - bestScores[(turn+1)%2]) 
                        bestScores = tempScores;
                }
                
            }
        }
        if (bestScores == null) return scores;
        else return bestScores;
    }
    
    /* getWinner
        Call findScore at the very top of the tree, and compare the scores
        returned to figure out whether it was a draw, Elly won, or Kris won. */
    public String getWinner(int[] chests) {
        int[] finalScores = findScore(chests, new int[]{0,0}, 0);
        String scoreText = String.format(("%4d"), finalScores[0]) + " " + String.format(("%4d"), finalScores[1]);
        if (finalScores[0] == finalScores[1])
            return "Draw"; // + scoreText;
        if (finalScores[0] > finalScores[1])
            return "Elly"; // + scoreText;
        else
            return "Kris"; //+ scoreText;
    }
    
    /* test
        Rough function to run a known case and test whether the code is working*/
    public static void test(int[] input, String expected) {
        String scorestring = "";
        for (int a : input) scorestring += String.format("%4d", a) + " ";
        scorestring += "\n";
        
        EllysCandyGame obj = new EllysCandyGame();
        String result = obj.getWinner(input);
        
        if (result.equals(expected)) System.out.print("PASS\t");
        else System.out.print("FAIL\t");
        System.out.print("Result: " + result); System.out.print("\tExpected: " + expected);
        System.out.print("\tInput: ");
        System.out.print(scorestring);
        
    }
    
    /* tests
        The testing data in the problem statement*/
    public static void tests() {
        test(new int[]{20, 50, 70, 0, 30}, "Kris");
        test(new int[]{42, 13, 7}, "Elly");
        test(new int[]{10, 20}, "Draw");
        test(new int[]{3, 1, 7, 11, 1, 1}, "Kris");
        test(new int[]{41, 449, 328, 474, 150, 501, 467, 329, 536, 440}, "Kris");
        test(new int[]{177, 131, 142, 171, 411, 391, 17, 222, 100, 298}, "Elly");
    }
    /* tests2
        The more rigorous extended testing data*/
    public static void tests2() {
     	test(new int[]{20, 50, 70, 0, 30}, "Kris"); 	
	test(new int[]{42, 13, 7}, "Elly"); 	
	test(new int[]{10, 20}, "Draw"); 	
	test(new int[]{3, 1, 7, 11, 1, 1}, "Kris"); 	
	test(new int[]{41, 449, 328, 474, 150, 501, 467, 329, 536, 440}, "Kris"); 	
	test(new int[]{177, 131, 142, 171, 411, 391, 17, 222, 100, 298}, "Elly"); 	
	test(new int[]{0, 2, 4}, "Draw"); 	
	test(new int[]{0, 2, 4, 0, 2, 4, 0, 2, 4}, "Draw"); 	
	test(new int[]{1000, 1, 1000}, "Elly"); 	
	test(new int[]{1, 1000, 1000}, "Elly"); 	
	test(new int[]{1, 1000, 1000, 1, 1000, 1000, 1, 1000, 1000}, "Elly"); 	
	test(new int[]{0}, "Draw"); 	
	test(new int[]{666}, "Elly"); 	
	test(new int[]{1000, 1, 1000, 0, 1000, 1, 1000}, "Draw"); 	
	test(new int[]{1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000}, "Elly"); 	
	test(new int[]{1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000}, "Kris"); 	
	test(new int[]{0, 0, 0, 0, 0, 0}, "Draw"); 	
	test(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0}, "Draw"); 	
	test(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, "Draw"); 	
	test(new int[]{1, 2, 4, 8, 16, 8, 4, 2, 1}, "Elly"); 	
	test(new int[]{1, 2, 4, 8, 16, 16, 8, 4, 2, 1}, "Kris"); 	
	test(new int[]{0, 1, 2, 4, 8, 8, 4, 2, 1, 0}, "Kris"); 	
	test(new int[]{16, 8, 4, 2, 1, 2, 4, 8, 16}, "Elly"); 	
	test(new int[]{16, 8, 4, 2, 1, 1, 2, 4, 8, 16}, "Kris"); 	
	test(new int[]{8, 4, 2, 1, 0, 0, 1, 2, 4, 8}, "Draw"); 	
	test(new int[]{511, 256, 257, 258, 259, 260, 261, 262, 263}, "Elly"); 	
	test(new int[]{511, 256, 257, 258, 259, 260, 261, 262, 263, 264}, "Kris"); 	
	test(new int[]{263, 262, 261, 260, 259, 258, 257, 256, 511}, "Elly"); 	
	test(new int[]{264, 263, 262, 261, 260, 259, 258, 257, 256, 511}, "Kris"); 	
	test(new int[]{449, 328}, "Kris"); 	
	test(new int[]{150}, "Elly"); 	
	test(new int[]{467, 329, 936, 440, 700}, "Elly"); 	
	test(new int[]{258, 811, 952, 491, 993, 931}, "Kris"); 	
	test(new int[]{431, 359, 590, 899, 153, 292, 370, 404}, "Kris"); 	
	test(new int[]{699, 876, 442, 705, 757, 527, 868}, "Elly"); 	
	test(new int[]{642, 273, 18}, "Elly"); 	
	test(new int[]{675, 788, 291, 303, 656}, "Elly"); 	
	test(new int[]{126, 704, 225, 862, 522}, "Elly"); 	
	test(new int[]{630, 725, 17, 847, 715}, "Kris"); 	
	test(new int[]{502, 778}, "Kris"); 	
	test(new int[]{32, 168, 841, 288, 76, 31, 934}, "Elly"); 	
	test(new int[]{626, 419, 782, 875, 723}, "Elly"); 	
	test(new int[]{335}, "Elly"); 	
	test(new int[]{70, 369, 545, 610, 611, 60, 935}, "Elly"); 	
	test(new int[]{829, 962, 369, 918, 282, 928, 407}, "Elly"); 	
	test(new int[]{312, 532, 517, 102, 80, 907, 525}, "Elly"); 	
	test(new int[]{84, 635, 629, 682}, "Elly"); 	
	test(new int[]{964}, "Elly"); 	
	test(new int[]{642, 364, 16, 717, 898, 53, 264}, "Elly"); 	
	test(new int[]{751, 558, 92, 496, 963, 277, 152, 618, 333, 743}, "Kris"); 	
	test(new int[]{559, 27, 40, 323, 149, 925}, "Elly"); 	
	test(new int[]{953, 427, 76, 161, 990}, "Elly"); 	
	test(new int[]{442, 275, 726, 373, 931, 901, 177, 749}, "Kris"); 	
	test(new int[]{570, 416}, "Kris"); 	
	test(new int[]{479, 17, 397, 139, 900, 559, 744}, "Elly"); 	
	test(new int[]{393, 353, 597, 517, 527, 477}, "Kris"); 	
	test(new int[]{37, 599, 326, 281, 806, 365}, "Elly"); 	
	test(new int[]{592}, "Elly"); 	
	test(new int[]{321, 176}, "Kris"); 	
	test(new int[]{460, 273, 730, 53, 998, 392, 911, 894, 785}, "Elly"); 	
	test(new int[]{467, 725, 879, 624, 461, 790, 419, 296}, "Kris"); 	
	test(new int[]{791, 505, 295, 609, 917, 434, 580, 244}, "Kris"); 	
	test(new int[]{525, 776, 273, 218, 998, 839, 577, 975, 670, 192}, "Kris"); 	
	test(new int[]{90, 329, 493, 586, 285, 494}, "Kris"); 	
	test(new int[]{175, 445, 612, 560, 777, 784, 266, 570, 778}, "Elly"); 	
	test(new int[]{130, 452, 599, 520, 280, 32, 155, 172, 628, 951}, "Kris"); 	
	test(new int[]{796, 866}, "Kris"); 	
	test(new int[]{500, 186, 632, 248, 35, 308, 624}, "Elly"); 	
	test(new int[]{882, 857, 405}, "Elly"); 	
	test(new int[]{122, 821, 415, 860, 967, 312, 633, 11, 694, 554}, "Elly"); 	
	test(new int[]{865, 365, 70, 702, 598, 508, 983}, "Elly"); 	
	test(new int[]{844, 674, 388, 780, 240, 407, 998, 575, 158, 275}, "Kris"); 	
	test(new int[]{395, 589, 734, 823, 902, 165, 152, 696, 172}, "Elly"); 	
	test(new int[]{298, 366, 664, 404, 545, 431, 533}, "Elly"); 	
	test(new int[]{503, 115}, "Elly"); 	
	test(new int[]{697}, "Elly"); 	
	test(new int[]{123, 411, 263, 971}, "Elly"); 	
	test(new int[]{517, 527, 420, 847, 937, 193, 172, 294}, "Kris"); 	
	test(new int[]{258, 89, 464, 266, 443, 709, 96}, "Elly"); 	
	test(new int[]{359, 718}, "Draw"); 	
	test(new int[]{261, 115, 437, 318}, "Draw"); 	
	test(new int[]{43, 542, 180, 362, 451, 704, 415}, "Draw"); 	
	test(new int[]{184, 140, 84, 192, 370, 71}, "Draw"); 	
	test(new int[]{42, 260, 172}, "Draw"); 	
	test(new int[]{56, 373, 236, 528, 597}, "Draw"); 	
	test(new int[]{663, 836, 59, 503, 211, 961, 765}, "Draw"); 	
	test(new int[]{156, 375, 94, 569, 490, 296, 586, 407, 214, 97}, "Draw"); 	
	test(new int[]{338, 415, 689, 379, 513, 133, 631, 541, 680, 60}, "Draw"); 	
	test(new int[]{168, 398, 9, 296, 642, 556, 111}, "Draw"); 	
	test(new int[]{42, 13, 7}, "Elly"); 	
	test(new int[]{3, 1, 7, 11, 1, 1}, "Kris"); 	
	test(new int[]{10, 20}, "Draw"); 	
	test(new int[]{0}, "Draw"); 	
	test(new int[]{177, 131, 142, 171, 411, 391, 17, 222, 100, 298}, "Elly"); 	
	test(new int[]{5}, "Elly"); 	
	test(new int[]{0, 0, 0, 0, 0}, "Draw"); 	
	test(new int[]{100, 90, 0, 1}, "Elly"); 	
	test(new int[]{0, 0}, "Draw"); 	
	test(new int[]{10, 11, 12, 13, 14, 15, 16, 17, 18, 19}, "Kris"); 	
	test(new int[]{2, 1}, "Draw"); 	
	test(new int[]{5, 0, 10, 9}, "Elly"); 		
    }
    
    public static void main(String[] args) {
        tests();
        tests2();
    }
    
}

/*  Diary:
    
    21 June 2016.
    Problem #1, Elly's Candy Game.

    This took me a hell of a long time to do. Conceptually I understood this
    problem instantly and I even knew the structure to use from previous
    recursive problems in university. However, I was used to using collections
    rather than arrays and it appears that the bug I did not find was that I had
    to copy the arrays of scores/chests rather than passing them by reference.

    Next I will continue picking programs from topcoder daily to implement when
    I am not working on personal projects. I will increase the difficulty as 
    needed to ensure I improve in the time it takes me to implement solutions to
    puzzles.
*/