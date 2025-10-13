package com.salus.agenda.Exceptions;

public class ScheduleConflictException extends RuntimeException {
    public ScheduleConflictException(String mensagem) {
        super(mensagem);
    }
}
