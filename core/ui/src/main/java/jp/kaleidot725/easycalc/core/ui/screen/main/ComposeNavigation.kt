package jp.kaleidot725.easycalc.core.ui.screen.main

import androidx.navigation.NavBackStackEntry
import jp.kaleidot725.easycalc.core.domain.model.question.QAList
import jp.kaleidot725.easycalc.core.domain.model.text.MathText
import jp.kaleidot725.easycalc.core.domain.model.text.MathTextId
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

sealed interface ComposeNavigation {
    val path: String
    val route: String

    object Home : ComposeNavigation {
        override val path: String = "home"
        override val route: String = path
    }

    object Stats : ComposeNavigation {
        override val path: String = "stats"
        override val route: String = path
    }

    object Quiz : ComposeNavigation {
        override val path: String = "quiz"
        override val route: String = path
    }

    object History : ComposeNavigation {
        override val path: String = "home/history"
        override val route: String = path
    }

    data class Category(val category: MathText.Category = MathText.Category.ADDITION) :
        ComposeNavigation {
        override val path: String = "home/category/{$CATEGORY_ARGUMENT_KEY}"
        override val route: String = "home/category/${category.name}"

        companion object {
            private const val CATEGORY_ARGUMENT_KEY = "category_category"
            fun NavBackStackEntry.getCategory(): MathText.Category {
                val name = this.arguments?.getString(CATEGORY_ARGUMENT_KEY)
                val category = MathText.Category.values().firstOrNull { it.name == name }
                return category ?: MathText.Category.ADDITION
            }

            fun String.isCategoryRoute(): Boolean {
                return this.startsWith("home/category")
            }
        }
    }

    object MyList : ComposeNavigation {
        override val path: String = "home/mylist"
        override val route: String = path
    }

    data class Start(val text: MathText? = null) : ComposeNavigation {
        override val path: String = "home/start/{$TEXT_ID_ARGUMENT_KEY}"
        override val route: String = "home/start/${text?.id?.value}"

        companion object {
            private const val TEXT_ID_ARGUMENT_KEY = "start_text"

            fun getTextId(navBackStackEntry: NavBackStackEntry?): MathTextId {
                val id = navBackStackEntry?.arguments?.getString(TEXT_ID_ARGUMENT_KEY) ?: ""
                return MathTextId(id)
            }

            fun String.isStartRoute(): Boolean {
                return this.startsWith("home/start")
            }
        }
    }

    data class Progress(val text: MathText? = null) : ComposeNavigation {
        override val path: String = "home/progress/{$TEXT_ID_ARGUMENT_KEY}"
        override val route: String = "home/progress/${text?.id?.value}"

        companion object {
            private const val TEXT_ID_ARGUMENT_KEY = "progress_text"
            fun getTextId(navBackStackEntry: NavBackStackEntry?): MathTextId {
                val id = navBackStackEntry?.arguments?.getString(TEXT_ID_ARGUMENT_KEY) ?: ""
                return MathTextId(id)
            }

            fun String.isProgressRoute(): Boolean {
                return this.startsWith("home/progress")
            }
        }
    }

    data class Result(
        private val text: MathText? = null,
        private val qalist: QAList? = null
    ) : ComposeNavigation {
        private val value get() = ResultValue(text?.id?.value, qalist)
        override val path: String = "home/result/{$VALUE_ARGUMENT_KEY}"
        override val route: String = "home/result/${Json.encodeToString(value)}"

        companion object {
            private const val VALUE_ARGUMENT_KEY = "result_value"
            fun getTextId(navBackStackEntry: NavBackStackEntry?): MathTextId {
                val json = navBackStackEntry?.arguments?.getString(VALUE_ARGUMENT_KEY) ?: "{}"
                val value = Json.decodeFromString<ResultValue>(json)
                return MathTextId(value.textId ?: "")
            }

            fun getQAList(navBackStackEntry: NavBackStackEntry?): QAList {
                val json = navBackStackEntry?.arguments?.getString(VALUE_ARGUMENT_KEY) ?: "{}"
                val value = Json.decodeFromString<ResultValue>(json)
                return value.qalist ?: QAList()
            }

            fun clear(navBackStackEntry: NavBackStackEntry?) {
                navBackStackEntry?.arguments?.remove(VALUE_ARGUMENT_KEY)
            }

            fun String.isResultRoute(): Boolean {
                return this.startsWith("home/result")
            }
        }

        @Serializable
        private data class ResultValue(
            val textId: String? = null,
            val qalist: QAList? = null
        )
    }

    object Interrupt : ComposeNavigation {
        override val path: String = "interrupt"
        override val route: String = path
    }

    object Setting : ComposeNavigation {
        override val path: String = "setting"
        override val route: String = path
    }

    object License : ComposeNavigation {
        override val path: String = "setting/license"
        override val route: String = path
    }

    object PrivacyPolicy : ComposeNavigation {
        override val path: String = "setting/privacy"
        override val route: String = path
    }

    object Theme : ComposeNavigation {
        override val path: String = "setting/theme"
        override val route: String = path
    }

    object Language : ComposeNavigation {
        override val path: String = "setting/language"
        override val route: String = path
    }
}
