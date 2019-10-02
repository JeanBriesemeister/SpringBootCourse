package com.jean.sbc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.jean.sbc.domain.Customer;
import com.jean.sbc.domain.enums.CustomerType;
import com.jean.sbc.dto.CustomerNewDTO;
import com.jean.sbc.repositories.CustomerRepository;
import com.jean.sbc.resources.exception.FieldMessage;
import com.jean.sbc.services.validation.utils.BR;

public class CustomerInsertValidator implements ConstraintValidator<CustomerInsert, CustomerNewDTO> {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public void initialize(CustomerInsert ann) {
	}

	@Override
	public boolean isValid(CustomerNewDTO customerNewDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (customerNewDTO.getCustomerType().equals(CustomerType.PESSOAFISICA.getCod())
				&& !BR.isValidCPF(customerNewDTO.getFinancialCode())) {
			list.add(new FieldMessage("financialCode", "CPF is invalid"));
		}

		if (customerNewDTO.getCustomerType().equals(CustomerType.PESSOAJURIDICA.getCod())
				&& !BR.isValidCPF(customerNewDTO.getFinancialCode())) {
			list.add(new FieldMessage("financialCode", "CNPJ is invalid"));
		}

		Customer customer = customerRepository.findByEmail(customerNewDTO.getEmail());
		if (customer != null) {
			list.add(new FieldMessage("email", "Email already exists"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}

		return list.isEmpty();
	}
}