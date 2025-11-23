package com.salus.agenda.exceptions;

public class ScheduleConflictException extends RuntimeException {
    public ScheduleConflictException(String mensagem) {
        super(mensagem);
    }
}
