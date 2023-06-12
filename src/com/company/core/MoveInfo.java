package com.company.core;

public record MoveInfo(
        Position newPosition,
        boolean isCapture,
        boolean isLegal
){}

