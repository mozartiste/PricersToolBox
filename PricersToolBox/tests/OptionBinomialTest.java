import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
		inputs = new InputsOptions(new Integer(100), new Integer(90), new Double(.20), new Double(0.05), new Integer(500), new Double(0.25), OptionType.CALL, ExerciseType.EUROPEAN);
	    pricer = new OptionBinomialPricer(inputs);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPricing() {
		//11.6702
		assertEquals(new Double(11.6702), new Double(pricer.GetValue()));

	}

}
