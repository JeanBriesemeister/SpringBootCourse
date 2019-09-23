package com.jean.sbc.domain.enums;

public enum CustomerType {

	PESSAOFISICA(1, "Pessoa Física"),

	PESSOAJURIDICA(2, "Pessoa Jurídica");

	private int cod;

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
			if (ct.equals(ct.getCod())) {
				return ct;
			}
		}

		throw new IllegalArgumentException("Invalid Id: " + cod);
	}
}
