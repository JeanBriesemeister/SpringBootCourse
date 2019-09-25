package com.jean.sbc.domain.enums;

public enum PaymentStatus {

	PENDENTE(1, "Pendentene"),

	QUITADO(2, "Quitado"),

	CANCELADO(3, "Cancelado");

	private Integer cod;

	private String description;

	private PaymentStatus(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return this.cod;
	}

	public String getDescription() {
		return this.description;
	}

	public static PaymentStatus toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (PaymentStatus ct : PaymentStatus.values()) {
			if (cod.equals(ct.getCod())) {
				return ct;
			}
		}

		throw new IllegalArgumentException("Invalid Id: " + cod);
	}
}
