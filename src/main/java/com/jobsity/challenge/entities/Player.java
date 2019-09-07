package com.jobsity.challenge.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Class that holds a Player information.
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Player {
    /**
     * Payer name.
     */
    private String name;

}
