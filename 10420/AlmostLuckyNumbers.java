
package almostluckynumbers;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.TreeMap;
import java.util.TreeSet;

/** https://community.topcoder.com/stat?c=problem_statement&pm=10420
    *A lucky number is a number whose decimal representation contains only the digits 4 and 7. 
    *An almost lucky number is a number that is divisible by a lucky number. 
    *For example, 14, 36 and 747 are almost lucky, but 2 and 17 are not. 
    *Note that a number can be both lucky and almost lucky at the same time (for example, 747).

    *Given long a and long b:
    *Return the number of almost lucky numbers between a and b, inclusive.
 
    Class:	TheAlmostLuckyNumbers
    Method:	count
    Parameters:	long, long
    Returns:	long
    Method signature:	long count(long a, long b)
    (be sure your method is public)
    
    Constraints
-	a will be between 1 and 10,000,000,000, inclusive.
-	b will be between a and 10,000,000,000, inclusive.
**/

public class AlmostLuckyNumbers {
    String maxNum;
    ArrayList<String> LuckyNumbers;
    TreeSet<String> Memoization;
    
    public AlmostLuckyNumbers() {
        maxNum = "7777777777";
    }

    /* increment()
        Increment the potential lucky number divisor, treat it like a binary
        string where 0 = 4 and 1 = 7, using regular binary addition rules, 
        (however with the initial zero included) */
    public String increment(String s) {
        StringBuilder newS = new StringBuilder(s);
        int pos = newS.length()-1;
        while (pos > -1 && newS.charAt(pos) == '7') {
            newS.setCharAt(pos--, '4');
        }
        if (pos == -1) return '4' + newS.toString();
        else {
            newS.setCharAt(pos, '7');
            return newS.toString();
        }
               
    }
    
    /* almostLucky()
        Predicate to indicate whether a number is 'almost lucky' or not */
    public boolean almostLucky(long num) {
        String currentNum = "4";
        //cut the division short when we've got a too large potential 'almost
        //lucky' number or division yields less than 1
        while (Long.parseLong(maxNum) >= Long.parseLong(currentNum) || ((float)num/Long.parseLong(currentNum)) > 1) {
            if (num % Long.parseLong(currentNum) == 0) return true;
            else currentNum = increment(currentNum);
        }
        return false;
    }
    
    /* count
        iterative function to count the number of 'almost lucky' numbers
        between input1 and input2 */
    public long count(long input1, long input2) {
        long finalCount = 0;
        for (long i = input1; i <= input2; i++) if (almostLucky(i)) finalCount++;
        return finalCount;
    }

    public static void RunTest(long input1, long input2, long expected) throws Exception {
        String formatnum = "%8d";
        AlmostLuckyNumbers obj = new AlmostLuckyNumbers();
        long answer = obj.count(input1, input2);
        if (answer == expected) System.out.print("PASS\t");
        else { System.out.print("FAIL\t"); } //throw new Exception("Fart"); }
        System.out.print("Expected: ");
        System.out.print(String.format(formatnum, expected));
        System.out.print("\tGot: ");
        System.out.print(String.format(formatnum, answer));
        System.out.print("\tLower bounds: ");
        System.out.print(String.format("%8d", input1));
        System.out.print("\tUpper bounds: ");
        System.out.print(String.format("%8d", input2));
        System.out.print("\n");
    }
    
    public static void examples() throws Exception {
        RunTest(1,10,3);
        RunTest(14,14,1);
        RunTest(1, 100, 39);
    	RunTest(1234, 4321, 1178);
    }
    
    public static void testData() throws Exception {
        
        RunTest(1, 10, 3); 	
	RunTest(14, 14, 1); 	
	RunTest(1, 100, 39); 	
	RunTest(1234, 4321, 1178); 	
	RunTest(1, 3, 0); 	
	RunTest(1, Long.parseLong("10000000000"), Long.parseLong("3823045312")); 	
	RunTest(123456789, 987654321, 330386840); 	
	RunTest(1, 1, 0); 	
	RunTest(1000000000, 1000000000, 1); 	
	RunTest(4, 4, 1); 	
	RunTest(2, 2, 0); 	
	RunTest(3, 3, 0); 	
	RunTest(999970000, 999999999, 11462); 	
	RunTest(44, 77, 15); 	
	RunTest(48, 73, 10); 	
	RunTest(757148, 167851001, 63880759); 	
	RunTest(301413357, 336971125, 13593801); 	
	RunTest(160567226, 659598369, 190781558); 	
	RunTest(4890852, 391749388, 147897472); 	
	RunTest(26239573, 35766291, 3642006); 	
	RunTest(597007, 473038165, 180616328); 	
	RunTest(3615545, 326051437, 123268713); 	
	RunTest(118341523, 392289611, 104731438); 	
	RunTest(37215529, 170427799, 50927765); 	
	RunTest(168544291, 675016434, 193626376); 	
	RunTest(683447134, 950090227, 101939155); 	
	RunTest(82426873, 116752252, 13122652); 	
	RunTest(194041605, 706221269, 195808401); 	
	RunTest(69909135, 257655784, 71776471); 	
	RunTest(21417563, 84970744, 24296649); 	
	RunTest(Long.parseLong("4735993214"), Long.parseLong("5788109700"), 402228932); 	
	RunTest(Long.parseLong("3788115833"), Long.parseLong("8956428880"), 1975870108); 	
	RunTest(1662213698, Long.parseLong("6214162788"), 1740230313); 	
	RunTest(Long.parseLong("3951218642"), Long.parseLong("6331312118"), 909920679); 	
	RunTest(1543831757, Long.parseLong("5048844734"), 1339982138); 	
	RunTest(1284888887, Long.parseLong("5105102205"), 1460485012); 	
	RunTest(Long.parseLong("4565962192"), Long.parseLong("7762163387"), 1221922073); 	
	RunTest(Long.parseLong("8735993214"), Long.parseLong("9788109700"), 402229076); 	
	RunTest(Long.parseLong("8788115833"), Long.parseLong("9956428880"), 446651414); 	
	RunTest(Long.parseLong("8662213698"), Long.parseLong("9214162788"), 211012663); 	
	RunTest(Long.parseLong("8951218642"), Long.parseLong("9331312118"), 145311415); 	
	RunTest(Long.parseLong("8543831757"), Long.parseLong("9048844734"), 193068806); 	
	RunTest(Long.parseLong("8284888887"), Long.parseLong("9105102205"), 313571473); 	
	RunTest(Long.parseLong("8565962192"), Long.parseLong("9762163387"), 457313225); 	
	RunTest(Long.parseLong("5000000000"), Long.parseLong("10000000000"), 1911522647); 	
	RunTest(Long.parseLong("9000000000"), Long.parseLong("10000000000"), 382304516); 	
	RunTest(Long.parseLong("4444444444"), Long.parseLong("7777777777"), 1274348603); 	
	RunTest(999, Long.parseLong("10000000000"), Long.parseLong("3823044930")); 	
	RunTest(1000000000, Long.parseLong("10000000000"), Long.parseLong("3440740760")); 	
	RunTest(3, 1234567891, 471980733); 	
	RunTest(123, Long.parseLong("9991999997"), Long.parseLong("3819986852")); 	
	RunTest(9999, Long.parseLong("10000000000"), Long.parseLong("3823041489")); 	
	RunTest(5, Long.parseLong("10000000000"), Long.parseLong("3823045311")); 	
	RunTest(Long.parseLong("9999999999"), Long.parseLong("10000000000"), 1); 	
	RunTest(2341243, Long.parseLong("10000000000"), Long.parseLong("3822150237")); 	
	RunTest(5, Long.parseLong("9987654321"), Long.parseLong("3818325523")); 	
	RunTest(515151515, Long.parseLong("10000000000"), Long.parseLong("3626100495")); 	
	RunTest(1, 1000000000, 382304553); 	
	RunTest(4745, Long.parseLong("4474745457"), 1710713382); 	
	RunTest(100, Long.parseLong("10000000000"), Long.parseLong("3823045274")); 	
	RunTest(1234567890, Long.parseLong("9876543210"), Long.parseLong("3303866595"));
    }
    
    public static void main(String[] args) throws Exception {
        examples();
        testData();
        /*AlmostLuckyNumbers A = new AlmostLuckyNumbers();
        String s = "4";
        while (!s.equals("7777777777")) {
            if (A.increment(s).length() > s.length()) System.out.print("\n");
            s = A.increment(s);
            System.out.print(s + "\t");
        }*/
    }
    
}


/* Diary:
    Firstly, I implemented this recursively and ran into stack overflow errors
    in some of the larger numbers. I then changed this to an iterative version
    and the thing just took so darn long that I realized i needed to optimize
    some of the comparisons or iterations out.
*/