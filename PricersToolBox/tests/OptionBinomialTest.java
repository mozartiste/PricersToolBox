import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mozartiste.interestrates.InterestFlatRate;
import com.mozartiste.interestrates.InterestRate;
import com.mozartiste.interestrates.InterestRateCurve;
import com.mozartiste.optionpricer.IPricer;
import com.mozartiste.optionpricer.InputsOptions;
import com.mozartiste.optionpricer.OptionBinomialPricer;
import com.mozartiste.optionpricer.ENUMS.ExerciseType;
import com.mozartiste.optionpricer.ENUMS.OptionType;

public class OptionBinomialTest {
	protected InputsOptions inputs;
	protected OptionBinomialPricer pricer;
	@Before
	public void setUp() throws Exception {
		// Exemple : European Call
		// Spot 100
		// Strike 90
		// interest rate 0.05
		// volatility 20 %
		// maturity 3 months : 0,25
		// number of simulations 1000
		InterestRate interestRate = new InterestFlatRate(0.05);
		inputs = new InputsOptions(new Integer(100), new Integer(90), new Double(.20), interestRate, new Integer(500), new Double(0.25), OptionType.CALL, ExerciseType.EUROPEAN);
	    pricer = new OptionBinomialPricer(inputs);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPricing() {
		assertEquals(new Double(11.6702), new Double(pricer.GetValue()));
	}
	@Test
	public void testDelta() {
		assertEquals(new Double(0.8904), new Double(pricer.GetDelta()));
	}
	@Test
	public void testGamma() {
		assertEquals(new Double(0.0188), new Double(pricer.GetGamma()));
	}
	@Test
	public void testVega() {
		assertEquals(new Double(0.0935), new Double(pricer.GetVega()));
	}
	@Test
	public void testTheta() {
		assertEquals(new Double(-0.0209), new Double(pricer.GetTheta()));

	}
	@Test
	public void testRho() {
		assertEquals(new Double(0.1934), new Double(pricer.GetRho()));

	}

}
