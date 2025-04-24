package com.yahaha.iit.calc;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.yahaha.iit.util.MoneyUtil;

import javax.money.MonetaryAmount;
import java.io.IOException;

public class MonetaryAmountSerializer extends StdSerializer<MonetaryAmount> {
    public MonetaryAmountSerializer() {
        this(null);
    }

    protected MonetaryAmountSerializer(Class<MonetaryAmount> t) {
        super(t);
    }

    @Override
    public void serialize(MonetaryAmount value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(MoneyUtil.format(value));
    }
}
