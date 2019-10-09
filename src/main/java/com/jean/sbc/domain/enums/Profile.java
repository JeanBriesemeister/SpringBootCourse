package com.jean.sbc.domain.enums;

public enum Profile {

	ADMIN(1, "ROLE_ADMIN"),

	CUSTOMER(2, "ROLE_CUSTOMER");

	private Integer cod;

	private String description;

	private Profile(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return this.cod;
	}

	public String getDescription() {
		return this.description;
	}

	public static Profile toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (Profile ct : Profile.values()) {
			if (cod.equals(ct.getCod())) {
				return ct;
			}
		}

		throw new IllegalArgumentException("Invalid Id: " + cod);
	}
}
