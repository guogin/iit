# IIT Calculator Library

A Java library for Individual Income Tax (IIT) calculation. After building, it produces a JAR that can be consumed by other projects.

## Build

```bash
mvn clean package
```

The JAR will be created under `target/`.

## Usage

Use `IITCalculatorImpl2024` to calculate or simulate tax. The `simulate` API returns two calculation results (one-time bonus taxation and integrated taxation), each including the final tax amount and a trace of the calculation process via `TraceableTaxCalculationResult`.

### Example

```java
import com.yahaha.iit.calc.IITCalculatorImpl2024;
import com.yahaha.iit.calc.IITRequest;
import com.yahaha.iit.calc.IITResponse;
import com.yahaha.iit.calc.BonusTaxationOption;
import com.yahaha.iit.calc.TraceableTaxCalculationResult;

import java.math.BigDecimal;
import java.util.Map;

public class MyIITSimulator {
    public static void main(String[] args) {
        IITCalculator calculator = new IITCalculatorImpl2024();

        IITRequest request = new IITRequest();
        request.setAnnualWageIncome(BigDecimal.valueOf(18000 * 12));
        request.setAnnualOneTimeBonus(BigDecimal.valueOf(300000));

        IITResponse response = calculator.simulate(request);
        Map<BonusTaxationOption, TraceableTaxCalculationResult> resultMap = response.getResults();

        TraceableTaxCalculationResult oneTime = resultMap.get(BonusTaxationOption.ONE_TIME_TAXATION);
        TraceableTaxCalculationResult integrated = resultMap.get(BonusTaxationOption.INTEGRATED_TAXATION);

        System.out.println("One-time tax: " + oneTime.getTotalTaxAmount());
        System.out.println("Integrated tax: " + integrated.getTotalTaxAmount());

        // Each TraceableTaxCalculationResult also contains the calculation steps.
        System.out.println(oneTime.getItems());
        System.out.println(integrated.getItems());
    }
}
```

## Notes

- `simulate` always computes both taxation options.
- Use `calculate` if you want to fix the bonus taxation option via `TaxCalculationParameter`.
