package com.submit.toyproject.rms_backend_springboot.domain.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Status {

    @Builder.Default
    @NotNull
    private Boolean isAccepted = false;

    @Builder.Default
    @NotNull
    private Boolean isSubmitted = false;

    public void updateAccepted(Boolean isAccepted) {
        this.isAccepted = isAccepted;
    }

    public void updateSubmit(Boolean isSubmitted) {
        this.isSubmitted = isSubmitted;
    }

}
