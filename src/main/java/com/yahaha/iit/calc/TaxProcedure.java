package com.yahaha.iit.calc;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
public class TaxProcedure {
    private List<TaxRoutine> routines = new ArrayList<>();

    public TraceableTaxCalculationResult execute(TaxCalculationParameter request) {
        List<TraceableTaxCalculationResultItem> taxCalculationResultItems = routines.stream()
                .map(routine -> routine.execute(request))
                .collect(Collectors.toList());

        return TraceableTaxCalculationResult.of(taxCalculationResultItems.toArray(new TraceableTaxCalculationResultItem[0]));
    }
}
