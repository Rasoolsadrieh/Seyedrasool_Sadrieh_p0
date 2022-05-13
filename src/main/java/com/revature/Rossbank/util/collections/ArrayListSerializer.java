package com.revature.Rossbank.util.collections;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.revature.Rossbank.models.BankUser;

import java.io.IOException;

public class ArrayListSerializer extends StdSerializer<ArrayList> {

    public ArrayListSerializer() {
        this(null);
    }

    public ArrayListSerializer(Class<ArrayList> t) {
        super(t);
    }

    @Override
    public void serialize(ArrayList arrayList, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        for(int i = 0; i < arrayList.size; i++) {
            BankUser bankUser = (BankUser) arrayList.get(i);
            jsonGenerator.writeStartObject();

            jsonGenerator.writeStringField("fname", bankUser.getFname());
            jsonGenerator.writeStringField("lname", bankUser.getLname());
            jsonGenerator.writeStringField("email", bankUser.getEmail());
            jsonGenerator.writeStringField("password", bankUser.getPassword());
            jsonGenerator.writeStringField("dob", bankUser.getDob());
            jsonGenerator.writeEndObject();
        }

    }
}
