package com.eliseo.vending.machine;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class MoneyWrapper {

	private List<Money> totalMoney = new ArrayList<Money>();
	
	public void addCoin(Money money) {
		totalMoney.add(money);
		
	}
	public void resetAmount() {
		totalMoney = new ArrayList<Money>();
	}


	public BigDecimal getTotalAmount() {
		BigDecimal total = new BigDecimal(0.0);
		if (totalMoney.isEmpty()) {
			return new BigDecimal(0.0);
		}
		
		for (int i = 0; i < totalMoney.size(); i++) {
//			System.out.print("Total debug "+total+ " + "+totalMoney.get(i).getCuantity());
			total = total.add( totalMoney.get(i).getCuantity());
//			System.out.println(" = "+ total+"\n");
//			System.out.println(total+ " == 0.30 "+( total == 0.3f) );
			
		}
		return total.setScale(2, RoundingMode.DOWN);
	}
	
	public int printTotalCoins() {
		int oneCent = 0;
		int fiveCents =0;
		int tenCents =0;
		int twentyFiveCents =0;
		int fiftyCents =0;
		int oneDollar =0;
		int twoDollars = 0;
		int totalOfCoins = 0;
		StringBuilder result = new StringBuilder("Total of coins and notes");
		
		for (int i = 0; i < totalMoney.size(); i++) {
			Money coin = totalMoney.get(i);
			if (coin == Money.TWO_DOLLAR) {
				twoDollars +=1;
			}else if (coin == Money.ONE_DOLLAR) {
				oneDollar +=1;
			}else if (coin == Money.FIFTY_CENTS) {
				fiftyCents += 1;
			}else if (coin == Money.TWENTY_FIVE_CENTS) {
				twentyFiveCents +=1;
			}else if (coin == Money.TEN_CENTS) {
				tenCents += 1;
			}else if (coin == Money.FIVE_CENTS) {
				fiveCents += 1;
			}else if (coin == Money.ONE_CENT){
				oneCent += 1;
			}
		}
		if (oneCent != 0) {
			result.append("\n["+oneCent+"] One cent coins total = "+ (    new BigDecimal(.01 ).multiply( new BigDecimal(oneCent)).setScale(2, RoundingMode.DOWN)   )   );
		}
		if (fiveCents != 0) {
			result.append("\n["+fiveCents+"] Five cents coins total = "+ (  new BigDecimal(.05 ).multiply( new BigDecimal(fiveCents)).setScale(2, RoundingMode.DOWN) )    );
		}
		if (tenCents != 0) {
			result.append("\n["+tenCents+"] Ten cents coins total = "+(  new BigDecimal(.10 ).multiply( new BigDecimal(tenCents)).setScale(2, RoundingMode.DOWN) )    );
		}
		if (twentyFiveCents != 0) {
			result.append("\n["+twentyFiveCents+"] Tweenty five cents coins total = "+(  new BigDecimal(.25 ).multiply( new BigDecimal(twentyFiveCents)).setScale(2, RoundingMode.DOWN) )       );
		}
		if (fiftyCents != 0) {
			result.append("\n["+fiftyCents+"] Fifty cent coins total = "+(    new BigDecimal(.50 ).multiply( new BigDecimal(fiftyCents)).setScale(2, RoundingMode.DOWN) )     );
		}
		if (oneDollar != 0) {
			result.append("\n["+oneDollar+"] One dollar notes total = "+(   new BigDecimal( 1 ).multiply( new BigDecimal(oneDollar)).setScale(2, RoundingMode.DOWN) )    );
		}
		if (twoDollars != 0) {
			result.append("\n["+twoDollars+"] Two dollars notes total = "+(   new BigDecimal( 2 ).multiply( new BigDecimal(twoDollars)).setScale(2, RoundingMode.DOWN) )    );
		}
		
		
		totalOfCoins = oneCent + fiveCents + tenCents+ twentyFiveCents+ fiftyCents+ oneDollar+ twoDollars;
		
		
		result.append("\nTotal = "+getTotalAmount() );
		result.append("\nQuantity of coins = "+totalOfCoins);
		System.out.println(result);
		return totalOfCoins;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((totalMoney == null) ? 0 : totalMoney.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MoneyWrapper other = (MoneyWrapper) obj;
		if (totalMoney == null) {
			if (other.totalMoney != null)
				return false;
		} else if (!totalMoney.equals(other.totalMoney))
			return false;
		return true;
	}
	
	
	
	
	

	
}

