package com.yahaha.iit.calc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class TaxProcedure {
    private final List<TaxRoutine> routines;

    public TaxProcedure(List<TaxRoutine> routines) {
        this.routines = new ArrayList<>(routines);
    }

    public void addRoutine(TaxRoutine routine) {
        routines.add(routine);
    }

    public List<TaxRoutine> getRoutines() {
        return Collections.unmodifiableList(routines);
    }

    public TraceableTaxCalculationResult execute(TaxCalculationParameter parameter) {
        return execute(parameter, Locale.SIMPLIFIED_CHINESE);
    }

    public TraceableTaxCalculationResult execute(TaxCalculationParameter parameter, Locale locale) {
        Map<RoutineCode, TraceableTaxCalculationResultItem> taxCalculationResultItems = new LinkedHashMap<>();
        for (TaxRoutine routine : routines) {
            taxCalculationResultItems.put(routine.getRoutineCode(), routine.execute(parameter, locale));
        }

        return TraceableTaxCalculationResult.of(taxCalculationResultItems);
    }
}
