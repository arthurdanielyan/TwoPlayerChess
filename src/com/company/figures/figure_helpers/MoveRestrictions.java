package com.company.figures.figure_helpers;

public enum MoveRestrictions {
    FREE,
    RTL_VER, // right-to-left diagonal and vertical
    LTR_HOR, // left-to-right diagonal and horizontal

    // for pawn
    RIGHT_TAKE,
    LEFT_TAKE,
    GO
}
