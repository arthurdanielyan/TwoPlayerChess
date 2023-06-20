package com.company.core;

import com.company.figures.Figure;

public record Move(
        Position from,
        Position to,
        Figure movedFigure,
        Figure capturedFigure
) {
}
