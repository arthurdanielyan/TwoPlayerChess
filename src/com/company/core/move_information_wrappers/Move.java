package com.company.core.move_information_wrappers;

import com.company.core.Position;
import com.company.figures.Figure;

public record Move(
        Position from,
        Position to,
        Figure movedFigure,
        Figure capturedFigure,
        Castle castle,
        Figure promotedTo
) {
}