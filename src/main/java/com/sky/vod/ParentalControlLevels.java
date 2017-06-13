package com.sky.vod;


public enum ParentalControlLevels {
	PCL_U("U"), PCL_PG("PG"), PCL_12("12"), PCL_15("15"), PCL_18("18");

	private String desc;

	private ParentalControlLevels(String desc) {
		this.desc = desc;
	}

	public static ParentalControlLevels fromString(String text) {
		if (text != null) {
			for (ParentalControlLevels b : ParentalControlLevels.values()) {
				if (text.equals(b.desc)) {
					return b;
				}
			}
		}
		return null;
	}


	public boolean isEqualToOrLessThan(ParentalControlLevels otherPCL) {
		if(otherPCL != null)
			return compareTo(otherPCL) <= 0;
		return false;
	}
	
}
