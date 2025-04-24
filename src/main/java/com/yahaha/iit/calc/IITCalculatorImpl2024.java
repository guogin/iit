package com.yahaha.iit.calc;

import com.yahaha.iit.util.MoneyUtil;

import javax.money.MonetaryAmount;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IITCalculatorImpl2024 implements IITCalculator {
    @Override
    public IITResult calculate(IITRequest request) {
        TaxProcedure procedure = determineTaxProcedure(request);
        return procedure.execute(request);
    }

    private TaxProcedure determineTaxProcedure(IITRequest request) {
        TaxProcedure procedure = new TaxProcedure();

        AnnualComprehensiveIncomeAssessor annualComprehensiveIncomeAssessor = new AnnualComprehensiveIncomeAssessor();
        AnnualComprehensiveIncomeTax annualComprehensiveIncomeTax = new AnnualComprehensiveIncomeTax();
        TaxRoutine annualComprehensiveIncomeTaxRoutine = new TaxRoutineImpl("年度综合所得", annualComprehensiveIncomeAssessor, annualComprehensiveIncomeTax);
        procedure.addRoutine(annualComprehensiveIncomeTaxRoutine);
        procedure.setName("全年一次性奖金并入综合所得");

        if (request.getBonusTaxationOption() == BonusTaxationOption.ONE_TIME_TAXATION) {
            AnnualOneTimeBonusAssessor annualOneTimeBonusAssessor = new AnnualOneTimeBonusAssessor();
            AnnualOneTimeBonusTax annualOneTimeBonusTax = new AnnualOneTimeBonusTax();
            TaxRoutine annualOneTimeBonusTaxRoutine = new TaxRoutineImpl("全年一次性奖金", annualOneTimeBonusAssessor, annualOneTimeBonusTax);
            procedure.addRoutine(annualOneTimeBonusTaxRoutine);
            procedure.setName("全年一次性奖金单独计税");
        }

        return procedure;
    }
}
