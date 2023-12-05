package jp.kaleidot725.easycalc

import jp.kaleidot725.easycalc.domain.model.question.QAList
import jp.kaleidot725.easycalc.domain.model.question.selector.QuestionSelector
import jp.kaleidot725.easycalc.domain.model.question.selector.QuestionSelectorImpl
import jp.kaleidot725.easycalc.domain.model.text.MathText
import jp.kaleidot725.easycalc.domain.model.text.MathTextId
import jp.kaleidot725.easycalc.domain.repository.LanguageRepository
import jp.kaleidot725.easycalc.domain.repository.SettingRepository
import jp.kaleidot725.easycalc.domain.repository.StatsRepository
import jp.kaleidot725.easycalc.domain.repository.TextRepository
import jp.kaleidot725.easycalc.domain.repository.ThemeRepository
import jp.kaleidot725.easycalc.repository.LanguageRepositoryImpl
import jp.kaleidot725.easycalc.repository.SettingRepositoryImpl
import jp.kaleidot725.easycalc.repository.StatsRepositoryImpl
import jp.kaleidot725.easycalc.repository.TextRepositoryImpl
import jp.kaleidot725.easycalc.repository.ThemeRepositoryImpl
import jp.kaleidot725.easycalc.ui.screen.category.CategoryViewModel
import jp.kaleidot725.easycalc.ui.screen.history.HistoryViewModel
import jp.kaleidot725.easycalc.ui.screen.home.HomeViewModel
import jp.kaleidot725.easycalc.ui.screen.main.ComposeAppViewModel
import jp.kaleidot725.easycalc.ui.screen.mylist.MyListViewModel
import jp.kaleidot725.easycalc.ui.screen.progress.ProgressViewModel
import jp.kaleidot725.easycalc.ui.screen.quiz.QuizViewModel
import jp.kaleidot725.easycalc.ui.screen.result.ResultViewModel
import jp.kaleidot725.easycalc.ui.screen.setting.language.LanguageViewModel
import jp.kaleidot725.easycalc.ui.screen.setting.theme.ThemeViewModel
import jp.kaleidot725.easycalc.ui.screen.start.StartViewModel
import jp.kaleidot725.easycalc.ui.screen.stats.StatsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val finishUnitId
    get() = if (BuildConfig.DEBUG_MODE) {
        "ca-app-pub-3940256099942544/1033173712"
    } else {
        "ca-app-pub-6306836711962723/5152177100"
    }

private val listUnitId
    get() = if (BuildConfig.DEBUG_MODE) {
        "ca-app-pub-3940256099942544/6300978111"
    } else {
        "ca-app-pub-6306836711962723/7596097332"
    }

private val mylistUnitId
    get() = if (BuildConfig.DEBUG_MODE) {
        "ca-app-pub-3940256099942544/6300978111"
    } else {
        "ca-app-pub-6306836711962723/2394972000"
    }

private val homeUnitId
    get() = if (BuildConfig.DEBUG_MODE) {
        "ca-app-pub-3940256099942544/6300978111"
    } else {
        "ca-app-pub-6306836711962723/3151785365"
    }

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
        ComposeAppViewModel(finishUnitId, get(), get())
    }
    viewModel { (id: MathTextId) ->
        ProgressViewModel(get(), id, get(), get())
    }
    viewModel { (id: MathTextId, qalist: QAList) ->
        ResultViewModel(id, qalist, get(), get())
    }
}
