package ro.jademy.domain.entities;

import java.math.BigDecimal;

public enum UserType {
	

	REGULAR{

		@Override
		public BigDecimal getDiscount() {
			
			return BigDecimal.ZERO;
		}
		
	},
	GOLD{

		@Override
		public BigDecimal getDiscount() {
			
			return BigDecimal.ONE;
		}
		
	},
	PLATINUM{

		@Override
		public BigDecimal getDiscount() {
			// TODO Auto-generated method stub
			return BigDecimal.valueOf(3);
		}
		
	},
	NEW_CLIENT{

		@Override
		public BigDecimal getDiscount() {
			return BigDecimal.valueOf(5);
		}
		
	};
	
	abstract public BigDecimal getDiscount();
}

