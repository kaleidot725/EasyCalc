package jp.kaleidot725.easycalc

object MainAdResource {
    val FINISH_UNIT_ID get() = if (BuildConfig.DEBUG_MODE) {
        "ca-app-pub-3940256099942544/1033173712"
    } else {
        "ca-app-pub-6306836711962723/5152177100"
    }

    val LIST_UNIT_ID get() = if (BuildConfig.DEBUG_MODE) {
        "ca-app-pub-3940256099942544/6300978111"
    } else {
        "ca-app-pub-6306836711962723/7596097332"
    }
}
