/**
 * 
 */
package no.hvl.dat104.veksling;

/**
 * @author larserik
 *
 */
public class BeregningNyValuta {

	public static Double beregnSum(String belop, ExchangeRate rate) {
		double veksleRate = rate.rate;
		double belopet;
		try {
			belopet = Double.parseDouble(belop);
		} catch (Exception e) {
			return null;
		}
		double nySum = belopet * veksleRate;
		return nySum;
	}

}
