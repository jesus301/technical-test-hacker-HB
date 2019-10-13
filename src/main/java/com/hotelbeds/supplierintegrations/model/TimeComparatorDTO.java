package com.hotelbeds.supplierintegrations.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Builder(builderClassName = "Builder")
@JsonDeserialize(builder = TimeComparatorDTO.Builder.class)
public class TimeComparatorDTO implements Serializable {

    private static final long serialVersionUID = 8512646235803918971L;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "EEE, dd MMM yyyy hh:MM:ss Z")
    @JsonDeserialize(using = DateDeserializers.TimestampDeserializer.class)
    private Timestamp time1;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "EEE, dd MMM yyyy hh:MM:ss Z")
    @JsonDeserialize(using = DateDeserializers.TimestampDeserializer.class)
    private Timestamp time2;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class Builder {
    }
}
