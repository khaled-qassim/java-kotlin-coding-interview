    package model

    import enums.Move

    interface Player {
        fun makeMove(): Move
    }