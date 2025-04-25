package com.yahaha.iit.calc;

import java.util.HashMap;
import java.util.Map;

public class IITCalculatorImpl2024 implements IITCalculator {
    @Override
    public IITResponse simulate(IITRequest request) {
        TaxCalculationParameter parameterOneTime = mapToParameter(request, BonusTaxationOption.ONE_TIME_TAXATION);
        TraceableTaxCalculationResult resultOneTime = calculate(parameterOneTime);

        TaxCalculationParameter parameterIntegrated = mapToParameter(request, BonusTaxationOption.INTEGRATED_TAXATION);
        TraceableTaxCalculationResult resultIntegrated = calculate(parameterIntegrated);

        return mapToResponse(resultOneTime, resultIntegrated);
    }

    @Override
    public TraceableTaxCalculationResult calculate(TaxCalculationParameter parameter) {
        TaxProcedure procedure = determineTaxProcedure(parameter);
        return procedure.execute(parameter);
    }

    private TaxProcedure determineTaxProcedure(TaxCalculationParameter request) {
        TaxProcedure procedure = new TaxProcedure();

        AnnualComprehensiveIncomeAssessor annualComprehensiveIncomeAssessor = new AnnualComprehensiveIncomeAssessor();
        AnnualComprehensiveIncomeTax annualComprehensiveIncomeTax = new AnnualComprehensiveIncomeTax();
        TaxRoutine annualComprehensiveIncomeTaxRoutine = new TaxRoutineImpl(annualComprehensiveIncomeAssessor, annualComprehensiveIncomeTax);
        procedure.getRoutines().add(annualComprehensiveIncomeTaxRoutine);

        if (request.getBonusTaxationOption() == BonusTaxationOption.ONE_TIME_TAXATION) {
            AnnualOneTimeBonusAssessor annualOneTimeBonusAssessor = new AnnualOneTimeBonusAssessor();
            AnnualOneTimeBonusTax annualOneTimeBonusTax = new AnnualOneTimeBonusTax();
            TaxRoutine annualOneTimeBonusTaxRoutine = new TaxRoutineImpl(annualOneTimeBonusAssessor, annualOneTimeBonusTax);
            procedure.getRoutines().add(annualOneTimeBonusTaxRoutine);
        }

        return procedure;
    }

    private TaxCalculationParameter mapToParameter(IITRequest request, BonusTaxationOption bonusTaxationOption) {
        TaxCalculationParameter parameter = new TaxCalculationParameter();
        parameter.setBonusTaxationOption(bonusTaxationOption);

        // Copy other fields
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

    private IITResponse mapToResponse(TraceableTaxCalculationResult resultOfOneTimeTaxation,
                                      TraceableTaxCalculationResult resultOfIntegratedTaxation) {
        IITResponse response = new IITResponse();
        Map<String, TraceableTaxCalculationResult> resultMap = new HashMap<>();

        resultMap.put(ONE_TIME_TAXATION, resultOfOneTimeTaxation);
        resultMap.put(INTEGRATED_TAXATION, resultOfIntegratedTaxation);

        response.setResults(resultMap);
        return response;
    }
}
