package jp.kaleidot725.easycalc.domain.model.language

import java.util.Locale

enum class Language(val lang: String, val locale: Locale) {
    English("en", Locale.ENGLISH),
    Japanese("ja", Locale.JAPANESE),
    German("de", Locale.GERMAN),
    Spain("es", Locale("ast")),
    France("fr", Locale.FRANCE),
    Indonesia("id", Locale("in")),
    Italy("it", Locale.ITALY),
    Portugal("pt", Locale("pt")),
    Thai("th", Locale("th")),
    Chinese("zh", Locale.CHINESE)
}
