package com.max.shop.validation;

import com.max.shop.dto.request.UserFormDto;
import org.apache.commons.lang3.StringUtils;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordUpdateValidator implements ConstraintValidator<PasswordUpdate, UserFormDto> {

    @Override public void initialize(PasswordUpdate passwordUpdate) {
    }

    @Override public boolean isValid(UserFormDto userFormDto, ConstraintValidatorContext constraintValidatorContext) {
        if (userFormDto == null) {
            return true;
        }

        if (userFormDto.getNewPassword() == null && userFormDto.getRepeatPassword() == null) {
            return true;
        }

        if (userFormDto.getId() == null && userFormDto.getNewPassword() == null) {
            return false;
        }

        return StringUtils.equals(userFormDto.getNewPassword(), userFormDto.getRepeatPassword());
    }
}
