
import com.dml.majiang.ShoupaiCalculator;

public class CalculateGouXingTest {
	public static void main(String[] args) {
		try {
			Class.forName("com.dml.majiang.GouXingCalculator");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		ShoupaiCalculator c = new ShoupaiCalculator();
		int[] paiQuantityArray = new int[] { 0, 0, 1, 0, 2, 1, 1, 0, 0, 0, 2, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 2,
				1, 1, 0, 1, 0, 0, 2, 0, 0 };
		c.setPaiQuantityArray(paiQuantityArray);
		c.calculateAllGouXing();
	}
}
