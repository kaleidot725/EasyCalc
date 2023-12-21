package jp.kaleidot725.easycalc

import jp.kaleidot725.easycalc.compose.ComposeAppViewModel
import jp.kaleidot725.easycalc.core.domain.model.question.selector.QuestionSelector
import jp.kaleidot725.easycalc.core.domain.model.question.selector.QuestionSelectorImpl
import jp.kaleidot725.easycalc.core.domain.model.text.MathText
import jp.kaleidot725.easycalc.core.domain.model.text.MathTextId
import jp.kaleidot725.easycalc.core.domain.repository.LanguageRepository
import jp.kaleidot725.easycalc.core.domain.repository.SettingRepository
import jp.kaleidot725.easycalc.core.domain.repository.StatsRepository
import jp.kaleidot725.easycalc.core.domain.repository.TextRepository
import jp.kaleidot725.easycalc.core.domain.repository.ThemeRepository
import jp.kaleidot725.easycalc.core.ui.screen.history.HistoryViewModel
import jp.kaleidot725.easycalc.core.ui.screen.home.HomeViewModel
import jp.kaleidot725.easycalc.core.ui.screen.mylist.MyListViewModel
import jp.kaleidot725.easycalc.core.ui.screen.progress.ProgressViewModel
import jp.kaleidot725.easycalc.core.ui.screen.quiz.QuizViewModel
import jp.kaleidot725.easycalc.core.ui.screen.result.ResultViewModel
import jp.kaleidot725.easycalc.core.ui.screen.setting.language.LanguageViewModel
import jp.kaleidot725.easycalc.core.ui.screen.setting.theme.ThemeViewModel
import jp.kaleidot725.easycalc.core.ui.screen.start.StartViewModel
import jp.kaleidot725.easycalc.core.ui.screen.stats.StatsViewModel
import jp.kaleidot725.easycalc.feature.category.CategoryViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single<ThemeRepository> {
        jp.kaleidot725.easycalc.core.repository.ThemeRepositoryImpl(androidContext())
    }
    single<LanguageRepository> {
        jp.kaleidot725.easycalc.core.repository.LanguageRepositoryImpl(androidContext())
    }
    single<SettingRepository> {
        jp.kaleidot725.easycalc.core.repository.SettingRepositoryImpl(androidContext())
    }
    single<TextRepository> {
        jp.kaleidot725.easycalc.core.repository.TextRepositoryImpl(androidContext())
    }
    single<QuestionSelector> {
        QuestionSelectorImpl()
    }
    single<StatsRepository> {
        jp.kaleidot725.easycalc.core.repository.StatsRepositoryImpl(
            get()
        )
    }
}

val appModule = module {
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
        ProgressViewModel(get(), id, get(), get())
    }
    viewModel { (id: MathTextId, qalist: jp.kaleidot725.easycalc.core.domain.model.question.QAList) ->
        ResultViewModel(id, qalist, get(), get())
    }
}
