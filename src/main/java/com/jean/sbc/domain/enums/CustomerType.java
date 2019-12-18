package com.jean.sbc.domain.enums;

public enum CustomerType {

	NATURALPERSON(1, "Natural Person"),

	LEGALPERSON(2, "Legal Person");

	private Integer cod;

	private String description;

	private CustomerType(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return this.cod;
	}

	public String getDescription() {
		return this.description;
	}

	public static CustomerType toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (CustomerType ct : CustomerType.values()) {
			if (cod.equals(ct.getCod())) {
				return ct;
			}
		}

		throw new IllegalArgumentException("Invalid Id: " + cod);
	}
}
