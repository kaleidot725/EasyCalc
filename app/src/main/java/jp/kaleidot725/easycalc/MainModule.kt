package jp.kaleidot725.easycalc

import jp.kaleidot725.easycalc.compose.app.ComposeAppViewModel
import jp.kaleidot725.easycalc.core.domain.model.question.QAList
import jp.kaleidot725.easycalc.core.domain.model.text.MathText
import jp.kaleidot725.easycalc.core.domain.model.text.MathTextId
import jp.kaleidot725.easycalc.feature.category.CategoryViewModel
import jp.kaleidot725.easycalc.feature.history.HistoryViewModel
import jp.kaleidot725.easycalc.feature.home.HomeViewModel
import jp.kaleidot725.easycalc.feature.mylist.MyListViewModel
import jp.kaleidot725.easycalc.feature.quiz.QuizViewModel
import jp.kaleidot725.easycalc.feature.result.ResultViewModel
import jp.kaleidot725.easycalc.feature.setting.language.LanguageViewModel
import jp.kaleidot725.easycalc.feature.setting.theme.ThemeViewModel
import jp.kaleidot725.easycalc.feature.start.StartViewModel
import jp.kaleidot725.easycalc.feature.stats.StatsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {
    viewModel {
        HomeViewModel(get(), get())
    }
    viewModel {
        StatsViewModel(get())
    }
    viewModel {
        ThemeViewModel(get())
    }
    viewModel {
        LanguageViewModel(get())
    }
    viewModel {
        QuizViewModel(get())
    }
    viewModel { (category: MathText.Category) ->
        CategoryViewModel(category, get())
    }
    viewModel {
        MyListViewModel(get())
    }
    viewModel {
        HistoryViewModel(get())
    }
    viewModel { (id: MathTextId) ->
        StartViewModel(id, get())
    }
    viewModel {
        ComposeAppViewModel(get(), get())
    }
    viewModel { (id: MathTextId) ->
        jp.kaleidot725.easycalc.feature.progress.ProgressViewModel(get(), id, get(), get())
    }
    viewModel { (id: MathTextId, qalist: QAList) ->
        ResultViewModel(id, qalist, get(), get())
    }
}
