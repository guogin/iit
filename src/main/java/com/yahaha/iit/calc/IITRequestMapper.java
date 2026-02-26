package com.yahaha.iit.calc;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public final class IITRequestMapper {
    @NotNull
    public Locale getLocale(IITRequest request) {
        Locale locale = request.getLocale() == null ? Locale.SIMPLIFIED_CHINESE : request.getLocale();
        return locale;
    }

    public TaxCalculationParameter toParameter(IITRequest request, BonusTaxationOption bonusTaxationOption) {
        TaxCalculationParameter parameter = new TaxCalculationParameter();
        parameter.setBonusTaxationOption(bonusTaxationOption);

        parameter.setAnnualWageIncome(request.getAnnualWageIncome());
        parameter.setAnnualOneTimeBonus(request.getAnnualOneTimeBonus());
        parameter.setAuthorsRemuneration(request.getAuthorsRemuneration());
        parameter.setRoyaltyFees(request.getRoyaltyFees());
        parameter.setServiceRemuneration(request.getServiceRemuneration());
        parameter.setSpecialDeductions(request.getSpecialDeductions());
        parameter.setAdditionalSpecialDeductions(request.getAdditionalSpecialDeductions());
        parameter.setOtherDeductions(request.getOtherDeductions());

        return parameter;
    }
}
