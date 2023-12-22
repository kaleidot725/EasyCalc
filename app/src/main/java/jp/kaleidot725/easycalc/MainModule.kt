package jp.kaleidot725.easycalc

import jp.kaleidot725.easycalc.compose.ComposeAppViewModel
import jp.kaleidot725.easycalc.core.domain.model.question.QAList
import jp.kaleidot725.easycalc.core.domain.model.question.selector.QuestionSelector
import jp.kaleidot725.easycalc.core.domain.model.question.selector.QuestionSelectorImpl
import jp.kaleidot725.easycalc.core.domain.model.text.MathText
import jp.kaleidot725.easycalc.core.domain.model.text.MathTextId
import jp.kaleidot725.easycalc.core.domain.repository.LanguageRepository
import jp.kaleidot725.easycalc.core.domain.repository.SettingRepository
import jp.kaleidot725.easycalc.core.domain.repository.StatsRepository
import jp.kaleidot725.easycalc.core.domain.repository.TextRepository
import jp.kaleidot725.easycalc.core.domain.repository.ThemeRepository
import jp.kaleidot725.easycalc.core.repository.LanguageRepositoryImpl
import jp.kaleidot725.easycalc.core.repository.SettingRepositoryImpl
import jp.kaleidot725.easycalc.core.repository.StatsRepositoryImpl
import jp.kaleidot725.easycalc.core.repository.TextRepositoryImpl
import jp.kaleidot725.easycalc.core.repository.ThemeRepositoryImpl
import jp.kaleidot725.easycalc.feature.category.CategoryViewModel
import jp.kaleidot725.easycalc.feature.history.HistoryViewModel
import jp.kaleidot725.easycalc.feature.home.HomeViewModel
import jp.kaleidot725.easycalc.feature.mylist.MyListViewModel
import jp.kaleidot725.easycalc.feature.result.ResultViewModel
import jp.kaleidot725.easycalc.feature.setting.language.LanguageViewModel
import jp.kaleidot725.easycalc.feature.setting.theme.ThemeViewModel
import jp.kaleidot725.easycalc.feature.start.StartViewModel
import jp.kaleidot725.easycalc.feature.stats.StatsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single<ThemeRepository> {
        ThemeRepositoryImpl(androidContext())
    }
    single<LanguageRepository> {
        LanguageRepositoryImpl(androidContext())
    }
    single<SettingRepository> {
        SettingRepositoryImpl(androidContext())
    }
    single<TextRepository> {
        TextRepositoryImpl(androidContext())
    }
    single<QuestionSelector> {
        QuestionSelectorImpl()
    }
    single<StatsRepository> {
        StatsRepositoryImpl(
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
        jp.kaleidot725.easycalc.feature.quiz.QuizViewModel(get())
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
