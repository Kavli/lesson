package com.apebug.entity;

public class EleLine {
    private Long fromEleId;
    private Long toEleId;

    public EleLine(Long fromEleId, Long toEleId) {
        this.fromEleId = fromEleId;
        this.toEleId = toEleId;
    }

    public Long getFromEleId() {
        return fromEleId;
    }

    public void setFromEleId(Long fromEleId) {
        this.fromEleId = fromEleId;
    }

    public Long getToEleId() {
        return toEleId;
    }

    public void setToEleId(Long toEleId) {
        this.toEleId = toEleId;
    }
}
