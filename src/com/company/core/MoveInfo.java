package com.company.core;

public record MoveInfo(
        Position position,
        boolean isCapture,
        boolean isLegal
){}

