package com.jobsity.challenge.exception;

import lombok.AllArgsConstructor;

/**
 * Bowling exception.
 */
@AllArgsConstructor
public class BowlingException extends Exception {
    private String description;

}
