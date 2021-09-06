package com.submit.toyproject.rms_backend_springboot.domain.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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

    private LocalDate submittedAt;

    private LocalDate acceptedAt;

    public void updateSubmit(Boolean isSubmitted) {
        this.isSubmitted = isSubmitted;
        this.acceptedAt = LocalDate.now();
    }

}
