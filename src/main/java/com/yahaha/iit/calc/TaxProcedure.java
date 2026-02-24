package com.yahaha.iit.calc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.List;
import java.util.stream.Collectors;

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

    public TraceableTaxCalculationResult execute(TaxCalculationParameter request) {
        List<TraceableTaxCalculationResultItem> taxCalculationResultItems = routines.stream()
                .map(routine -> routine.execute(request))
                .collect(Collectors.toList());

        return TraceableTaxCalculationResult.of(taxCalculationResultItems.toArray(new TraceableTaxCalculationResultItem[0]));
    }
}
