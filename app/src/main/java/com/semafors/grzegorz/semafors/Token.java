package com.semafors.grzegorz.semafors;

import java.util.UUID;

/**
 * Created by grzegorz on 06.11.17.
 */

public class Token {
    private UUID value;
    private Long creationTime;

    public UUID getValue() {
        return value;
    }

    public void setValue(UUID value) {
        this.value = value;
    }

    public Long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime;
    }
}
