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

	//CALL US
	@Test
	public void testPricingCallUS() {
		inputs.setType(OptionType.CALL);
		inputs.setExercise(ExerciseType.AMERICAN);
		assertEquals(new Double(11.6702), new Double(pricer.GetValue()));
	}
	@Test
	public void testDeltaCallUS() {
		inputs.setType(OptionType.CALL);
		inputs.setExercise(ExerciseType.AMERICAN);
		assertEquals(new Double(0.8904), new Double(pricer.GetDelta()));
	}
	@Test
	public void testGammaCallUS() {
		inputs.setType(OptionType.CALL);
		inputs.setExercise(ExerciseType.AMERICAN);
		assertEquals(new Double(0.0188), new Double(pricer.GetGamma()));
	}
	@Test
	public void testVegaCallUS() {
		inputs.setType(OptionType.CALL);
		inputs.setExercise(ExerciseType.AMERICAN);
		assertEquals(new Double(0.0935), new Double(pricer.GetVega()));
	}
	@Test
	public void testThetaCallUS() {
		inputs.setType(OptionType.CALL);
		inputs.setExercise(ExerciseType.AMERICAN);
		assertEquals(new Double(-0.0209), new Double(pricer.GetTheta()));

	}
	@Test
	public void testRhoCallUS() {
		inputs.setType(OptionType.CALL);
		inputs.setExercise(ExerciseType.AMERICAN);
		assertEquals(new Double(0.1934), new Double(pricer.GetRho()));

	}
	
	//CALL EU
	@Test
	public void testPricingCallEU() {
		inputs.setType(OptionType.CALL);
		inputs.setExercise(ExerciseType.EUROPEAN);
		assertEquals(new Double(11.6702), new Double(pricer.GetValue()));
	}
	@Test
	public void testDeltaCallEU() {
		inputs.setType(OptionType.CALL);
		inputs.setExercise(ExerciseType.EUROPEAN);
		assertEquals(new Double(0.8904), new Double(pricer.GetDelta()));
	}
	@Test
	public void testGammaCallEU() {
		inputs.setType(OptionType.CALL);
		inputs.setExercise(ExerciseType.EUROPEAN);
		assertEquals(new Double(0.0188), new Double(pricer.GetGamma()));
	}
	@Test
	public void testVegaCallEU() {
		inputs.setType(OptionType.CALL);
		inputs.setExercise(ExerciseType.EUROPEAN);
		assertEquals(new Double(0.0935), new Double(pricer.GetVega()));
	}
	@Test
	public void testThetaCallEU() {
		inputs.setType(OptionType.CALL);
		inputs.setExercise(ExerciseType.EUROPEAN);
		assertEquals(new Double(-0.0209), new Double(pricer.GetTheta()));

	}
	@Test
	public void testRhoCallEU() {
		inputs.setType(OptionType.CALL);
		inputs.setExercise(ExerciseType.EUROPEAN);
		assertEquals(new Double(0.1934), new Double(pricer.GetRho()));

	}

// PUT EU
	@Test
	public void testPricingPutEU() {
		inputs.setType(OptionType.PUT);
		inputs.setExercise(ExerciseType.EUROPEAN);
		assertEquals(new Double(0.5522), new Double(pricer.GetValue()));
	}
	@Test
	public void testDeltaPutEU() {
		inputs.setType(OptionType.PUT);
		inputs.setExercise(ExerciseType.EUROPEAN);
		assertEquals(new Double(-0.1096), new Double(pricer.GetDelta()));
	}
	@Test
	public void testGammaPutEU() {
		inputs.setType(OptionType.PUT);
		inputs.setExercise(ExerciseType.EUROPEAN);
		assertEquals(new Double(0.0188), new Double(pricer.GetGamma()));
	}
	@Test
	public void testVegaPutEU() {
		inputs.setType(OptionType.PUT);
		inputs.setExercise(ExerciseType.EUROPEAN);
		assertEquals(new Double(0.0935), new Double(pricer.GetVega()));
	}
	@Test
	public void testThetaPutEU() {
		inputs.setType(OptionType.PUT);
		inputs.setExercise(ExerciseType.EUROPEAN);
		assertEquals(new Double(-0.0087), new Double(pricer.GetTheta()));

	}
	@Test
	public void testRhoPutEU() {
		inputs.setType(OptionType.PUT);
		inputs.setExercise(ExerciseType.EUROPEAN);
		assertEquals(new Double(-0.0287), new Double(pricer.GetRho()));

	}
	
	// PUT US
	@Test
	public void testPricingPutUS() {
		inputs.setType(OptionType.PUT);
		inputs.setExercise(ExerciseType.AMERICAN);
		assertEquals(new Double(0.5633), new Double(pricer.GetValue()));
	}
	@Test
	public void testDeltaPutUS() {
		inputs.setType(OptionType.PUT);
		inputs.setExercise(ExerciseType.AMERICAN);
		assertEquals(new Double(-0.1123), new Double(pricer.GetDelta()));
	}
	@Test
	public void testGammaPutUS() {
		inputs.setType(OptionType.PUT);
		inputs.setExercise(ExerciseType.AMERICAN);
		assertEquals(new Double(0.0193), new Double(pricer.GetGamma()));
	}
	@Test
	public void testVegaPutUS() {
		inputs.setType(OptionType.PUT);
		inputs.setExercise(ExerciseType.AMERICAN);
		assertEquals(new Double(0.0949), new Double(pricer.GetVega()));
	}
	@Test
	public void testThetaPutUS() {
		inputs.setType(OptionType.PUT);
		inputs.setExercise(ExerciseType.AMERICAN);
		assertEquals(new Double(-0.009), new Double(pricer.GetTheta()));

	}
	@Test
	public void testRhoPutUS() {
		inputs.setType(OptionType.PUT);
		inputs.setExercise(ExerciseType.AMERICAN);
		assertEquals(new Double(-0.0265), new Double(pricer.GetRho()));

	}
}
