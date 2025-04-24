package com.yahaha.iit.calc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaxProcedure {
    private String name;
    private List<TaxRoutine> routines = new ArrayList<>();

    public TaxProcedure() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TaxRoutine> getRoutines() {
        return routines;
    }

    public void addRoutine(TaxRoutine routine) {
        this.routines.add(routine);
    }

    public IITResult execute(IITRequest request) {
        List<TaxItem> taxItems = routines.stream()
                .map(routine -> routine.execute(request))
                .collect(Collectors.toList());

        TraceLog traceLog = TraceLog.builder()
                .headerMessage(new DiagnosticMessage(name))
                .subTraceLogs(taxItems.stream().map(TaxItem::getTraceLog).collect(Collectors.toList()))
                .build();

        return IITResult.create(taxItems, traceLog);
    }
}
