package com.yahaha.iit.calc;

public class IITCalculatorImpl2024 implements IITCalculator {
    @Override
    public TraceableTaxCalculationResult calculate(TaxCalculationParameter request) {
        TaxProcedure procedure = determineTaxProcedure(request);
        return procedure.execute(request);
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
}
