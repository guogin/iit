package com.yahaha.iit.calc;

import java.util.EnumMap;
import java.util.Locale;
import java.util.Map;

public class IITCalculatorImpl2024 implements IITCalculator {
    private final TaxProcedureFactory taxProcedureFactory;
    private final IITRequestMapper requestMapper;

    public IITCalculatorImpl2024() {
        this(new TaxProcedureFactory2024(), new IITRequestMapper());
    }

    public IITCalculatorImpl2024(TaxProcedureFactory taxProcedureFactory, IITRequestMapper requestMapper) {
        this.taxProcedureFactory = taxProcedureFactory;
        this.requestMapper = requestMapper;
    }

    @Override
    public IITResponse simulate(IITRequest request) {
        Locale locale = requestMapper.getLocale(request);
        TaxCalculationParameter parameterOneTime = requestMapper.toParameter(request, BonusTaxationOption.ONE_TIME_TAXATION);
        TraceableTaxCalculationResult resultOneTime = calculate(parameterOneTime, locale);

        TaxCalculationParameter parameterIntegrated = requestMapper.toParameter(request, BonusTaxationOption.INTEGRATED_TAXATION);
        TraceableTaxCalculationResult resultIntegrated = calculate(parameterIntegrated, locale);

        return mapToResponse(resultOneTime, resultIntegrated);
    }

    @Override
    public TraceableTaxCalculationResult calculate(TaxCalculationParameter parameter, Locale locale) {
        TaxProcedure procedure = taxProcedureFactory.create(parameter);
        return procedure.execute(parameter, locale);
    }

    private IITResponse mapToResponse(TraceableTaxCalculationResult resultOfOneTimeTaxation,
                                      TraceableTaxCalculationResult resultOfIntegratedTaxation) {
        IITResponse response = new IITResponse();
        Map<BonusTaxationOption, TraceableTaxCalculationResult> resultMap = new EnumMap<>(BonusTaxationOption.class);

        resultMap.put(BonusTaxationOption.ONE_TIME_TAXATION, resultOfOneTimeTaxation);
        resultMap.put(BonusTaxationOption.INTEGRATED_TAXATION, resultOfIntegratedTaxation);

        response.setResults(resultMap);
        return response;
    }
}
