package com.yahaha.iit.calc;

public final class IITRequestMapper {
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

        String localeTag = request.getLocale();
        if (localeTag != null && !localeTag.isEmpty()) {
            parameter.setLocale(java.util.Locale.forLanguageTag(localeTag));
        }

        return parameter;
    }
}
