package com.salus.agenda.Models;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Embeddable
public class Hours {

    private Set<LocalTime> hours = new LinkedHashSet<>();

    public Hours(Set<LocalTime> hours) {
        this.hours = hours;
    }
    public Hours(){

    }

    public Set<LocalTime> getHours() {
        return hours;
    }

    public void setHours(Set<LocalTime> hours) {
        this.hours = hours;
    }
}
