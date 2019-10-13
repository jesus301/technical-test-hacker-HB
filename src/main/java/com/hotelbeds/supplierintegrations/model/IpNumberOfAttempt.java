package com.hotelbeds.supplierintegrations.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder(builderClassName = "Builder")
@JsonDeserialize(builder = IpNumberOfAttempt.Builder.class)
public class IpNumberOfAttempt implements Serializable {

    private static final long serialVersionUID = 535834258690799451L;
    private String ipErrorLogin;
    private LocalDateTime firstAttemptTimestamp;
    private Integer numberOfAttempt;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class Builder {
    }
}
