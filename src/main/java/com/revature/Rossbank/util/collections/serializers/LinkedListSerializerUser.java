package com.revature.Rossbank.util.collections.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.revature.Rossbank.models.BankUser;
import com.revature.Rossbank.util.collections.LinkedList;

import java.io.IOException;

public class LinkedListSerializerUser extends StdSerializer<LinkedList> {

    public LinkedListSerializerUser() {
        this(null);
    }

    public LinkedListSerializerUser(Class<LinkedList> t) {
        super(t);
    }

    @Override
    public void serialize(LinkedList linkedList, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        for (int i = 0; i < linkedList.size(); i++) {
            BankUser bankUser = (BankUser) linkedList.get(i);
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
